package duke.task;

import duke.command.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    void testToString() {
        Event event = new Event("Test task ~`!@#$%^&*()1234567890");
        try {
            event.setDuration("2020-01-01 23:33", "2020-01-02 23:33");
        } catch (DukeException e) {

        }
        assertEquals("[E][\u2718] Test task ~`!@#$%^&*()1234567890 (at: 1 Jan 2020 11:33 PM, end: 2 Jan 2020 11:33 PM)", event.toString());

        event.markAsDone();
        assertEquals("[E][\u2713] Test task ~`!@#$%^&*()1234567890 (at: 1 Jan 2020 11:33 PM, end: 2 Jan 2020 11:33 PM)", event.toString());
    }

    @Test
    void testFromSavableString() {
        Event event = new Event("Test task ~`!@#$%^&*()1234567890");
        Event event2 = new Event();
        try {
            event.setDuration("2020-01-01 23:33", "");
            event2.fromSavableString(event.toSavableString());
        } catch (DukeException e) {

        }
        assertEquals("[E][\u2718] Test task ~`!@#$%^&*()1234567890 (at: 1 Jan 2020 11:33 PM)", event2.toString());
    }

}
