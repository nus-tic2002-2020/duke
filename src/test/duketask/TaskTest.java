package duketask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void markAsDone() {
        Task testTask = new Task("testTask");
        testTask.markAsDone();
        assertEquals("\u2713",testTask.getStatusIcon());
    }

    @Test
    void markNotDone() {
        Task testTask = new Task("testTask");
        testTask.markAsDone();
        testTask.markNotDone();
        assertEquals("\u2718",testTask.getStatusIcon());
    }

    @Test
    void getStatusIcon() {
        Task testTask = new Task("testTask");
        testTask.markAsDone();
        assertEquals("\u2713",testTask.getStatusIcon());
    }

    @Test
    void getDescription() {
        Task testTask = new Task("testTask");
        assertEquals("testTask",testTask.getDescription());
    }

    @Test
    void setDescription() {
        Task testTask = new Task("testTask");
        testTask.setDescription("newTest");
        assertEquals("newTest",testTask.getDescription());
    }

    @Test
    void find() {
        Task testTask = new Task("testTask");
        assertEquals(true,testTask.find("te"));
    }

    @Test
    void testToString() {
        Task testTask = new Task("testTask");
        assertEquals("[\u2718] testTask",testTask.toString());
    }

}