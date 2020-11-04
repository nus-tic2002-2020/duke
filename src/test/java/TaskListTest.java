import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    // Test the taskList class 
    // Does storeList() method work as expected?
    @Test
    public void taskListAddTest() throws IllegalInputException {
     
        // new an instance 
        TaskList taskListCreatedByCode = new TaskList();
        // create an expected value. You assert if it matches with your
        // taskListCreatedByCode 
        ArrayList<Task> tasksExpectedValue = new ArrayList<Task>();
        // This is an expected value 
        Task expectedItem = new Task("todo buy book");
        tasksExpectedValue.add(expectedItem);
        // Execute the function to test
        taskListCreatedByCode.storeList("todo buy book");
        assertEquals(taskListCreatedByCode.tasks, tasksExpectedValue);
    }

}
