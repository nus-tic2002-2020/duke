package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.printMessage("Inputs in commands:\n[description] is a string that describes the task.\n" +
                "[Date and Time] refers to the input date. It goes by the format \"dd/MM/yyyy HH:mm:ss\". " +
                "Eg. 02/12/20 10:10:10\n" +
                "[Date and Time] For Event Tasks, \"ss\" seconds are not needed. Eg. 23:59, just before midnight\n" +
                "[Time] refers to the Time format of \"HH:mm\" where they represent the hours and minutes\n" +
                "[option] refers to the option number. The option number for each task can be viewed when the tasks are printed\n" +
                " as a list.(this can be seen by the command \"list\")\n" +
                "[keyword] refers to the phrase or word that you want to search for\n" +
                "The commands for the respective features are after the semi-colon\":\"\n");

        AddCommand.printHelp();
        RescheduleCommand.printHelp();

        ChangeDoneCommand.printHelp();
        DeleteCommand.printHelp();
        ClearListCommand.printHelp();
        ExitCommand.printHelp();
        FindCommand.printHelp();
        ListCommand.printHelp();

    }
}
