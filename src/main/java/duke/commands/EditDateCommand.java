package duke.commands;

import duke.notes.event.Event;
import duke.notes.todo.Deadline;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.Date;

/**
 * An extension of the {@code DukeCommand} object that performs the edition of {@code Date} objects in existing notes.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class EditDateCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    protected int targetNote;
    protected String dateToChange;
    protected Date newDate;
    protected Date oldDate;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs an {@code EditDateCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param targetNote The {@code Note} whose {@code Date} object is to be edited.
     * @param dateToChange The type of {@code Date} belonging to the {@code Note} to be edited.
     * @param newDate The new {@code Date} object that is to replace the old {@code Date} object.
     */
    public EditDateCommand(String cmdType, int targetNote, String dateToChange, Date newDate) {
        super(cmdType);
        this.targetNote = targetNote;
        this.dateToChange = dateToChange;
        this.newDate = newDate;
    }

    /**
     * This method initialises a {@code EditDateCommand} object.
     */
    public EditDateCommand() { super(); }


    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code EditDateCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     * @exception DateException If there are errors in the formats or substance of {@code Date} objects.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException, DateException {

        for(int i=0; i<dukeNotes.getNotes().size(); i++) {
            if(dukeNotes.getNotes().get(i).getSerialNum() == this.targetNote) {

                switch (this.dateToChange) {
                    case "target":
                        if(dukeNotes.getNotes().get(i) instanceof Deadline) {
                            if (dukeNotes.getNotes().get(i).getIsDone()) {
                                DukeUI.printDivider();
                                System.out.println("    The task had already been completed.");
                                System.out.println("    The date shouldn't be edited anymore.");
                            } else {
                                this.oldDate = ((Deadline) dukeNotes.getNotes().get(i)).getTargetDate();
                                ((Deadline) dukeNotes.getNotes().get(i)).setTargetDate(this.newDate);

                                DukeUI.printDivider();
                                System.out.println("    Target Date of Note #" + this.targetNote + " changed from...");
                                DukeUI.commandWrap(TASK_TIME.format(this.oldDate), 66);
                                System.out.println("    to...");
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
                                System.out.println("    The event had already concluded.");
                                System.out.println("    The date shouldn't be edited anymore.");
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
                                System.out.println("    Start and End Date of Note #" + this.targetNote +
                                        " changed from...");
                                DukeUI.commandWrap(TASK_TIME.format(this.oldDate) + " ...and... " +
                                        TASK_TIME.format(oldEnd), 66);
                                System.out.println("    to...");
                                DukeUI.commandWrap(TASK_TIME.format(this.newDate) + " ...and... " +
                                        TASK_TIME.format(newEnd), 66);
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
                                System.out.println("    The event had already concluded.");
                                System.out.println("    The date shouldn't be edited anymore.");
                            } else {
                                this.oldDate = ((Event) dukeNotes.getNotes().get(i)).getEndDate();
                                ((Event) dukeNotes.getNotes().get(i)).setEndDate(this.newDate);

                                DukeUI.printDivider();
                                System.out.println("    Start Date of Note #" + this.targetNote + " changed from...");
                                DukeUI.commandWrap(TASK_TIME.format(this.oldDate), 66);
                                System.out.println("    to...");
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
