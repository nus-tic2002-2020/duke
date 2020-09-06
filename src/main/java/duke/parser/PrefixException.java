package duke.parser;

import duke.commands.CommandException;
import duke.ui.*;

public class PrefixException extends Exception {

    public PrefixException(){
        super();
    }

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
