package duke.commands;

import duke.notes.event.Birthday;
import duke.notes.event.Event;
import duke.notes.event.Wedding;
import duke.notes.task.Bill;
import duke.notes.task.Shoplist;
import duke.parser.DateException;
import duke.storage.DukeList;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    protected static DukeList testNotes;
    protected static final Date testStart = new Date();

    protected static Date fromTestStart(double days) {
        long m = (long) (86400000 * days);
        return (new Date(testStart.getTime() + m));
    }

    @Test
    void selectionSortBudgetsTest() {

        testNotes = new DukeList();
        ListCommand test = new ListCommand();

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #4",
                fromTestStart(4.01), 7522.37, testStart));
        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #1",
                fromTestStart(1.75), fromTestStart(1.93), 668.88, testStart));
        test.selectionSortBudgets(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(1).getSerialNum());

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #0",
                4800.72, testStart));
        test.selectionSortBudgets(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(3, testNotes.getNotes().get(1).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(2).getSerialNum());

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #5",
                1440000.34, testStart));
        test.selectionSortBudgets(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(3, testNotes.getNotes().get(1).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(2).getSerialNum());
        assertEquals(4, testNotes.getNotes().get(3).getSerialNum());

    }

    @Test
    void selectionSortDatesTest() throws DateException {

        testNotes = new DukeList();
        ListCommand test = new ListCommand();

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #4",
                fromTestStart(4.56), fromTestStart(4.62), 358.65, testStart));
        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #1",
                fromTestStart(1.75), fromTestStart(1.93), 668.88, testStart));
        test.selectionSortDates(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(1).getSerialNum());

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #4",
                fromTestStart(4.18), fromTestStart(4.23), testStart));
        test.selectionSortDates(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(3, testNotes.getNotes().get(1).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(2).getSerialNum());

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #4",
                fromTestStart(4.82), fromTestStart(4.98), testStart));
        test.selectionSortDates(testNotes.getNotes());
        assertEquals(2, testNotes.getNotes().get(0).getSerialNum());
        assertEquals(3, testNotes.getNotes().get(1).getSerialNum());
        assertEquals(1, testNotes.getNotes().get(2).getSerialNum());
        assertEquals(4, testNotes.getNotes().get(3).getSerialNum());

    }

}

