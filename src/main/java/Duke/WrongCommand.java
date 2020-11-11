package Duke;

public class WrongCommand extends Command {
    String command;
    WrongCommand(String command){
        this.command = command;
    }

    /**
     * Execution of the any wrong command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String errorMessage = "Command '"+command+"' does not exists.";
        System.out.println(errorMessage);
        return errorMessage;
    }
}
