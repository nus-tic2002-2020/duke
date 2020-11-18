package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest{

    /**
     * Initalize test data
     * @return array of Task with data
     */
    public Task[] createlist(){
        Task[] list = new Task[3];
        list[0] = new ToDo("dinner");
        list[1] = new Deadline("collect shirt", "by 01/12/2020");
        list[2] = new Event("dinner with friends", "by 10/1/2021");
        return list;

    }

    Task[] testList = createlist();

    @Test
    public void todoTest(){
        assertEquals( 'T',testList[0].getD());
    }

    @Test
    public void deadlineTest(){
        assertEquals( 'D',testList[1].getD());
    }

    @Test
    public void eventTest(){
        assertEquals( 'E',testList[2].getD());
    }

    @Test
    public void dateTest(){
        String[] date = {"13/10/2008","20/2/2020", "4/3/2021"};
        for (int i =1; i<-3; i++){
            assertEquals(date[i-1],testList[i-1].getTime());

        }
    }
}