import java.io.*;
import java.lang.*;
import java.util.*;

public class Duke {
    private static Task[] t = new Task[100];
    private static int arrCounter = 0;

    public static void setTask(Task description){
        t[arrCounter] = description;
        arrCounter++;
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
                case "print list":
                    for(int i = 0; i<arrCounter; i++){
                        System.out.println(i+1 + ".[" +t[i].getStatusIcon() + "] " +t[i].description);
                    }
                    break;
                default:
                    if(userCommand.contains("mark")){
                        System.out.println(markAsDone(Integer.parseInt(userCommand.split(" ")[1])-1));
                        break;
                    }
                    setTask(new Task(userCommand));
                    System.out.println("added: " + userCommand);
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
