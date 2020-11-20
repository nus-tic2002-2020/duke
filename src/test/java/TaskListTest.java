package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList test = createList();

    /**
     * Initalize test data
     * @return list of test data
     */
    public TaskList createList() {
        TaskList list = new TaskList();
        list.AddTask("todo read book");
        list.AddTask("deadline return book /by 2/12/2019");
        list.AddTask("event meet friends /at 15/12/2020 ");
        return list;
    }

    @Test
    public void Testcount(){
        assertEquals(3, test.getCount());
    }

}
