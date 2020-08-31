import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static String humanName;
   // public static String [] listOfThings;
    // Storing userSentence into list

    public static String[] storeList(String[] listOfThings, String input){
        int counter = 0;
        while(true){
            if(listOfThings[counter] == null){
                listOfThings[counter] = input;
                System.out.println("\tadded: " + input);
                break;
            }
            counter++;
        }//end while loop
        return listOfThings;
    }

//print list
    public static void printListOfThings(String [] listOfThings){
        if (listOfThings[0] == null){
            System.out.println(humanName+"\tthe list is empty!");
            return;
        }

        for (int i = 1 ; i <= listOfThings.length ; i++){
            System.out.println("\t" + i + ". " + listOfThings[i-1] );
            if(listOfThings[i] == null){
                System.out.println("\tOk that's about everything!");
                return;
            }
        }
    }


    public static String [] echoCommands (String[] listOfThings)
    {
        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();

        Pattern pattern1  = Pattern.compile((".*" + "bye" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher1  = pattern1.matcher(userSentence);
        if (matcher1.find()){
            System.out.println("\tBye! Hope to see you again soon " +  humanName);
            System.exit(0);
        }


        Pattern pattern2 = Pattern.compile((".*" + "list" + ".*"), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userSentence);
        if (matcher2.find()) {
            printListOfThings(listOfThings);
        }


        else {
        listOfThings = storeList(listOfThings, userSentence);
           // System.out.println("\tAdded: "+ userSentence);
        }
        return listOfThings;
    }



    public static void main(String[] args) {
        String[] List = new String[100];
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

        while(true){
            //echoCommands();
            List = echoCommands(List);
        }


    }
}

