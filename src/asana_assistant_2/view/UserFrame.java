package asana_assistant_2.view;

import asana_assistant_1.control.ControlException;
import asana_assistant_1.control.IRouter;
import asana_assistant_1.control.JSONTaskParser;
import asana_assistant_1.control.dtos.DevelopmentFilter;
import asana_assistant_1.control.dtos.DisplayString;
import asana_assistant_1.control.dtos.TaskFilter;
import asana_assistant_1.model.Project;
import asana_assistant_1.model.Task;
import asana_assistant_1.model.User;
import asana_assistant_1.parse.ParseException;
import asana_assistant_1.report.ReportException;
import asana_assistant_1.report.printers.PDFReportPrinter;
import asana_assistant_1.view.DefaultView;
import asana_assistant_1.view.TaskTreeCellRenderer;
import asana_assistant_1.view.View;
import asana_assistant_2.control.CSVTaskParser;
import asana_assistant_2.control.ProjectPDFReportPrinter;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class UserFrame extends javax.swing.JFrame {
    private static final String JSON_DESCRIPTION = "JSON (.json)";
    private static final String CSV_DESCRIPTION = "CSV (.csv)";
    private static final String PDF_DESCRIPTION = "PDF (.pdf)";
    private static final long DATE_MIN = -62135734039898l;
    
    private LoginFrame parent;
    private IRouter router;
    private View source;
    private User user;
    private Project project;
    private Task task;
    
    private final DefaultListModel activeCollaboratorsListModel;
    private final DefaultListModel bannedCollaboratorsListModel;
    private final DefaultTreeModel tasksTreeModel;
    
    private final DefaultListModel developmentsListModel;
    private final DefaultListModel evidenceListModel;
    
    public UserFrame(View source, LoginFrame parent, User user) {
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setIconImage(parent.getIconImage());
        this.setTitle(user.getName() + " | " + user.getEmail());
        this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
        this.parent = parent;
        this.source = source;
        this.router = source.getRouter();
        this.user = user;
        
        this.activeCollaboratorsListModel = new DefaultListModel();
        this.ActiveList.setModel(activeCollaboratorsListModel);
        
        this.bannedCollaboratorsListModel = new DefaultListModel();
        this.BannedList.setModel(bannedCollaboratorsListModel);
        
        this.developmentsListModel = new DefaultListModel();
        this.DevelopmentsList.setModel(developmentsListModel);
        
        this.evidenceListModel = new DefaultListModel();
        this.EvidenceList.setModel(evidenceListModel);
        
        this.tasksTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("root"));
        this.TasksTree.setRootVisible(false);
        this.TasksTree.setCellRenderer(new TaskTreeCellRenderer(router));
        this.TasksTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.TasksTree.setModel(tasksTreeModel);
        this.TasksTree.setShowsRootHandles(true);
        
        this.FromDateChooser.getDateEditor().setEnabled(false);
        this.ToDateChooser.getDateEditor().setEnabled(false);
        
        //called to disable all unwanted interface options
        closeProject();
    }
    
    private void closeProject(){
        this.project = null;
        this.ProjectsIcon.setText("Open");
        this.ReportIcon.setVisible(false);
        this.SincronizeIcon.setVisible(false);
        this.CollaboratorsIcon.setVisible(false);
        this.CollaboratorsTabbedPane.setVisible(false);
        this.NombreFiltrosPanel.setVisible(false);
        this.TasksPanel.setVisible(false);
        this.InfoCalendarPanel.setVisible(false);
    }
    
    public void openProject(long id){
        try {
            Project project = router.getProject(id);
            this.NombreProyectoLabel.setText(project.getName());
            
            //populating collaborators
            reloadCollaboratorLists(project);
            
            //populating filters
            reloadFilters(project);
            resetFilters();
            
            //poulating tasks tree
            reloadTasks(project);
            
            //enabling everything
            this.ProjectsIcon.setText("Close");
            this.ReportIcon.setVisible(true);
            this.SincronizeIcon.setVisible(true);
            this.CollaboratorsIcon.setVisible(true);
            this.CollaboratorsTabbedPane.setVisible(true);
            this.NombreFiltrosPanel.setVisible(true);
            this.TasksPanel.setVisible(true);
            this.project = project;
        } catch (ControlException ex) {
            View.displayError(this, ex);
        }
    }
    
    private void resetFilters(){
        this.FromDateChooser.setDate(null);
        this.ToDateChooser.setDate(null);
        this.CollaboratorComboBox.setSelectedItem(null);
        this.TaskComboBox.setSelectedItem(null);
    }
    
    private void reloadFilters(Project project){
        try{
            List<DisplayString> tasks = router.getTaskStrings(project.getId());
            TaskComboBox.removeAllItems();
            for(DisplayString task : tasks)
                TaskComboBox.addItem(task);
            
            List<DisplayString> activeCollaborators = router.getActiveUserStrings(project.getId());
            List<DisplayString> bannedCollaborators = router.getBannedUserStrings(project.getId());
            CollaboratorComboBox.removeAllItems();
            for(DisplayString collaborator : activeCollaborators)
                CollaboratorComboBox.addItem(collaborator);
            for(DisplayString collaborator : bannedCollaborators)
                CollaboratorComboBox.addItem(collaborator);
            resetFilters();
        } catch(ControlException ex){
            DefaultView.displayError(this, ex);
        }
    }
    
    private void reloadCollaboratorLists(Project project){
        try{
            List<DisplayString> activeCollaborators = router.getActiveUserStrings(project.getId());
            List<DisplayString> bannedCollaborators = router.getBannedUserStrings(project.getId());
            
            this.activeCollaboratorsListModel.clear();
            for(DisplayString collaborator : activeCollaborators)
                activeCollaboratorsListModel.addElement(collaborator);  
            
            this.bannedCollaboratorsListModel.clear();
            for(DisplayString collaborator : bannedCollaborators)
                bannedCollaboratorsListModel.addElement(collaborator);
        } catch(ControlException ex){
            DefaultView.displayError(this, ex);
        }
    }
    
    private DefaultMutableTreeNode getTaskNode(DisplayString task, TaskFilter filter) throws ControlException{
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(task);
        for(DisplayString subtask : router.getSubtaskStrings(task.getId(), filter))
            node.add(getTaskNode(subtask, filter));
        return node;
    }
    
    private void reloadTasks(Project project){
        try{
            if(project != null){
                TaskFilter filter = getTaskFilter();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode)tasksTreeModel.getRoot();
                root.removeAllChildren();
                List<DisplayString> tasks = router.getTaskStrings(project.getId(), filter);
                for(DisplayString task : tasks)
                    root.add(getTaskNode(task, filter));
                tasksTreeModel.reload();
                for(int i = 0; i < TasksTree.getRowCount(); i++)
                    TasksTree.expandRow(i);
            }
        } catch(ControlException ex){
            View.displayError(this, ex);
        }
    }
    
    public void reloadDevelopments(Task task){
        try{
            developmentsListModel.clear();
            if(task != null){
                DevelopmentFilter filter = getDevelopmentFilter();
                List<DisplayString> developments = router.getDevelopmentStrings(task.getId(), filter);
                for(DisplayString development : developments)
                    developmentsListModel.addElement(development);
            }
        } catch(ControlException ex){
            View.displayError(this, ex);
        }
    }

    private void banSelectedCollaborator(){
        DisplayString collaborator = (DisplayString)ActiveList.getSelectedValue();
        if(collaborator == null)
            DefaultView.displayError(this, "You must select an active collaborator to ban.");
        else{
            try {
                router.banUser(project.getId(), collaborator.getId());
                reloadCollaboratorLists(project);
            } catch (ControlException ex) {
                View.displayError(this, ex);
            }
        }
    }
    
    private void unbanSelectedCollaborator(){
        DisplayString collaborator = (DisplayString)BannedList.getSelectedValue();
        if(collaborator == null)
            DefaultView.displayError(this, "You must select a banned collaborator to un-ban.");
        else{
            try {
                router.unbanUser(project.getId(), collaborator.getId());
                reloadCollaboratorLists(project);
            } catch (ControlException ex) {
                DefaultView.displayError(this, ex);
            }
        }
    }
    
    private void openSelectedTask(){
        try{
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)TasksTree.getLastSelectedPathComponent();
            if (node == null){
                this.InfoCalendarPanel.setVisible(false);
                this.task = null;
            }
            else{
                DisplayString taskString = (DisplayString)node.getUserObject();
                task = router.getTask(taskString.getId());
                DisplayString asignee = router.getAsigneeString(task.getId());
                
                NombreTareaLabel.setText(task.getName());
                
                if(asignee == null)
                    AsignadoLabel.setText("Unassigned");
                else
                    AsignadoLabel.setText(asignee.toString());
                
                reloadDevelopments(task);
                
                this.InfoCalendarPanel.setVisible(true);
            }
        } catch(ControlException ex){
            View.displayError(this, ex);
        }
    }
    
    private void synchronizeTasks(){
        if(DefaultView.displayConfirm(this, "Synchronizing tasks is permanent.\nDo you wish to continue?")){
            JFileChooser chooser  = new JFileChooser();
            chooser.setDialogTitle("Select Tasks File");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.addChoosableFileFilter(new FileNameExtensionFilter(JSON_DESCRIPTION, "json"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter(CSV_DESCRIPTION, "csv"));
            chooser.setAcceptAllFileFilterUsed(false);
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                try{
                    String file = chooser.getSelectedFile().getAbsolutePath();
                    if(chooser.getFileFilter().getDescription().equals(JSON_DESCRIPTION))
                        router.synchronize(project.getId(), file, new JSONTaskParser());
                    else if(chooser.getFileFilter().getDescription().equals(CSV_DESCRIPTION))
                        router.synchronize(project.getId(), file, new CSVTaskParser());
                    reloadFilters(project);
                    reloadTasks(project);
                    reloadCollaboratorLists(project);
                    DefaultView.displayInfo(this, "Tasks synchronized successfullly.");
                } catch(ControlException ex){
                    View.displayError(this, ex);
                } catch(ParseException ex){
                    View.displayError(this, ex);
                }
            }
        }
    }
    
    private DevelopmentFilter getDevelopmentFilter(){
        LocalDate start = null;
        LocalDate end = null;
        Date start_ = FromDateChooser.getDate();
        if(start_ != null)
            start = start_.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date end_ = ToDateChooser.getDate();
        if(end_ != null)
            end = end_.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new DevelopmentFilter(start, end);
    }
    
    private TaskFilter getTaskFilter(){
        Long userId = null;
        Long taskId = null;
        DisplayString user = (DisplayString)CollaboratorComboBox.getSelectedItem();
        DisplayString task = (DisplayString)TaskComboBox.getSelectedItem();
        if(task != null)
            taskId = task.getId();
        if(user != null)
            userId = user.getId();
        return new TaskFilter(taskId, userId);
    }
    
    private void printReport(){
        JFileChooser chooser  = new JFileChooser();
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(PDF_DESCRIPTION, "pdf"));
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try{
                String path = chooser.getSelectedFile().getAbsolutePath();
                DevelopmentFilter developmentFilter = getDevelopmentFilter();
                TaskFilter taskFilter = getTaskFilter();
                if(chooser.getFileFilter().getDescription().equals(PDF_DESCRIPTION)){
                   String filepath = path + "\\" + LocalDate.now() + " Report.pdf";
                   router.printReport(project.getId(), filepath, new ProjectPDFReportPrinter(), taskFilter, developmentFilter);
                }
            } catch(ControlException ex){
                View.displayError(this, ex);
            } catch(ReportException ex){
                View.displayError(this, ex);
            }
        }
    }
    
    public void displayEvidence(javax.swing.event.ListSelectionEvent evt){
        if(!evt.getValueIsAdjusting()){
            evidenceListModel.removeAllElements();
            DisplayString development = (DisplayString)DevelopmentsList.getSelectedValue();
            if(development != null){
                try {
                    for(DisplayString evidence : router.getEvidenceStrings(development.getId()))
                        evidenceListModel.addElement(evidence);
                } catch (ControlException ex) {
                    DefaultView.displayError(this, ex);
                }
            }
        }
    }
    
    private void filterDevelopments(java.beans.PropertyChangeEvent evt){
        if(evt.getPropertyName().equals("date") && task != null){
            Date start = FromDateChooser.getDate();
            Date end = ToDateChooser.getDate();
            Date current = calendar.getDate();
            calendar.setSelectableDateRange(new Date(DATE_MIN), new Date(Long.MAX_VALUE));
            if(start == null && end == null)
                calendar.setDate(current);
            else if(start == null && end != null){
                calendar.setMaxSelectableDate(end);
                if(current.after(end))
                    calendar.setDate(end);
                else
                    calendar.setDate(current);
            }
            else if(start != null && end == null){
                calendar.setMinSelectableDate(start);
                if(current.before(start))
                    calendar.setDate(start);
                else
                    calendar.setDate(start);
            }
            else if(start.before(end) || start.compareTo(end) == 0){
                calendar.setSelectableDateRange(start, end);
                if(current.before(start))
                    calendar.setDate(start);
                else if(current.after(end))
                    calendar.setDate(end);
                else
                    calendar.setDate(start);
            }
            else{
                FromDateChooser.setDate(null);
                ToDateChooser.setDate(null);
                DefaultView.displayError(this, "The dates picked must be in chronological order.");
            }
            reloadDevelopments(task);
        }
    }
    
    public void filterTasks(java.awt.event.ItemEvent evt){
        if(evt.getStateChange() == ItemEvent.SELECTED && project != null){
            reloadTasks(project);
        }
    }
    
    private void downloadEvidence(){
        DisplayString evidence = (DisplayString)EvidenceList.getSelectedValue();
        if(evidence == null)
            View.displayError(this, "You must select an evidence to download from the evidence list.");
        else{
            JFileChooser chooser  = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                try{
                    String directory = chooser.getSelectedFile().getAbsolutePath();
                    router.downloadEvidence(evidence.getId(), directory);
                } catch(ControlException ex){
                    DefaultView.displayError(this, ex);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        activeCollaboratorsPopupMenu = new javax.swing.JPopupMenu();
        banCollaboratorMenuItem = new javax.swing.JMenuItem();
        bannedCollaboratorsPopupMenu = new javax.swing.JPopupMenu();
        unbanCollaboratorMenuItem = new javax.swing.JMenuItem();
        developmentsPopupMenu = new javax.swing.JPopupMenu();
        addDevelopmentMenuItem = new javax.swing.JMenuItem();
        filtersPopupMenu = new javax.swing.JPopupMenu();
        resetFiltersMenuItem = new javax.swing.JMenuItem();
        evidencePopupMenu = new javax.swing.JPopupMenu();
        downloadEvidenceMenuItem = new javax.swing.JMenuItem();
        OptionsPanel = new javax.swing.JPanel();
        CollaboratorsIcon = new javax.swing.JLabel();
        LogoLabel = new javax.swing.JLabel();
        CollaboratorsTabbedPane = new javax.swing.JTabbedPane();
        ActiveScrollPane = new javax.swing.JScrollPane();
        ActiveList = new javax.swing.JList();
        BannedScrollPane = new javax.swing.JScrollPane();
        BannedList = new javax.swing.JList();
        SincronizeIcon = new javax.swing.JLabel();
        ProjectsIcon = new javax.swing.JLabel();
        ReportIcon = new javax.swing.JLabel();
        NombreFiltrosPanel = new javax.swing.JPanel();
        NombreProyectoLabel = new javax.swing.JLabel();
        ByActivityIcon = new javax.swing.JLabel();
        ByCollaboratorIcon = new javax.swing.JLabel();
        ToLabel = new javax.swing.JLabel();
        ToDateChooser = new com.toedter.calendar.JDateChooser();
        FromDateChooser = new com.toedter.calendar.JDateChooser();
        FromLabel = new javax.swing.JLabel();
        ByDateIcon = new javax.swing.JLabel();
        FiltersLabel = new javax.swing.JLabel();
        ByCollaboratorSeparator = new javax.swing.JSeparator();
        ByActivitySeparator = new javax.swing.JSeparator();
        ByDateSeparator = new javax.swing.JSeparator();
        TaskComboBox = new javax.swing.JComboBox();
        CollaboratorComboBox = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        TasksPanel = new javax.swing.JPanel();
        TreeTaskScrollPane = new javax.swing.JScrollPane();
        TasksTree = new javax.swing.JTree();
        InfoCalendarPanel = new javax.swing.JPanel();
        calendar = new com.toedter.calendar.JCalendar();
        DevelopmentsScrollpane = new javax.swing.JScrollPane();
        DevelopmentsList = new javax.swing.JList();
        EvidenceScrollPane = new javax.swing.JScrollPane();
        EvidenceList = new javax.swing.JList();
        AsignadoLabel = new javax.swing.JLabel();
        NombreTareaLabel = new javax.swing.JLabel();
        DevelopmentsLabel = new javax.swing.JLabel();
        EvidenceLabel = new javax.swing.JLabel();

        banCollaboratorMenuItem.setText("Ban Selected Collaborator");
        banCollaboratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banCollaboratorMenuItemActionPerformed(evt);
            }
        });
        activeCollaboratorsPopupMenu.add(banCollaboratorMenuItem);

        unbanCollaboratorMenuItem.setText("Un-ban Selected Collaborator");
        unbanCollaboratorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unbanCollaboratorMenuItemActionPerformed(evt);
            }
        });
        bannedCollaboratorsPopupMenu.add(unbanCollaboratorMenuItem);

        addDevelopmentMenuItem.setText("Add Development on Selected Date");
        addDevelopmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDevelopmentMenuItemActionPerformed(evt);
            }
        });
        developmentsPopupMenu.add(addDevelopmentMenuItem);

        resetFiltersMenuItem.setText("Reset Filters");
        resetFiltersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFiltersMenuItemActionPerformed(evt);
            }
        });
        filtersPopupMenu.add(resetFiltersMenuItem);

        downloadEvidenceMenuItem.setText("Download Selected Evidence");
        downloadEvidenceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadEvidenceMenuItemActionPerformed(evt);
            }
        });
        evidencePopupMenu.add(downloadEvidenceMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));

        OptionsPanel.setBackground(new java.awt.Color(29, 38, 52));

        CollaboratorsIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        CollaboratorsIcon.setForeground(new java.awt.Color(255, 255, 255));
        CollaboratorsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/collaborators_userframe.png"))); // NOI18N
        CollaboratorsIcon.setText("Collaborators");

        LogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logouserframe.png"))); // NOI18N

        CollaboratorsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        CollaboratorsTabbedPane.setForeground(new java.awt.Color(29, 38, 52));
        CollaboratorsTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CollaboratorsTabbedPane.setFont(new java.awt.Font("Proxima Nova Rg", 1, 14)); // NOI18N

        ActiveScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ActiveScrollPane.setBorder(null);
        ActiveScrollPane.setForeground(new java.awt.Color(85, 96, 115));
        ActiveScrollPane.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N

        ActiveList.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        ActiveList.setForeground(new java.awt.Color(85, 96, 115));
        ActiveList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ActiveList.setComponentPopupMenu(activeCollaboratorsPopupMenu);
        ActiveList.setSelectionBackground(new java.awt.Color(255, 102, 0));
        ActiveList.setSelectionForeground(new java.awt.Color(243, 242, 242));
        ActiveScrollPane.setViewportView(ActiveList);

        CollaboratorsTabbedPane.addTab("Active", ActiveScrollPane);

        BannedScrollPane.setBorder(null);
        BannedScrollPane.setForeground(new java.awt.Color(85, 96, 115));
        BannedScrollPane.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N

        BannedList.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        BannedList.setForeground(new java.awt.Color(85, 96, 115));
        BannedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        BannedList.setComponentPopupMenu(bannedCollaboratorsPopupMenu);
        BannedList.setSelectionBackground(new java.awt.Color(255, 102, 0));
        BannedList.setSelectionForeground(new java.awt.Color(243, 242, 242));
        BannedScrollPane.setViewportView(BannedList);

        CollaboratorsTabbedPane.addTab("Banned", BannedScrollPane);

        SincronizeIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        SincronizeIcon.setForeground(new java.awt.Color(255, 255, 255));
        SincronizeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/synchronizeuserframe.png"))); // NOI18N
        SincronizeIcon.setText("Synchronize");
        SincronizeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SincronizeIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SincronizeIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SincronizeIconMouseExited(evt);
            }
        });

        ProjectsIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        ProjectsIcon.setForeground(new java.awt.Color(255, 255, 255));
        ProjectsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icon.png"))); // NOI18N
        ProjectsIcon.setText("Close");
        ProjectsIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProjectsIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProjectsIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProjectsIconMouseExited(evt);
            }
        });

        ReportIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        ReportIcon.setForeground(new java.awt.Color(255, 255, 255));
        ReportIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report_userframe.png"))); // NOI18N
        ReportIcon.setText("Report");
        ReportIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReportIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportIconMouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionsPanelLayout = new javax.swing.GroupLayout(OptionsPanel);
        OptionsPanel.setLayout(OptionsPanelLayout);
        OptionsPanelLayout.setHorizontalGroup(
            OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionsPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OptionsPanelLayout.createSequentialGroup()
                        .addComponent(ProjectsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionsPanelLayout.createSequentialGroup()
                        .addGroup(OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReportIcon)
                            .addComponent(SincronizeIcon)
                            .addGroup(OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(CollaboratorsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CollaboratorsIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))))
        );
        OptionsPanelLayout.setVerticalGroup(
            OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProjectsIcon)
                .addGap(18, 18, 18)
                .addComponent(ReportIcon)
                .addGap(18, 18, 18)
                .addComponent(SincronizeIcon)
                .addGap(18, 18, 18)
                .addComponent(CollaboratorsIcon)
                .addGap(18, 18, 18)
                .addComponent(CollaboratorsTabbedPane)
                .addContainerGap())
        );

        NombreFiltrosPanel.setBackground(new java.awt.Color(255, 255, 255));
        NombreFiltrosPanel.setComponentPopupMenu(filtersPopupMenu);
        NombreFiltrosPanel.setPreferredSize(new java.awt.Dimension(1024, 108));

        NombreProyectoLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 36)); // NOI18N
        NombreProyectoLabel.setForeground(new java.awt.Color(29, 38, 52));
        NombreProyectoLabel.setText("Name Of Project");

        ByActivityIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        ByActivityIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bytask.png"))); // NOI18N
        ByActivityIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ByActivityIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ByActivityIconMouseExited(evt);
            }
        });

        ByCollaboratorIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        ByCollaboratorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bycollaborator.png"))); // NOI18N
        ByCollaboratorIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ByCollaboratorIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ByCollaboratorIconMouseExited(evt);
            }
        });

        ToLabel.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        ToLabel.setText("  To:");

        ToDateChooser.setInheritsPopupMenu(true);
        ToDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToDateChooserPropertyChange(evt);
            }
        });

        FromDateChooser.setInheritsPopupMenu(true);
        FromDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FromDateChooserPropertyChange(evt);
            }
        });

        FromLabel.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        FromLabel.setText("  From:");

        ByDateIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        ByDateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bydate.png"))); // NOI18N
        ByDateIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ByDateIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ByDateIconMouseExited(evt);
            }
        });

        FiltersLabel.setFont(new java.awt.Font("Proxima Nova Rg", 0, 18)); // NOI18N
        FiltersLabel.setText("Filters:");

        ByCollaboratorSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ByActivitySeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ByDateSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        ByDateSeparator.setInheritsPopupMenu(true);

        TaskComboBox.setInheritsPopupMenu(true);
        TaskComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TaskComboBoxItemStateChanged(evt);
            }
        });

        CollaboratorComboBox.setInheritsPopupMenu(true);
        CollaboratorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CollaboratorComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout NombreFiltrosPanelLayout = new javax.swing.GroupLayout(NombreFiltrosPanel);
        NombreFiltrosPanel.setLayout(NombreFiltrosPanelLayout);
        NombreFiltrosPanelLayout.setHorizontalGroup(
            NombreFiltrosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NombreFiltrosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NombreFiltrosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NombreProyectoLabel)
                    .addGroup(NombreFiltrosPanelLayout.createSequentialGroup()
                        .addComponent(FiltersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ByDateSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ByDateIcon)
                        .addGap(0, 0, 0)
                        .addComponent(FromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ToLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ToDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ByActivitySeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(ByActivityIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TaskComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ByCollaboratorSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(ByCollaboratorIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CollaboratorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        NombreFiltrosPanelLayout.setVerticalGroup(
            NombreFiltrosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NombreFiltrosPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(NombreProyectoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NombreFiltrosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ByActivityIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ByCollaboratorSeparator)
                    .addComponent(ToDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(ToLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FromDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(FromLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ByDateIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FiltersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ByActivitySeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(ByDateSeparator)
                    .addComponent(TaskComboBox)
                    .addComponent(CollaboratorComboBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ByCollaboratorIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TasksPanel.setBackground(new java.awt.Color(204, 204, 204));

        TreeTaskScrollPane.setBorder(null);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        TasksTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        TasksTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                TasksTreeValueChanged(evt);
            }
        });
        TreeTaskScrollPane.setViewportView(TasksTree);

        javax.swing.GroupLayout TasksPanelLayout = new javax.swing.GroupLayout(TasksPanel);
        TasksPanel.setLayout(TasksPanelLayout);
        TasksPanelLayout.setHorizontalGroup(
            TasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TasksPanelLayout.createSequentialGroup()
                .addComponent(TreeTaskScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        TasksPanelLayout.setVerticalGroup(
            TasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TreeTaskScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );

        InfoCalendarPanel.setBackground(new java.awt.Color(214, 217, 225));

        calendar.setForeground(new java.awt.Color(29, 38, 52));
        calendar.setDecorationBackgroundColor(new java.awt.Color(85, 96, 115));
        calendar.setSundayForeground(new java.awt.Color(255, 102, 0));
        calendar.setWeekdayForeground(new java.awt.Color(255, 255, 255));

        DevelopmentsList.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        DevelopmentsList.setForeground(new java.awt.Color(85, 96, 115));
        DevelopmentsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DevelopmentsList.setComponentPopupMenu(developmentsPopupMenu);
        DevelopmentsList.setSelectionBackground(new java.awt.Color(255, 102, 0));
        DevelopmentsList.setSelectionForeground(new java.awt.Color(243, 242, 242));
        DevelopmentsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                DevelopmentsListValueChanged(evt);
            }
        });
        DevelopmentsScrollpane.setViewportView(DevelopmentsList);

        EvidenceList.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        EvidenceList.setForeground(new java.awt.Color(85, 96, 115));
        EvidenceList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        EvidenceList.setComponentPopupMenu(evidencePopupMenu);
        EvidenceList.setSelectionBackground(new java.awt.Color(255, 102, 0));
        EvidenceList.setSelectionForeground(new java.awt.Color(243, 242, 242));
        EvidenceScrollPane.setViewportView(EvidenceList);

        AsignadoLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        AsignadoLabel.setForeground(new java.awt.Color(85, 96, 115));
        AsignadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AsignadoLabel.setText("Asignee");

        NombreTareaLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        NombreTareaLabel.setForeground(new java.awt.Color(85, 96, 115));
        NombreTareaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NombreTareaLabel.setText("Name Of Task");

        DevelopmentsLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        DevelopmentsLabel.setForeground(new java.awt.Color(85, 96, 115));
        DevelopmentsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DevelopmentsLabel.setText("Developments");

        EvidenceLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        EvidenceLabel.setForeground(new java.awt.Color(85, 96, 115));
        EvidenceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EvidenceLabel.setText("Evidence");

        javax.swing.GroupLayout InfoCalendarPanelLayout = new javax.swing.GroupLayout(InfoCalendarPanel);
        InfoCalendarPanel.setLayout(InfoCalendarPanelLayout);
        InfoCalendarPanelLayout.setHorizontalGroup(
            InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                        .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                        .addComponent(NombreTareaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AsignadoLabel)
                        .addGap(29, 29, 29))
                    .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                        .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DevelopmentsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(DevelopmentsLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                                .addComponent(EvidenceLabel)
                                .addGap(0, 167, Short.MAX_VALUE))
                            .addComponent(EvidenceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        InfoCalendarPanelLayout.setVerticalGroup(
            InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AsignadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreTareaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DevelopmentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EvidenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DevelopmentsScrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(EvidenceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(OptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TasksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(InfoCalendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(NombreFiltrosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NombreFiltrosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TasksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InfoCalendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(OptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1242, 756));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ByDateIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByDateIconMouseEntered
        ByDateIcon.setIcon(new ImageIcon("assets/bydate2.png"));
    }//GEN-LAST:event_ByDateIconMouseEntered

    private void ByActivityIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByActivityIconMouseEntered
        ByActivityIcon.setIcon(new ImageIcon("assets/bytask2.png"));
    }//GEN-LAST:event_ByActivityIconMouseEntered

    private void ByCollaboratorIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByCollaboratorIconMouseEntered
        ByCollaboratorIcon.setIcon(new ImageIcon("assets/bycollaborator2.png"));
    }//GEN-LAST:event_ByCollaboratorIconMouseEntered

    private void ByDateIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByDateIconMouseExited
        ByDateIcon.setIcon(new ImageIcon("assets/bydate.png"));
    }//GEN-LAST:event_ByDateIconMouseExited

    private void ByActivityIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByActivityIconMouseExited
        ByActivityIcon.setIcon(new ImageIcon("assets/bytask.png"));
    }//GEN-LAST:event_ByActivityIconMouseExited

    private void ByCollaboratorIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByCollaboratorIconMouseExited
        ByCollaboratorIcon.setIcon(new ImageIcon("assets/bycollaborator.png"));
    }//GEN-LAST:event_ByCollaboratorIconMouseExited

    private void ProjectsIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectsIconMouseEntered
        ProjectsIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_ProjectsIconMouseEntered

    private void ProjectsIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectsIconMouseExited
        ProjectsIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_ProjectsIconMouseExited

    private void ReportIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportIconMouseEntered
        ReportIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_ReportIconMouseEntered

    private void ReportIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportIconMouseExited
        ReportIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_ReportIconMouseExited

    private void SincronizeIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SincronizeIconMouseEntered
        SincronizeIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_SincronizeIconMouseEntered

    private void SincronizeIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SincronizeIconMouseExited
        SincronizeIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_SincronizeIconMouseExited

    private void ProjectsIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectsIconMouseClicked
        if(project == null)
            new ProjectDialog(source,this,user).setVisible(true);
        else
            closeProject();
    }//GEN-LAST:event_ProjectsIconMouseClicked

    private void banCollaboratorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banCollaboratorMenuItemActionPerformed
        banSelectedCollaborator();
    }//GEN-LAST:event_banCollaboratorMenuItemActionPerformed

    private void unbanCollaboratorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unbanCollaboratorMenuItemActionPerformed
        unbanSelectedCollaborator();
    }//GEN-LAST:event_unbanCollaboratorMenuItemActionPerformed

    private void TasksTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_TasksTreeValueChanged
        openSelectedTask();
    }//GEN-LAST:event_TasksTreeValueChanged

    private void SincronizeIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SincronizeIconMouseClicked
        synchronizeTasks();
    }//GEN-LAST:event_SincronizeIconMouseClicked

    private void addDevelopmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDevelopmentMenuItemActionPerformed
        new AddDevelopmentDialog(source, this, task, calendar.getDate()).setVisible(true);
    }//GEN-LAST:event_addDevelopmentMenuItemActionPerformed

    private void DevelopmentsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_DevelopmentsListValueChanged
        displayEvidence(evt);
    }//GEN-LAST:event_DevelopmentsListValueChanged

    private void FromDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FromDateChooserPropertyChange
        filterDevelopments(evt);
    }//GEN-LAST:event_FromDateChooserPropertyChange

    private void ToDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToDateChooserPropertyChange
        filterDevelopments(evt);
    }//GEN-LAST:event_ToDateChooserPropertyChange

    private void TaskComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TaskComboBoxItemStateChanged
        filterTasks(evt);
    }//GEN-LAST:event_TaskComboBoxItemStateChanged

    private void CollaboratorComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CollaboratorComboBoxItemStateChanged
        filterTasks(evt);
    }//GEN-LAST:event_CollaboratorComboBoxItemStateChanged

    private void resetFiltersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFiltersMenuItemActionPerformed
        resetFilters();
    }//GEN-LAST:event_resetFiltersMenuItemActionPerformed

    private void ReportIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportIconMouseClicked
        printReport();
    }//GEN-LAST:event_ReportIconMouseClicked

    private void downloadEvidenceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadEvidenceMenuItemActionPerformed
        downloadEvidence();
    }//GEN-LAST:event_downloadEvidenceMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList ActiveList;
    private javax.swing.JScrollPane ActiveScrollPane;
    private javax.swing.JLabel AsignadoLabel;
    private javax.swing.JList BannedList;
    private javax.swing.JScrollPane BannedScrollPane;
    private javax.swing.JLabel ByActivityIcon;
    private javax.swing.JSeparator ByActivitySeparator;
    private javax.swing.JLabel ByCollaboratorIcon;
    private javax.swing.JSeparator ByCollaboratorSeparator;
    private javax.swing.JLabel ByDateIcon;
    private javax.swing.JSeparator ByDateSeparator;
    private javax.swing.JComboBox CollaboratorComboBox;
    private javax.swing.JLabel CollaboratorsIcon;
    private javax.swing.JTabbedPane CollaboratorsTabbedPane;
    private javax.swing.JLabel DevelopmentsLabel;
    private javax.swing.JList DevelopmentsList;
    private javax.swing.JScrollPane DevelopmentsScrollpane;
    private javax.swing.JLabel EvidenceLabel;
    private javax.swing.JList EvidenceList;
    private javax.swing.JScrollPane EvidenceScrollPane;
    private javax.swing.JLabel FiltersLabel;
    private com.toedter.calendar.JDateChooser FromDateChooser;
    private javax.swing.JLabel FromLabel;
    private javax.swing.JPanel InfoCalendarPanel;
    private javax.swing.JLabel LogoLabel;
    private javax.swing.JPanel NombreFiltrosPanel;
    private javax.swing.JLabel NombreProyectoLabel;
    private javax.swing.JLabel NombreTareaLabel;
    private javax.swing.JPanel OptionsPanel;
    private javax.swing.JLabel ProjectsIcon;
    private javax.swing.JLabel ReportIcon;
    private javax.swing.JLabel SincronizeIcon;
    private javax.swing.JComboBox TaskComboBox;
    private javax.swing.JPanel TasksPanel;
    private javax.swing.JTree TasksTree;
    private com.toedter.calendar.JDateChooser ToDateChooser;
    private javax.swing.JLabel ToLabel;
    private javax.swing.JScrollPane TreeTaskScrollPane;
    private javax.swing.JPopupMenu activeCollaboratorsPopupMenu;
    private javax.swing.JMenuItem addDevelopmentMenuItem;
    private javax.swing.JMenuItem banCollaboratorMenuItem;
    private javax.swing.JPopupMenu bannedCollaboratorsPopupMenu;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JPopupMenu developmentsPopupMenu;
    private javax.swing.JMenuItem downloadEvidenceMenuItem;
    private javax.swing.JPopupMenu evidencePopupMenu;
    private javax.swing.JPopupMenu filtersPopupMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem resetFiltersMenuItem;
    private javax.swing.JMenuItem unbanCollaboratorMenuItem;
    // End of variables declaration//GEN-END:variables
}
