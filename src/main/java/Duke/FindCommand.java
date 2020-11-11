package Duke;

public class FindCommand extends Command {
    private String details;
    public FindCommand(String details) {
        this.details = details;
    }
    /**
     * Execution of the find command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String findTask = "";
            int counter = 0;
            for (Task task : taskList.taskList) {
                if (task.getDescription().contains(details)) {
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