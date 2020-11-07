package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DeadlineTest {

    @Test
    void getBy_success() {
        assertEquals("20 Feb 2000 10:10:10", new Deadline("running", "20 Feb 2000 10:10:10").getBy());
    }

    @Test
    void testToString_success() {
        assertEquals("[D][✗] running (by: 20 Feb 2000 10:10:10)", new Deadline("running", "20 Feb 2000 10:10:10").toString());
    }
}