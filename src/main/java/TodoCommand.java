import java.io.IOException;

public class TodoCommand extends Command{
    protected Todo todo;
    public TodoCommand(boolean isExit, String commandLine){
        super(isExit, commandLine);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((commandLine.substring(4).trim()).isEmpty()){
            throw new DukeException("todo");
        }
        todo = new Todo(commandLine.substring(5));
        taskList.addToTaskList(todo);
        ui.showOutput("Got it. I've added this task:\n\t  " + todo.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
        storage.saveToFile();
    }

}
