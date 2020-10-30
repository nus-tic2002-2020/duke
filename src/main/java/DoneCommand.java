import java.io.IOException;

public class DoneCommand extends Command{

    private int index;

    public DoneCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((commandLine.substring(4).trim()).isEmpty()){
            throw new DukeException("Cannot mark empty values as done.");
        }
        try{
            index = searchIndex(commandLine);
            (taskList.getTasks(index)).setDone();
            ui.showOutput("Nice! I've marked this task as done:\n\t  " + taskList.getTasks(index).toString());
            storage.saveToFile();
        }
        catch (IndexOutOfBoundsException e){
            throw new DukeException("The list cannot be empty.");
        }
        catch (NumberFormatException e){
            throw new DukeException("Please input an integer.");
        }
    }

    private int searchIndex(String args){
        return Integer.parseInt(commandLine.split(" ")[1])-1;
    }
}