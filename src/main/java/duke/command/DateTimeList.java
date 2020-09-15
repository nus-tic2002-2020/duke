package duke.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTimeList {
    protected static List<DateTimeFormatter> knownPatterns = new ArrayList<>();


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