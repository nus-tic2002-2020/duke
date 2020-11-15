package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    protected String[] args;
    protected List<String> outputs = new ArrayList<String>();

    public Command() {

    }

    public Command(String[] args) {
        this.args = args;
    }

    public abstract CommandType getType();

    public abstract boolean isExit();

    public abstract boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException;

    public List<String> getOutputs() {
        return outputs;
    }

    public static Command parse(String input) throws DukeException {
        Command command = null;
        String[] args = input.split(" ");
        String text = "";
        if (args.length > 0 && !args[0].isBlank()) {
            String commandText = args[0];

            switch (commandText) {
                case "list":
                    //printTasks();
                    command = new ListCommand();
                    break;
                case "done":
                    //doneTasks(args);
                    command = new DoneCommand(args);
                    break;
                case "delete":
                    //deleteTasks(args);
                    command = new DeleteCommand(args);
                    break;
                case "todo":
                    text = readInputParameter(args, null);
                    if (text.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    //addTask(new Todo(text));
                    command = new AddCommand(new Todo(text), args);
                    break;
                case "deadline":
                    text = readInputParameter(args, "/by");
                    if (text.isBlank()) {
                        throw new DukeException("The description of a deadline cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String by = readSlashParameter(args, "/by", true);
                    if (by.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    //addTask(new Deadline(text, by));
                    command = new AddCommand(new Deadline(text, by), args);
                    break;
                case "event":
                    text = readInputParameter(args, "/at");
                    if (text.isBlank()) {
                        throw new DukeException("The description of an event cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String at = readSlashParameter(args, "/at", true);
                    if (at.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String end = readSlashParameter(args, "/end", false);
                    //addTask(new Event(text, at));
                    Event event = new Event(text);
                    event.setDuration(at, end);
                    command = new AddCommand(event, args);
                    break;
                case "find":
                    command = new FindCommand(args);
                    break;
                case "schedule":
                    command = new ScheduleCommand(args);
                    break;
                case "bye":
                    //bye();
                    command = new ExitCommand();
                    break;
                default:
                    throw new DukeException("Sorli, but I dunno what that means :-(",
                            DukeException.DukeError.UNKNOWN_COMMAND);
            }
        }
        return command;
    }

    private static String readInputParameter(String[] args, String until) {
        String value = "";
        int index = args.length;
        if (until != null && !until.isBlank()) {
            int new_index = indexOf(args, until);
            index = new_index >= 0 ? new_index : index; // If new_index is negative, revert to use args length
        }
        for(int i = 1; i < args.length && i < index; i++) { // add strings between command to

            if (args[i].strip().startsWith("/")) { // Exit once hit next slash character
                break;
            }

            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static String readSlashParameter(String[] args, String param, boolean isRequired) throws DukeException {
        String value = "";
        int index = indexOf(args, param);

        if (index < 0) {
            if (isRequired) {
                throw new DukeException(String.format("Cannot find required %s in args. (index = %d)!", param, index),
                        DukeException.DukeError.MISSING_ARGUMENT);
            }
            return null; // Early exit if missing but not required
        }

        for(int i = index+1; i < args.length; i++) { // add strings between slash to end of args

            if (args[i].strip().startsWith("/")) { // Exit once hit next slash character
                break;
            }

            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static int indexOf(Object[] arr, Object o) {
        int index = -1;
        if (arr != null)  {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i].equals(o)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }


}
