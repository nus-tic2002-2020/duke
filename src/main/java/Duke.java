import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Duke {

    public static String humanName;
    public static int taskNumber;
    public static int numberOftask = 0;




// Storing userSentence into list
// storage

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

    //print list
    public static void printListOfThings(ArrayList<Task> listOfThings) {
        if(listOfThings.size() == 0) {
            System.out.println(humanName + "\tthe list is empty!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
//        for (int i = 1; i <= listOfThings.length; i++) {
//
//            System.out.println("\t" + i + ". " + listOfThings[i-1].getTag() +  "[" + listOfThings[i-1].getStatusIcon() + "] " + listOfThings[i - 1].description );
//           // String lineItem = listOfThings[i-1].getTag() +  " | " + listOfThings[i-1].getStatusIcon()  + " | " + listOfThings[i - 1].description;
//
//            if (listOfThings[i] == null) {
//                System.out.println("\tOk that's about everything!");
//                return;
//            }
//        }
        for(int i = 1 ; i <= numberOftask ; i++) {
            System.out.println("\t" + i+". "  + listOfThings.get(i-1).getTag() + "[" + listOfThings.get(i-1).getStatusIcon() + "] " + listOfThings.get(i-1).description );
        }

//        for (Task temp: listOfThings){
//            int i = 1;
//            System.out.println("\t" + i+". "  + temp.getTag() + "[" + temp.getStatusIcon() + "] " + temp.description );
//            i++;
//        }
    }

//UI
    public static ArrayList<Task> echoCommands(ArrayList<Task> listOfThings) throws IllegalInputException {

        //int taskNumber;

        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();

        Pattern pattern1 = Pattern.compile((".*" + "bye" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(userSentence);
        if (matcher1.find()) {
            System.out.println("\tBye! Hope to see you again soon " + humanName);
            System.exit(0);
        }

        Pattern pattern2 = Pattern.compile((".*" + "list" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userSentence);
        Pattern pattern3 = Pattern.compile((".*" + "done" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(userSentence);
        Pattern pattern4 = Pattern.compile((".*" + "delete" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher4 = pattern4.matcher(userSentence);
        if (matcher2.find()) {
            printListOfThings(listOfThings); //
        }
        else if (matcher3.find()){
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            taskNumber = Integer.parseInt(numberOnly);
            System.out.println("\tNice! I've marked this task as done: ");
            listOfThings.get(taskNumber-1).isDone = true; // this might just access not modify
            System.out.println(listOfThings.get(taskNumber-1).getTag()+" ["+listOfThings.get(taskNumber-1).getStatusIcon()+"] "+ listOfThings.get(taskNumber - 1).description );
        }
        //handling delete
        else if (matcher4.find()){
            Pattern pattern = Pattern.compile("[^0-9]");
            String numberOnly = pattern.matcher(userSentence).replaceAll("");
            taskNumber = Integer.parseInt(numberOnly);
            numberOftask--;
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println(listOfThings.get(taskNumber-1).getTag()+" ["+listOfThings.get(taskNumber-1).getStatusIcon()+"] "+ listOfThings.get(taskNumber-1).description );
        // add delete logic here by modifying listOfThings
            listOfThings.remove(taskNumber-1);

        }
        else{
            listOfThings = storeList(listOfThings, userSentence);

        }

            return listOfThings;
        }




        public static void main (String[] args) throws IllegalInputException {
            ArrayList<Task> List = new ArrayList<Task>() ;



            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            //yyyy/MM/dd
            System.out.println("\tHello! I'm Duke\n\tWhat is your name?");


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            //String humanName;
            Scanner in = new Scanner(System.in);
            System.out.println("What is your name?\n");
            humanName = in.nextLine();

            System.out.println(humanName + " today is: " + (dtf.format(now)));
            System.out.println("What can I do for you?\n");
            //Maybe list out supported commands?

            while (true) {
                //echoCommands();
                List = echoCommands(List);
            }


        }

}
