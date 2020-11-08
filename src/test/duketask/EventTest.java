package duketask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void setSchedule() {
        Event test = new Event("testEvent /at 10/10/1990 1500");
        assertEquals("10/10/1990 1500",test.getSchedule());
    }

    @Test
    void reset() {
        Event test = new Event("testEvent /at 10/10/1990 1500");
        test.reset("newEvent /at 11/11/2011 1100");
        assertEquals("[E][\u2718] newEvent (at: 11 Nov 2011, 11:00AM)",test.toString());
    }

    @Test
    void copy() {
        Event test = new Event("testEvent /at 10/10/1990 1500");
        assertEquals("testEvent /at 10/10/1990 1500",test.copy());
    }

    @Test
    void testToString() {
        Event test = new Event("testEvent /at 10/10/1990 1500");
        assertEquals("[E][\u2718] testEvent (at: 10 Oct 1990, 03:00PM)",test.toString());
    }
}