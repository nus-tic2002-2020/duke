package duke.commands;

import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An extension of the {@code DukeCommand} object that performs the deletion of existing {@code Note} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DeleteCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code DeleteCommand} object.
     *
     * @param inputs The accompanying attributes of the command as provided by the user.
     */
    public DeleteCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    /**
     * This method initialises a {@code DeleteCommand} object.
     */
    public DeleteCommand() {
        super();
    }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code DeleteCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {


        if (CmdType.getKey(this.cmdType).toString().equals("DELETE")) {

            ArrayList<Integer> notes = new ArrayList<Integer>();
            for(int i=1; i<inputs.size(); i++) {
                int toDelete = Integer.parseInt(inputs.get(i));
                if(toDelete > dukeNotes.getNotes().size()) {
                    throw new IndexOutOfBoundsException();
                } else {
                    notes.add(toDelete);
                }
            }

            String confirmDelete;
            Scanner delete = new Scanner(System.in);

            DukeUI.printDivider();
            System.out.println("\tAre you sure you want to delete the following notes?");
            for (int note : notes) {
                for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                    if (dukeNotes.getNotes().get(i).getSerialNum() == note) {
                        dukeNotes.getNotes().get(i).printList();
                    }
                }
            }
            System.out.println("\tData would be lost forever.");
            DukeUI.askForConfirmation();
            DukeUI.printDivider();

            confirmDelete = delete.nextLine();

            if (confirmDelete.equals("Y")) {

                DukeUI.printDivider();
                for (int note : notes) {
                    for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                        if (dukeNotes.getNotes().get(i).getSerialNum() == note) {
                            dukeNotes.getNotes().get(i).deleteExistingNote();
                            dukeNotes.getNotes().remove(i);
                            break;
                        }
                    }
                }
                System.out.println("\n\tDeletion(s) completed...");

                if(dukeNotes.getNotes().size() == 0){
                    System.out.println("\t...there are no notes on your list.");
                } else {
                    System.out.println("\t...renumbering the remaining note(s)...");
                    for (int i = 0; i < dukeNotes.getNotes().size(); i++) {
                        System.out.print("\t#");
                        System.out.print(String.format("%3d", dukeNotes.getNotes().get(i).getSerialNum()));
                        System.out.print("\t >>> ");
                        dukeNotes.getNotes().get(i).setSerialNum(i + 1);
                        System.out.print("\t#");
                        System.out.print(String.format("%3d", dukeNotes.getNotes().get(i).getSerialNum()));
                        System.out.print("\n");
                    }
                    System.out.println("\tThe remaining notes have been renumbered!");
                }
                System.out.println("");

                DukeUI.printCompleted();
                DukeUI.printOutstanding();
                DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                DukeUI.suggestListNotes();
            } else {
                DukeUI.printDivider();
                System.out.println("\tDeletion aborted.");
            }
            DukeUI.printDivider();
        }
    }

}
