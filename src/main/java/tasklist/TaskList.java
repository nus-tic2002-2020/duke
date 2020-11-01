package tasklist;

import exceptions.*;
import exceptions.Exception;
import java.util.ArrayList;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.Within;

/**
 * Class which holds a list of tasks stored as string in ArrayList, and related methods
 */
public class TaskList {
    private ArrayList<Task> store;

    /**
     * Constructor. Loads task data from txt file and stores them to an ArrayList
     *
     * @param taskArray ArrayList of string which makes up the tasks
     */
    public TaskList(ArrayList<String> taskArray) {
        this.store = new ArrayList<>();
        for (String task : taskArray) {
            String[] splitSections = task.split("\\|");
            String function = splitSections[0].trim();
            switch(function) {
                case "T":
                    store.add(new ToDo(splitSections[2].trim()));
                    break;
                case "D":
                    store.add(new Deadline(splitSections[2].trim(), splitSections[3].trim()));
                    break;
                case "E":
                    store.add(new Event(splitSections[2].trim(), splitSections[3].trim()));
                    break;
                case "W":
                    String[] startEnd = splitSections[3].split("-");
                    store.add(new Within(splitSections[2].trim(), startEnd[0].trim(), startEnd[1].trim()));
                    break;
            }
        }
    }

    /**
     * @return Returns the ArrayList
     */
    public ArrayList<Task> getStore() {
        return store;
    }

    /**
     * Helper method, used by add-method to identify user-input errors.
     *
     * @param input User-input
     * @return
     */
    public static boolean catcher(String input) { // exception helper method
        String[] splitDescription = input.split(" ");
        return input.trim().length() == splitDescription[0].length();
    }

    /**
     * Sets the done status of the selected task to true by calling method in task-class
     *
     * @param input User-input
     */
    public void setDone(String input) throws TooManySpacesException{
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        String[] splitIndex = input.split(" ");
        int index = Integer.parseInt(splitIndex[1]) - 1;
        store.get(index).setDone();
    }

    /**
     * Deletes the selected task from the ArrayList
     *
     * @param input User-input
     * @throws Exception Error when invalid task index number is inputted or when input format is wrong
     */
    public void delete(String input) throws TooManySpacesException {
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        String[] splitIndex = input.split(" ");
        store.remove(Integer.parseInt(splitIndex[1]) - 1);
    }

    /**
     * Adds the task to the ArrayList. Catches the first alphabet of the input to determine type of task. Calls
     * constructor of that task
     *
     * @param input User-input
     * @throws Exception Error when input format is wrong or missing information
     */
    public void add(String input) throws Exception {
        int function = 0;
        if (input.toLowerCase().startsWith("t")) { function = 1; }
        if (input.toLowerCase().startsWith("d")) { function = 2; }
        if (input.toLowerCase().startsWith("e")) { function = 3; }
        if (input.toLowerCase().startsWith("w")) { function = 4; }
        switch (function) {
            case 1:
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                store.add(new ToDo(input.substring(4).trim()));
                break;
            case 2:
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] splitBy = input.split("/by");
                if (catcher(splitBy[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Deadline(splitBy[0].substring(8).trim(), splitBy[1].trim()));
                break;
            case 3:
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] splitAt = input.split("/at");
                if (catcher(splitAt[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Event(splitAt[0].substring(5).trim(), splitAt[1].trim()));
                break;
            case 4:
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] splitFromTo = input.split("/from");
                String[] dates = splitFromTo[1].split("/to");
                if (catcher(dates[0]) || catcher(dates[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Within(splitFromTo[0].substring(6).trim(), dates[0].trim(), dates[1].trim()));
                break;
        }
    }
}
