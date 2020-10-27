import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class Duke {
    //variables
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    //constructor
    public Duke(String filepath){
        ui = new UI();
        storage  = new Storage(filepath);
        tasks = new TaskList();
        storage.load(tasks, ui);
    }
    //accessors
    public void run(){
        //Duke header
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nHello from\n" + logo);
        duke_says("Hello! I'm Duke\n\tWhat can I do for you?");

        while(true){
            ui.reader(tasks, storage);
        }

    }
    public static void duke_says(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }//end duke_says

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }//end main
}
