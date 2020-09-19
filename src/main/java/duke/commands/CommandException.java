package duke.commands;

import duke.ui.DukeUI;

/**
 * An extension of the {@code Exception} object that addresses exceptions in command execution.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class CommandException extends Exception {

    String message;

    /**
     * This method constructs a {@code CommandException} object.
     *
     * @param message The message explaining the reason behind the exception.
     */
    public CommandException(String message){
        super();
        this.message = message;
    }

    /**
     * This method initialises a {@code CommandException} object.
     */
    public CommandException(){
        super();
    }

    /**
     * This method returns the message detailing the reason behind the exception.
     *
     * @return String The message detailing the reason behind the exception.
     */
    public String getMessage(){
        return message;
    }

    /**
     * This method prints an explanation specifying the command in question and reason behind the exception.
     *
     * @param input The command that caused the exception.
     * @exception CommandException If there are errors in the command input.
     */
    public void printExplanation(String input)
            throws CommandException {

        DukeUI.printDivider();
        System.out.println("\tI don't understand what you meant by...\n");
        DukeUI.commandWrap(input, 66);
        System.out.println("\t" + this.message);
        DukeUI.suggestCommands();
        DukeUI.printDivider();
    }
}
