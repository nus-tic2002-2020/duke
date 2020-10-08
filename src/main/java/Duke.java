import java.io.*;
import java.lang.*;
import java.util.*;


public class Duke {


    public static void commandInput(){
        int arrSize = 100;
        String[] commands = new String[arrSize];
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < arrSize; i++){

            String userCommand = sc.nextLine();

            if(userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if(userCommand.equals("print list")){
                //System.out.println(Arrays.toString(commands));
                for(int y = 0; y<i; y++){
                    if(commands[y].equals(null)){
                        System.out.println("Please input a value");
                        break;
                    }
                    else{
                        System.out.println(y+1+". "+ commands[y]);
                    }
                }
            }

            else{
                commands[i] = userCommand;
                System.out.println("added: " + userCommand);
            }
        }
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
