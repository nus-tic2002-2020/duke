package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

import java.io.IOException;

/**
 * This represents the exit command. It is used to change the running boolean to false.
 * Afterwards, during execution, it will write the tasks into the file.
 */
public class ExitCommand extends Command{

    public ExitCommand(){
        this.running = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        System.out.println("Executing Exit Command: Saving Task List to storage");
        storage.save(tasks);
        System.out.println("Finished saving Task List to storage");
    }

}
