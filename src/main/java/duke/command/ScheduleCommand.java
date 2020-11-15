package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.task.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Command to display {@link Task} according to {@link LocalDate} provided
 * @see duke.command.Command
 */
public class ScheduleCommand extends Command {

    /**
     * Constructor for instantiating new ScheduleCommand
     * @param args of raw user command for further processing in downstream classes
     */
    public ScheduleCommand(String[] args) {
        super(args);
    }

    /**
     * Getter for Command Type enum
     * @return CommandType
     * @see  CommandType
     */
    @Override
    public CommandType getType() {
        return CommandType.FIND;
    }

    /**
     * Getter for exit behavior to be used by calling application
     * @return exit status, always false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Execute ScheduleCommand to display {@link Task} inside {@link TaskManager} with string date from args.
     * Date string is converted into {@link LocalDate} internally.
     * @param taskManager
     * @param ui
     * @param storage
     * @return boolean
     * @throws DukeException if date format is wrong
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

        assert ui != null : "Command ui cannot be null";
        assert outputs != null : "Command outputs cannot be null";
        assert storage != null : "Command storage cannot be null";
        assert taskManager != null : "Command task cannot be null";

        if (super.args == null || super.args.length < 2) { // at least 1 command argument, else error
            throw new DukeException("Eh, where your date ah?",
                    DukeException.DukeError.MISSING_ARGUMENT);
        }

        String keyword = String.valueOf(super.args[1]);
        LocalDate localDate;
        try {

            localDate = LocalDate.parse(keyword, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        } catch (DateTimeParseException ex) {
            throw new DukeException("Your date format wrong la!", DukeException.DukeError.ANOMALY_ARGUMENT);
        }

        // Find tasks by LocalDate, only dated tasks included
        List<DatedTask> tasks = taskManager.findTasks(localDate);

        // Sort dated tasks by date
        Collections.sort(tasks, new Comparator<DatedTask>() {
            @Override
            public int compare(DatedTask o1, DatedTask o2) {
                LocalDateTime d1 = o1.getComparableDateTime();
                LocalDateTime d2 = o2.getComparableDateTime();
                return d1.compareTo(d2);
            }
        });

        String output = String.format("Your schedule for %s:", localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
        ui.echo(output);
        outputs.add(output);

        int i = 0;
        for (DatedTask t: tasks) {
            ui.echo(String.format("%d.%s", ++i, t.toString()));
            outputs.add(String.format("%d.%s", ++i, t.toString()));
        }

        return true;
    }
}
