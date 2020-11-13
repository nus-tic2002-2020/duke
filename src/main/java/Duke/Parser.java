package Duke;

public class Parser {
    public Parser() {
    }
    public static Command parse(String input){
        String argStr = " ";
        String[] fullStr = input.split(" ", 2);
        String commandStr = fullStr[0];
        //System.out.println("commandStr: "+commandStr);
        if(fullStr.length > 1) {
            argStr = fullStr[1];
            //System.out.println("argStr: " + argStr);
        }
            switch (commandStr) {
                case "list":
                    return new ListCommand();
                case "done":
                    return new DoneCommand(argStr);
                case "delete":
                    return new DeleteCommand(argStr);
                case "todo":
                    return new TodoCommand(argStr);
                case "deadline":
                    return new DeadlineCommand(argStr);
                case "event":
                    return new EventCommand(argStr);
                case "bye":
                    return new ByeCommand();
                case "find":
                    return new FindCommand(argStr);
                case "priority":
                    return new PriorityCommand(argStr);
                case "findp":
                    return new FindPriorityCommand(argStr);
                case "help":
                    return new HelpCommand();
                default:
                    return new WrongCommand(commandStr);
            }
    }
}