package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import tasks.Deadline;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class RescheduleCommand extends Command{

    String newBy;
    int option = 0;

    public RescheduleCommand(int option, String newBy){
        this.newBy = newBy;
        this.option = option;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DateTimeParseException, IndexOutOfBoundsException {
        try{
            if(tasks.get(this.option-1) instanceof Deadline){
                ((Deadline) (tasks.get(this.option-1))).rescheduleBy(this.newBy);
                System.out.println("Rescheduled Date time: " + tasks.get(this.option-1).toString());
            }

        }catch(DateTimeParseException e){
            System.out.println("Invalid Date Time set for /by. It will be set to the current " +
                    "time. Format should be \"dd MMM yyyy H:m:s\" ");
            throw e;
        }catch(IndexOutOfBoundsException e){
            System.out.println("Index out of bounds error");
            throw e;
        }

    }

    public static void printHelp(){
        System.out.println("Reschedule a Deadline: reschedule [option number] /by [Date and time]");
    }
}
