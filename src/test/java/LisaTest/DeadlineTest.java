package LisaTest;

import enumerations.PriorityEnum;
import enumerations.SymbolEnum;
import task.Deadline;
import task.Task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the Deadline class.
 *
 * @author Aloysius Wong
 * @version 1.0
 * @since 08-11-2020
 */
public class DeadlineTest {

    /**
     * Tests:
     * 1) Constructor
     * 2) getDescription
     * 3) getSymbol
     * 4) getDone
     * 5) getPriority
     * 6) LocalDateTime
     */
    @Test
    public void constructorTest() {
        Task test = new Deadline("tEsT cAsE", "10/10/2020 10:10");
        assertEquals("tEsT cAsE", test.getDescription());
        assertEquals(SymbolEnum.D, test.getSymbol());
        assertEquals("[✗]", test.getDone());
        assertFalse(test.isDone);
        assertEquals(PriorityEnum.NA, test.getPriority());
        assertEquals(10, test.getDateAndTime().getDayOfMonth());
        assertEquals(10, test.getDateAndTime().getMonthValue());
        assertEquals(2020, test.getDateAndTime().getYear());
    }

    /**
     * Tests:
     * 1) setDone
     */
    @Test
    public void setDoneTest() {
        Task test = new Deadline("test case", "10/10/2020 10:10");
        test.setDone();
        assertEquals("[✓]", test.getDone());
    }

    /**
     * Tests:
     * 1) setPriority
     */
    @Test
    public void setPriorityTest() {
        Task test = new Deadline("test case", "10/10/2020 10:10");
        test.setPriority("hiGh");
        assertEquals(PriorityEnum.HIGH, test.getPriority());
        test.setPriority("MEDIUM");
        assertEquals(PriorityEnum.MEDIUM, test.getPriority());
        test.setPriority("low");
        assertEquals(PriorityEnum.LOW, test.getPriority());
        test.setPriority("Na");
        assertEquals(PriorityEnum.NA, test.getPriority());
    }
}
