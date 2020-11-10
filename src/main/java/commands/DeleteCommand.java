package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasks.*;

/**
 * This represents the command to delete a specific task from the task list.
 *
 */
public class DeleteCommand extends Command{
    int option = 0;
    /**
     *This is the constructor for the DeleteCommand.
     * @param option This indicates the Task that the user wants to delete
     */
    public DeleteCommand(int option){
        this.option = option;
    }

    /**
     * This executes the DeleteCommand. It will call the deleteTask method.
     * @param tasks This is the Task List that contains the list of tasks.
     * @param ui This is the ui, to be used for scanning and printing
     * @param storage This is the storage, used to read and write over the file.
     * @throws
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try{
            tasks.deleteTask(option - 1);
        }catch(IndexOutOfBoundsException e){
            throw new DukeException("Input option for Delete invalid.");
        }
        tasks.printList();
    }

    public static void printHelp(){
        System.out.println("Deleting a task from the list: delete [option number]");
    }
}
