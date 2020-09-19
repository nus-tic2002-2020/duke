package duke;

import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.Date;

/**
 * This enum lists the movements of Mercury for the Gregorian date periods of the Year 2020.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public enum MercuryMovement implements DukeUI {

    PROG1("01-JAN-2020 00:00:00", "01-FEB-2020 23:59:59", "Mercury in Prograde"),
    PRER1("02-FEB-2020 00:00:00", "15-FEB-2020 23:59:59", "Mercury in Pre-Retrograde"),
    RTRG1("16-FEB-2020 00:00:00", "09-MAR-2020 23:59:59", "Mercury in Retrograde"),
    POSR1("10-MAR-2020 00:00:00", "29-MAR-2020 23:59:59", "Mercury in Post-Retrograde"),
    PROG2("30-MAR-2020 00:00:00", "01-JUN-2020 23:59:59", "Mercury in Prograde"),
    PRER2("02-JUN-2020 00:00:00", "17-JUN-2020 23:59:59", "Mercury in Pre-Retrograde"),
    RTRG2("18-JUN-2020 00:00:00", "12-JUL-2020 23:59:59", "Mercury in Retrograde"),
    POSR2("13-JUL-2020 00:00:00", "26-JUL-2020 23:59:59", "Mercury in Post-Retrograde"),
    PROG3("27-JUL-2020 00:00:00", "22-SEP-2020 23:59:59", "Mercury in Prograde"),
    PRER3("23-SEP-2020 00:00:00", "12-OCT-2020 23:59:59", "Mercury in Pre-Retrograde"),
    RTRG3("13-OCT-2020 00:00:00", "03-NOV-2020 23:59:59", "Mercury in Retrograde"),
    POSR3("04-NOV-2020 00:00:00", "19-NOV-2020 23:59:59", "Mercury in Post-Retrograde"),
    PROG4("20-NOV-2020 00:00:00", "31-DEC-2020 23:59:59", "Mercury in Prograde");


    private final String from;
    private final String to;
    private final String mercuryMovement;

    /**
     * This method constructs the various {@code MercuryMovement} enum items.
     *
     * @param from The starting date of the Gregorian date period.
     * @param to The ending date of the Gregorian date period.
     * @param mercuryMovement The corresponding movement of Mercury for the Gregorian date period.
     */
    MercuryMovement(String from, String to, String mercuryMovement) {
        this.from = from;
        this.to = to;
        this.mercuryMovement = mercuryMovement;
    }

    /**
     * This method returns corresponding movement of Mercury
     * for the Gregorian date provided.
     *
     * @param date The {@code Date} object reflecting the Gregorian date provided.
     * @return String The corresponding movement of Mercury for the Gregorian date.
     * @exception ParseException If there are errors converting the String input into a {@code Date} object.
     */
    public static String getMercuryMovement(Date date) throws ParseException {

        for(duke.MercuryMovement mercury: duke.MercuryMovement.values()){
            Date start = INPUT_TIME.parse(mercury.from);
            Date end = INPUT_TIME.parse(mercury.to);
            if(date.compareTo(start)>=0 && date.compareTo(end)<=0){
                return mercury.mercuryMovement;
            }
        }
        return "ERROR";
    }
}
