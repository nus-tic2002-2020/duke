import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void DoneCommandTest(){
        TaskList taskList = new TaskList();
        taskList.addToTaskList(new Todo("Testing JUnit"));
        taskList.getTasks(0).setDone();
        assertEquals(taskList.getTasks(0).toString(),"[T][\u2713]Testing JUnit");
    }
}
