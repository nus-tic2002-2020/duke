package duke;

import duke.ui.DukeUI;
import java.text.ParseException;
import java.util.Date;

public enum LunarMonth implements DukeUI {

    PIG12("26-DEC-2019", "24-JAN-2020", "12th Lunar Month"),
    RAT01("25-JAN-2020", "22-FEB-2020", "1st Lunar Month"),
    RAT02("23-FEB-2020", "23-MAR-2020", "2nd Lunar Month"),
    RAT03("24-MAR-2020", "22-APR-2020", "3rd Lunar Month"),
    RAT04("23-APR-2020", "22-MAY-2020", "4th Lunar Month"),
    RAT04L("23-MAY-2020", "20-JUN-2020", "4th Lunar Month (Leap Month)"),
    RAT05("21-JUN-2020", "20-JUL-2020", "5th Lunar Month"),
    RAT06("21-JUL-2020", "18-AUG-2020", "6th Lunar Month"),
    RAT07("19-AUG-2020", "16-SEP-2020", "7th Lunar Month"),
    RAT08("17-SEP-2020", "16-OCT-2020", "8th Lunar Month"),
    RAT09("17-OCT-2020", "14-NOV-2020", "9th Lunar Month"),
    RAT10("15-NOV-2020", "14-DEC-2020", "10th Lunar Month"),
    RAT11("15-DEC-2020", "12-JAN-2021", "11th Lunar Month"),
    RAT12("13-JAN-2021", "11-FEB-2021", "12th Lunar Month");


    private final String from;
    private final String to;
    private final String lunarMonth;

    LunarMonth(String from, String to, String lunarMonth) {
        this.from = from;
        this.to = to;
        this.lunarMonth = lunarMonth;
    }

    public static String getLunarMonth(Date date) throws ParseException {

        for(duke.LunarMonth month: duke.LunarMonth.values()){
            String from = month.from + " 00:00:00";
            String to = month.to + " 23:59:59";
            Date start = INPUT_TIME.parse(from);
            Date end = INPUT_TIME.parse(to);
            if(date.compareTo(start)>=0 && date.compareTo(end)<=0){
                return month.lunarMonth;
            }
        }
        return "ERROR";
    }
}