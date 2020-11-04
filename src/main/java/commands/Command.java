package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasks.*;

import java.io.IOException;

/**
 * This is the abstract parent Command Class.
 *
 */
public abstract class Command {

    protected static boolean running = true;

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws IOException, DukeException;

    public boolean isRunning(){
        return this.running;
    }

}
