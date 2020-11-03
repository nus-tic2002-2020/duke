
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void printLine(){
		System.out.println("\t____________________________________________________________");
    }

    public static ArrayList<Task> printUserInput(ArrayList<Task> listItems) {
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
                Task t = new Events(eventDescription, dateTime);
                listItems.add(t);

                printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems.get(listItems.size()-1).toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                printLine();

            }
            // add deadline task
            else if (userInput.startsWith("deadline")){
                int divPosition = userInput.indexOf("/");
                String deadlineDescription = userInput.substring(9,divPosition-1);
                String filteredDate = userInput.substring(divPosition + 4);
                Task t = new Deadline(deadlineDescription, filteredDate);
                listItems.add(t);

                printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems.get(listItems.size()-1).toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                printLine();
            }
            // add to do task
            else if (userInput.startsWith("todo")) {
                try{
                    String todoDescription = userInput.substring(5);
                    CheckEmpty(todoDescription);
                    Task t = new Todo(todoDescription);
                    listItems.add(t);

                    printLine();
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t" + listItems.get(listItems.size()-1).toString());
                    System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                    printLine();
                }
                catch (StringIndexOutOfBoundsException e) {
                    printLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                    }
            }
            else if (userInput.startsWith("delete")) {
                int numTask = Integer.valueOf(userInput.substring(7, userInput.length()));
                Task t = listItems.get(numTask - 1);
                listItems.remove(numTask - 1);
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t" + t.toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                printLine();
            }
          /*   else{
                printLine();
                listItems = storeUserList (listItems, userInput);
                printLine();
                } */
        }
    }

    //store items in list
    public static ArrayList<Task> storeUserList(ArrayList<Task> listItems, String input) {
        int counter = 0;
            while(true){
                listItems.add(new Task (input));
                System.out.println ("\tadded: " + input);
                break;

            }
        return listItems;
    }

        //print items in list
    public static void printUserList(ArrayList<Task> listItems){
        if(listItems.size() == 0){
            System.out.println("List is empty");
            return;
        }
        printLine();

        for (int i = 0 ; i < listItems.size(); i++){
            System.out.println("\t" + (i+1) + ". " + listItems.get(i).toString());
        }

        printLine();
        return;
    }



    public static ArrayList<Task> doneMessage(ArrayList<Task> listItems, String userInput)   {

       try {
           int filteredNumber = Integer.parseInt(userInput.substring(5));
           listItems.get(filteredNumber - 1).markDone();

           printLine();
           System.out.println("\tNice! I've marked this task as done:");
           System.out.println("\t" + listItems.get(filteredNumber - 1).toString());
           printLine();

       }
        catch (NumberFormatException e){
            printLine();
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printLine();
        }
       catch (NullPointerException e){
           printLine();
           System.out.println("\t☹ OOPS!!! Numbers out of range :-(");
           printLine();
       }
        return listItems;
    }

    private static void CheckEmpty(String description) throws StringIndexOutOfBoundsException {
        if (description.isEmpty()){
            throw new StringIndexOutOfBoundsException ();
        }
    }

    public static void main(String[] args) {
      //  String [] ListofItems = new String[100];
        ArrayList<Task> listTask = new ArrayList<Task>();

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
