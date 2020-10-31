import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {

    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public Event(String description, Boolean status) {
        super(description,status);
    }

    public Event(String description,Boolean status, LocalDateTime time) {
        super(description,status);
        this.time = time;
    }

    private String generateDateAndTimeString() {
        String dateAndTime = "" + time.getDayOfMonth();
        int day = time.getDayOfMonth();

        if (day == 1 || day == 21 || day == 31) {
            dateAndTime += "st";
        } else if (day == 2 || day == 22) {
            dateAndTime += "nd";
        } else if (day == 3 || day == 23) {
            dateAndTime += "rd";
        } else {
            dateAndTime += "th";
        }

        dateAndTime += " of " + time.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " " + time.getYear() + ", ";

        if (time.getHour() > 12) {
            dateAndTime += (time.getHour() % 12) + ""
                    + (time.getMinute() == 0 ? "" : "." + (time.getMinute() < 10 ? "0" : "")
                    + time.getMinute()) + "pm";
        } else if (time.getHour() == 12) {
            dateAndTime += time.getHour() + ""
                    + (time.getMinute() == 0 ? "" : "." + (time.getMinute() < 10 ? "0" : "")
                    + time.getMinute()) + "pm";
        } else if (time.getHour() == 0) {
            dateAndTime += 12 + ""
                    + (time.getMinute() == 0 ? "" : "." + (time.getMinute() < 10 ? "0" : "")
                    + time.getMinute()) + "am";
        } else {
            dateAndTime += time.getHour() + ""
                    + (time.getMinute() == 0 ? "" : "." + (time.getMinute() < 10 ? "0" : "") + time.getMinute()) + "am";
        }

        return dateAndTime;
    }

    @Override
    public String toString() {
        return ( "[E]" + super.toString() + " (at: " + generateDateAndTimeString() + ")");
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + time;
    }


}
