package parser;//deals with making sense of the user command


import commands.*;
import exceptions.DukeException;


/**
 * This represents the parser. It parses the input from the ui. Depending on the input,
 * it will parse the respective command
 */

public class Parser {

    /**
     * Constructor for Parser
     */
    public Parser(){
    }

    /**
     *
     *
     * @param
     * @return Command
     * @throws DukeException, NumberFormatException
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

        if(input.equals("bye")){
            return new ExitCommand();
        }

        switch(commandWord){
            case "list":
                return new ListCommand();

            case "done":
                option = Integer.parseInt(input.replaceFirst("done", "").trim());
                return new ChangeDoneCommand(option, true);

            case "undone":
                option = Integer.parseInt(input.replaceFirst("undone", "").trim());
                return new ChangeDoneCommand(option,false);

            case "/clear":
                return new ClearListCommand();

            case "/help":
                return new HelpCommand();

            case "todo":
                description = input.replaceFirst("todo", "").stripLeading();
                return new AddCommand("todo", description, secPart);

            case "event":
                try{
                    index = input.indexOf("/at");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst( "/at", "").stripLeading();

                    description = input.substring(0,index);
                    description = description.replaceFirst("event", "").stripLeading();
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Incomplete Command for Add Event, will autofill");
                }finally {
                    return new AddCommand("event", description, secPart);
                }

            case "deadline":
                try{
                    index = input.indexOf("/by");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst( "/by", "").stripLeading();
                    description = input.substring(0,index);
                    description = description.replaceFirst("deadline", "").stripLeading();
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Incomplete Command for Add Deadline, will autofill");
                }finally {
                    return new AddCommand("deadline", description, secPart);
                }

            case "delete":
                option = Integer.parseInt(input.replace("delete", "").trim());
                return new DeleteCommand(option);

            case "find":
                keyword = input.replaceFirst( "find", "").stripLeading();
                return new FindCommand(keyword);

            case "reschedule":
                index = input.indexOf("/new");
                secPart = input.substring(index);
                secPart = secPart.replaceFirst( "/new", "").stripLeading();

                option = Integer.parseInt
                        (input.substring(0,index).replaceFirst("reschedule", "").trim());
                return new RescheduleCommand(option,secPart);

            default:
                description = input;
                return new AddCommand("task", description, secPart);

        }
    }

}
