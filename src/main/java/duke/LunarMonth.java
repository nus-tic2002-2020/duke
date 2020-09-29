package duke;

import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.Date;

/**
 * This enum lists the corresponding Lunar Months for the Gregorian date periods of the Year 2020.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
@SuppressWarnings("unused")
public enum LunarMonth implements DukeUI {

    PIG12("26-DEC-2019 00:00", "24-JAN-2020 23:59:59", "12th Lunar Month"),
    RAT01("25-JAN-2020 00:00", "22-FEB-2020 23:59:59", "1st Lunar Month"),
    RAT02("23-FEB-2020 00:00", "23-MAR-2020 23:59:59", "2nd Lunar Month"),
    RAT03("24-MAR-2020 00:00", "22-APR-2020 23:59:59", "3rd Lunar Month"),
    RAT04("23-APR-2020 00:00", "22-MAY-2020 23:59:59", "4th Lunar Month"),
    RAT04L("23-MAY-2020 00:00", "20-JUN-2020 23:59:59", "4th Lunar Month (Leap Month)"),
    RAT05("21-JUN-2020 00:00", "20-JUL-2020 23:59:59", "5th Lunar Month"),
    RAT06("21-JUL-2020 00:00", "18-AUG-2020 23:59:59", "6th Lunar Month"),
    RAT07("19-AUG-2020 00:00", "16-SEP-2020 23:59:59", "7th Lunar Month"),
    RAT08("17-SEP-2020 00:00", "16-OCT-2020 23:59:59", "8th Lunar Month"),
    RAT09("17-OCT-2020 00:00", "14-NOV-2020 23:59:59", "9th Lunar Month"),
    RAT10("15-NOV-2020 00:00", "14-DEC-2020 23:59:59", "10th Lunar Month"),
    RAT11("15-DEC-2020 00:00", "12-JAN-2021 23:59:59", "11th Lunar Month"),
    RAT12("13-JAN-2021 00:00", "11-FEB-2021 23:59:59", "12th Lunar Month");


    private final String from;
    private final String to;
    private final String lunarMonth;

    /**
     * This method constructs the various {@code LunarMonth} enum items.
     *
     * @param from The starting date of the Gregorian date period.
     * @param to The ending date of the Gregorian date period.
     * @param lunarMonth The corresponding Lunar Month for the Gregorian date period.
     */
    LunarMonth(String from, String to, String lunarMonth) {
        this.from = from;
        this.to = to;
        this.lunarMonth = lunarMonth;
    }

    /**
     * This method returns corresponding Lunar Month
     * for the Gregorian date provided.
     *
     * @param date The {@code Date} object reflecting the Gregorian date provided.
     * @return String The corresponding Lunar Month for the Gregorian date.
     * @exception ParseException If there are errors converting the String input into a {@code Date} object.
     */
    public static String getLunarMonth(Date date) throws ParseException {

        for(duke.LunarMonth month: duke.LunarMonth.values()){
            Date start = INPUT_TIME.parse(month.from);
            Date end = INPUT_TIME.parse(month.to);
            if(date.compareTo(start)>=0 && date.compareTo(end)<=0){
                return month.lunarMonth;
            }
        }
        return "ERROR";
    }
}