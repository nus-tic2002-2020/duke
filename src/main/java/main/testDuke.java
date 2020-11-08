package main;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class testDuke {

    @Test
    public void addOneTaskTest() {
        ToDo t = new ToDo("Walk the dog", true);
        TaskList task = new TaskList();
        task.add(t);
        assertEquals(1, task.getCount());
    }
}