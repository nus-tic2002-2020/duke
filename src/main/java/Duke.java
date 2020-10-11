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

    public static void commandInput() throws DukeException {
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
                        try{
                            if((userCommand.substring(userCommand.indexOf("mark") + 5, userCommand.length()).trim()).equals("")){
                                throw new DukeException();
                            }System.out.println(markAsDone(Integer.parseInt(userCommand.split(" ")[1])-1));
                        }catch(StringIndexOutOfBoundsException | DukeException e){
                            System.out.println("☹ OOPS!!! Item to be marked cannot be empty.");
                        }
                        break;
                    }
                    if(userCommand.contains("todo")){
                        try{
                            if((userCommand.substring(userCommand.indexOf("todo") + 5, userCommand.length()).trim()).equals("")){
                                throw new DukeException();
                            }setTask(new Todo(userCommand.substring(userCommand.indexOf("todo") + 5, userCommand.length())));
                        }catch(StringIndexOutOfBoundsException | DukeException e){
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    }

                    if (userCommand.contains("deadline")) {
                        try{
                            if((userCommand.substring(userCommand.indexOf("deadline") + 9, userCommand.length()).trim()).equals("")){
                                throw new DukeException();
                            }setTask(new Deadline(userCommand.substring(userCommand.indexOf("deadline") + 9, userCommand.indexOf("by") - 1), userCommand.substring(userCommand.indexOf("by") + 3, userCommand.length())));
                        }catch(StringIndexOutOfBoundsException | DukeException e){
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        break;
                    }

                    if(userCommand.contains("event")){
                        try{
                            if((userCommand.substring(userCommand.indexOf("event") + 6, userCommand.length()).trim()).equals("")){
                                throw new DukeException();
                            }setTask(new Event(userCommand.substring(userCommand.indexOf("event") + 6, userCommand.indexOf("at") - 1), userCommand.substring(userCommand.indexOf("at") + 3, userCommand.length())));
                        }catch(StringIndexOutOfBoundsException | DukeException e){
                            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        break;
                    }
                    else{
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
            }

        }while(!(userCommand.equals("bye")));

    }

    public static void main(String[] args) throws DukeException {
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
