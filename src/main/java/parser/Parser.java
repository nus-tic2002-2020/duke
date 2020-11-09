package parser;//deals with making sense of the user command


import commands.*;
import exceptions.DukeException;


/**
 * This represents the parser. It parses the input from the ui. Depending on the input,
 * it will parse the respective command
 */

public class Parser {
    String parsedInput;

    /**
     *
     *
     * @param
     * @return
     * @throws
     */
    public Parser(){
        this.parsedInput = "";
    }

    /**
     *
     *
     * @param
     * @return
     * @throws
     */
    public static Command parse(String input) throws DukeException, NumberFormatException {
        String description = "";
        String secPart = "";
        String keyword;
        int option = 0;
        int index = 0;
        String commandWord = input.stripLeading().split(" ")[0];

        assert !(commandWord.equals(null)) : "commandWord should not be null";

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
                option = Integer.parseInt(input.replaceFirst("done", "").trim());
                return new ChangeDoneCommand(option, true);

            case "undone":
                option = Integer.parseInt(input.replaceFirst("undone", "").trim());
                return new ChangeDoneCommand(option,false);

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

            case "reschedule":
                index = input.indexOf("/by");
                secPart = input.substring(index);
                secPart = secPart.replaceFirst( "/by", "").stripLeading();

                option = Integer.parseInt
                        (input.substring(0,index - 1).replaceFirst("reschedule", "").trim());
                return new RescheduleCommand(option,secPart);

            default:
                description = input;
                return new AddCommand("task", description, secPart);

        }
    }

}
