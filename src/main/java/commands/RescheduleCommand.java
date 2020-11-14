package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import tasks.Deadline;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class RescheduleCommand extends Command {

    String newDateTime;
    int option = 0;

    public RescheduleCommand(int option, String newDateTime) {
        this.newDateTime = newDateTime;
        this.option = option;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {

            if (tasks.get(this.option - 1) instanceof Deadline) {
                ((Deadline) (tasks.get(this.option - 1))).rescheduleBy(this.newDateTime);
                ui.printMessage("Rescheduled Date time: " + tasks.get(this.option - 1).toString());
            }else if (tasks.get(this.option - 1) instanceof Event) {
                ((Event) (tasks.get(this.option - 1))).rescheduleAt(this.newDateTime);
                ui.printMessage("Rescheduled Date time: " + tasks.get(this.option - 1).toString());
            }else{
                throw new DukeException("Error: You did not reschedule on a Deadline or Event");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: Input option for Reschedule command is invalid");
        }

    }

    public static void printHelp() {
        System.out.println("Reschedule a Deadline: reschedule [option number] /new [Date and time]");
        System.out.println("Reschedule a Deadline: reschedule [option number] /new [Date and time] - [Time]");
    }
}
