import java.io.*;
import java.lang.*;
import java.util.*;

public class Duke {
    private static Task[] t = new Task[100];
    private static int arrCounter = 0;

    public static void setTask(Task description){
        t[arrCounter] = description;
        System.out.println("Got it. I've added this task:");
        System.out.println(t[arrCounter].toString());
        arrCounter++;
        System.out.println("Now you have " + arrCounter +  " tasks in the list.");

    }
    public static String markAsDone(int taskNumber){
        return t[taskNumber].setDone();
    }
    
    public static void commandInput(){
        int arrSize = 100;
        String[] commands = new String[arrSize];
        Scanner sc = new Scanner(System.in);
        String userCommand;
        do{
            userCommand = sc.nextLine();

            switch(userCommand){
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    for(int i = 0; i<arrCounter; i++){
                        System.out.println(i+1 + t[i].toString());
                    }
                    break;
                default:
                    if(userCommand.contains("mark")){
                        System.out.println(markAsDone(Integer.parseInt(userCommand.split(" ")[1])-1));
                        break;
                    }
                    if(userCommand.contains("todo")){
                        setTask(new Todo(userCommand.substring(userCommand.indexOf("todo") + 5, userCommand.length())));
                        break;
                    }
                    if(userCommand.contains("deadline")){
                        setTask(new Deadline(userCommand.substring(userCommand.indexOf("deadline") + 9, userCommand.indexOf("by") - 1), userCommand.substring(userCommand.indexOf("by") + 3, userCommand.length())));
                        break;
                    }
                    if(userCommand.contains("event")){
                        setTask(new Event(userCommand.substring(userCommand.indexOf("event") + 6, userCommand.indexOf("at") - 1), userCommand.substring(userCommand.indexOf("at") + 3, userCommand.length())));
                        break;
                    }
                    else{
                        setTask(new Task(userCommand));
                    }
            }

        }while(!(userCommand.equals("bye")));

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        commandInput();
    }
}
