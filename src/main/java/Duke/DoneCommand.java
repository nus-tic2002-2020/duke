package Duke;

public class DoneCommand extends Command {
    int option;
    DoneCommand(String argStr){
        option = Integer.parseInt(argStr);
    }

    /**
     * Execution of the done command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return taskList.markTaskAsDone(option - 1);
        }
        catch(Exception e) {
            return ui.showDoneError();
        }
    }
}
