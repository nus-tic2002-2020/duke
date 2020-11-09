package tasklist;

import enumerations.PriorityEnum;
import enumerations.SymbolEnum;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This class tests the TaskList class.
 *
 * @author Aloysius Wong
 * @Version 1.0
 * @since 08-11-2020
 */
public class TaskListTest {

    /**
     * This is a private method to create an ArrayList of tasks as strings in text file format, for editing and loading
     * into ArrayList as system format.
     *
     * @return Returns the ArrayList of strings.
     */
    private ArrayList<String> constructArray() {
        ArrayList taskArray = new ArrayList<String>();
        taskArray.add("T | ✗ | TODO TEST | NA");
        taskArray.add("D | ✓ | DEADLINE TEST | 10/04/2020, 07:08 | LOW");
        taskArray.add("E | ✗ | EVENT TEST | 09/05/2019, 05:06 | MEDIUM");
        taskArray.add("W | ✓ | WITHIN TEST | 08/06/2018, 03:04 - 02/03/2021, 01:02 | HIGH");

        return taskArray;
    }

    /**
     * This tests the constructor of TaskList. Ensures that the change of format from text file to system is proper.
     */
    @Test
    public void constructorTest() {
        ArrayList taskArray = constructArray();
        TaskList tasks = new TaskList(taskArray);

        // First task testing
        assertEquals(SymbolEnum.T , tasks.getStore().get(0).getSymbol());
        assertEquals("[✗]", tasks.getStore().get(0).getDone());
        assertEquals("TODO TEST", tasks.getStore().get(0).getDescription());
        assertEquals(PriorityEnum.NA, tasks.getStore().get(0).getPriority());

        // Second task testing
        assertEquals(SymbolEnum.D , tasks.getStore().get(1).getSymbol());
        assertEquals("[✓]", tasks.getStore().get(1).getDone());
        assertEquals("DEADLINE TEST", tasks.getStore().get(1).getDescription());
        assertEquals(PriorityEnum.LOW, tasks.getStore().get(1).getPriority());
        assertEquals(10, tasks.getStore().get(1).getDateAndTime().getDayOfMonth());
        assertEquals(4, tasks.getStore().get(1).getDateAndTime().getMonthValue());
        assertEquals(2020, tasks.getStore().get(1).getDateAndTime().getYear());
        assertEquals(7, tasks.getStore().get(1).getDateAndTime().getHour());
        assertEquals(8, tasks.getStore().get(1).getDateAndTime().getMinute());

        // Third task testing
        assertEquals(SymbolEnum.E , tasks.getStore().get(2).getSymbol());
        assertEquals("[✗]", tasks.getStore().get(2).getDone());
        assertEquals("EVENT TEST", tasks.getStore().get(2).getDescription());
        assertEquals(PriorityEnum.MEDIUM, tasks.getStore().get(2).getPriority());
        assertEquals(9, tasks.getStore().get(2).getDateAndTime().getDayOfMonth());
        assertEquals(5, tasks.getStore().get(2).getDateAndTime().getMonthValue());
        assertEquals(2019, tasks.getStore().get(2).getDateAndTime().getYear());
        assertEquals(5, tasks.getStore().get(2).getDateAndTime().getHour());
        assertEquals(6, tasks.getStore().get(2).getDateAndTime().getMinute());

        // Fourth task testing
        assertEquals(SymbolEnum.W , tasks.getStore().get(3).getSymbol());
        assertEquals("[✓]", tasks.getStore().get(3).getDone());
        assertEquals("WITHIN TEST", tasks.getStore().get(3).getDescription());
        assertEquals(PriorityEnum.HIGH, tasks.getStore().get(3).getPriority());
        assertEquals(8, tasks.getStore().get(3).getStart().getDayOfMonth());
        assertEquals(6, tasks.getStore().get(3).getStart().getMonthValue());
        assertEquals(2018, tasks.getStore().get(3).getStart().getYear());
        assertEquals(3, tasks.getStore().get(3).getStart().getHour());
        assertEquals(4, tasks.getStore().get(3).getStart().getMinute());
        assertEquals(2, tasks.getStore().get(3).getEnd().getDayOfMonth());
        assertEquals(3, tasks.getStore().get(3).getEnd().getMonthValue());
        assertEquals(2021, tasks.getStore().get(3).getEnd().getYear());
        assertEquals(1, tasks.getStore().get(3).getEnd().getHour());
        assertEquals(2, tasks.getStore().get(3).getEnd().getMinute());
    }

    @Test
    public void catcherTest() {
        ArrayList taskArray = constructArray();
        TaskList tasks = new TaskList(taskArray);

        assertTrue(tasks.catcher("TODO"));
        assertTrue(tasks.catcher("TODO "));
        assertFalse(tasks.catcher("TODO d"));
        assertFalse(tasks.catcher("TODO d d"));
    }
}
