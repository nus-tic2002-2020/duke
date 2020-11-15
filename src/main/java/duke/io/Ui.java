package duke.io;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.List;
import java.util.Scanner;

/**
 * Command line UI class
 */
public class Ui {

    protected Scanner in = new Scanner(System.in);

    /**
     * Read raw user command from input stream
     * @return raw user command as string
     */
    public String readCommand() {
        if (in.hasNextLine()) {
            String input = in.nextLine();
            return input.trim();
        }
        return null;
    }

    /**
     * Helper method to display and return welcome message
     * @return welcome string
     */
    public String welcome() {
        String logo = "  ___    *   *    ____        *\n"
                + "|  _  | | | | |  |  _ \\ _   _| | _____\n"
                + "| |_| | | |_| |  | | | | | | | |/ / _ \\\n"
                + "|  _  | |  _  |  | |_| | |_| |   <  __/\n"
                + "| | | | | | | |  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        return "Hello from\n" + logo;
    }

    /**
     * Helper method to display and return greeting message
     * @return greeting string
     */
    public String greet() {
        echoLine();
        echo("Oei! I'm Ah Duke");
        echo("What can I do for you leh?");
        echoLine();
        return "Oei! I'm Ah Duke\nWhat can I do for you leh?";
    }

    /**
     * Helper method to display and return goodbye message
     * @return goodbye message
     */
    public String bye() {
        echo("    Bye bye. Hope to see you again soon!");
        return "Bye bye. Hope to see you again soon!";
    }

    /**
     * Helper method to display and return load error message
     * @return load error message
     */
    public String loadError() {
        echoLine();
        echo("    Cannot load leh!");
        echoLine();
        return "Cannot load leh!";
    }

    /**
     * Helper method to display and return task list full error message
     * @return task list full error message
     */
    public String errorTaskFull() {
        echoLine();
        echo("    Peiseh, my task list is full!");
        echoLine();
        return "Peiseh, my task list is full!";
    }

    /**
     * Helper method to print and return horizontal line
     * @return command line horizontal line string
     */
    public void echoLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Helper method to display list of messages in command line
     * @param messages as list of messages to display
     */
    public void echoAll(List<String> messages) {
        for (String m: messages) {
            echo(m);
        }
    }

    /**
     * Helper method to print message in command line
     * @param message to display
     */
    public void echo(String message) {
        System.out.printf("    %s\n", message);
    }

}
