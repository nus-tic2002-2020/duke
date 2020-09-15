package duke.commands;

import duke.notes.event.*;
import duke.notes.todo.*;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class EditDateCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected Date oldDate;
    protected Date newDate;
    protected String dateToChange;
    protected String targetNote;

    //CONSTRUCTORS--------------------------------------
    public EditDateCommand(ArrayList<String> inputs) throws ParseException {
        super(inputs);
        this.targetNote = inputs.get(1);
        this.dateToChange = inputs.get(2);
        this.newDate = INPUT_TIME.parse(inputs.get(3));
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException, DateException {

        Date now = new Date();
        if(CmdType.getKey(this.cmdType).toString().equals("EDITDATE")) {

            int targetSerialNum = Integer.parseInt(this.targetNote);
            for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                if(dukeNotes.getNotes().get(i).getSerialNum() == targetSerialNum) {

                    switch (this.dateToChange) {
                        case "target":
                            if(dukeNotes.getNotes().get(i) instanceof Deadline) {
                                if (dukeNotes.getNotes().get(i).getIsDone()) {
                                    DukeUI.printDivider();
                                    System.out.println("\tThe task had already been completed.");
                                    System.out.println("\tThe date shouldn't be edited anymore.");
                                } else {
                                    this.oldDate = ((Deadline) dukeNotes.getNotes().get(i)).getTargetDate();
                                    ((Deadline) dukeNotes.getNotes().get(i)).setTargetDate(this.newDate);

                                    DukeUI.printDivider();
                                    System.out.println("\tTarget Date of Note #" + this.targetNote + " changed from...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.oldDate), 66);
                                    System.out.println("\tto...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.newDate), 66);
                                    DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                                    DukeUI.suggestListNotes();
                                }
                            } else {
                                throw new DateException(this.newDate, "NoTarget");
                            }
                            break;
                        case "start":
                            if(dukeNotes.getNotes().get(i) instanceof Event) {
                                if (dukeNotes.getNotes().get(i).getIsDone()) {
                                    DukeUI.printDivider();
                                    System.out.println("\tThe event had already concluded.");
                                    System.out.println("\tThe date shouldn't be edited anymore.");
                                } else {
                                    this.oldDate = ((Event) dukeNotes.getNotes().get(i)).getStartDate();
                                    Date oldEnd = ((Event) dukeNotes.getNotes().get(i)).getEndDate();
                                    long durationInMS = ((Event) dukeNotes.getNotes().get(i)).getDurationMinutes() * 60000;
                                    Date newEnd = new Date(this.newDate.getTime() + durationInMS);

                                    if(this.newDate.after(this.oldDate)) {
                                        ((Event) dukeNotes.getNotes().get(i)).setEndDate(newEnd);
                                        ((Event) dukeNotes.getNotes().get(i)).setStartDate(this.newDate);
                                    } else {
                                        ((Event) dukeNotes.getNotes().get(i)).setStartDate(this.newDate);
                                        ((Event) dukeNotes.getNotes().get(i)).setEndDate(newEnd);
                                    }

                                    DukeUI.printDivider();
                                    System.out.println("\tStart and End Date of Note #" + this.targetNote + " changed from...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.oldDate) + " ...and... " + TASK_TIME.format(oldEnd), 66);
                                    System.out.println("\tto...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.newDate) + " ...and... " + TASK_TIME.format(newEnd), 66);
                                    DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                                    DukeUI.suggestListNotes();
                                }
                            } else {
                                throw new DateException(this.newDate, "NoStart");
                            }
                            break;
                        case "end":
                            if(dukeNotes.getNotes().get(i) instanceof Event) {
                                if (dukeNotes.getNotes().get(i).getIsDone()) {
                                    DukeUI.printDivider();
                                    System.out.println("\tThe event had already concluded.");
                                    System.out.println("\tThe date shouldn't be edited anymore.");
                                } else {
                                    this.oldDate = ((Event) dukeNotes.getNotes().get(i)).getEndDate();
                                    ((Event) dukeNotes.getNotes().get(i)).setEndDate(this.newDate);

                                    DukeUI.printDivider();
                                    System.out.println("\tStart Date of Note #" + this.targetNote + " changed from...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.oldDate), 66);
                                    System.out.println("\tto...");
                                    DukeUI.commandWrap(TASK_TIME.format(this.newDate), 66);
                                    DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                                    DukeUI.suggestListNotes();
                                }
                            } else {
                                throw new DateException(this.newDate, "NoEnd");
                            }
                            break;
                        default:
                            throw new CommandException("The type of date you are trying to edit does not exist.");
                    }
                    DukeUI.printDivider();
                    break;
                }
            }
        }
    }
}
