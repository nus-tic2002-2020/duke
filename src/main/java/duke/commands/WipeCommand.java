package duke.commands;

import duke.storage.*;
import duke.ui.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WipeCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String cmdType;

    //CONSTRUCTORS--------------------------------------
    public WipeCommand(ArrayList<String> inputs) {
        super(inputs);
        this.cmdType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        DukeUI.printDivider();
        if(CmdType.getKey(cmdType).toString().equals("WIPEDUKE")) {
            String confirmWipe;
            Scanner wipeDuke = new Scanner(System.in);

            DukeUI.printOutstanding();
            System.out.println("\tAre you sure you want to wipe Duke's Memory?");
            System.out.println("\tAll data, including saved files, would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            confirmWipe = wipeDuke.nextLine();

            if(confirmWipe.equals("Y")) {

                ArrayList<String> dukeMemory = new ArrayList<String>();
                dukeMemory.add("DELETE");
                for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                    int serialNum = dukeNotes.getNotes().get(i).getSerialNum();
                    dukeMemory.add(String.valueOf(serialNum));
                }

                DeleteCommand wipeMemory = new DeleteCommand(dukeMemory);
                wipeMemory.execute(dukeNotes, dukeStorage);

                File newFile = new File(dukeStorage.getPath());
                dukeStorage.setFile(newFile);

                System.out.println("\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:\n\t:");
                DukeUI.printDivider();
                System.out.println("\tGood Bye! I won't remember who you are.");

            } else {
                System.out.println("\tYay! Thank you for keeping me!");

            }
        }
        DukeUI.printDivider();
    }

}
