package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;

public abstract class Command {

    protected static boolean running = true;

    public abstract void execute(TaskList list, Ui ui, Storage storage);

    public boolean isRunning(){
        return this.running;
    }

}
