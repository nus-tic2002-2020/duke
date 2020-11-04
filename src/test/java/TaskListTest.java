import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TestAddToTaskList(){
        TaskList taskList = new TaskList();
        taskList.addToTaskList(new Todo("Testing JUnit"));
        assertEquals(taskList.getSize(),1);
        assertEquals(taskList.getTasks(0).toString(),"[T][\u2718]Testing JUnit");
        taskList.deleteFromTaskList(0);
        assertEquals(taskList.getSize(),0);
    }
}
