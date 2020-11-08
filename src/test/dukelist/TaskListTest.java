package dukelist;

import duketask.Event;
import duketask.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void size() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        assertEquals(1,test.size());
    }

    @Test
    void getTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        assertEquals("[T][\u2718] testTodo",test.getTask(0).toString());
    }

    @Test
    void addTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        assertEquals("[T][\u2718] testTodo",test.getTask(0).toString());
    }

    @Test
    void deleteTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        test.deleteTask(0);
        assertEquals(0,test.size());
    }

    @Test
    void doneTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        test.getTask(0).markAsDone();
        assertEquals("\u2713",test.getTask(0).getStatusIcon());
    }

    @Test
    void findTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        test.addTask(new Event("testEvent /at 10/10/1990 1500"));
        assertEquals("testEvent",test.findTask("en").getTask(0).getDescription());
    }

    @Test
    void updateTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        test.updateTask("1 /reset newTodo /takes 5h");
        assertEquals("[T][\u2718] newTodo (takes: 5h)",test.getTask(0).toString());
    }

    @Test
    void copyTask() {
        TaskList test = new TaskList();
        test.addTask(new Todo("testTodo"));
        test.copyTask(0);
        assertEquals("[T][\u2718] testTodo",test.getTask(1).toString());
    }
}