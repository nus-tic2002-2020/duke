import java.io.IOException;
import java.util.Arrays;
public class DeadlineCommand extends Command{
    protected Deadline deadline;

    public DeadlineCommand(boolean isExit, String commandLine){
        super(isExit,commandLine);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeIOException, DukeException, IOException{
        int index = 0;
        if((commandLine.substring(8).trim()).isEmpty()){
            throw new DukeIOException("deadline");
        }
        if(!commandLine.contains("by")){
            throw new DukeException("The date of a deadline cannot be empty.");
        }
        for(String word : commandLine.split(" ")){
            ++index;
            if(word.equals("by")){
                if(String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)).isEmpty()){
                    throw new DukeException("The date of a deadline cannot be empty.");
                }
                else {
                    deadline = new Deadline(String.join(" ", Arrays.copyOfRange(commandLine.split(" "), 1, index-1)), String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)));
                    taskList.addToTaskList(deadline);
                    ui.showOutput("Got it. I've added this task:\n\t  " + deadline.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
                    storage.saveToFile();
                }
            }
        }
    }
}
