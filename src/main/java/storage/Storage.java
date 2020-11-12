package storage;

import exception.DukeException;
import tasklist.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    public static void writeToFile(String fileContent) {

        try {
            FileWriter f = new FileWriter("C:\\data\\duke.txt");
            PrintWriter pw = new PrintWriter(f);
            pw.println(fileContent);
            pw.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static Task creatingEventOrDeadline(String taskType, String taskDes, String taskDateTime) {
        Task task;
        String eventDescription = taskDes;
        String deadlineDescription = taskDes;
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
        LocalDateTime formattedDate = LocalDateTime.parse(taskDateTime, outputDateFormat);

        if (!(taskType.contains("E") || taskType.contains("D"))) {
            System.out.println("Task Type not found");
        }
        if (taskType.contains("E")) {
            task = new Events(eventDescription, formattedDate);
            TaskList.listTask.add(task);
            return task;
        }
        task = new Deadline(deadlineDescription, formattedDate);
        TaskList.listTask.add(task);
        return task;
    }

    private static Task convertTaskFromFile(String fileContent) throws DukeException {
        Task task;

        int firstDivider = fileContent.indexOf("|");
        String taskType = fileContent.substring(0, firstDivider);
        String taskDoneString = fileContent.substring(2, firstDivider + 2);
        String taskDetails = fileContent.substring(4);
        boolean isDone = false;

        if (taskDoneString.contains("1")) {
            isDone = true;
        }
        if (taskDetails.contains("|")) {
            int timeDivider = taskDetails.indexOf("|");
            String taskDes = taskDetails.substring(0, timeDivider);
            String taskDateTime = taskDetails.substring(timeDivider + 1);
            task = creatingEventOrDeadline(taskType, taskDes, taskDateTime);
            task.editDone(isDone);
            return task;
        } else if (!taskType.contains("T")) {
            System.out.println("Error when loading file");
        }
        task = new Todo(taskDetails);
        task.editDone(isDone);
        TaskList.listTask.add(task);
        return task;
    }

    public static ArrayList<Task> loadFile() throws FileNotFoundException {

        ArrayList<Task> Tasks = new ArrayList<>();
        File f = new File("C:\\data\\duke.txt");
        if (f.length() == 0) {
            return new ArrayList<Task>();
        }

        Scanner myReader = new Scanner(f);
        while (myReader.hasNext()) {
            try {
                Task t = convertTaskFromFile(myReader.nextLine());
                Tasks.add(t);
                t.setTaskIndex(Tasks.size() - 1);
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
        return Tasks;
    }
}
