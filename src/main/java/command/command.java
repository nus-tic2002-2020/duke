package command;

import exception.ListEmptyException;
import storage.Storage;
import tasklist.*;
import ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class command {

    public static void save() {
        String list = "";
        for (int i = 0; i < TaskList.listTask.size(); i++) {
            list += TaskList.listTask.get(i).saveFormat() + "\n";
        }
        Storage.writeToFile(list);
    }

    public static void list() {
        try {
            listEmpty(TaskList.listTask);
            UI.printUserList(TaskList.listTask);
        } catch (exception.ListEmptyException e) {
            UI.printListEmpty();
        }
    }

    public static void listEmpty(ArrayList<Task> listTask) throws ListEmptyException {
        if (listTask.isEmpty()) {
            throw new ListEmptyException();
        }
    }

    public static void doneTask(String userInput) {

        try {
            int filteredNumber = Integer.parseInt(userInput.substring(5));
            TaskList.listTask.get(filteredNumber - 1).markDone();

            UI.printLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + TaskList.listTask.get(filteredNumber - 1).toString());
            UI.printLine();

        } catch (NumberFormatException e) {
            UI.printLine();
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            UI.printLine();
        } catch (NullPointerException e) {
            UI.printLine();
            System.out.println("\t☹ OOPS!!! Numbers out of range :-(");
            UI.printLine();
        }
    }

    /**
     * @param userInput user to enter input
     */
    public static void todoTask(String userInput) {
        try {
            String todoDescription = userInput.substring(5);
            CheckEmpty(todoDescription);
            Task t = new Todo(todoDescription);
            TaskList.listTask.add(t);

            UI.printLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + TaskList.listTask.get(TaskList.listTask.size() - 1).toString());
            UI.printNoOfTask(TaskList.listTask);
        } catch (StringIndexOutOfBoundsException e) {
            UI.printEmptyToDoException();
        }
    }

    public static void eventTask(String userInput) {
        int divPosition = userInput.indexOf("/");
        String eventDescription = userInput.substring(6, divPosition - 1);
        String dateTime = userInput.substring(divPosition + 4);
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDateTime formattedDate = LocalDateTime.parse(dateTime, inputDateFormat);
            Task t = new Events(eventDescription, formattedDate);
            TaskList.listTask.add(t);
            UI.printLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + TaskList.listTask.get(TaskList.listTask.size() - 1).toString());
            UI.printNoOfTask(TaskList.listTask);
        } catch (DateTimeParseException e) {
            UI.printDateTimeFormatError();
        }
    }

    public static void deadlineTask(String userInput) {
        int divPosition = userInput.indexOf("/");
        String deadlineDescription = userInput.substring(9, divPosition - 1);
        String filteredDate = userInput.substring(divPosition + 4);
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDateTime formattedDate = LocalDateTime.parse(filteredDate, inputDateFormat);
            Task t = new Deadline(deadlineDescription, formattedDate);
            TaskList.listTask.add(t);
            UI.printLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + TaskList.listTask.get(TaskList.listTask.size() - 1).toString());
            UI.printNoOfTask(TaskList.listTask);
        } catch (DateTimeParseException e) {
            UI.printDateTimeFormatError();
        }
    }

    public static void deleteTask(String userInput) {

        int numTask = Integer.valueOf(userInput.substring(7, userInput.length()));
        Task t = TaskList.listTask.get(numTask - 1);
        TaskList.listTask.remove(numTask - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t.toString());
        UI.printNoOfTask(TaskList.listTask);
        UI.printLine();
    }

    public static void findTask(String userInput) {
        String findDescription = userInput.substring(5);
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : TaskList.listTask) {
            if (task.find(findDescription)) {
                result.add(task);
            }
        }
        UI.printResultSearch(result);
    }

    static void CheckEmpty(String description) throws StringIndexOutOfBoundsException {
        if (description.isEmpty()) {
            throw new StringIndexOutOfBoundsException();
        }
    }
}
