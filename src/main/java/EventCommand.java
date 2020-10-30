import java.io.IOException;
import java.util.Arrays;

public class EventCommand extends Command{
    protected Event event;

    public EventCommand(boolean isExit, String commandLine){
        super(isExit,commandLine);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeIOException, DukeException, IOException{
        if((commandLine.substring(5).trim()).isEmpty()){
            throw new DukeIOException("event");
        }
        if(!commandLine.contains("at")){
            throw new DukeException("The date of a event cannot be empty.");
        }
        int index = 0;
        for(String command : commandLine.split(" ")){
            ++index;
            if(command.equals("at")){
                if(String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)).isEmpty()){
                    throw new DukeException("The date of a event cannot be empty.");
                }
                else {
                    event = new Event (String.join(" ", Arrays.copyOfRange(commandLine.split(" "), 1, index-1)), String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)));
                    taskList.addToTaskList(event);
                    ui.showOutput("Got it. I've added this task:\n\t  " + event.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
                    storage.saveToFile();
                }
            }
        }
    }
}
