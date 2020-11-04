/*************************************************************
 *
 *         Class to end the program.
 *
 * *************************************************************/

public class Bye extends Command{
    public Bye(boolean isExit, String commandLine){
        super(isExit, commandLine);
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.showGoodbye();
    }
}
