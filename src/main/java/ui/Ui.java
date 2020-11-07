package ui;

import exceptions.TooManySpacesException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import task.Task;
import tasklist.TaskList;

/** Ui (User interface) class for handling printing to the terminal */
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
        System.out.println(logo +
                "\nHello, Lisa here~\nHow can I help you today?\n**Type \"help\" for a list of commands**");
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
        System.out.println("Noted. I've removed that task.");
    }

    public void printEmptyListMessage() {
        System.out.println("Your list is already empty.");
    }

    /**
     * Prints message when task has been succesfully set as done
     */
    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done.");
    }

    public void printPriorityMessage() {
        System.out.println("Roger. I've set the priority of this task as such.");
    }

    /**
     * Prints error message when marker "/by" or "/at" is incorrectly inputted. Relevant methods will not work
     * in this case
     */
    public void showIndexError() {
        System.out.println("Make sure you typed identifier \"/by\", \"/at\", \"/from\" and/or \"/to\" correctly!"); }

    /**
     * Prints error message when date format is wrong (used when adding task)
     */
    public void showDateFormatError1() {
        System.out.println("Date format is wrong. Use dd/mm/yyyy hh:mm.");
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

    public void showIncorrectPriorityError() {
        System.out.println("Invalid priority setting. Use either NA, LOW, MEDIUM or HIGH");
    }

    public void printCmdList() {
        System.out.println("-ToDo description\n" +
                "-Deadline description /by dd/mm/yyyy hh:mm\n" +
                "-Event description /at dd/mm/yyyy hh:mm\n" +
                "-Within description /from dd/mm/yyyy hh:mm /to dd/mm/yyyy hh:mm" +
                "-List\n" +
                "-Day dd/mm/yyyy\n" +
                "-Month mm/yyyy\n" +
                "-Year yyyy\n" +
                "-Delete (item number)\n" +
                "-Done (item number)\n" +
                "-Priority (item number) (priority level)\n" +
                "-Bye");
    }

    /**
     * Prints error message when command inputted is incorrect
     */
    public void showIllegalArgumentError() {
        System.out.println("Invalid input :( Please try again and follow the template closely!");
        printCmdList();
    }

    private void printTodo(Task task) {
        System.out.println("[T]" + task.getDone() + " " + task.getDescription() + " (Priority: " + task.getPriority() + ")");
    }

    private void printDeadline(Task task) {
        System.out.println("[D]" + task.getDone() + " " + task.getDescription() + " (by: " +
                task.getDateAndTime().getDayOfMonth() + " " + task.getDateAndTime().getMonth() + " "
                + task.getDateAndTime().getYear() + ", " + task.getDateAndTime().getHour() + ":"
                + task.getDateAndTime().getMinute() + ") (Priority: " + task.getPriority() + ")");
    }

    private void printEvent(Task task) {
        System.out.println("[E]" + task.getDone() + " " + task.getDescription() + " (at: " +
                task.getDateAndTime().getDayOfMonth() + " " + task.getDateAndTime().getMonth() + " "
                + task.getDateAndTime().getYear() + ", " + task.getDateAndTime().getHour() + ":"
                + task.getDateAndTime().getMinute() + ") (Priority: " + task.getPriority() + ")");
    }

    private void printWithin(Task task) {
        System.out.println("[W]" + task.getDone() + " " + task.getDescription() + " (within: "
                + task.getStart().getDayOfMonth() + " " + task.getStart().getMonth() + " "
                + task.getStart().getYear() + ", " + task.getStart().getHour() + ":" + task.getStart().getMinute()
                + " to " + task.getEnd().getDayOfMonth() + " " + task.getEnd().getMonth() + " "
                + task.getEnd().getYear() + ", " + task.getEnd().getHour() + ":" + task.getEnd().getMinute() + ") (Priority: " + task.getPriority() + ")");
    }

    /**
     * Prints message when addition of task is successful
     *
     * @param task The added Task-object
     */
    public void printAddMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        String function = task.getSymbol().toUpperCase();
        switch (function) {
            case "[T]":
                printTodo(task);
                break;
            case "[D]":
                printDeadline(task);
                break;
            case "[E]":
                printEvent(task);
                break;
            case "[W]":
                printWithin(task);
                break;
        }
    }

    /**
     * Prints all the task whose description contains the search query
     * @param input Input by user
     * @param taskList TaskList class containing the store of tasks
     * @throws TooManySpacesException
     */
    public void find(String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }
        if (taskList.getStore().size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        String[] headers = new String[2];
        headers[0] = "There are no matching tasks";
        headers[1] = "Here are the matching tasks in your list:";
        int index = 1;
        String[] query = input.split(" ");
        for (Task task : taskList.getStore()) {
            String symbol = task.getSymbol();
            switch (symbol) {
                case "[T]":
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printTodo(task);
                        index++;
                        break;
                    }
                case "[D]":
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                        break;
                    }
                case "[E]":
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                        break;
                    }
                case "[W]":
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printWithin(task);
                        index++;
                        break;
                    }
            }
        }
        if (index == 1) {
            System.out.println (headers[0]);
        }
    }

    /**
     * Prints out the ArrayList of tasks to the terminal
     */
    public void list(TaskList taskList) {
        if (taskList.getStore().size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList.getStore()) {
            String symbol = task.getSymbol();
            switch (symbol) {
                case "[T]":
                    System.out.print(index + ". ");
                    printTodo(task);
                    index++;
                    break;
                case "[D]":
                    System.out.print(index + ". ");
                    printDeadline(task);
                    index++;
                    break;
                case "[E]":
                    System.out.print(index + ". ");
                    printEvent(task);
                    index++;
                    break;
                case "[W]":
                    System.out.print(index + ". ");
                    printWithin(task);
                    index++;
                    break;
            }
        }
    }

    /**
     * Prints out the tasks for the selected day
     * @param input User-input, includes the day
     * @param taskList ArrayList of tasks
     */
    public void listByDay (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }
        String[] headers = new String[2];
        headers[0] = "There are no tasks for that day";
        headers[1] = "Here are the tasks for that day:";
        String dateString = input.substring(3).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        int index = 1;
        for (Task task : taskList.getStore()) {
            String symbol = task.getSymbol();
            switch (symbol) {
                case "[D]":
                    if (task.getDateAndTime().getDayOfYear() == date.getDayOfYear()
                            && task.getDateAndTime().getYear() == date.getYear()) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                    }
                    break;
                case "[E]":
                    if (task.getDateAndTime().getDayOfYear() == date.getDayOfYear()
                            && task.getDateAndTime().getYear() == date.getYear()) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                    }
                    break;
                case "[W]":
                    if (task.getStart().getDayOfYear() <= date.getDayOfYear()
                            && task.getEnd().getDayOfYear() >= date.getDayOfYear()
                            && task.getStart().getYear() == date.getYear()
                            && task.getEnd().getYear() == date.getYear()) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printWithin(task);
                        index++;
                    }
                    break;
            }
        }
        if (index == 1) {
            System.out.println (headers[0]);
        }
    }

    /**
     * Prints out the tasks for the selected month
     * @param input User-input, includes the month
     * @param taskList ArrayList of tasks
     */
    public void listByMonth (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }
        String[] headers = new String[2];
        headers[0] = "There are no tasks for that month";
        headers[1] = "Here are the tasks for that month:";
        String monthString = input.substring(5).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/yyyy");
        YearMonth yearMonth = YearMonth.parse(monthString, formatter);
        int index = 1;
        for (Task task : taskList.getStore()) {
            String symbol = task.getSymbol();
            switch (symbol) {
                case "[D]":
                    if (task.getDateAndTime().getMonth() == yearMonth.getMonth()
                            && yearMonth.getYear() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                    }
                    break;
                case "[E]":
                    if (task.getDateAndTime().getMonth() == yearMonth.getMonth()
                            && yearMonth.getYear() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                    }
                    break;
                case "[W]":
                    int situationOfYear = 0;
                    if (task.getStart().getYear() == yearMonth.getYear() &&
                            task.getEnd().getYear() == yearMonth.getYear()) {
                        situationOfYear = 1;
                    }
                    if (task.getStart().getYear() < yearMonth.getYear() &&
                            task.getEnd().getYear() == yearMonth.getYear()) {
                        situationOfYear = 2;
                    }
                    if (task.getStart().getYear() == yearMonth.getYear() &&
                            task.getEnd().getYear() > yearMonth.getYear()) {
                        situationOfYear = 3;
                    }
                    if (task.getStart().getYear() < yearMonth.getYear() &&
                            task.getEnd().getYear() > yearMonth.getYear()) {
                        situationOfYear = 4;
                    }
                    switch (situationOfYear) {
                        case 1:
                            if (task.getStart().getMonthValue() <= yearMonth.getMonthValue() &&
                                    task.getEnd().getMonthValue() >= yearMonth.getMonthValue() ) {
                                if (index == 1) {
                                    System.out.println (headers[1]);
                                }
                                System.out.print(index + ". ");
                                printWithin(task);
                                index++;
                            }
                            break;
                        case 2:
                            if (task.getEnd().getMonthValue() >= yearMonth.getMonthValue()) {
                                if (index == 1) {
                                    System.out.println (headers[1]);
                                }
                                System.out.print(index + ". ");
                                printWithin(task);
                                index++;
                            }
                            break;
                        case 3:
                            if (task.getStart().getMonthValue() <= yearMonth.getMonthValue()) {
                                if (index == 1) {
                                    System.out.println (headers[1]);
                                }
                                System.out.print(index + ". ");
                                printWithin(task);
                                index++;
                            }
                            break;
                        case 4:
                            if (index == 1) {
                                System.out.println (headers[1]);
                            }
                                System.out.print(index + ". ");
                                printWithin(task);
                                index++;
                            break;
                    }
            }
        }
        if (index == 1) {
            System.out.println (headers[0]);
        }
    }

    /**
     * Prints out the tasks for the selected year
     * @param input User-input, includes the year
     * @param taskList ArrayList of tasks
     */
    public void listByYear (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }
        String[] headers = new String[2];
        headers[0] = "There are no tasks for that year";
        headers[1] = "Here are the tasks for that year:";
        String yearString = input.substring(4).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year year = Year.parse(yearString, formatter);
        int index = 1;
        for (Task task : taskList.getStore()) {
            String symbol = task.getSymbol();
            switch (symbol) {
                case "[D]":
                    if (year.getValue() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                    }
                    break;
                case "[E]":
                    if (year.getValue() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                    }
                    break;
                case "[W]":
                    boolean isYearWithin = false;
                    if (task.getStart().getYear() == year.getValue() &&
                            task.getEnd().getYear() == year.getValue()) {
                        isYearWithin = true;
                    }
                    if (task.getStart().getYear() < year.getValue() &&
                            task.getEnd().getYear() == year.getValue()) {
                        isYearWithin = true;
                    }
                    if (task.getStart().getYear() == year.getValue() &&
                            task.getEnd().getYear() > year.getValue()) {
                        isYearWithin = true;
                    }
                    if (task.getStart().getYear() < year.getValue() &&
                            task.getEnd().getYear() > year.getValue()) {
                        isYearWithin = true;
                    }
                    if (isYearWithin) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printWithin(task);
                        index++;
                        break;
                    }
            }
        }
        if (index == 1) {
            System.out.println (headers[0]);
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
