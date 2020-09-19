package main;
import java.util.Scanner;
import java.util.ArrayList;
//import parser.InputText;

public class Duke {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        /*level 1*/
        while (true) {
            String input = in.nextLine();
            if(!input.equals("list")){
                list.add(input);
            }
            if(input.equals("list")) {
                int i=0;
                for(String name:list) {
                    System.out.println(++i + ". " + name);
                }
            }
            if(!input.equals("bye") && !input.equals("list")){
                System.out.println("added: " + input);
            }

             if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
        }
    }
}
