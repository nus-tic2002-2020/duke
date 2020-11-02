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

    public void welcome() {
        String logo = "  ___    *   *    ____        *\n"
                + "|  _  | | | | |  |  _ \\ _   _| | _____\n"
                + "| |_| | | |_| |  | | | | | | | |/ / _ \\\n"
                + "|  _  | |  _  |  | |_| | |_| |   <  __/\n"
                + "| | | | | | | |  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void greet() {
        echoLine();
        echo("Oei! I'm Ah Duke");
        echo("What can I do for you leh?");
        echoLine();
    }

    public void bye() {
        echo("    Bye bye. Hope to see you again soon!");
    }

    public void loadError() {
        echoLine();
        echo("    Cannot load leh!");
        echoLine();
    }

    public void errorTaskFull() {
        echoLine();
        echo("    Peiseh, my task list is full!");
        echoLine();
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
