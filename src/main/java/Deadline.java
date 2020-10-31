import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task{


    protected LocalDateTime timecheck;

    public Deadline (String description, LocalDateTime timecheck) {
        super(description);
        this.timecheck = timecheck;
    }

    public Deadline(String description, Boolean status) { //check
        super(description, status);
    }

    public Deadline(String description, Boolean status, LocalDateTime timecheck) {
        super(description, status);
        this.timecheck = timecheck;
    }

    private String generateDateAndTimeString() {
        String dateAndTime = "" + timecheck.getDayOfMonth();
    int day = timecheck.getDayOfMonth();

        if (day == 1 || day == 21 || day == 31) {
        dateAndTime += "st";
    } else if (day == 2 || day == 22) {
        dateAndTime += "nd";
    } else if (day == 3 || day == 23) {
        dateAndTime += "rd";
    } else {
        dateAndTime += "th";
    }

    dateAndTime += " of " + timecheck.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " " + timecheck.getYear() + ", ";

        if (timecheck.getHour() > 12) {
        dateAndTime += (timecheck.getHour() % 12) + ""
                + (timecheck.getMinute() == 0 ? "" : "." + (timecheck.getMinute() < 10 ? "0" : "")
                + timecheck.getMinute()) + "pm";
    } else if (timecheck.getHour() == 12) {
        dateAndTime += timecheck.getHour() + ""
                + (timecheck.getMinute() == 0 ? "" : "." + (timecheck.getMinute() < 10 ? "0" : "")
                + timecheck.getMinute()) + "pm";
    } else if (timecheck.getHour() == 0) {
        dateAndTime += 12 + ""
                + (timecheck.getMinute() == 0 ? "" : "." + (timecheck.getMinute() < 10 ? "0" : "")
                + timecheck.getMinute()) + "am";
    } else {
        dateAndTime += timecheck.getHour() + ""
                + (timecheck.getMinute() == 0 ? "" : "." + (timecheck.getMinute() < 10 ? "0" : "") + timecheck.getMinute()) + "am";
    }

        return dateAndTime;
}

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + generateDateAndTimeString() + ")";
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + timecheck;
    }

}
