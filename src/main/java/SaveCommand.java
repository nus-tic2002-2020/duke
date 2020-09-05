import java.io.IOException;
import java.util.ArrayList;

public class SaveCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String cmdType;
    private boolean autoSave;

    //CONSTRUCTORS--------------------------------------
    public SaveCommand(ArrayList<String> inputs) {
        super(inputs);
        this.cmdType = inputs.get(0);
    }

    public SaveCommand() {
        super();
        this.autoSave = true;
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IOException {

        DukeUI.printDivider();
        if(CmdType.getKey(cmdType).toString().equals("SAVENOTES")) {
            dukeStorage.writeToFile(dukeNotes.getNotes());
            System.out.println("\tSave operation completed!");
        } else {
            System.out.println("\tSave operation cancelled!");
        }
        DukeUI.printDivider();
    }

    public boolean autoSave(DukeList dukeNotes, DukeStorage dukeStorage) {

        try {
            if (autoSave) {
                dukeStorage.writeToFile(dukeNotes.getNotes());
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
