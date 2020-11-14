package tasklist;

import java.util.ArrayList;

import enumerations.SymbolEnum;
import exceptions.*;
import exceptions.Exception;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.Within;

/**
 * This is the TaskList class for handling interactions with the ArrayList of tasks. Available functions include
 * getting the ArrayList, changing the status of a task to 'done' (from [✗] -> [✓]), editing the priority of the task,
 * deletion and insertion of tasks into the ArrayList.
 *
 * @author Aloysius Wong
 * @version 1.0
 * @since 08-11-2020
 */
public class TaskList {

    /**
     * This is the ArrayList of tasks contained by the TaskList class.
     */
    private final ArrayList<Task> store;

    /**
     * This creates the TaskList-class object. It receives another ArrayList of string as the task list loaded from the
     * text file. Any tasks in the ArrayList from the text file is then loaded to the new ArrayList in a new format.
     *
     * @param taskArray The ArrayList of strings (task list) loaded from the text file.
     */
    public TaskList(ArrayList<String> taskArray) throws EndTimeBeforeStartTimeException {
        this.store = new ArrayList<>();
        for (String task : taskArray) {
            String[] splitSections = task.split("\\|");
            SymbolEnum symbol = SymbolEnum.valueOf(splitSections[0].trim());
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during loading from text file to ArrayList.";

            switch(symbol) {
                case T:
                    store.add(new ToDo(splitSections[2].trim()));
                    store.get(store.size()-1).setPriority(splitSections[3].trim());
                    break;

                case D:
                    store.add(new Deadline(splitSections[2].trim(), splitSections[3].trim()));
                    store.get(store.size()-1).setPriority(splitSections[4].trim());
                    break;

                case E:
                    store.add(new Event(splitSections[2].trim(), splitSections[3].trim()));
                    store.get(store.size()-1).setPriority(splitSections[4].trim());
                    break;

                case W:
                    String[] startEnd = splitSections[3].split("-");
                    store.add(new Within(splitSections[2].trim(), startEnd[0].trim(), startEnd[1].trim()));
                    store.get(store.size()-1).setPriority(splitSections[4].trim());
                    break;
            }

            if (splitSections[1].trim().equals("✓")) {
                store.get(store.size()-1).setDone();
            }
        }
    }

    /**
     * This method returns the ArrayList of tasks.
     *
     * @return Returns the ArrayList.
     */
    public ArrayList<Task> getStore() {
        return store;
    }

    /**
     * This is a helper method, used by the add method to identify user-input error where the description of the task
     * is missing.
     *
     * @param input This is the input by the user, to be tested for error.
     * @return Returns true if the description is missing, false if a description is present.
     */
    public boolean errorCatch(String input) {
        String[] splitDescription = input.split(" ");
        return input.trim().length() == splitDescription[0].length();
    }

    /**
     * This method sets the status of the selected task to 'done'.
     *
     * @param input This is the input by the user in the form of "done (index)".
     * @return Returns false if the task has already been marked completed. True if otherwise.
     * @throws TooManySpacesException TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors
     */
    public boolean setDone(String input) throws TooManySpacesException {
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        String[] splitIndex = input.split(" ");
        int index = Integer.parseInt(splitIndex[1]) - 1;
        if (store.get(index).getDone().equals(("Completed: YES"))) {
            return false;
        }
        store.get(index).setDone();
        assert store.get(index).getDone().equals("Completed: YES") : "Failed to set task to done status properly.";
        return true;
    }

    /**
     * This method changes the priority of the selected task.
     *
     * @param input This is the input by the user in the form of "priority (index) (priority-level)".
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     * many errors.
     */
    public void setPriority(String input) throws TooManySpacesException {
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        String[] splitIndex = input.split(" ");
        int index = Integer.parseInt(splitIndex[1]) - 1;
        if (index > store.size()-1) {
            throw new IndexOutOfBoundsException();
        }
        if (!input.toUpperCase().contains("HIGH") && !input.toUpperCase().contains("MEDIUM")
                && !input.toUpperCase().contains("LOW") && !input.toUpperCase().contains("NA")) {
            throw new IllegalArgumentException();
        }
        store.get(index).setPriority(splitIndex[2].trim());
        assert store.get(index).getPriority().toString().equals(splitIndex[2].toUpperCase())
                : "Setting of priority was incorrect.";
    }

    /**
     * This method deletes a task from the ArrayList.
     *
     * @param input This is the input by the user in the form of "delete (index)".
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     * many errors.
     */
    public void delete(String input) throws TooManySpacesException {
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        String[] splitIndex = input.split(" ");
        store.remove(Integer.parseInt(splitIndex[1]) - 1);
    }

    /**
     * This method adds a task to the ArrayList. It detects what the type of the task to be added is based on the
     * starting character of the user-input.
     *
     * @param input This is the user input. The form differs based on what the type of task is. E.g
     *              ToDo: ToDo shopping
     *              Deadline: Deadline homework /by 10/10/2020 10:10
     *              Event: Event concert /at 10/10/2020 10:10
     *              Within: Within semester /from 1/1/2020 10:10 /to 1/4/2020 10:10
     * @throws Exception This exception-superclass is thrown whenever any of it's subclass is thrown (refer to Exception
     *                   class for details).
     */
    public void add(String input) throws Exception {
        SymbolEnum symbol = SymbolEnum.valueOf(input.substring(0, 1).toUpperCase());
        assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                : "Symbol is invalid during loading addition of task to ArrayList.";

        switch (symbol) {
            case T:
                if (errorCatch(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                store.add(new ToDo(input.substring(4).trim()));
                break;

            case D:
                if (errorCatch(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                if (!input.toLowerCase().contains("/by")) {
                    throw new IndexOutOfBoundsException();
                }
                String[] splitBy = input.split("/by");
                if (splitBy.length == 1 || splitBy[1].equals(" ")) {
                    throw new NoTimeframeException();
                }
                store.add(new Deadline(splitBy[0].substring(8).trim(), splitBy[1].trim()));
                break;

            case E:
                if (errorCatch(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                if (!input.toLowerCase().contains("/at")) {
                    throw new IndexOutOfBoundsException();
                }
                String[] splitAt = input.split("/at");
                if (splitAt.length == 1 || splitAt[1].equals(" ")) {
                    throw new NoTimeframeException();
                }
                store.add(new Event(splitAt[0].substring(5).trim(), splitAt[1].trim()));
                break;

            case W:
                if (errorCatch(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                if (!input.toLowerCase().contains("/from") || !input.toLowerCase().contains("/to")) {
                    throw new IndexOutOfBoundsException();
                }
                String[] splitFromTo = input.split("/from");
                if (splitFromTo.length == 1) {
                    throw new NoTimeframeException();
                }
                String[] dates = splitFromTo[1].split("/to");
                if (dates.length == 1) {
                    throw new NoTimeframeException();
                }
                try {
                    store.add(new Within(splitFromTo[0].substring(6).trim(), dates[0].trim(), dates[1].trim()));
                } catch (EndTimeBeforeStartTimeException e) {
                    throw new EndTimeBeforeStartTimeException();
                }
                break;
        }
    }
}
