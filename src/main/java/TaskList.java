import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Stores user input as taskList, categorisation is done by task.java
     * Successful addition will output added task massage if not exception is thrown.
     *
     */
    public void storeList (String input) throws IllegalInputException { //tasks should be array class task?

        while (true) {
            // here task/categorization assignment is being done by task.java
            this.tasks.add(new Task(input));
            System.out.println("\tGot it. I've added this task: ");
            System.out.println("\t" + tasks.get(Duke.numberOftask).getTag() + "[" + tasks.get(Duke.numberOftask).getStatusIcon() + "] " + tasks.get(Duke.numberOftask).description +  tasks.get(Duke.numberOftask).getTimeDate());
            System.out.println("\tNow you have " + (Duke.numberOftask+1) +" task in the list ");

            Duke.numberOftask++;
            break;

        }//end while loop


    }

    /**
     * Processing of task details to save in text file
     * used in storage.java
     * @param index of the Task details to be processed
     * @return string in format to be saved
     */

    public String processtoTextfile(int index){
        String status = (this.tasks.get(index - 1).getStatusIcon());
        String lineItem = this.tasks.get(index - 1).getTag() + " | " + status + " | " + this.tasks.get(index - 1).description;
        if(this.tasks.get(index - 1).getTag() != "T"){
            lineItem += ("| " + this.tasks.get(index - 1).timeDate);
        }
        return lineItem;
    }

    /**
     * Prints tasklist invoked when user inputs list in UI
     * @return empty if list is empty, if not prints contents of list
     */

    public void printTasks() {
        if(this.tasks.size() == 0) {
            System.out.println(Duke.humanName + "\tthe list is empty!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");

        for(int i = 1 ; i <= Duke.numberOftask ; i++) {
            System.out.println("\t" + i+". "  + this.tasks.get(i-1).getTag() + "[" + this.tasks.get(i-1).getStatusIcon() + "] " + this.tasks.get(i-1).description );

        }

    }
}

