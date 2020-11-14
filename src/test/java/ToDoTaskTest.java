import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import tasks.ToDo;

public class ToDoTaskTest {
    @Test
    public void toDoTask() {

        ToDo todoTravelling = new ToDo("Travelling","T", false);
        ToDo todoRunning = new ToDo("Running","T", false);
        ToDo todoPlaySoccer = new ToDo("Play Soccer","T", false);

        assertEquals("[T][\u2718] Travelling",todoTravelling.getTaskListInfo());
        assertEquals("[T][\u2718] Running",todoRunning.getTaskListInfo());
        assertEquals("[T][\u2718] Play Soccer",todoPlaySoccer.getTaskListInfo());
    }

    @Test
    public void isDoneTest() {
        ToDo todoPainting = new ToDo("Painting", "T", false);
        todoPainting.markAsDone();
        assertEquals("[T][\u2713] Painting", todoPainting.getTaskListInfo());
    }
}
