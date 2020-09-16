package duke.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a list of different possible configurations of Date and Time formats that the user might input.
 * <br> It allows for flexibility in the user-input and tries to catch as many patterns as possible without throwing an error.
 */

public class DateTimeList {
    protected static List<DateTimeFormatter> knownPatterns = new ArrayList<>();

    /**
     * This method adds the various kinds of Date and Time formats into the knownPatterns list.
     */

    private static void formatMatch() {
        knownPatterns.add(DateTimeFormatter.ofPattern("y/M/d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y/M/d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M/d/y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M/d/y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/M/y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/M/y HHmm"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y-M-d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y-M-d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M-d-y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M-d-y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-M-y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-M-y HHmm"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y M d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y M d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M d y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M d y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d M y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d M y HHmm"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y/MMM/d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y/MMM/d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM/d/y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM/d/y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/MMM/y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/MMM/y HHmm"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y-MMM-d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y-MMM-d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM-d-y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM-d-y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-MMM-y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-MMM-y HHmm"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y MMM d H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y MMM d HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM d y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM d y HHmm"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d MMM y H:m"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d MMM y HHmm"));

        //------------------------------------------------------------//

        knownPatterns.add(DateTimeFormatter.ofPattern("y/M/d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y/M/d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M/d/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M/d/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/M/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/M/y"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y-M-d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y-M-d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M-d-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M-d-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-M-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-M-y"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y M d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y M d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M d y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("M d y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d M y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d M y"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y/MMM/d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y/MMM/d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM/d/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM/d/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/MMM/y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d/MMM/y"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y-MMM-d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y-MMM-d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM-d-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM-d-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-MMM-y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d-MMM-y"));

        knownPatterns.add(DateTimeFormatter.ofPattern("y MMM d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("y MMM d"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM d y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("MMM d y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d MMM y"));
        knownPatterns.add(DateTimeFormatter.ofPattern("d MMM y"));

    }
    public static void main() {
        formatMatch();
    }
}