package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UndoLastCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    public UndoLastCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IOException, ParseException {

        DukeUI.printDivider();
        if(CmdType.getKey(this.cmdType).toString().equals("UNDOLAST")) {
            String confirmUndo;
            Scanner undoDuke = new Scanner(System.in);

            DukeUI.printCompleted();
            DukeUI.printOutstanding();
            System.out.println("\tAre you sure you want to undo the last save?");
            System.out.println("\tAll unsaved data would be lost.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            confirmUndo = undoDuke.nextLine();

            DukeUI.printDivider();
            if(confirmUndo.equals("Y")) {

                switch (dukeStorage.revertToLastSave(dukeNotes)) {
                    case -1 -> {
                        System.out.println("\tI have undone the maximum number of times.\n");
                    }
                    case 0 -> {
                        System.out.println("\tI have undone the last save operation.");
                        System.out.println("\tYou have no more opportunities to undo.\n");
                    }
                    case 1 -> {
                        System.out.println("\tI have undone the last save operation.");
                        System.out.println("\tYou have 1 last opportunity to undo.\n");
                    }
                    case 2 -> {
                        System.out.println("\tI have undone the last save operation.");
                        System.out.println("\tYou have 2 more opportunities to undo.\n");
                    }
                }

            } else {
                System.out.println("\tUndo last save aborted!");
            }
        }
        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        DukeUI.printDivider();
    }
}
