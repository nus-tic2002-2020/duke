package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class HelpCommand extends Command{

    public HelpCommand(){
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Inputs in commands:\n[description] is a string that describes the task.\n" +
                "[Date and Time] refers to the input date. It goes by the format \"dd MMM yyyy H:m:s\". " +
                "Eg. 02 Feb 2002 10:10:10\n[option] refers to the option number. The option number for each task can be viewed when the tasks are printed as " +
                "a list.(this can be seen by the command \"list\")\n[keyword] refers to the phrase or word that you want to search for\n" +
                "[location] is a string that refers to the location for the activity\n" +
                "The command for the respective features are after the semi-colon \":\"\n");

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
