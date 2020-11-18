import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;

public class Duke {
    private UI ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = new TaskList();
        welcome();
        storage.LoadFile(tasks, ui);
    }

    public void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void run() {
        while (true) {
            ui.read(tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

