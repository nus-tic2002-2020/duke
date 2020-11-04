import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *Handles Ui
 *
 * reads user input, and gives response
 */
public class Ui {
    public String scheduledDate;
    public String matchTerm;
    public TaskList tasks;
    public Storage storage;

    public Ui (TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Read input from user and process.
     * Error processing will be handle in this method.
     * uses user input to get response.
     *
     */

    public void readInput() {
        //reads in user input
        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();
        //replies to user input
        try {
            System.out.println(userSentence);
            response(userSentence);
        } catch (IllegalInputException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.print("Follow the correct syntax below:\n" +
                    "\tlist\n"+
                    "\tdone <task number>\n"+
                    "\tdelete <task number>\n"+
                    "\ttodo <description>\n"+
                    "\tdeadline <description> /by <DD/MM/YYYY>\n" +
                    "\tevent <description> /at <DD/MM/YYYY>\n" +
                    "\tfind <description>\n" +
                    "\tcheck <DD/MM/YYYY>\n" );
        }
    }

    /**
     * Direct input to correct response/method
     * handles bye,list,done deletion of task, addition of task
     * @param userSentence read from scanner
     * @throws IllegalInputException if input is invalid
     */
    public void response(String userSentence) throws IllegalInputException {
        Pattern pattern1 = Pattern.compile((".*" + "bye" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(userSentence);

        Pattern pattern2 = Pattern.compile((".*" + "list" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userSentence);

        Pattern pattern3 = Pattern.compile((".*" + "done" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(userSentence);

        Pattern pattern4 = Pattern.compile((".*" + "delete" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher4 = pattern4.matcher(userSentence);

        Pattern pattern5 = Pattern.compile((".*" + "check" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher5 = pattern5.matcher(userSentence);

        Pattern pattern6 = Pattern.compile((".*" + "find" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher6 = pattern6.matcher(userSentence);

        //when user inputs bye
        if (matcher1.find()) {
            storage.save(tasks);
            System.out.println("\tBye! Hope to see you again soon " + Duke.humanName);
            System.exit(0);
        }
        // when user inputs list
        if (matcher2.find()) {
            tasks.printTasks();
        }

        else if (matcher5.find()) {
            String[] temp;
            temp = userSentence.split("\\s",2);
            scheduledDate = temp[1];
            tasks.printScheduledView(scheduledDate);
        }

        else if (matcher6.find()) {
            String[] temp;
            temp = userSentence.split("\\s",2);
            matchTerm = temp[1];
            tasks.printMatchingTask(matchTerm);
        }

        //handling user input 'done'
        else if (matcher3.find()) {
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            Duke.taskNumber = Integer.parseInt(numberOnly);
            System.out.println("\tNice! I've marked this task as done: ");
            tasks.tasks.get(Duke.taskNumber - 1).isDone = true; // this might just access not modify
            System.out.println(tasks.tasks.get(Duke.taskNumber - 1).getTag() + " [" + tasks.tasks.get(Duke.taskNumber - 1).getStatusIcon() + "] " + tasks.tasks.get(Duke.taskNumber - 1).description);
        }
        //handling user input delete
        else if (matcher4.find()) {
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            Duke.taskNumber = Integer.parseInt(numberOnly);
            Duke.numberOftask--;
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println(tasks.tasks.get(Duke.taskNumber - 1).getTag() + " [" + tasks.tasks.get(Duke.taskNumber - 1).getStatusIcon() + "] " + tasks.tasks.get(Duke.taskNumber - 1).description);
            // add delete logic here by modifying tasks
            tasks.tasks.remove(Duke.taskNumber - 1);
        }
        // all else will be added into tasklist
        else {
            tasks.storeList(userSentence);
        }
    }
}
