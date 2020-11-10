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

        AddCommand.printHelp();
        RescheduleCommand.printHelp();

        ChangeDoneCommand.printHelp();
        ClearListCommand.printHelp();
        DeleteCommand.printHelp();
        ExitCommand.printHelp();
        FindCommand.printHelp();
        ListCommand.printHelp();

    }
}
