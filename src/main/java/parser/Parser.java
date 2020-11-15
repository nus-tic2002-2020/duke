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
    public Parser() {
    }

    /**
     * This parses the input gotten from the ui. This method will parse and return the appropriate Command.
     *
     * @param input
     * @return Command
     * @throws DukeException When the input for option number in various Commands is not a number or when the input
     *                       for Adding a new Task is incomplete.
     */
    public static Command parse(String input) throws DukeException {
        String description = "";
        String secPart = "";
        String keyword;
        int option = 0;
        int index = 0;
        String commandWord = input.stripLeading().split(" ")[0];

        assert !(commandWord.equals(null)) : "commandWord should not be null";

        if (input.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (input.strip().equals("")) {
            throw new DukeException("☹ OOPS!!! You did not enter any command");
        }

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        switch (commandWord) {
            case "list":
                return new ListCommand();

            case "done":
                try {
                    option = Integer.parseInt(input.replaceFirst("done", "").trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("Error: Invalid option number, your input is not a number");
                }

                return new ChangeDoneCommand(option, true);

            case "undone":
                try {
                    option = Integer.parseInt(input.replaceFirst("undone", "").trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("Error: Invalid option number, your input is not a number");
                }

                return new ChangeDoneCommand(option, false);

            case "/clear":
                return new ClearListCommand();

            case "/help":
                return new HelpCommand();

            case "todo":
                description = input.replaceFirst("todo", "").trim();
                return new AddCommand("todo", description, secPart);

            case "event":
                try {
                    index = input.indexOf("/at");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst("/at", "").trim();

                    description = input.substring(0, index);
                    description = description.replaceFirst("event", "").trim();
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Error: Incomplete Command for Add Event");
                }
                return new AddCommand("event", description, secPart);


            case "deadline":
                try {
                    index = input.indexOf("/by");
                    secPart = input.substring(index);
                    secPart = secPart.replaceFirst("/by", "").trim();
                    description = input.substring(0, index);
                    description = description.replaceFirst("deadline", "").trim();
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Error: Incomplete Command for Add Deadline.");
                }

                return new AddCommand("deadline", description, secPart);


            case "delete":
                try {
                    option = Integer.parseInt(input.replace("delete", "").trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("Error: Invalid option number, your input is not a number");
                }
                return new DeleteCommand(option);

            case "find":
                keyword = input.replaceFirst("find", "").trim();
                return new FindCommand(keyword);

            case "reschedule":
                index = input.indexOf("/new");
                secPart = input.substring(index);
                secPart = secPart.replaceFirst("/new", "").trim();

                try {
                    option = Integer.parseInt
                            (input.substring(0, index).replaceFirst("reschedule", "").trim());
                } catch (NumberFormatException e) {
                    throw new DukeException("Error: Invalid option number, your input is not a number");
                }

                return new RescheduleCommand(option, secPart);

            default:
                description = input;
                return new AddCommand("task", description, secPart);

        }
    }

}
