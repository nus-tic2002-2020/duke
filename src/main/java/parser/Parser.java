package parser;//deals with making sense of the user command


import commands.*;
import exceptions.DukeException;


/**
 * This represents the parser. It parses the input from the ui. Depending on the input,
 * it will parse the respective command
 */

public class Parser {
    String parsedInput;

    public Parser(){
        this.parsedInput = "";
    }

    public static Command parse(String input) throws DukeException {
        String description = "";
        String secPart = "";
        String keyword;
        int option = 0;
        int index = 0;
        String commandWord = input.stripLeading().split(" ")[0];

        if(input.equals("blah")){
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if(input.strip().equals("")){
            throw new DukeException("☹ OOPS!!! You did not enter any command");
        }

        switch(commandWord){
            case "list":
                return new ListCommand();

            case "bye":
                return new ExitCommand();

            case "done":
                option = Integer.parseInt(input.replace("done", "").trim());
                return new DoneCommand(option);

            case "todo":
                description = input.replaceFirst("todo", "").stripLeading();
                if(input.isEmpty() == true) {
                    System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                }
                return new AddCommand("todo", description, secPart);

            case "event":
                index = input.indexOf("/at");
                secPart = input.substring(index);
                secPart = secPart.replaceFirst( "/at", "").stripLeading();

                description = input.substring(0,index - 1);
                description = description.replaceFirst("event", "").stripLeading();
                return new AddCommand("event", description, secPart);

            case "deadline":
                index = input.indexOf("/by");
                secPart = input.substring(index);
                secPart = secPart.replaceFirst( "/by", "").stripLeading();

                description = input.substring(0,index - 1);
                description = description.replaceFirst("deadline", "").stripLeading();
                return new AddCommand("deadline", description, secPart);

            case "delete":
                option = Integer.parseInt(input.replace("delete", "").trim());
                return new DeleteCommand(option);

            case "find":
                keyword = input.replaceFirst( "find", "").stripLeading();
                return new FindCommand(keyword);

            default:
                description = input;
                return new AddCommand("task", description, secPart);

        }
    }

}
