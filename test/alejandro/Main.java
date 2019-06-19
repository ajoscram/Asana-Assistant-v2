package alejandro;

import com.opencsv.CSVReader;
import control.CSVTaskParser;
import control.dtos.TaskDTO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import parse.ParseException;

public class Main {

    public static void main(String[] args) {
        /*OPEN CSV TEST
        try {
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
        try {
            CSVTaskParser parser = new CSVTaskParser();
            for(TaskDTO task : parser.parse("tasks.csv"))
                printTask(task, 0);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }*/
        
        
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
