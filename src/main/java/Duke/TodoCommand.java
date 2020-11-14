package Duke;

public class TodoCommand extends Command {
    private String details;
    public Todo todo;
    public TodoCommand(String details) {

        this.details = details;
    }
    /**
     * Execution of the todo command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (details.trim().isEmpty()) {
            return ui.showTodoError();
        }
        else {
            todo = new Todo(details, false,0);
            taskList.add(todo);
            return ui.showTaskAdded(todo, taskList);
        }
    }
}
