import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI  {
    /*Handling user input*/

    public void readInput(TaskList tasks, Storage storage) {
        //reads in user input
        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();

        //replies to user input
        try {
            response(task, storage, userSentence);
        } catch (IllegalInputException e) {
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void response(ArrayList<Task> listOfThings, String userSentence)throws IllegalInputException {
        Pattern pattern1 = Pattern.compile((".*" + "bye" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(userSentence)

        Pattern pattern2 = Pattern.compile((".*" + "list" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userSentence);

        Pattern pattern3 = Pattern.compile((".*" + "done" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(userSentence);

        Pattern pattern4 = Pattern.compile((".*" + "delete" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher4 = pattern4.matcher(userSentence);


        //when user inputs bye
        if (matcher1.find()) {
            storage.save(tasks);
            System.out.println("\tBye! Hope to see you again soon " + Duke.humanName);
            System.exit(0);
        }
        // when user inputs list
        if (matcher2.find()) {
            TaskList.printListOfThings(listOfThings); //
        }
        //handling user input 'done'
        else if (matcher3.find()) {
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            Duke.taskNumber = Integer.parseInt(numberOnly);
            System.out.println("\tNice! I've marked this task as done: ");
            listOfThings.get(Duke.taskNumber - 1).isDone = true; // this might just access not modify
            System.out.println(listOfThings.get(Duke.taskNumber - 1).getTag() + " [" + listOfThings.get(Duke.taskNumber - 1).getStatusIcon() + "] " + listOfThings.get(Duke.taskNumber - 1).description);
        }
        //handling user input delete
        else if (matcher4.find()) {
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            Duke.taskNumber = Integer.parseInt(numberOnly);
            Duke.numberOftask--;
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println(listOfThings.get(Duke.taskNumber - 1).getTag() + " [" + listOfThings.get(Duke.taskNumber - 1).getStatusIcon() + "] " + listOfThings.get(Duke.taskNumber - 1).description);
            // add delete logic here by modifying listOfThings
            listOfThings.remove(Duke.taskNumber - 1);
        }
        // all else will be added into tasklist
        else {
            listOfThings = TaskList.storeList(listOfThings, userSentence);

        }
        return listOfThings;
    }


}
