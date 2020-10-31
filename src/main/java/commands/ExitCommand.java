package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

import java.io.IOException;


public class ExitCommand extends Command{

    public ExitCommand(){
        this.running = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IOException {
        storage.writeToFile(list);
    }

}
