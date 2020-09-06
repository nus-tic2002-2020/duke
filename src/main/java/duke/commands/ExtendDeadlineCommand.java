package duke.commands;

import duke.notes.todo.*;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;
import java.util.Date;

public class ExtendDeadlineCommand extends DukeCommand implements DukeUI{

    //VARIABLES-----------------------------------------
    protected Date newDate;
    protected Date oldDate;
    protected String extendByDays;
    protected String extendByHours;
    protected String extendByMinutes;
    protected long millisecondsToExtend;
    protected String targetNote;

    //CONSTRUCTORS--------------------------------------
    public ExtendDeadlineCommand(ArrayList<String> inputs) {
        super(inputs);
        this.targetNote = inputs.get(1);
        this.extendByDays = inputs.get(2);
        this.extendByHours = inputs.get(3);
        this.extendByMinutes = inputs.get(4);
        this.millisecondsToExtend = (Integer.parseInt(this.extendByDays) * 86400000) +
                (Integer.parseInt(this.extendByHours) * 3600000) +
                (Integer.parseInt(this.extendByMinutes) * 60000);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, IndexOutOfBoundsException {

        Date doneDate = new Date();
        if(CmdType.getKey(this.cmdType).toString().equals("EXTDLINE")) {

            int targetSerialNum = Integer.parseInt(this.targetNote);
            for(int i=0; i<dukeNotes.getNotes().size(); i++) {
                if(dukeNotes.getNotes().get(i).getSerialNum() == targetSerialNum) {

                    DukeUI.printDivider();
                    if(!(dukeNotes.getNotes().get(i) instanceof Deadline)) {
                        System.out.println("\tThe note selected is not a task with a deadline.");
                        System.out.println("\tThe deadline shouldn't be edited anymore.");
                    } else if(dukeNotes.getNotes().get(i).getIsDone()) {
                        System.out.println("\tThe task had already been completed.");
                        System.out.println("\tThe deadline shouldn't be edited anymore.");
                    } else {
                        System.out.println("\tDeadline of Note #" + this.targetNote + ":");
                        dukeNotes.getNotes().get(i).printList();

                        this.oldDate = ((Deadline) dukeNotes.getNotes().get(i)).getTargetDate();
                        this.newDate = new Date(this.oldDate.getTime() + millisecondsToExtend);
                        ((Deadline) dukeNotes.getNotes().get(i)).setTargetDate(this.newDate);

                        System.out.println("\textended from...");
                        DukeUI.commandWrap(TASK_DATE.format(this.oldDate), 66);
                        System.out.println("\tto...");
                        DukeUI.commandWrap(TASK_DATE.format(this.newDate), 66);
                        DukeUI.autoSaveConfirmation(new SaveCommand().autoSave(dukeNotes, dukeStorage));
                        DukeUI.suggestListNotes();
                    }
                    DukeUI.printDivider();
                    break;
                }
            }
        }
    }
}
