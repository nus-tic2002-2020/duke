package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDescription() {
        Deadline deadline = new Deadline("Test Description", LocalDateTime.now() ,false,0);
        assertEquals("Test Description", deadline.getDescription());
    }
}