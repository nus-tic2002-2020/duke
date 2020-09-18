
import java.util.Scanner;

public class Duke {

    public static Task[] printUserInput(Task[] listItems){
        //user input
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();
        //bye to exit
        if (userInput.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t____________________________________________________________");
            System.exit(0);
        }
        //print list
        else if(userInput.equals ("list")) {
            printUserList(listItems);
        }
        else if (userInput.startsWith ("done"))
        {
            doneMessage(listItems, userInput);

        }
        else{
            System.out.println("\t____________________________________________________________");
            listItems = storeUserList (listItems, userInput);
            System.out.println("\t____________________________________________________________");
        }
        return listItems;
    }

    //store items in list
    public static Task[] storeUserList(Task[] listItems, String input) {
        int counter = 0;
            while(true){
                if(listItems[counter] == null){
                    listItems[counter] = new Task(input);
                    System.out.println ("\tadded: " + input);
                    break;
                }
                counter++;
            }
        return listItems;
    }

        //print items in list
    public static void printUserList(Task[] listItems){
        if(listItems[0] == null){
            System.out.println("List is empty");
            return;
        }

        System.out.println("\t____________________________________________________________");

        for (int i = 1 ; i <= listItems.length ; i++){
            System.out.println("\t" + i + ". " + "[" + listItems[i-1].getStatusIcon() + "]" + listItems[i-1].description);
                if(listItems[i] == null){
                    System.out.println("\t____________________________________________________________");
                    return;
                }
            }
        }

    public static Task[] doneMessage(Task[] listItems, String userInput){


        int filteredNumber = Integer.parseInt(userInput.substring(5));
        listItems[filteredNumber-1].markDone();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + "[" + listItems[filteredNumber-1].getStatusIcon()+"]"+ listItems[filteredNumber-1].description);
        System.out.println("\t____________________________________________________________");


        return listItems;
    }

    public static void main(String[] args) {
      //  String [] ListofItems = new String[100];
        Task[] listTask = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        while (true) {
            listTask= printUserInput(listTask);

        }
    }
}