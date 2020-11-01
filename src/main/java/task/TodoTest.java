package task;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void constructorTest() {
        Task test = new ToDo("test case");
        assertEquals("test case", test.getDescription());
        assertEquals("[T]", test.getSymbol());
        assertEquals("[✗]", test.getDone());
    }

    @Test
    public void setDoneTest() {
        Task test = new ToDo("test case");
        test.setDone();
        assertEquals("[✓]", test.getDone());
    }
}
