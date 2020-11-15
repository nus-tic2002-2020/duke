package duke.io;

import duke.command.Duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Command Line mode usage: -c
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("-c")) {
            // Launch Command Line mode
            Duke.main(args);
        } else {
            // Launch GUI mode
            Application.launch(Duke.class, args);
        }
    }
}
