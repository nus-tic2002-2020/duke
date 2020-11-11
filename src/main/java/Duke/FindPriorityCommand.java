package Duke;

public class FindPriorityCommand extends Command {
    private int priority;
    public FindPriorityCommand(int priority) {
        this.priority = priority;
    }
    /**
     * Execution of the find priority command. Finds all task of certain priority.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String findTask = "";
            int counter = 0;
            for (Task task : taskList.taskList) {
                if (task.getPriority() == priority) {
                    findTask += (counter + 1) + "." + task.toString() + "\n";
                }
                counter++;
            }
            System.out.println(findTask);
            return findTask;
        }
        catch(Exception e) {
            return ui.showFindError();
        }
    }
}