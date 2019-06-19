package asana_assistant_2.control;

import asana_assistant_1.control.TaskParser;
import asana_assistant_1.control.dtos.TaskDTO;
import asana_assistant_1.control.dtos.UserDTO;
import asana_assistant_1.model.Task.Type;
import asana_assistant_1.parse.ParseException;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVTaskParser implements TaskParser {
    
    private static final int ID_INDEX = 0;
    private static final int CREATED_INDEX = 1;
    private static final int COMPLETED_INDEX = 2;
    private static final int NAME_INDEX = 4;
    private static final int ASIGNEE_NAME_INDEX = 5;
    private static final int ASIGNEE_EMAIL_INDEX = 6;
    private static final int DUE_INDEX = 8;
    private static final int PARENT_INDEX = 12;
    
    public CSVTaskParser(){ }
    
    private UserDTO parseUser(String[] line) throws ParseException{
        String email = line[ASIGNEE_EMAIL_INDEX];
        if(email.equals(""))
            return null;
        else{
            String name = line[ASIGNEE_NAME_INDEX];
            return new UserDTO(name, email, null, null);
        }
    }
    
    private LocalDate parseDate(String date){
        if(date.equals(""))
            return null;
        else{
            try{
                return Instant.parse(date).atZone(ZoneId.systemDefault()).toLocalDate();
            } catch(DateTimeParseException e){
                return LocalDate.parse(date);
            }
        }
    }
    
    private TaskDTO parse(String[] line, Type type, int index) throws ParseException{
        try{
            long id = Long.parseLong(line[ID_INDEX]);
            String name = line[NAME_INDEX];
            UserDTO asignee = parseUser(line);
            LocalDate created = parseDate(line[CREATED_INDEX]);
            LocalDate due = parseDate(line[DUE_INDEX]);
            LocalDate completed = parseDate(line[COMPLETED_INDEX]);
            return new TaskDTO(id, name, type, asignee, index, created, due, completed);
        } catch(NumberFormatException | DateTimeParseException ex){
            throw new ParseException(ParseException.Type.STRUCTURE);
        }
    }
    
    private List<String[]> read(String path) throws ParseException{
        try {
            ArrayList<String[]> lines = new ArrayList();
            CSVReader reader = new CSVReader(new FileReader("tasks.csv"));
            String[] line;
            while((line = reader.readNext()) != null)
                lines.add(line);
            return lines;
        } catch (IOException ex) {
            throw new ParseException(ParseException.Type.FILE_IO_ERROR);
        }
    }
    
    private TaskDTO getTask(List<TaskDTO> tasks, String name){
        for(TaskDTO task : tasks){
            if(task.getName().equals(name))
                return task;
            TaskDTO subtask = getTask(task.getSubtasks(), name);
            if(subtask != null)
                return subtask;
        }
        return null;
    }
    
    private void addSubtask(List<TaskDTO> tasks, String[] line) throws ParseException{
        TaskDTO parent = getTask(tasks, line[PARENT_INDEX]);
        if(parent == null)
            throw new ParseException(ParseException.Type.STRUCTURE);
        else{
            int index = parent.getSubtasks().size();
            parent.addSubtask(parse(line, Type.SUBTASK, index));
        }
    }
    
    @Override
    public List<TaskDTO> parse(String path) throws ParseException {
        try{
            List<String[]> lines = read(path);
            lines.remove(0); //the very first line contains title data
            ArrayList<TaskDTO> tasks = new ArrayList();
            for(String[] line : lines){
                if(line[PARENT_INDEX].equals(""))
                    tasks.add(parse(line, Type.SINGLE, tasks.size()));
                else
                    addSubtask(tasks, line);
            }
            return tasks;
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new ParseException(ParseException.Type.STRUCTURE);
        }
    }
}
