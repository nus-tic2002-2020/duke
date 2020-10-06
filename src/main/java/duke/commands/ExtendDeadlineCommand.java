package duke.commands;

import duke.notes.task.Deadline;
import duke.parser.DateException;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.Date;

/**
 * An extension of the {@code DukeCommand} object that performs the edition of the {@code targetDate}
 * attribute of a {@code Deadline} object by a measure of milliseconds.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class ExtendDeadlineCommand extends DukeCommand implements DukeUI{

    //VARIABLES-----------------------------------------
    protected int targetNote;
    protected long millisecondsToExtend;
    protected Date newDate;
    protected Date oldDate;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code ExtendDeadlineCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param targetNote The {@code Note} whose {@code Date} object is to be extended.
     * @param millisecondsToExtend The number of milliseconds to extend the {@code Date} object by.
     */
    public ExtendDeadlineCommand(String cmdType, int targetNote, long millisecondsToExtend) {
        super(cmdType);
        this.targetNote = targetNote;
        this.millisecondsToExtend = millisecondsToExtend;
    }

    /**
     * This method initialises a {@code ExtendDeadlineCommand} object.
     */
    @SuppressWarnings("unused")
    public ExtendDeadlineCommand() { super(); }

    //METHODS-------------------------------------------
    /**
     * This method executes the function of the {@code ExtendDeadlineCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     * @exception CommandException If there are errors in the command input.
     * @exception IndexOutOfBoundsException If the note specified does not exist.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, IndexOutOfBoundsException, DateException {

        for(int i=0; i<dukeNotes.getNotes().size(); i++) {
            if(dukeNotes.getNotes().get(i).getSerialNum() == this.targetNote) {

                DukeUI.printDivider();
                if(!(dukeNotes.getNotes().get(i) instanceof Deadline)) {
                    System.out.println("    The note selected is not a task with a deadline.");
                    System.out.println("    The deadline shouldn't be edited anymore.");
                } else if(dukeNotes.getNotes().get(i).getIsDone()) {
                    System.out.println("    The task had already been completed.");
                    System.out.println("    The deadline shouldn't be edited anymore.");
                } else {
                    System.out.println("    Deadline of Note #" + this.targetNote + ":");
                    dukeNotes.getNotes().get(i).printList();

                    this.oldDate = ((Deadline) dukeNotes.getNotes().get(i)).getTargetDate();
                    this.newDate = new Date(this.oldDate.getTime() + millisecondsToExtend);
                    ((Deadline) dukeNotes.getNotes().get(i)).setTargetDate(this.newDate);

                    System.out.println("    extended from...");
                    DukeUI.commandWrap(NOTE_TIME.format(this.oldDate), 66);
                    System.out.println("    to...");
                    DukeUI.commandWrap(NOTE_TIME.format(this.newDate), 66);
                    DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                    DukeUI.suggestListNotes();
                }
                DukeUI.printDivider();
                break;
            }
        }
    }
}
