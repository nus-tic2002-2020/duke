package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getDescription() {
        Event event = new Event("Test Description", LocalDateTime.now() ,false,0);
        assertEquals("Test Description", event.getDescription());
    }
}