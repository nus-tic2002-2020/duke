package classes;

import exceptions.*;
import exceptions.Exception;
import java.util.ArrayList;

/**
 * Class which holds a list of tasks stored as string in ArrayList, and related methods
 */
public class TaskList {
    private ArrayList<Task> store;

    /**
     * Constructor. Loads task data from txt file and stores them to an ArrayList
     *
     * @param tasks ArrayList of string which makes up the tasks
     */
    public TaskList(ArrayList<String> tasks) {
        this.store = new ArrayList<>();
        for (String i : tasks) {
            String[] temp = i.split("\\|");
            String function = temp[0].trim();
            switch(function) {
                case "T": {
                    store.add(new ToDo(temp[2].trim()));
                    break;
                }
                case "D": {
                    store.add(new Deadline(temp[2].trim(), temp[3].trim()));
                    break;
                }
                case "E": {
                    store.add(new Event(temp[2].trim(), temp[3].trim()));
                    break;
                }
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
        input = input.toLowerCase();
        String[] split = input.split(" ");
        return input.trim().length() == split[0].length();
    }

    /**
     * Sets the done status of the selected task to true by calling method in task-class
     *
     * @param input User-input
     */
    public void done(String input) {
        String[] temp = input.split(" ");
        int index = Integer.parseInt(temp[1]) - 1;
        store.get(index).setDone();
    }

    /**
     * Deletes the selected task from the ArrayList
     *
     * @param input User-input
     * @throws Exception Error when invalid task index number is inputted or when input format is wrong
     */
    public void delete(String input) throws Exception {
        String[] temp = input.split(" ");
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        store.remove(Integer.parseInt(temp[1]) - 1);
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
        String[] s = input.split(" ");
        if (input.toLowerCase().startsWith("t")) { function = 1; }
        if (input.toLowerCase().startsWith("d")) { function = 2; }
        if (input.toLowerCase().startsWith("e")) { function = 3; }
        switch (function) {
            case 1: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                store.add(new ToDo(input.substring(4).trim()));
                break;
            }
            case 2: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] split = input.split("/by");
                if (catcher(split[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Deadline(split[0].substring(8).trim(), split[1].trim()));
                break;
            }
            case 3: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] split = input.split("/at");
                if (catcher(split[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Event(split[0].substring(5).trim(), split[1].trim()));
                break;
            }
        }
    }
}
