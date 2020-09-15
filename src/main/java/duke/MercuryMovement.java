package duke;

import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.Date;

public enum MercuryMovement implements DukeUI {

    PROG1("01-JAN-2020", "01-FEB-2020", "Mercury in Prograde"),
    PRER1("02-FEB-2020", "15-FEB-2020", "Mercury in Pre-Retrograde"),
    RTRG1("16-FEB-2020", "09-MAR-2020", "Mercury in Retrograde"),
    POSR1("10-MAR-2020", "29-MAR-2020", "Mercury in Post-Retrograde"),
    PROG2("30-MAR-2020", "01-JUN-2020", "Mercury in Prograde"),
    PRER2("02-JUN-2020", "17-JUN-2020", "Mercury in Pre-Retrograde"),
    RTRG2("18-JUN-2020", "12-JUL-2020", "Mercury in Retrograde"),
    POSR2("13-JUL-2020", "26-JUL-2020", "Mercury in Post-Retrograde"),
    PROG3("27-JUL-2020", "22-SEP-2020", "Mercury in Prograde"),
    PRER3("23-SEP-2020", "12-OCT-2020", "Mercury in Pre-Retrograde"),
    RTRG3("13-OCT-2020", "03-NOV-2020", "Mercury in Retrograde"),
    POSR3("04-NOV-2020", "19-NOV-2020", "Mercury in Post-Retrograde"),
    PROG4("20-NOV-2020", "31-DEC-2020", "Mercury in Prograde");


    private final String from;
    private final String to;
    private final String mercuryMovement;

    MercuryMovement(String from, String to, String mercuryMovement) {
        this.from = from;
        this.to = to;
        this.mercuryMovement = mercuryMovement;
    }

    public static String getMercuryMovement(Date date) throws ParseException {

        for(duke.MercuryMovement mercury: duke.MercuryMovement.values()){
            String from = mercury.from + " 00:00:00";
            String to = mercury.to + " 23:59:59";
            Date start = INPUT_TIME.parse(from);
            Date end = INPUT_TIME.parse(to);
            if(date.compareTo(start)>=0 && date.compareTo(end)<=0){
                return mercury.mercuryMovement;
            }
        }
        return "ERROR";
    }
}
