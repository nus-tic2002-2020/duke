package seedu.duke.ui;

import java.io.InputStream;
import java.util.Scanner;

public class Ui{

    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "\n\t____________________________________________________________\n";
    private final Scanner input;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream input) {
        this.input = new Scanner(input);
    }

    private static boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String getUserCommand() {
        String fullInputLine = input.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = input.nextLine();
        }
        return fullInputLine;
    }

    public static void showWelcomeMessage() {
        String logo
                = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER + "\tHello from\n" + logo + "\tWhat can I do for you?");
    }

    public void showGoodbyeMessage() {
        System.out.println(DIVIDER + "\tBye. Hope to see you again soon." + DIVIDER);
    }

    public void showOutputToUser(String output) {
        System.out.println(DIVIDER + "\t" + output + DIVIDER);
    }

    public static void showLoadingError(String errorMessage){
        System.out.println(DIVIDER + "\t☹ OOPS!!! " + "\t" + errorMessage + DIVIDER);
    }
}