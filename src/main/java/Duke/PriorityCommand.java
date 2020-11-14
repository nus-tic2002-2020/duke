package Duke;

public class PriorityCommand extends Command {
    int option;
    int priority;
    PriorityCommand(String argStr){
        String[] fullStr = argStr.split(" ");
        option = Integer.parseInt(fullStr[0]);
        priority = Integer.parseInt(fullStr[1]);
    }
    /**
     * Execution of the priority command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return taskList.assignPriority(option-1, priority);
        }
        catch(Exception e)
        {
            return ui.showDoneError();
        }
    }
}