/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.io.IOException;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((commandLine.substring(6).trim()).isEmpty()){
            throw new DukeException("Cannot delete empty values.");
        }try{
            index = searchIndex(commandLine);
            ui.showOutput("Noted. I've removed this task:\n\t  " + taskList.getTasks(index).toString());
            taskList.deleteFromTaskList(index);
            storage.saveToFile();
        }catch (IndexOutOfBoundsException e){
            throw new DukeException("The list cannot be empty.");
        }catch (NumberFormatException e){
            throw new DukeException("Please input an integer.");
        }
    }
    private int searchIndex(String args){
        return Integer.parseInt(commandLine.split(" ")[1])-1;
    }
}