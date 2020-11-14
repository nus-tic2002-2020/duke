import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import tasks.*;

public class TaskListTest {
    @Test
    public void taskList () {
        ToDo todoTravelling = new ToDo("Travelling","T", false);
        TaskList tasks = new TaskList();
        tasks.addTask(todoTravelling);

        assertEquals(1, tasks.size());
        assertEquals(todoTravelling, tasks.getTask(0));
    }
}
