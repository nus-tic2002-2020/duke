package Duke;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class DeadlineCommand extends Command {
    private String details;
    public Deadline deadLine;
    public DeadlineCommand(String details) {
        this.details = details;
    }
    /**
     * Execution of the deadline command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String[] desc = details.split(" /by ");
            //yyyy-MM-dd-HH-mm 2020-12-30-18-30
            //String testDateString2 = "2020-12-30-18-30";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(desc[1], formatter);
            deadLine = new Deadline(desc[0], ldt, false,0);
            taskList.add(deadLine);
            return ui.showTaskAdded(deadLine, taskList);
        }
        catch(Exception e) {
            return ui.showDeadlineError();
        }
    }
}
