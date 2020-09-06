package duke.commands;

import duke.notes.*;
import duke.notes.event.*;
import duke.notes.todo.*;
import duke.storage.*;
import duke.ui.*;
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
            ArrayList<Task> notes = new ArrayList<Task>();
            switch (NoteType.getKey(noteType).toString()) {
                case "BILL" -> {
                    String description = inputs.get(1);
                    Date targetDate = INPUT_DATE.parse(inputs.get(2));
                    if(targetDate.compareTo(addDate) < 0 ){
                        throw new DateException(targetDate, "TargetDate");
                    }
                    double itemBudget = Double.parseDouble(inputs.get(3));
                    Task note1 = new Bill(nextSerialNum, description, targetDate, itemBudget, addDate);
                    notes.add(note1);

                }
                case "BIRTHDAY" -> {
                    String description = inputs.get(1);
                    String giftDescription = "Birthday gift for " + description;
                    Date startDate = INPUT_DATE.parse(inputs.get(2));
                    if(startDate.compareTo(addDate) < 0 ){
                        throw new DateException(startDate, "StartB4Now");
                    }
                    Date endDate = INPUT_DATE.parse(inputs.get(3));
                    if(endDate.compareTo(startDate) < 0 ){
                        throw new DateException(endDate, "EndB4Start");
                    }
                    double itemBudget = Double.parseDouble(inputs.get(4));

                    Task note1 = new Shoplist(nextSerialNum, giftDescription, itemBudget, addDate);
                    notes.add(note1);
                    Task note2 = new Birthday(nextSerialNum+1, description, startDate, endDate, addDate);
                    notes.add(note2);
                }
                case "DEADLINE" -> {
                    String description = inputs.get(1);
                    Date targetDate = INPUT_DATE.parse(inputs.get(2));
                    if(targetDate.compareTo(addDate) < 0 ){
                        throw new DateException(targetDate, "TargetDate");
                    }
                    Task note1 = new Deadline(nextSerialNum, description, targetDate, addDate);
                    notes.add(note1);
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
                    Task note1 = new Event(nextSerialNum, description, startDate, endDate, addDate);
                    notes.add(note1);
                }
                case "SHOPLIST" -> {
                    String description = inputs.get(1);
                    double itemBudget = Double.parseDouble(inputs.get(2));
                    Task note1 = new Shoplist(nextSerialNum, description, itemBudget, addDate);
                    notes.add(note1);
                }
                case "TODO" -> {
                    String description = inputs.get(1);
                    Task note1 = new Todo(nextSerialNum, description, addDate);
                    notes.add(note1);
                }
                case "WEDDING" -> {
                    String description = inputs.get(1);
                    Date startDate = INPUT_DATE.parse(inputs.get(2));
                    if(startDate.compareTo(addDate) < 0 ){
                        throw new DateException(startDate, "StartB4Now");
                    }
                    Date endDate = INPUT_DATE.parse(inputs.get(3));
                    if(endDate.compareTo(startDate) < 0 ){
                        throw new DateException(endDate, "EndB4Start");
                    }
                    double itemBudget = Double.parseDouble(inputs.get(4));

                    Task note1 = new Wedding(nextSerialNum, description, startDate, endDate, itemBudget, addDate);
                    notes.add(note1);
                }
            }
            for(int i=0; i < notes.size(); i++) {
                DukeUI.addConfirm(notes.get(i).getObjectClass());
                dukeNotes.getNotes().add(notes.get(i));
                dukeNotes.getNotes().get(nextSerialNum + i - 1).printList();
            }
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
