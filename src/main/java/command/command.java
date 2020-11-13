package command;

import exception.DukeException;
import exception.ListEmptyException;
import storage.Storage;
import tasklist.*;
import ui.UI;
import ui.helpPage;

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

    /**
     * to print out list of task
     * catches ListEmptyException and print out list is empty
     */
    public static void list() {
        try {
            listEmpty(TaskList.listTask);
            UI.printUserList(TaskList.listTask);
        } catch (ListEmptyException e) {
            UI.printListEmpty();
        }
    }

    public static void listEmpty(ArrayList<Task> listTask) throws ListEmptyException {
        if (listTask.isEmpty()) {
            throw new ListEmptyException();
        }
    }

    /**
     *
     * @param userInput mark the selected task as done
     * @throws DukeException when error occurs
     * @throws NumberFormatException when error occurs
     * @throws NullPointerException when error occurs
     */
    public static void doneTask(String userInput) throws StringIndexOutOfBoundsException, NumberFormatException, NullPointerException {
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
        } catch (StringIndexOutOfBoundsException e) {
            UI.printEmptyToDoException();
        }
    }

    /**
     * add to do task to list of task
     * @param userInput adds  to do description
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

    /**
     * add event to the list of task
     * @param userInput add event description
     * @throws StringIndexOutOfBoundsException
     * @throws DateTimeParseException
     */
    public static void eventTask(String userInput) throws StringIndexOutOfBoundsException, DateTimeParseException {
        try {
        int divPosition = userInput.indexOf("/");
        String eventDescription = userInput.substring(6, divPosition - 1);
        CheckEmpty(eventDescription);
        String dateTime = userInput.substring(divPosition + 4);
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


            LocalDateTime formattedDate = LocalDateTime.parse(dateTime, inputDateFormat);
            Task t = new Events(eventDescription, formattedDate);
            TaskList.listTask.add(t);
            UI.printLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + TaskList.listTask.get(TaskList.listTask.size() - 1).toString());
            UI.printNoOfTask(TaskList.listTask);
        } catch (DateTimeParseException e) {
            UI.printDateTimeFormatError();
        } catch (StringIndexOutOfBoundsException e) {
            UI.printEmptyToDoException();
        }
    }

    /**
     * adds deadline task to list of task
     * @param userInput adds task description, date and time
     * @throws StringIndexOutOfBoundsException prints error message
     * @throws DateTimeParseException prints the format to use
     */

    public static void deadlineTask(String userInput) throws StringIndexOutOfBoundsException, DateTimeParseException {
        try {
            int divPosition = userInput.indexOf("/");
            String deadlineDescription = userInput.substring(9, divPosition - 1);
            String filteredDate = userInput.substring(divPosition + 4);
            DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

            CheckEmpty(deadlineDescription);
            LocalDateTime formattedDate = LocalDateTime.parse(filteredDate, inputDateFormat);
            Task t = new Deadline(deadlineDescription, formattedDate);
            TaskList.listTask.add(t);
            UI.printLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t" + TaskList.listTask.get(TaskList.listTask.size() - 1).toString());
            UI.printNoOfTask(TaskList.listTask);
        } catch (DateTimeParseException e) {
            UI.printDateTimeFormatError();
        } catch (StringIndexOutOfBoundsException e) {
            UI.printEmptyToDoException();
        }
    }

    /**
     * delete task from list of task
     * @param userInput deletes task from list of task
     */
    public static void deleteTask(String userInput) {

        int numTask = Integer.valueOf(userInput.substring(7, userInput.length()));
        Task t = TaskList.listTask.get(numTask - 1);
        TaskList.listTask.remove(numTask - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t.toString());
        UI.printNoOfTask(TaskList.listTask);
        UI.printLine();
    }

    /**
     * find keywords from list of task
     * @param userInput types in the keywords to search
     * @throws StringIndexOutOfBoundsException
     */
    public static void findTask(String userInput) throws StringIndexOutOfBoundsException {
        try{
            String findDescription = userInput.substring(5);
            CheckEmpty(findDescription);
            ArrayList<Task> result = new ArrayList<Task>();
            for (Task task : TaskList.listTask) {
                if (task.find(findDescription)) {
                    result.add(task);
                }
                UI.printResultSearch(result);
            }
        }catch (StringIndexOutOfBoundsException e){
            UI.printInputError();
        }

    }

    /**
     * This prints out help page for user information
     * @param userInput enters 'h' or help
     */
    public static void helpTask(String userInput) {
        helpPage.printHelpPage2();
    }

    /**
     * This is to check that the description is not empty
     * @param description is not empty
     * @throws StringIndexOutOfBoundsException
     */
    static void CheckEmpty(String description) throws StringIndexOutOfBoundsException {
        if (description.isEmpty()) {
            throw new StringIndexOutOfBoundsException();
        }
    }
}