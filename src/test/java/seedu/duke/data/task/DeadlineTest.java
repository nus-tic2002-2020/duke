package seedu.duke.data.task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Deadline;

public class DeadlineTest {
    @Test
    @DisplayName("Insert Deadline for Test Case 1 by 09/11/2020 1234H")
    public void TestToString() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 9, 12, 34);
        Deadline deadline = new Deadline("This is Test Case 1", dateTime);
        assertEquals("[D][\u2718] This is Test Case 1 (by: 9/11/2020 1234)", deadline.getDescription());

        deadline.setDone();
        assertEquals("[D][\u2713] This is Test Case 1 (by: 9/11/2020 1234)", deadline.getDescription());
    }
}