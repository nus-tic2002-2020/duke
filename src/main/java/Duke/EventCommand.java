package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    private String details;
    public Event event;
    public EventCommand(String details) {

        this.details = details;
    }
    /**
     * Execution of the event command.
     * @param taskList overall taskList object
     * @param ui       overall ui object
     * @param storage  overall storage object
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String[] desc = details.split(" /at ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(desc[1], formatter);
            event = new Event(desc[0], ldt, false,0);
            taskList.add(event);
            return ui.showTaskAdded(event, taskList);
        }
        catch(Exception e) {
            return ui.showEventError();
        }
    }
}
