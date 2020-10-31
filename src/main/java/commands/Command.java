package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

import java.io.IOException;

public abstract class Command {

    protected static boolean running = true;

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws IOException;

    public boolean isRunning(){
        return this.running;
    }

}
