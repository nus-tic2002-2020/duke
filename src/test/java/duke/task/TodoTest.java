package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    void testToString(){
        Todo todo = new Todo("Test task ~`!@#$%^&*()1234567890");
        assertEquals(todo.toString(), "[T][\u2718] Test task ~`!@#$%^&*()1234567890");

        todo.markAsDone();
        assertEquals(todo.toString(), "[T][\u2713] Test task ~`!@#$%^&*()1234567890");
    }

}
