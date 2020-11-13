package Duke;

public class FindPriorityCommand extends Command {
    private String details;
    public FindPriorityCommand(String details) {
        this.details = details;
    }

    /**
     * Execution of the find priority command. Finds all task of certain priority.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (details.trim().isEmpty() || isInteger(details)==false) {
            return ui.showFindPriorityError();
        }
        else {
            int priority = Integer.parseInt(details);
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
    }
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}