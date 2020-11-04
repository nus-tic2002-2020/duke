import DukeTask.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        Task t = new Task("Sleep");
        assertEquals("[✓] Sleep", t.toString()); //
    }
}

//Tests use Assert.assertEquals(expected, actual) methods to compare the expected output with the actual output.