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

public class ScheduleCommand extends Command{

    public ScheduleCommand(String[] args) {
        super(args);
    }

    @Override
    public CommandType getType() {
        return CommandType.FIND;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {

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

        ui.echo(String.format("Your schedule for %s:", localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))));
        int i = 0;
        for (DatedTask t: tasks) {
            ui.echo(String.format("%d.%s", ++i, t.toString()));
        }

        return true;
    }
}
