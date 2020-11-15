package seedu.duke.data.task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.base.Event;

public class EventTest {
    @Test
    @DisplayName("Insert Event for Testing by 09/11/2020 1234H")
    public void TestGetDescription() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 9, 12, 34);
        Event event = new Event("This is Testing 1", dateTime);
        assertEquals("[E][\u2718] This is Testing 1 (by: 9/11/2020 1234)", event.getDescription());
        event.setDone();
        assertEquals("[E][\u2713] This is Testing 1 (by: 9/11/2020 1234)", event.getDescription());
    }
}