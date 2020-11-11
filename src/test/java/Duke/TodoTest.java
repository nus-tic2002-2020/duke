package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getDescription() {
        Todo todo = new Todo("Test Description", false,0);
        assertEquals("Test Description", todo.getDescription());
    }
}