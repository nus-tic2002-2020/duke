package duke.io;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.List;
import java.util.Scanner;

public class Ui {

    protected Scanner in = new Scanner(System.in);

    public String readCommand() {
        if (in.hasNextLine()) {
            String input = in.nextLine();
            return input.trim();
        }
        return null;
    }

    public String welcome() {
        String logo = "  ___    *   *    ____        *\n"
                + "|  _  | | | | |  |  _ \\ _   _| | _____\n"
                + "| |_| | | |_| |  | | | | | | | |/ / _ \\\n"
                + "|  _  | |  _  |  | |_| | |_| |   <  __/\n"
                + "| | | | | | | |  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        return "Hello from\n" + logo;
    }

    public String greet() {
        echoLine();
        echo("Oei! I'm Ah Duke");
        echo("What can I do for you leh?");
        echoLine();
        return "Oei! I'm Ah Duke\nWhat can I do for you leh?";
    }

    public String bye() {
        echo("    Bye bye. Hope to see you again soon!");
        return "Bye bye. Hope to see you again soon!";
    }

    public String loadError() {
        echoLine();
        echo("    Cannot load leh!");
        echoLine();
        return "Cannot load leh!";
    }

    public String errorTaskFull() {
        echoLine();
        echo("    Peiseh, my task list is full!");
        echoLine();
        return "Peiseh, my task list is full!";
    }

    public void echoLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void echoAll(List<String> messages) {
        for (String m: messages) {
            echo(m);
        }
    }

    public void echo(String message) {
        System.out.printf("    %s\n", message);
    }

}
