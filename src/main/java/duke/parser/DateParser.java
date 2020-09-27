package duke.parser;


import duke.commands.CommandException;
import duke.commands.ExitConfirm;
import duke.ui.DukeUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface DateParser extends DukeUI {

    SimpleDateFormat UNDERSTOOD_DAYM = new SimpleDateFormat("dd");
    SimpleDateFormat UNDERSTOOD_MONTH = new SimpleDateFormat("MMM");
    SimpleDateFormat UNDERSTOOD_YEAR = new SimpleDateFormat("yyyy");
    SimpleDateFormat UNDERSTOOD_DAYW = new SimpleDateFormat("EEEEE");
    SimpleDateFormat UNDERSTOOD_TIME = new SimpleDateFormat("HH:mm");


    static boolean checkForDayW(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        Date now = new Date();
        Date tmr = new Date(now.getTime() + 86400000);
        if (input.toUpperCase().equals("TODAY")) {
            understoodDate.replace("day", UNDERSTOOD_DAYM.format(now));
            understoodDate.replace("month", UNDERSTOOD_MONTH.format(now));
            understoodDate.replace("year", UNDERSTOOD_YEAR.format(now));
            return true;
        } else if (input.toUpperCase().equals("TOMORROW")) {
            understoodDate.replace("day", UNDERSTOOD_DAYM.format(tmr));
            understoodDate.replace("month", UNDERSTOOD_MONTH.format(tmr));
            understoodDate.replace("year", UNDERSTOOD_YEAR.format(tmr));
            return true;
        } else {
            for (int i = 1; i < 8; i++) {
                Date next = new Date(now.getTime() + (i * 86400000));
                String nextDay = UNDERSTOOD_DAYW.format(next).toUpperCase();
                if (input.toUpperCase().equals(nextDay)) {
                    understoodDate.replace("day", UNDERSTOOD_DAYM.format(next));
                    understoodDate.replace("month", UNDERSTOOD_MONTH.format(next));
                    understoodDate.replace("year", UNDERSTOOD_YEAR.format(next));
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkForDayM(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        input = input.replace("1ST", "1");
        input = input.replace("2ND", "2");
        input = input.replace("3RD", "3");
        input = input.replace("1TH", "1");
        input = input.replace("2TH", "2");
        input = input.replace("3TH", "3");
        input = input.replace("4TH", "4");
        input = input.replace("5TH", "5");
        input = input.replace("6TH", "6");
        input = input.replace("7TH", "7");
        input = input.replace("8TH", "8");
        input = input.replace("9TH", "9");
        input = input.replace("0TH", "0");

        try {
            if (Integer.parseInt(input) <= 31 && Integer.parseInt(input) >= 1) {
                understoodDate.replace("day", input);
                return true;
            }
        } catch (Exception ignored) { }
        return false;
    }


    static boolean checkForMonth(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        String[] months = {"MMMMM", "MMM", "MM"};
        ArrayList<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

        for (String month : months) {
            formats.add(new SimpleDateFormat(month));
        }

        Date now = new Date();
        if (input.toUpperCase().equals("THIS MONTH")) {
            understoodDate.replace("month", UNDERSTOOD_MONTH.format(now));
            understoodDate.replace("year", UNDERSTOOD_YEAR.format(now));
            return true;
        } else {
            for (SimpleDateFormat format : formats) {
                try {
                    understoodDate.replace("month", UNDERSTOOD_MONTH.format(format.parse(input)));
                    return true;
                } catch (ParseException ignored) { }
            }
        }
        return false;
    }

    static boolean checkForYear(String input, HashMap<String, String> understoodDate) throws ParseException {

        if(input == null) { return false; }
        input = input.replace("'", "");
        SimpleDateFormat format = new SimpleDateFormat("yy");

        Date now = new Date();
        if (input.toUpperCase().equals("THIS YEAR")) {
            understoodDate.replace("year", UNDERSTOOD_YEAR.format(now));
            return true;
        } else {
            try {
                understoodDate.replace("year", UNDERSTOOD_YEAR.format(format.parse(input)));
                return true;
            } catch (ParseException ignored) { }
        }
        return false;
    }

    static boolean checkForDayMonth(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        input = input.replace("-", "~");
        input = input.replace(".", "~");
        input = input.replace("'", "~");

        String[] days = {"dd"};
        String[] months = {"MM", "MMM", "MMMMM"};
        ArrayList<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

        for (String day : days) {
            for (String month : months) {
                formats.add(new SimpleDateFormat(day + "~" + month));
                if (!month.equals("MM")) {
                    formats.add(new SimpleDateFormat(month + "~" + day));
                    formats.add(new SimpleDateFormat(month + day));
                    formats.add(new SimpleDateFormat(day + month));
                }
            }
        }

        for (SimpleDateFormat format : formats) {
            try {
                understoodDate.replace("day", UNDERSTOOD_DAYM.format(format.parse(input)));
                understoodDate.replace("month", UNDERSTOOD_MONTH.format(format.parse(input)));
                return true;
            } catch (ParseException ignored) { }
        }
        return false;
    }

    static boolean checkForMonthYear(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        input = input.replace("-", "~");
        input = input.replace(".", "~");
        input = input.replace("'", "~");

        String[] months = {"MMM", "MMMMM"};
        String[] years = {"yy"};
        ArrayList<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

        for (String month : months) {
            for (String year : years) {
                formats.add(new SimpleDateFormat(month + "~" + year));
                formats.add(new SimpleDateFormat(year + "~" + month));
            }
        }

        for (SimpleDateFormat format : formats) {
            try {
                understoodDate.replace("month", UNDERSTOOD_MONTH.format(format.parse(input)));
                understoodDate.replace("year", UNDERSTOOD_YEAR.format(format.parse(input)));
                return true;
            } catch (ParseException ignored) { }
        }
        return false;
    }

    static boolean checkForFullDate(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        input = input.replace("-", "~");
        input = input.replace(".", "~");
        input = input.replace("'", "~");
        input = input.replace("/", "~");

        String[] days = {"dd"};
        String[] months = {"MM", "MMM", "MMMMM"};
        String[] years = {"yy"};
        ArrayList<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

        for (String month: months) {
            for(String year: years) {
                for (String day: days) {
                    formats.add(new SimpleDateFormat(day + "~" + month + "~" + year));
                    formats.add(new SimpleDateFormat(year + "~" + month + "~" + day));
                    if (!month.equals("MM")) {
                        formats.add(new SimpleDateFormat(month + day + "~" + year));
                        formats.add(new SimpleDateFormat(year + "~" + month + day));
                        formats.add(new SimpleDateFormat(day + "~" + month + year));
                        formats.add(new SimpleDateFormat(month + year + "~" + day));
                        formats.add(new SimpleDateFormat(day + month + "~" + year));
                        formats.add(new SimpleDateFormat(year + "~" + day + month));
                        formats.add(new SimpleDateFormat(day + month + year));
                    }
                }
            }
        }
        for (SimpleDateFormat format : formats) {
            try {
                understoodDate.replace("day", UNDERSTOOD_DAYM.format(format.parse(input)));
                understoodDate.replace("month", UNDERSTOOD_MONTH.format(format.parse(input)));
                understoodDate.replace("year", UNDERSTOOD_YEAR.format(format.parse(input)));
                return true;
            } catch (ParseException ignored) { }
        }
        return false;
    }

    static boolean checkForTime(String input, HashMap<String, String> understoodDate) {

        if(input == null) { return false; }
        input = input.replace("-", "~");
        input = input.replace(".", "~");
        input = input.replace("'", "~");
        input = input.replace(":", "~");

        String[] times = {"hh~mma", "hha", "HHmm'HS'", "HHmm'H'", "HH~mm"};
        ArrayList<SimpleDateFormat> formats = new ArrayList<SimpleDateFormat>();

        for(String time: times) {
            formats.add(new SimpleDateFormat(time));
        }

        for (SimpleDateFormat format: formats) {
            try {
                understoodDate.replace("time", UNDERSTOOD_TIME.format(format.parse(input)));
                return true;
            } catch (ParseException ignored) { }
        }
        return false;
    }


    static Date understandDateInput(String userInput) throws ParseException, CommandException {

        Date now = new Date();
        Date understoodDateTime;
        HashMap<String, String> dateHash = new HashMap<String, String>();
        dateHash.put("day", null);
        dateHash.put("month", null);
        dateHash.put("year", null);
        dateHash.put("time", null);

        String[] userInputs = userInput.toUpperCase().split(" ");
        ArrayList<String> inputs = new ArrayList<String>();
        for(String input: userInputs){ inputs.add(input.trim()); }


        for(int i=0; i<inputs.size(); i++) {
            String input = inputs.get(i);
            if (checkForDayW(input, dateHash)) {
                System.out.println("DAYW: " + input);
                inputs.remove(i);
                break;
            }
        }

        for(int i=0; i<inputs.size(); i++) {
            String input = inputs.get(i);
            if (checkForTime(input, dateHash)) {
                System.out.println("TIME: " + input);
                inputs.remove(i);
                break;
            }
        }

        if(dateHash.get("day") == null && dateHash.get("month") == null && dateHash.get("year") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForFullDate(input, dateHash)) {
                    System.out.println("FULLDATE: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if (dateHash.get("day") == null && dateHash.get("month") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForDayMonth(input, dateHash)) {
                    System.out.println("DAYMTH: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if(dateHash.get("month") == null && dateHash.get("year") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForMonthYear(input, dateHash)) {
                    System.out.println("MTHYR: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if(dateHash.get("day") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForDayM(input, dateHash)) {
                    System.out.println("DAY: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if(dateHash.get("month") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForMonth(input, dateHash)) {
                    System.out.println("MTH: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if(dateHash.get("year") == null) {
            for(int i=0; i<inputs.size(); i++) {
                String input = inputs.get(i);
                if(checkForYear(input, dateHash)) {
                    System.out.println("YR: " + input);
                    inputs.remove(i);
                    break;
                }
            }
        }

        if(dateHash.get("year") == null) {
            dateHash.replace("year", UNDERSTOOD_YEAR.format(now));
            System.out.println("SET YR");

            Date cutOffMin = new Date(now.getTime() - ((long)90*86400000));
            Date cutOffMax = new Date(now.getTime() - ((long)276*86400000));

            String dateString = dateHash.get("day") + "-" +
                    dateHash.get("month") + "-" + dateHash.get("year");
            Date testDate = DukeUI.INPUT_DATE.parse(dateString);

            if(testDate.before(cutOffMin)) {
                dateHash.replace("year", Integer.toString((Integer.parseInt(UNDERSTOOD_YEAR.format(now)) + 1)));
            } else if(testDate.after(cutOffMax)) {
                dateHash.replace("year", Integer.toString((Integer.parseInt(UNDERSTOOD_YEAR.format(now)) - 1)));
            }
        }

        String dateTimeString = dateHash.get("day") + "-" +
                dateHash.get("month") + "-" +  dateHash.get("year") + " " +
                dateHash.get("time");
        try {
            understoodDateTime = DukeUI.INPUT_TIME.parse(dateTimeString);
        } catch (ParseException e){
            throw new CommandException("I can't understand the date and time you are trying to specify.");
        }
        return understoodDateTime;
    }
}
