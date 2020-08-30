import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static String humanName;

    public static void echoCommands ()
    {
        String userSentence;
        Scanner input = new Scanner(System.in);
        userSentence = input.nextLine();

        Pattern pattern = Pattern.compile(".*" + "bye" + ".*");
        Matcher matcher = pattern.matcher(userSentence);
        if (matcher.find()){
            System.out.println("\tBye! Hope to see you again soon" +  humanName);
            System.exit(0);
        }

        else {
            System.out.println("\t" + userSentence);
        }
    }



    public static void main(String[] args) {
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
            echoCommands();
        }


    }
}

