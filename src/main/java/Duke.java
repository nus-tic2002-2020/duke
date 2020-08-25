import java.util.Scanner;

public class Duke {
    //for printing single line reply
    public static void dukeReply(String dukeEcho){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + dukeEcho);
        System.out.println("\t____________________________________________________________");
    }

    //storing single item into list
    public static String[] storeList(String[] curList, String input){
        int counter = 0;
        while(true){
            if(curList[counter] == null){
                curList[counter] = input;
                dukeReply("added: " + input);
                break;
            }
            counter++;
        }//end while loop
        return curList;
    }

    //print items in list
    public static void printList(String[] curList){
        if(curList[0] == null){
            dukeReply("List if empty");
            return; //end method
        }//if end
        System.out.println("\t____________________________________________________________");
        for (int i = 1 ; i <= curList.length ; i++){
            System.out.println("\t" + i + ". " + curList[i-1] );
            if(curList[i] == null){
                System.out.println("\t____________________________________________________________");
                return; //end method
            }
        }//for loop end
    }


    public static String[] printUserInput(String[] curList){
        //user input
        String user_input;
        Scanner input = new Scanner(System.in);
        user_input = input.nextLine();

        //bye: end program
        if(user_input.equals("bye")){
            dukeReply("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        //list: print list
        else if(user_input.equals("list")){
            printList(curList);
        }
        //store item
        else{
            curList = storeList(curList, user_input);
        }
        return curList;
    }

    public static void main(String[] args) {
        String[] List = new String[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeReply("Hello! I'm Duke\n\tWhat can I do for you?");
        while(true){
            List = printUserInput(List);
        }
    }
}
