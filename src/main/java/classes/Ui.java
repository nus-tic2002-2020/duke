package classes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Ui (User interface) class for handling printing to the terminal
 */
public class Ui {

    /**
     * Constructor. Prints welcome message
     */
    public Ui() {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");
    }

    /**
     * Prints error message when description is missing
     */
    public void showNoDescriptionError() {
        System.out.println("I need a description or my code will burn!");
    }

    /**
     * Prints error message when date and time is missing
     */
    public void showNoTimeFrameError() {
        System.out.println("I need a timeframe or my code will be blown away!");
    }

    /**
     * Prints error message when too many spaces inputted. Too many spaces will mess with the relevant methods
     */
    public void showTooManySpacesError() {
        System.out.println("No double spaces or my code will crash!");
    }

    /**
     * Prints goodbye message when closing programme
     */
    public void printByeMessage() {
        System.out.println("Goodbye~ Hope to see you again soon! :3");
    }

    /**
     * Prints error message when invalid task number is inputted.
     */
    public void showInvalidNumberError() {
        System.out.println("Invalid function number. Try again");
    }

    /**
     * Prints message when deletion of task is successful
     */
    public void printDeleteMessage() {
        System.out.println("Noted. I've removed that task:");
    }

    /**
     * Prints message when task has been succesfully set as done
     */
    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints error message when marker "/by" or "/at" is incorrectly inputted. Relevant methods will not work
     * in this case
     */
    public void showIndexError() {
        System.out.println("Make sure you typed identifier \"/by\" or \"/at\" correctly!"); }

    /**
     * Prints error message when date format is wrong (used when adding task)
     */
    public void showDateFormatError1() {
        System.out.println("Date format is wrong. Use dd/mm/yyyy hh:mm");
    }

    /**
     * Prints error message when date format is wrong (used when searching for tasks in that day)
     */
    public void showDateFormatError2() {
        System.out.println("Date format is wrong. Use dd/mm/yyyy");
    }

    /**
     * Prints error message when date format is wrong (used when searching for tasks in that month)
     */
    public void showDateFormatError3() {
        System.out.println("Date format is wrong. Use mm/yyyy");
    }

    /**
     * Prints error message when date format is wrong (used when searching for tasks in that year)
     */
    public void showDateFormatError4() {
        System.out.println("Date format is wrong. Use yyyy");
    }

