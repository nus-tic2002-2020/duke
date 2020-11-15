package seedu.duke.ui;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "_______________________________________________________\n";
    private final Scanner input;
    private String output;

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
     * Prints the welcome message
     */
    public static void showWelcomeMessage() {
        String logo
                = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER + "\tHello from\n" + logo + "\tWhat can I do for you?\n" + DIVIDER);
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbyeMessage() {
        output = DIVIDER + "Bye. Hope to see you again soon.\n" + DIVIDER;
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
     * Prints the output accordingly.
     */
    public String showOutput() {
        return this.output;
    }

    public void showOutputToUser(String output){
        this.output = output;
    }

    /**
     * Prints exception/error message.
     * @param    errorMessage       The exception/error message.
     */
    public String showLoadingError(String errorMessage) {
        return DIVIDER + "\u2639 OOPS!!! " + errorMessage + "\n" + DIVIDER;
    }
}