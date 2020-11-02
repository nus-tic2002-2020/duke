/*************************************************************
 *
 *         Public class by factionsypho
 *
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
            default:
                throw new DukeException("I'm Sorry, but I don't know what that means :-(");
        }
    }
}
