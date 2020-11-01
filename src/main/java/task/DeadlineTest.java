package task;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void constructorTest() {
        Task test = new Deadline("test case", "10/10/2020 10:10");
        assertEquals("test case", test.getDescription());
        assertEquals("[D]", test.getSymbol());
        assertEquals("[✗]", test.getDone());
        assertEquals(10, test.getDateAndTime().getDayOfMonth());
        assertEquals(10, test.getDateAndTime().getMonthValue());
        assertEquals(2020, test.getDateAndTime().getYear());
    }

    @Test
    public void setDoneTest() {
        Task test = new Deadline("test case", "10/10/2020 10:10");
        test.setDone();
        assertEquals("[✓]", test.getDone());
    }
}
