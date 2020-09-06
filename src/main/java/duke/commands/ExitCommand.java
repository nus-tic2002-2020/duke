package duke.commands;

import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExitCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String cmdType;

    //CONSTRUCTORS--------------------------------------
    public ExitCommand(ArrayList<String> inputs) {
        super(inputs);
        this.cmdType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        DukeUI.printDivider();
        if(CmdType.getKey(cmdType).toString().equals("EXITDUKE")) {
            String confirmQuit;
            Scanner quitDuke = new Scanner(System.in);

            DukeUI.printOutstanding();
            System.out.println("\tAre you sure you want to exit Duke?");
            System.out.println("\tAll unsaved data would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            confirmQuit = quitDuke.nextLine();

            DukeUI.printDivider();
            if(confirmQuit.equals("Y")) {
                System.out.println("\tGood Bye! Hope to see you again soon!");
                this.confirmExit = true;

            } else {
                System.out.println("\tYay! Thanks for staying!");
                this.confirmExit = false;
            }
        }
        DukeUI.printDivider();
    }

}

