/****************************************************************
 *
 *         This class takes in the full commandline and searches
 *         for its respective command class to be called.
 *
 * @param commandLine is the user input passed in from Duke.java.
 * @return Command executions based on the user input.
 * *************************************************************/

public class Parser {
    public static Command parse(String commandLine) throws DukeException{
        switch(commandLine.split(" ")[0]){
            case "todo":
                return new TodoCommand(false,commandLine);
            case "deadline":
                return new DeadlineCommand(false, commandLine);
            case "event":
                return new EventCommand(false, commandLine);
            case "bye":
                return new Bye(true, "");
            case "list":
                return new ListCommand(false, commandLine);
            case "delete":
                return new DeleteCommand(false, commandLine);
            case "done":
                return new DoneCommand(false, commandLine);
            case "snooze":
                return new SnoozeCommand(false,commandLine);
            case "find":
                return new FindCommand(false,commandLine);
            case "help":
                return new HelpCommand(false,commandLine);
            default:
                throw new DukeException("I'm Sorry, but I don't know what that means :-(");
        }
    }
}
