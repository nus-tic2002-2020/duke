package Duke;

public class DeleteCommand extends Command {
    int option;
    DeleteCommand(String argStr){
        option = Integer.parseInt(argStr);
    }

    /**
     * Execution of the delete command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return taskList.deleteTask(option - 1);
        }
        catch(Exception e) {
            return ui.showDeleteError();
        }
    }
}
