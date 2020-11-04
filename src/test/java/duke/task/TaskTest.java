package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    public Task[] createTask(){
        Task[] list = new Task[3];
        list[0] = new todo("dinner");
        list[1] = new deadline("homework", "by 10/11/2020");
        list[2] = new event("fireworks", "at 11/11/2020");
        return list;
    }

    Task[] testList = createTask();

    /*Category test*/
    @Test
    public void todoTest(){
        assertEquals(testList[0].getCat(), 'T');
    }
    @Test
    public void deadlineTest(){
        assertEquals(testList[1].getCat(), 'D');
    }
    @Test
    public void eventTest(){
        assertEquals(testList[2].getCat(), 'E');
    }
    @Test
    public void dateTest(){
        String[] dateCheck = {"", "10/11/2020", "11/11/2020"};
        for(int i = 1 ; i <= 3 ; i++){
            assertEquals(testList[i - 1].getTime(), dateCheck[i - 1]);
        }
    }
    @Test
    public void completionTest(){
        for(int i = 1 ; i <= 3 ; i++){
            assertEquals(testList[i - 1].getStatus(), false);
            testList[i - 1].mark_completed();
        }
        for(int i = 1 ; i <= 3 ; i++){
            assertEquals(testList[i - 1].getStatus(), true);
        }
    }
}
