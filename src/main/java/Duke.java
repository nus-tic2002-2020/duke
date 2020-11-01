import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class Duke {
    /**Variables of Duke Class*/
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor of Duke class
     *
     * @param filepath file path
     */
    public Duke(String filepath){
        ui = new UI();
        storage  = new Storage(filepath);
        tasks = new TaskList();
        storage.load(tasks, ui);
    }

    /**
     * Initialize Duke
     */
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

    /**
     * Message printed by Duke which include top and bottom line
     *
     * @param message to be printed
     */
    public static void duke_says(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }//end duke_says

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }//end main
}
