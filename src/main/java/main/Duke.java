package main;
import java.util.Scanner;
//import parser.InputText;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");


        //InputText response = new InputText();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        //response.parseInput(input);
         System.out.println(input);
    }
}
