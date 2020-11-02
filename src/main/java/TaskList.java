import java.util.ArrayList;


public class TaskList {

    public ArrayList<Task> tasks;
    //public int numberOftask = 0;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void storeList (String input) throws IllegalInputException { //tasks should be array class task?

        while (true) {
            // here task/categorization assignment is being done by task.java
            this.tasks.add(new Task(input));
            System.out.println("\tGot it. I've added this task: ");
            System.out.println("\t" + tasks.get(Duke.numberOftask).getTag() + "[" + tasks.get(Duke.numberOftask).getStatusIcon() + "] " + tasks.get(Duke.numberOftask).description +  tasks.get(Duke.numberOftask).timeDate);
            System.out.println("\tNow you have " + (Duke.numberOftask+1) +" task in the list ");

            Duke.numberOftask++;
            break;

        }//end while loop


    }

    public String convert_lineItem(int index){
        String status = (this.tasks.get(index - 1).getStatusIcon());
        String lineItem = this.tasks.get(index - 1).getTag() + " | " + status + " | " + this.tasks.get(index - 1).description;
        if(this.tasks.get(index - 1).getTag() != "T"){
            lineItem += ("| " + this.tasks.get(index - 1).timeDate);
        }
        return lineItem;
    }

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

