package Duke;

public class HelpCommand extends Command {

    HelpCommand(){
       ;
    }

    /**
     * Execution of the any wrong command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String commandMessage = "Command List\n\r"+
                "todo        Adds a Todo task. Usage: todo <Description>\n\r"+
                "event       Adds an Event task. Usage: event <Description> /at <YYYY-MM-DD HH:MM>\n\r"+
                "deadline    Adds a Deadline task. Usage: deadline <Description> /by <YYYY-MM-DD HH:MM>\n\r"+
                "delete      Deletes a task. Usage: delete <task number>\n\r"+
                "list        List all current task\n\r"+
                "done        Marks task as done Usage: done <task number>\n\r"+
                "find        Find task(s) that matches a description. Usage: find <description>\n\r"+
                "priority    Sets priority of task. Usage: priority <task number> <priority>\n\r"+
                "findp       Find task(s) of a certain priority. Usage: findp <priority>\n\r"+
                "bye         Quits duke and save Task list to file test.txt\n\r"+
                "help        Shows all available commands";
        System.out.println(commandMessage);
        return commandMessage;
    }
}
