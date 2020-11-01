import java.util.ArrayList;


public class TaskList {

    public ArrayList<Task> listOfThings;
    public static int numberOftask = 0;

    public TaskList(){
       this.listOfThings = new ArrayList<Task>();
    }

    public static ArrayList<Task> storeList(ArrayList<Task> listOfThings, String input) throws IllegalInputException { //listOfThings should be array class task?

        while (true) {
            // here task/categorization assignment is being done by task.java

            listOfThings.add(new Task (input));
            System.out.println("\tGot it. I've added this task: ");
            System.out.println("\t" + listOfThings.get(numberOftask).getTag() + "[" + listOfThings.get(numberOftask).getStatusIcon() + "] " + listOfThings.get(numberOftask).description +  listOfThings.get(numberOftask).timeDate);
            System.out.println("\tNow you have " + (numberOftask+1) +" task in the list ");

            numberOftask++;
            break;

        }//end while loop

        return listOfThings;
    }

    public String convert_lineItem(int index){
        String status = (this.listOfThings.get(index - 1).getStatusIcon());
        String lineItem = this.listOfThings.get(index - 1).getTag() + " | " + status + " | "
                + this.listOfThings.get(index - 1).description();
        if(this.listOfThings.get(index - 1).getTag() != 'T'){
            lineItem += ("| " + this.listOfThings.get(index - 1).timeDate);
        }
        return lineItem;
    }

    public static void printListOfThings(ArrayList<Task> listOfThings) {
        if(listOfThings.size() == 0) {
            System.out.println(Duke.humanName + "\tthe list is empty!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");

        for(int i = 1 ; i <= numberOftask ; i++) {
            System.out.println("\t" + i+". "  + listOfThings.get(i-1).getTag() + "[" + listOfThings.get(i-1).getStatusIcon() + "] " + listOfThings.get(i-1).description );

        }

    }
}

