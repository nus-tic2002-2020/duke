package duke;
import duke.task.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class Duke {
    /**Variables of Duke Class*/
    private Storage newStorage;
    private TaskList newTaskList;
    private UI newUI;

    /**
     * Constructor of Duke class
     *
     * @param filePath file path
     */
    public Duke(String filePath){
        newUI = new UI();
        newStorage  = new Storage(filePath);
        newTaskList = new TaskList();
        newStorage.load(newTaskList, newUI);
    }

    /**
     * Initialize Duke
     */
    public void run(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nHello from\n" + logo);
        dukeSays("Hello! I'm Duke\n\tWhat can I do for you?");
        while(true){
            newUI.reader(newTaskList, newStorage);
        }
    }

    /**
     * Message printed by Duke which include top and bottom line
     *
     * @param message to be printed
     */
    public static void dukeSays(String message){
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
