package ui;
// deals with interactions with the user

import java.util.Scanner;

public class Ui {
    protected String intro = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public String input;
    Scanner scan;

    public Ui(){

        this.input = "";
        scan = new Scanner(System.in);
    }

    public void printIntro(){
        System.out.println(intro);
    }

    public void showLine(){
        System.out.println("____________________________________");
    }

    public void printBye(){
        System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
    }

    public String scanForInput(){
        System.out.println(System.lineSeparator() + "What can I do for you?");
        return this.scan.nextLine();
    }


    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }



}
