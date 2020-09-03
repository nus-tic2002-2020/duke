import java.text.ParseException;
import java.util.Date;

public enum ZodiacYear implements DukeUI{

    YEAR_20_21("25-JAN-2020 00:00:00", "11-FEB-2021 23:59:59", "Year of the Rat"),
    YEAR_21_22("12-FEB-2021 00:00:00", "31-JAN-2022 23:59:59", "Year of the Ox"),
    YEAR_22_23("01-FEB-2022 00:00:00", "21-JAN-2023 23:59:59", "Year of the Tiger"),
    YEAR_23_24("22-JAN-2023 00:00:00", "09-FEB-2024 23:59:59", "Year of the Rabbit"),
    YEAR_24_25("10-FEB-2024 00:00:00", "28-JAN-2025 23:59:59", "Year of the Dragon"),
    YEAR_25_26("29-JAN-2025 00:00:00", "16-FEB-2026 23:59:59", "Year of the Snake"),
    YEAR_26_27("17-FEB-2026 00:00:00", "05-FEB-2027 23:59:59", "Year of the Horse"),
    YEAR_27_28("06-FEB-2027 00:00:00", "25-JAN-2028 23:59:59", "Year of the Goat"),
    YEAR_28_29("26-JAN-2028 00:00:00", "12-FEB-2029 23:59:59", "Year of the Monkey"),
    YEAR_29_30("13-FEB-2029 00:00:00", "02-FEB-2030 23:59:59", "Year of the Rooster"),
    YEAR_30_31("03-FEB-2030 00:00:00", "22-JAN-2031 23:59:59", "Year of the Dog"),
    YEAR_31_32("23-JAN-2031 00:00:00", "10-FEB-2032 23:59:59", "Year of the Pig");

    private final String from;
    private final String to;
    private final String year;

    ZodiacYear(String from, String to, String year) {
        this.from = from;
        this.to = to;
        this.year = year;
    }

    public static String getZodiacYear(Date date) throws ParseException {

        for(ZodiacYear year: ZodiacYear.values()){
            Date start = INPUT_DATE.parse(year.from);
            Date end = INPUT_DATE.parse(year.to);
            if(date.compareTo(start)>0 && date.compareTo(end)<0){
                return year.year;
            }
        }
        return "ERROR";
    }
}
