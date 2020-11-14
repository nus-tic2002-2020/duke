package tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void testToString() {
        Task tester = new Todo("Duke");
        assertEquals("[T][" + tester.getStatusIcon() + "]Duke", tester.toString());
    }

    @Test
    void testToSaveFormat() {
        Task tester = new Todo("Duke");
        assertEquals("T|0|Duke", tester.saveFormat());
    }
}
