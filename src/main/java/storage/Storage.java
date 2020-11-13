package storage;

import exception.DukeException;
import tasklist.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * writes list of task to file (C:\data\duke.txt)
 * prints out error when catches IOExpection
 */
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

    /**
     * This separates the event and deadline task to different variables
     * and add to list of task
     * @param taskType to identify type of task
     * @param taskDes to identify task description
     * @param taskDateTime to convert to LocalDateTime
     * @return task to list of task
     */
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

    /**
     * This converts the list of task from file back to array of list of task
     * separates to type of task, task done, and task description.
     *
     * @param fileContent contains the description, date and time
     * @return task to list of task
     * @throws DukeException when error occurred
     */

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

    /**
     * This read the saved file into memory
     * @return list of task
     * @throws FileNotFoundException when file not found
     */

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
