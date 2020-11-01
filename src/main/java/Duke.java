import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Duke {

    public static String humanName;
    public static int taskNumber;
    public static int numberOftask = 0;
    //public static String writetofile;
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filepath){
        this.ui = new UI();
        storage  = new Storage(filepath);
        tasks = new TaskList();
        storage.load(tasks, ui);
    }

    public void run(){

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke\n\tWhat is your name?");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?\n");
        humanName = in.nextLine();

        System.out.println(humanName + " today is: " + (dtf.format(now)));
        System.out.println("What can I do for you?\n");

        while (true) {
            //echoCommands();
            //List = echoCommands(List);
            ui.readInput(tasks, storage);
        }

    }


        public static void main(String[] args) {
        new Duke("duke.txt").run();
    }//end main

}
