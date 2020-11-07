package duke.task;

import duke.command.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Test task ~`!@#$%^&*()1234567890", "2020-01-01 23:33");
        assertEquals("[D][\u2718] Test task ~`!@#$%^&*()1234567890 (by: 1 Jan 2020 11:33 PM)", deadline.toString());

        deadline.markAsDone();
        assertEquals("[D][\u2713] Test task ~`!@#$%^&*()1234567890 (by: 1 Jan 2020 11:33 PM)", deadline.toString());
    }

    @Test
    void testFromSavableString() {
        Deadline deadline = new Deadline("Test task ~`!@#$%^&*()1234567890", "2020-01-01 23:33");
        Deadline deadline2 = new Deadline();
        try {
            deadline2.fromSavableString(deadline.toSavableString());
        } catch (DukeException e) {

        }
        assertEquals("[D][\u2718] Test task ~`!@#$%^&*()1234567890 (by: 1 Jan 2020 11:33 PM)", deadline2.toString());
    }

}
