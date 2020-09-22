package duke;

import duke.commands.*;
import duke.notes.event.Birthday;
import duke.notes.event.Event;
import duke.notes.event.Wedding;
import duke.notes.todo.Bill;
import duke.notes.todo.Deadline;
import duke.notes.todo.Shoplist;
import duke.notes.todo.Todo;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    protected static DukeStorage testStorage = new DukeStorage("data/testnotes.txt");
    protected static DukeList testNotes = new DukeList();
    protected static final Date testStart = new Date();

    protected static Date fromTestStart(double days) {
        long m = (long) (86400000 * days);
        return (new Date(testStart.getTime() + m));
    }

    @BeforeAll
    public static void initNotes() throws DateException {

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #0",
                fromTestStart(0.8), 780.27, testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #4",
                fromTestStart(4.56), fromTestStart(4.62), 358.65, testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #2",
                fromTestStart(2.1), testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #2",
                89900.28, testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #1",
                fromTestStart(1.8), testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #1",
                3900.12, testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #1",
                fromTestStart(1.75), fromTestStart(1.93), 668.88, testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #2",
                testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #4",
                fromTestStart(4.2), testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #4",
                fromTestStart(4.18), fromTestStart(4.23), testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #5",
                testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #5",
                fromTestStart(5.12), testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #5",
                fromTestStart(5.15), fromTestStart(5.26), 298.15, testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #6",
                testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #5",
                fromTestStart(5.61), 702.23, testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #5",
                fromTestStart(5.72), fromTestStart(5.81), testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #5",
                fromTestStart(5.60), fromTestStart(5.70), testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #6",
                1852243.81, testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #0",
                4800.72, testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #6",
                fromTestStart(6.25), fromTestStart(6.37), testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #2",
                fromTestStart(2.14), 230.17, testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #2",
                fromTestStart(2.48), fromTestStart(2.68), testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #3",
                150000.73, testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #3",
                fromTestStart(3.16), 850.16, testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #4",
                888000.47, testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #4",
                fromTestStart(4.01), 7522.37, testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #4",
                fromTestStart(4.82), fromTestStart(4.98), testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #0",
                testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #3",
                fromTestStart(3.42), fromTestStart(3.65), 988.88, testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #4",
                testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #6",
                fromTestStart(6.15), 702.23, testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #6",
                fromTestStart(6.76), fromTestStart(6.85), testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #3",
                fromTestStart(3.8), testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #6",
                fromTestStart(6.45), fromTestStart(6.59), 298.15, testStart));

        testNotes.getNotes().add(new Bill(testNotes.getNotes().size()+1, "Bill test #1",
                fromTestStart(1.74), 610.83, testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #1",
                fromTestStart(1.2), fromTestStart(1.38), testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #0",
                fromTestStart(0.3), fromTestStart(0.5), testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #0",
                fromTestStart(0.7), fromTestStart(0.8), testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #0",
                fromTestStart(0.55), fromTestStart(0.6), 500.27, testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #1",
                testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #1",
                fromTestStart(1.40), fromTestStart(1.58), testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #0",
                fromTestStart(0.25), testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #2",
                fromTestStart(2.02), fromTestStart(2.15), testStart));

        testNotes.getNotes().add(new Wedding(testNotes.getNotes().size()+1, "Wedding test #2",
                fromTestStart(2.75), fromTestStart(2.90), 728.37, testStart));

        testNotes.getNotes().add(new Todo(testNotes.getNotes().size()+1, "Todo test #3",
                testStart));

        testNotes.getNotes().add(new Event(testNotes.getNotes().size()+1, "Event test #3",
                fromTestStart(3.08), fromTestStart(3.28), testStart));

        testNotes.getNotes().add(new Birthday(testNotes.getNotes().size()+1, "Birthday test #3",
                fromTestStart(3.86), fromTestStart(3.99), testStart));

        testNotes.getNotes().add(new Deadline(testNotes.getNotes().size()+1, "Deadline test #6",
                fromTestStart(6.92), testStart));

        testNotes.getNotes().add(new Shoplist(testNotes.getNotes().size()+1, "Shoplist test #5",
                1440000.34, testStart));

    }

    @Test
    void countOutstandingTest() {
        assertEquals(28, Todo.getTasksOutstanding());
        assertEquals(21, Event.getEventsOutstanding());
    }

    @Test
    void printNotesTest() throws CommandException, ParseException, DateException, IOException {
        DukeCommand testPrint = new ListCommand("LISTNOTES");
        testPrint.execute(testNotes, testStorage);
    }

}
