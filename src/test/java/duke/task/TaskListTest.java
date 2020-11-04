package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList test = createTaskList();

    public TaskList createTaskList() {
        TaskList tasks = new TaskList();
        tasks.insert_item("todo homework");
        tasks.insert_item("deadline buy lunch /by 5/11/2020");
        tasks.insert_item("event fireworks /at 11/11/2020");
        return tasks;
    }

    @Test
    //test item count and delete
    public void Test() {
        assertEquals(3, test.getItem_count());
        while(test.getItem_count() > 0){
            test.delete("delete 1");
        }
        assertEquals(0, test.getItem_count());
    }
}

