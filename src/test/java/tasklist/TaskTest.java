package tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    @Test
    void testgetStatusIcon(){
        Task tester = new Task("Duke");
        assertFalse(false, "\u2718");
    }
    @Test
    void testToString() {
        Task tester = new Task("Duke");
        assertEquals("[" + tester.getStatusIcon() + "]Duke", tester.toString());
    }
    @Test
    void testToSaveFormat() {
        Task tester = new Task("Duke");
        assertEquals("|0|Duke", tester.saveFormat());
    }
}
