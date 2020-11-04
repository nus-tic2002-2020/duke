public class HelpCommand extends Command{
    public HelpCommand(boolean isExit, String commandLine){
        super(isExit,commandLine);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        System.out.println("List of commands: todo || deadline || event || bye || list || delete || done || snooze || find");
    }
}