    /**
     * Prints message when addition of task is successful
     *
     * @param task The added Task-object
     */
    public void printAddMessage(Task task) {
        String function = task.getSymbol().toUpperCase();
        switch (function) {
            case "[T]": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][X] " + task.getDescription());
                break;
            }
            case "[D]": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[D][X] " + task.getDescription() + " (by: " + task.getDateAndTime().getDayOfMonth()
                        + " " + task.getDateAndTime().getMonth() + " " + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute() + ")");
                break;
            }
            case "[E]": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[E][X] " + task.getDescription() + " (at: " + task.getDateAndTime().getDayOfMonth()
                        + " " + task.getDateAndTime().getMonth() + " " + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute() + ")");
                break;
            }
        }
    }

    /**
     * Prints error message when command inputted is incorrect
     */
    public void showIllegalArgumentError() {
        System.out.println("Invalid input :( Please try again and follow the template closely!\n" +
                "-ToDo description\n" +
                "-Deadline description /by dd/mm/yyyy hh:mm\n" +
                "-Event description /at dd/mm/yyyy hh:mm\n" +
                "-List\n" +
                "-Day dd/mm/yyyy\n" +
                "-Month mm/yyyy\n" +
                "-Year yyyy\n" +
                "-Delete (item number)\n" +
                "-Done (item number)\n" +
                "-Bye");
    }

    /**
     * Prints out the ArrayList of tasks to the terminal
     */
    public void list(TaskList i) {
        if (i.getStore().size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task z : i.getStore()) {
            String symbol = z.getSymbol();
            switch (symbol) {
                case "[T]": {
                    System.out.println(index + ". [T]" + z.getDone() + " " + z.getDescription());
                    index++;
                    break;
                }
                case "[D]": {
                    System.out.println(index + ". [D]" + z.getDone() + " " + z.getDescription() +
                            " (by: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                            + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                            + z.getDateAndTime().getMinute() + ")");
                    index++;
                    break;
                }
                case "[E]": {
                    System.out.println(index + ". [E]" + z.getDone() + " " + z.getDescription() +
                            " (at: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                            + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                            + z.getDateAndTime().getMinute() + ")");
                    index++;
                    break;
                }
            }
        }
    }

    /**
     * Prints out the tasks for the selected day
     * @param input User-input, includes the day
     * @param i ArrayList of tasks
     */
    public void listByDay (String input, TaskList i) {
        String dateString = input.substring(3).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        int index = 1;
        for (Task z : i.getStore()) {
            String symbol = z.getSymbol();
            switch (symbol) {
                case "[D]": {
                    if (z.getDateAndTime().getDayOfYear() == date.getDayOfYear()
                            && z.getDateAndTime().getYear() == date.getYear()) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that day:");
                        }
                        System.out.println(index + ". [D]" + z.getDone() + " " + z.getDescription() +
                                " (by: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
                case "[E]": {
                    if (z.getDateAndTime().getDayOfYear() == date.getDayOfYear()
                            && z.getDateAndTime().getYear() == date.getYear()) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that day:");
                        }
                        System.out.println(index + ". [E]" + z.getDone() + " " + z.getDescription() +
                                " (at: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
            }
        }
        if (index == 1) {
            System.out.println ("There are no tasks for that day");
        }
    }

    /**
     * Prints out the tasks for the selected month
     * @param input User-input, includes the month
     * @param i ArrayList of tasks
     */
    public void listByMonth (String input, TaskList i) {
        String monthString = input.substring(5).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/yyyy");
        YearMonth yearMonth = YearMonth.parse(monthString, formatter);
        int index = 1;
        for (Task z : i.getStore()) {
            String symbol = z.getSymbol();
            switch (symbol) {
                case "[D]": {
                    if (z.getDateAndTime().getMonth() == yearMonth.getMonth()
                            && yearMonth.getYear() == (z.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that month:");
                        }
                        System.out.println(index + ". [D]" + z.getDone() + " " + z.getDescription() +
                                " (by: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
                case "[E]": {
                    if (z.getDateAndTime().getMonth() == yearMonth.getMonth()
                            && yearMonth.getYear() == (z.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that month:");
                        }
                        System.out.println(index + ". [E]" + z.getDone() + " " + z.getDescription() +
                                " (at: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
            }
        }
        if (index == 1) {
            System.out.println ("There are no tasks for that month");
        }
    }

    /**
     * Prints out the tasks for the selected year
     * @param input User-input, includes the year
     * @param i ArrayList of tasks
     */
    public void listByYear (String input, TaskList i) {
        String yearString = input.substring(4).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year year = Year.parse(yearString, formatter);
        int index = 1;
        for (Task z : i.getStore()) {
            String symbol = z.getSymbol();
            switch (symbol) {
                case "[T]": {
                    if (year.getValue() == (z.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that year:");
                        }
                        System.out.println(index + ". [T]" + z.getDone() + " " + z.getDescription());
                        index++;
                    }
                    break;
                }
                case "[D]": {
                    if (year.getValue() == (z.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println ("Here are the tasks for that year:");
                        }
                        System.out.println(index + ". [D]" + z.getDone() + " " + z.getDescription() +
                                " (by: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
                case "[E]": {
                    if (year.getValue() == (z.getDateAndTime().getYear())) {
                        System.out.println(index + ". [E]" + z.getDone() + " " + z.getDescription() +
                                " (at: " + z.getDateAndTime().getDayOfMonth() + " " + z.getDateAndTime().getMonth() + " "
                                + z.getDateAndTime().getYear() + ", " + z.getDateAndTime().getHour() + ":"
                                + z.getDateAndTime().getMinute() + ")");
                        index++;
                    }
                    break;
                }
            }
        }
        if (index == 1) {
            System.out.println ("There are no tasks for that year");
        }
    }

    /**
     * Method to allow programme to receive user-input
     * @return Returns the user-input as a string
     */
    public String receive() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
