package commands;

import ui.Ui;
import storage.Storage;


public abstract class Command {

    protected static boolean isRunning = true;

    public abstract void execute(TaskList list, Ui ui, Storage storage);

}
