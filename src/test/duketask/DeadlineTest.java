package duketask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void setSchedule() {
        Deadline test = new Deadline("testDeadline /by 10/10/1990 1500");
        assertEquals("10/10/1990 1500",test.getSchedule());
    }

    @Test
    void reset() {
        Deadline test = new Deadline("testDeadline /by 10/10/1990 1500");
        test.reset("newDeadline /by 11/11/2011 1100");
        assertEquals("[D][\u2718] newDeadline (by: 11 Nov 2011, 11:00AM)",test.toString());
    }

    @Test
    void copy() {
        Deadline test = new Deadline("testDeadline /by 10/10/1990 1500");
        assertEquals("testDeadline /by 10/10/1990 1500",test.copy());
    }

    @Test
    void testToString() {
        Deadline test = new Deadline("testDeadline /by 10/10/1990 1500");
        assertEquals("[D][\u2718] testDeadline (by: 10 Oct 1990, 03:00PM)",test.toString());
    }
}