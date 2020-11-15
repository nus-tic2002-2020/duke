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
        String expectedValue = expectedItem.description;

        // Execute the function to test
        taskListCreatedByCode.storeList("todo buy book");
        assertEquals(taskListCreatedByCode.tasks.get(0).description , expectedValue);

        Task expectedItem2 = new Task("deadline ppt /by 23/11/2020");
        tasksExpectedValue.add(expectedItem2);
        String expectedValue2 = expectedItem2.getTag();

        // Execute the function to test
        taskListCreatedByCode.storeList("deadline ppt /by 23/11/2020");
        assertEquals(taskListCreatedByCode.tasks.get(1).getTag() , expectedValue2);

        Task expectedItem3 = new Task("event sports meet /at 23/11/2020");
        tasksExpectedValue.add(expectedItem2);
        String expectedValue3 = expectedItem2.getTimeDate();

        // Execute the function to test
        taskListCreatedByCode.storeList("deadline ppt /by 23/11/2020");
        assertEquals(taskListCreatedByCode.tasks.get(2).getTimeDate() , expectedValue3);

    }


}
