
import java.util.Scanner;

public class Duke {

    public static void printLine(){
		System.out.println("\t____________________________________________________________");
    }

    public static Task[] printUserInput(Task[] listItems) {
        //user input
        String userInput;
        Scanner input = new Scanner(System.in);
        int pointer = 0;

        while (true) {
            userInput = input.nextLine();
            //bye to exit
            if (userInput.equals("bye")) {
                printLine();
                System.out.println("\tBye. Hope to see you again soon!");
                printLine();
                System.exit(0);
            }
            //when type list
            else if (userInput.equals("list")) {
                printUserList(listItems);
            }
            // when type done
            else if (userInput.startsWith("done")) {
                doneMessage(listItems, userInput);
            }
            // add event task
            else if (userInput.startsWith("event")) {
                int divPosition = userInput.indexOf("/");
                String eventDescription = userInput.substring(6, divPosition-1);
                String dateTime = userInput.substring(divPosition + 4);
                listItems[pointer] = new Events(eventDescription, dateTime);

                printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems[pointer].toString());
                System.out.println("\tNow you have " + (pointer + 1) + " tasks in the list.");
                printLine();
                pointer++;

            }
            // add deadline task
            else if (userInput.startsWith("deadline")){
                int divPosition = userInput.indexOf("/");
                String deadlineDescription = userInput.substring(9,divPosition-1);
                String filteredDate = userInput.substring(divPosition + 4);
                listItems[pointer] = new Deadline(deadlineDescription, filteredDate);

                printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems[pointer].toString());
                System.out.println("\tNow you have " + (pointer + 1) + " tasks in the list.");
                printLine();
                pointer++;
            }
            // add to do task
            else if (userInput.startsWith("todo")) {
                String todoDescription = userInput.substring(5);
                listItems[pointer] = new Todo(todoDescription);
                printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems[pointer].toString());
                System.out.println("\tNow you have " + (pointer + 1) + " tasks in the list.");
                printLine();
                pointer++;
            }
          /*   else{
                printLine();
                listItems = storeUserList (listItems, userInput);
                printLine();
                } */
        }
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

        printLine();

        for (int i = 1 ; i <= listItems.length ; i++){
            System.out.println("\t" + i + ". " + "[" + listItems[i-1].getStatusIcon() + "]" + listItems[i-1].description);
                if(listItems[i] == null){
                    printLine();
                    return;
                }
            }
        }

    public static Task[] doneMessage(Task[] listItems, String userInput){


        int filteredNumber = Integer.parseInt(userInput.substring(5));
        listItems[filteredNumber-1].markDone();

        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + listItems[filteredNumber-1].toString());
        printLine();

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