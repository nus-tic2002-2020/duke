package duke;

import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.Date;

/**
 * This enum lists the Sun Signs for the Gregorian date periods of each year.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
@SuppressWarnings("unused")
public enum SunSign implements DukeUI{

    ARIES("21-MAR", "19-APR", "Sun in Aries"),
    TAURUS("20-APR", "20-MAY", "Sun in Taurus"),
    GEMINI("21-MAY", "20-JUN", "Sun in Gemini"),
    CANCER("21-JUN", "22-JUL", "Sun in Cancer"),
    LEO("23-JUL", "22-AUG", "Sun in Leo"),
    VIRGO("23-AUG", "22-SEP", "Sun in Virgo"),
    LIBRA("23-SEP", "22-OCT", "Sun in Libra"),
    SCORPIO("23-OCT", "21-NOV", "Sun in Scorpio"),
    SAGITTARIUS("22-NOV", "21-DEC", "Sun in Sagittarius"),
    CAPRICORN("22-DEC", "19-JAN", "Sun in Capricorn"),
    AQUARIUS("20-JAN", "18-FEB", "Sun in Aquarius"),
    PISCES("19-FEB", "20-MAR", "Sun in Pisces");

    private final String from;
    private final String to;
    private final String sunSign;

    /**
     * This method constructs the various {@code SunSign} enum items.
     *
     * @param from The starting date of the Gregorian date period.
     * @param to The ending date of the Gregorian date period.
     * @param sunSign The corresponding Sun Sign for the Gregorian date period.
     */
    SunSign(String from, String to, String sunSign) {
        this.from = from;
        this.to = to;
        this.sunSign = sunSign;
    }

    /**
     * This method returns corresponding Sun Sign
     * for the Gregorian date provided.
     *
     * @param date The {@code Date} object reflecting the Gregorian date provided.
     * @return String The corresponding Sun Sign for the Gregorian date.
     * @exception ParseException If there are errors converting the String input into a {@code Date} object.
     */
    public static String getSunSign(Date date) throws ParseException {

        for(SunSign sign: SunSign.values()){
            String from = sign.from + "-" + YEAR_TODAY.format(date) + " 00:00:00";
            String to = sign.to + "-" + YEAR_TODAY.format(date)  + " 23:59:59";
            Date start = INPUT_TIME.parse(from);
            Date end = INPUT_TIME.parse(to);
            if(date.compareTo(start)>=0 && date.compareTo(end)<=0){
                return sign.sunSign;
            }
        }
        return "ERROR";
    }
}
