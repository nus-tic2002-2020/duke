package commands;

import storage.Storage;
import ui.Ui;
import tasks.*;


public class ExitCommand extends Command{

    public ExitCommand(){
        this.running = false;
    }

    public void execute(TaskList list, Ui ui, Storage storage){

    }

}
