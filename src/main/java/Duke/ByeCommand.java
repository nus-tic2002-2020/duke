package Duke;

/**
 * Execution of the bye command. changes isExit to true and saves the taskList before closing the program.
 * @param taskList overall taskList object
 * @param ui       overall ui object
 * @param storage  overall storage object
 */
public class ByeCommand extends Command {
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        isExit = true;
        storage.save(taskList);
        return ui.showBye();
    }
}
