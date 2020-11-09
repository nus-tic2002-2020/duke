package LisaTest;

import enumerations.PriorityEnum;
import enumerations.SymbolEnum;
import task.Task;
import task.Within;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the Within class.
 *
 * @author Aloysius Wong
 * @version 1.0
 * @since 08-11-2020
 */
public class WithinTest {

    /**
     * Tests:
     * 1) Constructor
     * 2) getDescription
     * 3) getSymbol
     * 4) getDone
     * 5) getPriority
     * 6) Start LocalDateTime
     * 7) End LocalDateTime
     */
    @Test
    public void constructorTest() {
        Task test = new Within("tEsT cAsE", "10/10/2020 10:10", "23/8/2025 03:20");
        assertEquals("tEsT cAsE", test.getDescription());
        assertEquals(SymbolEnum.W, test.getSymbol());
        assertEquals("[✗]", test.getDone());
        assertFalse(test.isDone);
        assertEquals(PriorityEnum.NA, test.getPriority());
        assertEquals(10, test.getStart().getDayOfMonth());
        assertEquals(10, test.getStart().getMonthValue());
        assertEquals(2020, test.getStart().getYear());
        assertEquals(23, test.getEnd().getDayOfMonth());
        assertEquals(8, test.getEnd().getMonthValue());
        assertEquals(2025, test.getEnd().getYear());
    }

    /**
     * Tests:
     * 1) setDone
     */
    @Test
    public void setDoneTest() {
        Task test = new Within("tEsT cAsE", "10/10/2020 10:10", "23/8/2025 03:20");
        test.setDone();
        assertEquals("[✓]", test.getDone());
    }

    /**
     * Tests:
     * 1) setPriority
     */
    @Test
    public void setPriorityTest() {
        Task test = new Within("tEsT cAsE", "10/10/2020 10:10", "23/8/2025 03:20");
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
