import java.util.ArrayList;


public class TaskList {

    public ArrayList<Task> tasks;
    public int numberOftask = 0;

    public TaskList(){
       this.tasks = new ArrayList<Task>();
    }

    public void storeList (String input) throws IllegalInputException { //tasks should be array class task?

        while (true) {
            // here task/categorization assignment is being done by task.java

            tasks.add(new Task(input));
            System.out.println("\tGot it. I've added this task: ");
            System.out.println("\t" + tasks.get(numberOftask).getTag() + "[" + tasks.get(numberOftask).getStatusIcon() + "] " + tasks.get(numberOftask).description +  tasks.get(numberOftask).timeDate);
            System.out.println("\tNow you have " + (numberOftask+1) +" task in the list ");

            numberOftask++;
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
        if(tasks.size() == 0) {
            System.out.println(Duke.humanName + "\tthe list is empty!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");

        for(int i = 1 ; i <= numberOftask ; i++) {
            System.out.println("\t" + i+". "  + tasks.get(i-1).getTag() + "[" + tasks.get(i-1).getStatusIcon() + "] " + tasks.get(i-1).description );

        }

    }
}

