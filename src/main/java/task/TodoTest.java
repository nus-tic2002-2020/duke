package task;

import enumerations.PriorityEnum;
import enumerations.SymbolEnum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the ToDo class.
 *
 * @author Aloysius Wong
 * @Version 1.0
 * @since 08-11-2020
 */
public class TodoTest {

    /**
     * Tests:
     * 1) Constructor
     * 2) getDescription
     * 3) getSymbol
     * 4) getDone
     * 5) getPriority
     */
    @Test
    public void constructorTest() {
        Task test = new ToDo("test case");
        assertEquals("test case", test.getDescription());
        assertEquals(SymbolEnum.T, test.getSymbol());
        assertEquals("[✗]", test.getDone());
        assertFalse(test.isDone);
        assertEquals(PriorityEnum.NA, test.getPriority());
    }

    /**
     * Tests:
     * 1) setDone
     */
    @Test
    public void setDoneTest() {
        Task test = new ToDo("test case");
        test.setDone();
        assertEquals("[✓]", test.getDone());
    }

    /**
     * Tests:
     * 1) setPriority
     */
    @Test
    public void setPriorityTest() {
        Task test = new ToDo("test case");
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
