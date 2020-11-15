package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasks.*;

import java.io.IOException;

/**
 * This is the abstract parent Command Class.
 */
public abstract class Command {

    protected static boolean running = true;

    /**
     * This is the abstract method for execute.
     *
     * @param tasks   This is the Task List that contains the list of tasks.
     * @param ui      This is the ui, to be used for scanning and printing
     * @param storage This is the storage, used to read and write over the file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * This method is an accessor. It returns the boolean value of running.
     *
     * @return running This indicates if Duke is to continue to run or not
     */
    public boolean isRunning() {
        return this.running;
    }

}
