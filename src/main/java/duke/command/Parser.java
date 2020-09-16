package duke.command;
import duke.task.TaskList;
import duke.task.DukeException;

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
    protected static String input;
    protected static LocalDateTime localDateTime;
    protected static LocalDate localDate;
    protected static List<DateTimeFormatter> knownPatterns = new ArrayList<>();

    /**
     * This method is a constructor the Parser Class.
     * @param input Provides the user-input field to be parsed.
     */

    public Parser(String input) {
        Parser.input = input;
    }

    /**
     * This method takes in a user-input as a String, makes sense of the command and returns a command to the TaskList for execution or
     * returns a relevant Date and Time.
     * @param input Provides the user-input field to be parsed.
     * @return Returns a possible Date and Time field.
     * @throws DukeException Error if user-input is in a incompatible format.
     */

    public static LocalDateTime parse(String input) throws DukeException {
        if (input.trim().equalsIgnoreCase("bye"))         { TaskList.Bye();}
        else if (input.trim().equalsIgnoreCase("list"))   { TaskList.List();}
        else if (input.toLowerCase().contains("done"))               { TaskList.Done(input);}
        else if (input.toLowerCase().contains("delete"))             { TaskList.Delete(input);}
        else if (input.toLowerCase().contains("todo"))               { TaskList.Todo(input);}

        else if ((input.toLowerCase().contains("deadline")) || (input.toLowerCase().contains("event")) ||
                (input.toLowerCase().contains("occurrence")) || (input.contains("[D]")) || (input.contains("[E]"))) {

            int n = 0;
            if (input.contains("/")) {n = input.indexOf('/'); }
            else if (input.contains("(")) {n = input.indexOf('('); }

            by = input.substring(n + 4).trim();

            knownPatterns = DateTimeList.knownPatterns;

            for (DateTimeFormatter pattern : knownPatterns) {
                try {
                    localDate = LocalDate.parse(by,pattern);
                    localDateTime = LocalDateTime.parse(by, pattern);
                } catch (DateTimeParseException ignored) {}
            }

            if (localDateTime == null) {
                throw new DukeException("No known Date format found: " + by);
            }

            if (input.toLowerCase().contains("deadline"))           { TaskList.Deadline(input, localDateTime); }
            else if (input.toLowerCase().contains("event"))         { TaskList.Event(input, localDateTime); }
            else if ((input.toLowerCase().contains("occurrence")))  { TaskList.Occurrence(localDate); }
            else { return localDateTime; }
        }
        else throw new DukeException("Error: Please enter a valid task description\n");
        return null;
    }
}