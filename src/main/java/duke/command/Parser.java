package duke.command;
import duke.task.TaskList;
import duke.task.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class registers the user-inputs and makes sense of the command that the program is supposed to execute.
 * <br> It contains the following approved actions:
 * <ol>
 *     <li>List: Allows to list all the tasks inside TaskList</li>
 *     <li>Bye: Exits the Task Manager</li>
 *     <li>Find: Find existing tasks from the TaskList through matching of the description</li>
 *     <li>Undo: Undo the last action including erase from History</li>
 *     <li>History: Prints out the history of actions taken</li>
 *     <li>Done: Marks a task as completed</li>
 *     <li>Delete: Deletes a task from the TaskList</li>
 *     <li>Todo / Event / Deadline: Adds one of the following tasks into the TaskList</li>
 *     <li>Occurrence: Lists which tasks fall on a specified date</li>
 * </ol>
 * <p>
 * Once it understand what the user-input is referring to, it passes the appropriate command to the TaskList for execution.
 */

public class Parser {
    protected static String by;
    protected static LocalDate localDate;
    protected static LocalDateTime localDateTime;
    protected static List<DateTimeFormatter> knownPatterns = new ArrayList<>();

    /**
     * This method takes in a user-input as a String, makes sense of the command and returns a command to the TaskList for execution or
     * returns a relevant Date and Time.
     * @param input Provides the user-input field to be parsed.
     * @throws DukeException Error if user-input is in a incompatible format.
     */

    public static LocalDateTime parse(String input) throws DukeException, IOException {
        boolean change = false;
        if (input.trim().equalsIgnoreCase("bye"))         { TaskList.Bye();}
        else if (input.trim().equalsIgnoreCase("list"))   { TaskList.List();}
        else if (input.toLowerCase().contains("find"))               { TaskList.Find(input.trim().substring(5));}
        else if (input.toLowerCase().contains("done"))               { TaskList.Done(input); change = true;}
        else if (input.toLowerCase().contains("delete"))             { TaskList.Delete(input); change = true;}
        else if (input.toLowerCase().contains("todo"))               { TaskList.Todo(input); change = true;}
        else if (input.toLowerCase().contains("undo"))               { TaskList.Undo();}
        else if (input.toLowerCase().contains("history"))            { Undo.printQueue();}

        else if ((input.toLowerCase().contains("deadline")) || (input.toLowerCase().contains("event")) ||
                (input.toLowerCase().contains("occurrence")) || (input.contains("[D]")) || (input.contains("[E]"))) {

            int n = 0;

            if (input.contains("(")) {n = input.indexOf('('); }
            else if (input.contains("/")) {n = input.indexOf('/'); }

            by = input.substring(n + 4).trim();

            knownPatterns = DateTimeList.knownPatterns;

            for (DateTimeFormatter pattern : knownPatterns) {
                try {
                    localDate = LocalDate.parse(by,pattern);
                    if (localDate == null) { localDateTime = LocalDateTime.parse(by, pattern);}
                } catch (DateTimeParseException ignored) {}
            }

            if (localDateTime == null) {
                throw new DukeException("Either incorrect format of input or no known date format found: " + by);
            }

            if (input.toLowerCase().contains("deadline"))           { TaskList.Deadline(input, localDateTime); change = true;}
            else if (input.toLowerCase().contains("event"))         { TaskList.Event(input,localDateTime); change = true;}
            else if ((input.toLowerCase().contains("occurrence")))  { TaskList.Occurrence(input, localDate); }
            else return localDateTime;
        }
        else throw new DukeException("Error: Please enter a valid task description\n");
        if (input.toLowerCase().contains("undo")) { Storage.writeToFile(false);} //Only Write to file but don't make change to History
        else if (change) { Storage.writeToFile(true);} //Write to file and add change to History
        return null;
    }
}