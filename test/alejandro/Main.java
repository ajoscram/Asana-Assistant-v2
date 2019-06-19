package alejandro;

import asana_assistant_1.control.ProjectReportBuilder;
import asana_assistant_1.control.Router;
import asana_assistant_1.control.daos.connection.Connection;
import asana_assistant_1.control.dtos.TaskDTO;
import com.opencsv.CSVReader;
import asana_assistant_2.control.CSVTaskParser;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import asana_assistant_1.model.Project;
import asana_assistant_1.report.Report;
import asana_assistant_2.control.ProjectPDFReportPrinter;

public class Main {

    public static void main(String[] args) {
        try {    
            /*OPEN CSV TEST
            CSVReader reader = new CSVReader(new FileReader("tasks.csv"));
            String[] line;
            while ((line = reader.readNext()) != null) {
            for( int i = 0; i < line.length; i++){
            if(line[i].equals(""))
            System.out.print("emp");
            System.out.print(line[i] + "\t");
            }
            System.out.println();
            }
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            } catch (IOException ex) {
            ex.printStackTrace();
            }*/
            
            /*CSVTaskParser TEST
            CSVTaskParser parser = new CSVTaskParser();
            for(TaskDTO task : parser.parse("tasks.csv"))
            printTask(task, 0);*/
            
            /*PrettyPDFReportPrinter TEST*/
            Connection.connect();
            Router router = new Router();
            Project project = router.getProject(1);
            ProjectReportBuilder builder = new ProjectReportBuilder().setAsignee(1l).setStart(LocalDate.now()).setEnd(LocalDate.now());
            Report report = builder.build(project);
            new ProjectPDFReportPrinter().print(report, "C:\\Users\\Alejandro\\Downloads\\report.pdf");
            Desktop.getDesktop().open(new File("C:\\Users\\Alejandro\\Downloads\\report.pdf"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void printTask(TaskDTO task, int level){
        String string = "";
        while(level > 0){
            string += '\t';
            level--;
        }
        string += task.getName();
        System.out.println(string);
        for(TaskDTO subtask : task.getSubtasks())
            printTask(subtask, level+1);
    }
}