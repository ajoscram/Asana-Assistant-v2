/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newviews;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import view.View;

/**
 *
 * @author Gabriel
 */
public class UserFrame extends javax.swing.JFrame {
    private static LoginFrame parent;
    /**
     * Creates new form UserFrame
     */
    public UserFrame(newviews.LoginFrame parent) {
        initComponents();
        this.parent=parent;
        this.setLocationRelativeTo(parent);
        this.setIconImage(parent.getIconImage());
       
        //this.router = source.getRouter();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OptionsPanel = new javax.swing.JPanel();
        CollaboratorsIcon = new javax.swing.JLabel();
        ProjectsIcon = new javax.swing.JLabel();
        ReportIcon = new javax.swing.JLabel();
        LogoLabel = new javax.swing.JLabel();
        CollaboratorsTabbedPane = new javax.swing.JTabbedPane();
        ActiveScrollPane = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        BannedScrollPane = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        SincronizeIcon = new javax.swing.JLabel();
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        TasksPanel = new javax.swing.JPanel();
        TreeTaskScrollPane = new javax.swing.JScrollPane();
        TasksTree = new javax.swing.JTree();
        InfoCalendarPanel = new javax.swing.JPanel();
        calendar = new com.toedter.calendar.JCalendar();
        DevelopmentsScrollpane = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        EvidenceScrollPane = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        AsignadoLabel = new javax.swing.JLabel();
        NombreTareaLabel = new javax.swing.JLabel();
        DevelopmentsLabel = new javax.swing.JLabel();
        EvidenceLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        OptionsPanel.setBackground(new java.awt.Color(29, 38, 52));

        CollaboratorsIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        CollaboratorsIcon.setForeground(new java.awt.Color(255, 255, 255));
        CollaboratorsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/collaborators_userframe.png"))); // NOI18N
        CollaboratorsIcon.setText("Collaborators");
        CollaboratorsIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CollaboratorsIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CollaboratorsIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CollaboratorsIconMouseExited(evt);
            }
        });

        ProjectsIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        ProjectsIcon.setForeground(new java.awt.Color(255, 255, 255));
        ProjectsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projects.png"))); // NOI18N
        ProjectsIcon.setText("Projects");
        ProjectsIcon.addMouseListener(new java.awt.event.MouseAdapter() {
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReportIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportIconMouseExited(evt);
            }
        });

        LogoLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Gabriel\\Downloads\\logo_long_clear(2).png")); // NOI18N

        CollaboratorsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        CollaboratorsTabbedPane.setForeground(new java.awt.Color(29, 38, 52));
        CollaboratorsTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CollaboratorsTabbedPane.setFont(new java.awt.Font("Proxima Nova Rg", 1, 14)); // NOI18N

        ActiveScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ActiveScrollPane.setBorder(null);
        ActiveScrollPane.setForeground(new java.awt.Color(85, 96, 115));
        ActiveScrollPane.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N

        jList3.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        jList3.setForeground(new java.awt.Color(85, 96, 115));
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList3.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jList3.setSelectionForeground(new java.awt.Color(243, 242, 242));
        ActiveScrollPane.setViewportView(jList3);

        CollaboratorsTabbedPane.addTab("Active", ActiveScrollPane);

        BannedScrollPane.setBorder(null);
        BannedScrollPane.setForeground(new java.awt.Color(85, 96, 115));
        BannedScrollPane.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N

        jList4.setFont(new java.awt.Font("Proxima Nova Rg", 0, 14)); // NOI18N
        jList4.setForeground(new java.awt.Color(85, 96, 115));
        jList4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList4.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jList4.setSelectionForeground(new java.awt.Color(243, 242, 242));
        BannedScrollPane.setViewportView(jList4);

        CollaboratorsTabbedPane.addTab("Banned", BannedScrollPane);

        SincronizeIcon.setFont(new java.awt.Font("Proxima Nova Rg", 0, 24)); // NOI18N
        SincronizeIcon.setForeground(new java.awt.Color(255, 255, 255));
        SincronizeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/synchronizeuserframe.png"))); // NOI18N
        SincronizeIcon.setText("Sincronize");
        SincronizeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SincronizeIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SincronizeIconMouseExited(evt);
            }
        });

        javax.swing.GroupLayout OptionsPanelLayout = new javax.swing.GroupLayout(OptionsPanel);
        OptionsPanel.setLayout(OptionsPanelLayout);
        OptionsPanelLayout.setHorizontalGroup(
            OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReportIcon)
                    .addComponent(ProjectsIcon)
                    .addComponent(SincronizeIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OptionsPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CollaboratorsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CollaboratorsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        OptionsPanelLayout.setVerticalGroup(
            OptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OptionsPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CollaboratorsIcon)
                .addGap(19, 19, 19)
                .addComponent(CollaboratorsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProjectsIcon)
                .addGap(18, 18, 18)
                .addComponent(SincronizeIcon)
                .addGap(18, 18, 18)
                .addComponent(ReportIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NombreFiltrosPanel.setBackground(new java.awt.Color(255, 255, 255));
        NombreFiltrosPanel.setPreferredSize(new java.awt.Dimension(1024, 108));

        NombreProyectoLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 36)); // NOI18N
        NombreProyectoLabel.setForeground(new java.awt.Color(29, 38, 52));
        NombreProyectoLabel.setText("Nombre del Proyecto");

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ByCollaboratorSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(ByCollaboratorIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        NombreFiltrosPanelLayout.setVerticalGroup(
            NombreFiltrosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NombreFiltrosPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(NombreProyectoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
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
                    .addComponent(jComboBox1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ByCollaboratorIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TasksPanel.setBackground(new java.awt.Color(204, 204, 204));

        TreeTaskScrollPane.setBorder(null);
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
            .addComponent(TreeTaskScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        InfoCalendarPanel.setBackground(new java.awt.Color(214, 217, 225));

        calendar.setForeground(new java.awt.Color(29, 38, 52));
        calendar.setDecorationBackgroundColor(new java.awt.Color(85, 96, 115));
        calendar.setDecorationBackgroundVisible(false);
        calendar.setSundayForeground(new java.awt.Color(255, 102, 0));
        calendar.setWeekdayForeground(new java.awt.Color(255, 255, 255));

        jList1.setForeground(new java.awt.Color(85, 96, 115));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jList1.setSelectionForeground(new java.awt.Color(243, 242, 242));
        DevelopmentsScrollpane.setViewportView(jList1);

        jList2.setForeground(new java.awt.Color(85, 96, 115));
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jList2.setSelectionForeground(new java.awt.Color(243, 242, 242));
        EvidenceScrollPane.setViewportView(jList2);

        AsignadoLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        AsignadoLabel.setForeground(new java.awt.Color(85, 96, 115));
        AsignadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AsignadoLabel.setText("Asignado");

        NombreTareaLabel.setFont(new java.awt.Font("Proxima Nova Rg", 1, 18)); // NOI18N
        NombreTareaLabel.setForeground(new java.awt.Color(85, 96, 115));
        NombreTareaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NombreTareaLabel.setText("Nombre Tarea");

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
                            .addComponent(DevelopmentsScrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                            .addComponent(DevelopmentsLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(InfoCalendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InfoCalendarPanelLayout.createSequentialGroup()
                                .addComponent(EvidenceLabel)
                                .addGap(0, 177, Short.MAX_VALUE))
                            .addComponent(EvidenceScrollPane))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InfoCalendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(NombreFiltrosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CollaboratorsIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CollaboratorsIconMouseEntered
        
    }//GEN-LAST:event_CollaboratorsIconMouseEntered

    private void CollaboratorsIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CollaboratorsIconMouseExited
        
    }//GEN-LAST:event_CollaboratorsIconMouseExited

    private void ReportIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportIconMouseEntered
       ReportIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_ReportIconMouseEntered

    private void ReportIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportIconMouseExited
       ReportIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_ReportIconMouseExited

    private void CollaboratorsIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CollaboratorsIconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CollaboratorsIconMouseClicked

    private void ByDateIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByDateIconMouseEntered
        //ByDateIcon.setForeground(java.awt.Color.decode("#0099FF"));
        ByDateIcon.setIcon(new ImageIcon("assets/bydate2.png"));
    }//GEN-LAST:event_ByDateIconMouseEntered

    private void ByActivityIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByActivityIconMouseEntered
        //ByActivityIcon.setForeground(java.awt.Color.decode("#0099FF"));
        ByActivityIcon.setIcon(new ImageIcon("assets/bytask2.png"));
    }//GEN-LAST:event_ByActivityIconMouseEntered

    private void ByCollaboratorIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByCollaboratorIconMouseEntered
        //ByCollaboratorIcon.setForeground(java.awt.Color.decode("#0099FF"));
        ByCollaboratorIcon.setIcon(new ImageIcon("assets/bycollaborator2.png"));
    }//GEN-LAST:event_ByCollaboratorIconMouseEntered

    private void ByDateIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByDateIconMouseExited
        //ByDateIcon.setForeground(java.awt.Color.decode("#1D2634"));
        ByDateIcon.setIcon(new ImageIcon("assets/bydate.png"));
    }//GEN-LAST:event_ByDateIconMouseExited

    private void ByActivityIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByActivityIconMouseExited
        //ByActivityIcon.setForeground(java.awt.Color.decode("#1D2634"));
        ByActivityIcon.setIcon(new ImageIcon("assets/bytask.png"));
    }//GEN-LAST:event_ByActivityIconMouseExited

    private void ByCollaboratorIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ByCollaboratorIconMouseExited
        //ByCollaboratorIcon.setForeground(java.awt.Color.decode("#1D2634"));
        ByCollaboratorIcon.setIcon(new ImageIcon("assets/bycollaborator.png"));
    }//GEN-LAST:event_ByCollaboratorIconMouseExited

    private void SincronizeIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SincronizeIconMouseEntered
        SincronizeIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_SincronizeIconMouseEntered

    private void SincronizeIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SincronizeIconMouseExited
        SincronizeIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_SincronizeIconMouseExited

    private void ProjectsIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectsIconMouseExited
        ProjectsIcon.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_ProjectsIconMouseExited

    private void ProjectsIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProjectsIconMouseEntered
        ProjectsIcon.setForeground(java.awt.Color.decode("#FF6600"));
    }//GEN-LAST:event_ProjectsIconMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame(parent).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ActiveScrollPane;
    private javax.swing.JLabel AsignadoLabel;
    private javax.swing.JScrollPane BannedScrollPane;
    private javax.swing.JLabel ByActivityIcon;
    private javax.swing.JSeparator ByActivitySeparator;
    private javax.swing.JLabel ByCollaboratorIcon;
    private javax.swing.JSeparator ByCollaboratorSeparator;
    private javax.swing.JLabel ByDateIcon;
    private javax.swing.JSeparator ByDateSeparator;
    private javax.swing.JLabel CollaboratorsIcon;
    private javax.swing.JTabbedPane CollaboratorsTabbedPane;
    private javax.swing.JLabel DevelopmentsLabel;
    private javax.swing.JScrollPane DevelopmentsScrollpane;
    private javax.swing.JLabel EvidenceLabel;
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
    private javax.swing.JPanel TasksPanel;
    private javax.swing.JTree TasksTree;
    private com.toedter.calendar.JDateChooser ToDateChooser;
    private javax.swing.JLabel ToLabel;
    private javax.swing.JScrollPane TreeTaskScrollPane;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}