import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class NewNoteCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected String noteType;

    //CONSTRUCTORS--------------------------------------
    public NewNoteCommand(ArrayList<String> inputs) {
        super(inputs);
        this.noteType = inputs.get(0);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, ParseException {

        try {
            DukeUI.printDivider();
            Date addDate = new Date();
            int nextSerialNum = dukeNotes.getNotes().size() + 1;
            Task note = null;
            switch (NoteType.getKey(noteType).toString()) {
                case "BILL" -> {
                    String description = inputs.get(1);
                    Date targetDate = INPUT_DATE.parse(inputs.get(2));
                    if(targetDate.compareTo(addDate) < 0 ){
                        throw new DateException(targetDate, "TargetDate");
                    }
                    double itemBudget = Double.parseDouble(inputs.get(3));
                    note = new Bill(nextSerialNum, description, targetDate, itemBudget, addDate);
                    DukeUI.addConfirm("Bill");
                }
                case "DEADLINE" -> {
                    String description = inputs.get(1);
                    Date targetDate = INPUT_DATE.parse(inputs.get(2));
                    if(targetDate.compareTo(addDate) < 0 ){
                        throw new DateException(targetDate, "TargetDate");
                    }
                    note = new Deadline(nextSerialNum, description, targetDate, addDate);
                    DukeUI.addConfirm("Deadline");
                }
                case "EVENT" -> {
                    String description = inputs.get(1);
                    Date startDate = INPUT_DATE.parse(inputs.get(2));
                    if(startDate.compareTo(addDate) < 0 ){
                        throw new DateException(startDate, "StartB4Now");
                    }
                    Date endDate = INPUT_DATE.parse(inputs.get(3));
                    if(endDate.compareTo(startDate) < 0 ){
                        throw new DateException(endDate, "EndB4Start");
                    }
                    note = new Event(nextSerialNum, description, startDate, endDate, addDate);
                    DukeUI.addConfirm("Event");
                }
                case "SHOPLIST" -> {
                    String description = inputs.get(1);
                    double itemBudget = Double.parseDouble(inputs.get(2));
                    note = new Shoplist(nextSerialNum, description, itemBudget, addDate);
                    DukeUI.addConfirm("Shoplist");
                }
                case "TODO" -> {
                    String description = inputs.get(1);
                    note = new Todo(nextSerialNum, description, addDate);
                    DukeUI.addConfirm("Todo");
                }
            }
            dukeNotes.getNotes().add(note);
            dukeNotes.getNotes().get(nextSerialNum-1).printList();
            DukeUI.printOutstanding();
            DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
            DukeUI.suggestListNotes();
            DukeUI.printDivider();

        } catch (DateException e) {
            e.printExplanation();
        }
    }


    //GET STATEMENTS------------------------------------



    //ABSTRACT METHODS----------------------------------


}
