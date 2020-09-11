package duke.command;
import duke.task.TaskList;
import duke.task.DukeException;

public class Parser {
    protected static String input;

    public Parser(String input) {
        Parser.input = input;
    }

    public static void parse(String input) throws DukeException {
        if (input.trim().equalsIgnoreCase("bye")) {
            TaskList.Bye();
        } else if (input.trim().equalsIgnoreCase("list")) {
            TaskList.List();
        } else if (input.toLowerCase().contains("done")) {
            TaskList.Done(input);
        } else if (input.toLowerCase().contains("delete")) {
            TaskList.Delete(input);
        } else if (input.toLowerCase().contains("todo")) {
            TaskList.Todo(input);
        } else if (input.toLowerCase().contains("deadline")) {
            TaskList.Deadline(input);
        } else if (input.toLowerCase().contains("event")) {
            TaskList.Event(input);
        } else {
            throw new DukeException("Error: Please enter a valid task such as todo / deadline / event\n");
        }
    }
}