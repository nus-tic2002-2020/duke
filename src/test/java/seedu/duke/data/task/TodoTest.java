package seedu.duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.base.Todo;

public class TodoTest {
    @Test
    @DisplayName("Insert todo finish assignments")
    public void TestGetDescription() {
        Todo todo = new Todo("finish assignments");
        assertEquals("[T][\u2718] finish assignments", todo.getDescription());
    }
}