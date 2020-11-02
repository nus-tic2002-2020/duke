package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskManager;
import duke.task.Todo;

public abstract class Command {

    public abstract CommandType getType();

    public abstract boolean isExit();

    public abstract boolean execute(TaskManager taskManager, Ui ui, Storage storage);

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

                    break;
                case "delete":
                    //deleteTasks(args);
                    break;
                case "todo":
                    text = readInputParameter(args, null);
                    if (text.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    //addTask(new Todo(text));
                    break;
                case "deadline":
                    text = readInputParameter(args, "/by");
                    if (text.isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String by = readSlashParameter(args, "/by");
                    if (by.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    //addTask(new Deadline(text, by));
                    break;
                case "event":
                    text = readInputParameter(args, "/at");
                    if (text.isBlank()) {
                        throw new DukeException("The description of an event cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    String at = readSlashParameter(args, "/at");
                    if (at.isBlank()) {
                        throw new DukeException("The argument for /by cannot be empty.",
                                DukeException.DukeError.MISSING_ARGUMENT);
                    }
                    //addTask(new Event(text, at));
                    break;
                case "bye":
                    //bye();

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
        for(int i = 1; i < args.length && i < index; i++) { // add strings between command to until
            value +=  args[i] + " ";
        }
        return value.trim();
    }

    private static String readSlashParameter(String[] args, String param) throws DukeException {
        String value = "";
        int index = indexOf(args, param);
        if (index < 0) {
            throw new DukeException(String.format("Cannot find required %s in args. (index = %d)!", param, index),
                    DukeException.DukeError.MISSING_ARGUMENT);
        }
        for(int i = index+1; i < args.length; i++) { // add strings between slash to end of args
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
