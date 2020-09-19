package duke.parser;

import duke.commands.CommandException;
import duke.ui.DukeUI;

/**
 * An extension of the Exception object that addresses exceptions in user input.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class PrefixException extends Exception {

    /**
     * This method constructs a {@code PrefixException} object.
     */
    public PrefixException(){
        super();
    }

    /**
     * This method prints an explanation specifying the user input in question and reason behind the exception.
     *
     * @param input The user input that caused the exception.
     */
    public void printExplanation(String input) throws CommandException {

        DukeUI.printDivider();
        System.out.println("\tI don't understand what you mean by...\n");
        DukeUI.commandWrap(input, 66);
        System.out.println("\tI'd love to hold a conversation, but I can't, yet.");
        System.out.println("\tPlease include the required command prefix to activate a command.");
        DukeUI.suggestCommands();
        DukeUI.printDivider();
    }
}
