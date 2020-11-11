package Duke;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath)
    {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException
    {
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        File myObj = new File(filePath);
        Scanner sc = new Scanner(myObj);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] lineArr = line.split("\\s[|]\\s");
            String taskType = lineArr[0];
            if (taskType.equals("T")) {
                loadedTaskList.add(new Todo(lineArr[2],Boolean.parseBoolean(lineArr[1]),Integer.parseInt(lineArr[3])));
                //System.out.println("T Description: "+ lineArr[2] +" Boolean: "+ lineArr[1] + "Priority: " + lineArr[3]);
            }
            else if (taskType.equals("E")) {
                //System.out.println("E Description: "+ lineArr[2] +" Boolean: "+ lineArr[1] + " At: "+ lineArr[3] + " Priority: " + lineArr[4]);
                loadedTaskList.add(new Event(lineArr[2], LocalDateTime.parse(lineArr[3],formatter), Boolean.parseBoolean(lineArr[1]), Integer.parseInt(lineArr[4])));

            }
            else if (taskType.equals("D")) {
                loadedTaskList.add(new Deadline(lineArr[2], LocalDateTime.parse(lineArr[3],formatter), Boolean.parseBoolean(lineArr[1]), Integer.parseInt(lineArr[4])));
                //System.out.println("D Description: "+ lineArr[2] +" Boolean: "+ lineArr[1] + " At: "+ lineArr[3] + " Priority: " + lineArr[4]);
            }
        }
        return loadedTaskList;
    }
    public void save(TaskList taskList){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            String output = "";
            File myObj = new File(filePath);
            FileWriter fw = new FileWriter(myObj);
            for (Task task : taskList.taskList) {
                String taskType = task.getClass().getSimpleName();
                String Description = task.getDescription();
                int Priority = task.getPriority();
                boolean isDone = task.isDone;
                //System.out.println("taskType: "+taskType);
                if (taskType.equals("Todo")) {
                    output += "T | " + isDone + " | " + Description + " | " + Priority +  "\r\n";
                }
                else if(taskType.equals("Deadline")) {
                    output += "D | " + isDone + " | " + Description + " | " + ((Deadline) task).getBy().format(formatter) + " | " + Priority +  "\r\n";
                }
                else if(taskType.equals("Event")) {
                    output += "E | " + isDone + " | " + Description + " | " + ((Event) task).getAt().format(formatter) + " | " + Priority + "\r\n";
                }
            }
            //System.out.println("output: "+output);
            fw.write(output);
            fw.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
