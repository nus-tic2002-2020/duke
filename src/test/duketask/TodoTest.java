package duketask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void copy() {
        Todo test = new Todo("test /takes 5 days");
        assertEquals("test /takes 5 days",test.copy());
    }

    @Test
    void getSchedule() {
        Todo testTodo = new Todo("test /takes 5 days");
        assertEquals("5 days",testTodo.getSchedule());
    }

    @Test
    void setSchedule() {
        Todo testTodo = new Todo("test /takes 5 days");
        testTodo.setSchedule("10h");
        assertEquals("10h",testTodo.getSchedule());
    }

    @Test
    void reset() {
        Todo testTodo = new Todo("test");
        testTodo.reset("reset /takes 5m");
        assertEquals("[T][\u2718] reset (takes: 5m)",testTodo.toString());
    }

    @Test
    void testCopy() {
        Todo testTodo = new Todo("test /takes 5 days");
        assertEquals("test /takes 5 days",testTodo.copy());
    }

    @Test
    void testToString() {
        Todo testTodo = new Todo("test /takes 5 days");
        assertEquals("[T][\u2718] test (takes: 5 days)",testTodo.toString());
    }
}