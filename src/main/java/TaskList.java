import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void storeList (String input) throws IllegalInputException {

        while (true) {
            // here task/categorization assignment is being done by task.java
            this.tasks.add(new Task(input));
            System.out.println("\tGot it. I've added this task: ");
            System.out.println("\t" + tasks.get(Duke.numberOftask).getTag() + "[" + tasks.get(Duke.numberOftask).getStatusIcon() + "] " + tasks.get(Duke.numberOftask).description +  tasks.get(Duke.numberOftask).getTimeDate());
            System.out.println("\tNow you have " + (Duke.numberOftask+1) +" task in the list ");
            Duke.numberOftask++;
            assert Duke.numberOftask >= 0 : "number of task must be more than 0";
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
        assert Duke.numberOftask <= -1 : "number of task cannot be less than 0";
        for(int i = 1 ; i <= Duke.numberOftask ; i++) {
            System.out.println("\t" + i+". "  + this.tasks.get(i-1).getTag() + "[" + this.tasks.get(i-1).getStatusIcon() + "] " + this.tasks.get(i-1).description  + this.tasks.get(i-1).getTimeDate());
        }
    }

    /**
     * Prints taskList of a specific date invoked when user inputs list in UI
     * @return empty if list is empty, if not prints contents of list
     */
    public void printScheduledView(String scheduledDate) {
        int j = 1;
        int countForEmptyList = 0;
        int countForNotEmptyList = 0;

        for(int i = 1 ; i <= Duke.numberOftask ; i++) {
            String scheduledDate2;
            LocalDate formattedDate;
            System.out.println(scheduledDate + "zero");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            formattedDate = LocalDate.parse(scheduledDate, formatter);
            scheduledDate2 = formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            if (this.tasks.get(i-1).getTimeDate().contains(scheduledDate2)) {

                countForNotEmptyList++;
                if(countForNotEmptyList == 1){
                    System.out.println("\tHere are the tasks in your list planned that day: ");
                }

                System.out.println("\t" + j + ". " + this.tasks.get(i - 1).getTag() + "[" + this.tasks.get(i - 1).getStatusIcon() + "] " + this.tasks.get(i - 1).description + this.tasks.get(i - 1).getTimeDate());
                j++;
            }
            else {

                countForEmptyList++;
                if (countForEmptyList == Duke.numberOftask)
                System.out.println(Duke.humanName + "\tthe list is empty! It seems you do not have anything that day!");
            }
        }
    }

    /**
     * Handles response of /find command for user input
     * Prints taskList of a user specified keyword
     * @return empty if there is no matching keyword in tasklist
     */
    public void printMatchingTask(String matchTerm) {
        int countForNotEmptyList = 0;
        int countForEmptyList = 0;

        System.out.println("\tHere are the matching tasks in your list: ");
        for(int i = 1 ; i <= 1 ; i++) {
            Pattern pattern = Pattern.compile(matchTerm, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(this.tasks.get(i - 1).description);
            if (matcher.find()) {
                countForEmptyList++;
                countForNotEmptyList++;
                System.out.println("\t" + i + ". " + this.tasks.get(i - 1).getTag() + "[" + this.tasks.get(i - 1).getStatusIcon() + "] " + this.tasks.get(i - 1).description + this.tasks.get(i - 1).getTimeDate());
            }
        }
        for(int i = 2 ; i <= Duke.numberOftask ; i++) {

            Pattern pattern2 = Pattern.compile(matchTerm, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(this.tasks.get(i - 1).description);
            if (matcher2.find()){
                countForEmptyList++;
                countForNotEmptyList++;
                System.out.println("\t" + countForNotEmptyList + ". " + this.tasks.get(i - 1).getTag() + "[" + this.tasks.get(i - 1).getStatusIcon() + "] " + this.tasks.get(i - 1).description + this.tasks.get(i - 1).getTimeDate());
            }
        }
        if (countForEmptyList == 0){
            System.out.println(Duke.humanName + "\tthe list is empty! or It seems there is no matching item");
        }
    }

}

