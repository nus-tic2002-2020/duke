package seedu.duke.ui;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "\n\t____________________________________________________________\n";
    private final Scanner input;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream input) {
        this.input = new Scanner(input);
    }

    /**
     * Checks if user input is empty.
     * @param   rawInputLine       The user input.
     * @return  boolean            Return true if user input is empty, otherwise.
     */
    private static boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Reads the user input as command.
     * @return   String       The user input.
     */
    public String getUserCommand() {
        String fullInputLine = input.nextLine();
        while (shouldIgnore(fullInputLine)) { // silently consume all ignored lines
            fullInputLine = input.nextLine();
        }
        return fullInputLine;
    }

    /**
     * Prints the welcome message
     */
    public static void showWelcomeMessage() {
        String logo
                = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER + "\tHello from\n" + logo + "\tWhat can I do for you?" + DIVIDER);
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println(DIVIDER + "\tBye. Hope to see you again soon." + DIVIDER);
    }

    /**
     * Prints the output accordingly.
     */
    public void showOutputToUser(String output) {
        System.out.println(DIVIDER + "\t" + output + DIVIDER);
    }

    /**
     * Prints exception/error message.
     * @param    errorMessage       The exception/error message.
     */
    public static void showLoadingError(String errorMessage) {
        System.out.println(DIVIDER + "\t☹ OOPS!!! " + errorMessage + DIVIDER);
    }
}