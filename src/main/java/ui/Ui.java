package ui;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import task.Task;
import tasklist.TaskList;
import enumerations.PriorityEnum;
import enumerations.SymbolEnum;
import exceptions.TooManySpacesException;

/**
 * This is the Ui (User interface) class for handling prints to the terminal.
 *
 * @author Aloysius Wong
 * @version 1.0
 * @since 08-11-2020
 */
public class Ui {

    /**
     * This creates the Ui-class object. It prints a welcome message to the terminal.
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
     * This method prints an error message when description is missing.
     */
    public void showNoDescriptionError() {
        System.out.println("I need a description or my code will burn!");
    }

    /**
     * This method prints an error message when date and time is missing.
     */
    public void showNoTimeFrameError() {
        System.out.println("I need a timeframe or my code will be blown away!");
    }

    /**
     * This method prints an error message when double-spacing is detected.
     */
    public void showTooManySpacesError() {
        System.out.println("No double spaces or my code will crash!");
    }

    /**
     * This method prints an error message when an invalid command is input by user. A list of commands is then printed.
     */
    public void showIllegalArgumentError() {
        System.out.println("Invalid input :( Please try again and follow the template closely!");
        printCmdList();
    }

    /**
     * This method prints an error message when invalid task number is inputted.
     */
    public void showInvalidNumberError() {
        System.out.println("Invalid function number. Try again.");
    }

    /**
     * This method prints an error message when marker "/by" or "/at" is incorrectly input by user.
     */
    public void showIndexError() {
        System.out.println("Make sure you typed identifier \"/by\", \"/at\", \"/from\" and/or \"/to\" correctly!"); }

    /**
     * This method prints an error message when priority-level is incorrectly input by user.
     */
    public void showIncorrectPriorityError() {
        System.out.println("Invalid priority setting. Use either NA, LOW, MEDIUM or HIGH.");
    }

    /**
     * This method prints an error message when priority-level is missing.
     */
    public void showMissingPriorityError() {
        System.out.println("Missing priority setting. Use either NA, LOW, MEDIUM or HIGH.");
    }

    /**
     * This method prints an error message when the date format input by user is invalid.
     * showDateFormatError1 is used when adding tasks.
     */
    public void showDateFormatError1() {
        System.out.println("Date format is wrong. Use dd/mm/yyyy hh:mm.");
    }

    /**
     * This method prints an error message when the date format input by user is invalid.
     * showDateFormatError1 is used when searching for tasks in a particular day.
     */
    public void showDateFormatError2() {
        System.out.println("Date format is wrong. Use dd/mm/yyyy.");
    }

    /**
     * This method prints an error message when the date format input by user is invalid.
     * showDateFormatError3 is used when searching for tasks in a particular month.
     */
    public void showDateFormatError3() {
        System.out.println("Date format is wrong. Use mm/yyyy.");
    }

    /**
     * This method prints an error message when the date format input by user is invalid.
     * showDateFormatError4 is used when searching for tasks in a particular year.
     */
    public void showDateFormatError4() {
        System.out.println("Date format is wrong. Use yyyy.");
    }

    /**
     * This method prints a goodbye message when closing programme.
     */
    public void printByeMessage() {
        System.out.println("Goodbye~ Hope to see you again soon! :3");
    }

    /**
     * This method prints a message when the deletion of a task is successful.
     */
    public void printDeleteMessage() {
        System.out.println("Noted. I've removed that task.");
    }

    /**
     * This method prints a message when edits are attempted on the list of tasks when the list is empty.
     */
    public void printEmptyListMessage() {
        System.out.println("Your list is already empty.");
    }

    /**
     * This method prints a message when a task has been successfully set as 'done'.
     */
    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done.");
    }

    /**
     * This method prints a message when the edit of a task priority-level is successful.
     */
    public void printPriorityMessage() {
        System.out.println("Roger. I've set the priority of this task as such.");
    }

    /**
     * This method prints a list of available commands to the user.
     */
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
                "-Change_Priority (item number) (priority level)\n" +
                "-Search_Priority (Priority-level)\n" +
                "-Bye");
    }

    /**
     * This private method is a helper function which prints the selected task to the terminal. It is reused multiple
     * times in the other public methods.
     *
     * @param task This is the task to be printed.
     */
    public void printTodo(Task task) {
        System.out.println("[T]"
                + task.getDone()
                + " "
                + task.getDescription()
                + " (Priority: " + task.getPriority()
                + ")");
    }

    /**
     * This private method is a helper function which prints the selected task to the terminal. It is reused multiple
     * times in the other public methods.
     *
     * @param task This is the task to be printed.
     */
    public void printDeadline(Task task) {
        System.out.println("[D]"
                + task.getDone()
                + " "
                + task.getDescription()
                + " (by: "
                + task.getDateAndTime().getDayOfMonth()
                + " "
                + task.getDateAndTime().getMonth()
                + " "
                + task.getDateAndTime().getYear()
                + ", "
                + String.format("%02d", task.getDateAndTime().getHour())
                + ":"
                + String.format("%02d", task.getDateAndTime().getMinute())
                + ") (Priority: "
                + task.getPriority()
                + ")");
    }

    /**
     * This private method is a helper function which prints the selected task to the terminal. It is reused multiple
     * times in the other public methods.
     *
     * @param task This is the task to be printed.
     */
    public void printEvent(Task task) {
        System.out.println("[E]"
                + task.getDone()
                + " "
                + task.getDescription()
                + " (at: "
                + task.getDateAndTime().getDayOfMonth()
                + " "
                + task.getDateAndTime().getMonth()
                + " "
                + task.getDateAndTime().getYear()
                + ", "
                + String.format("%02d", task.getDateAndTime().getHour())
                + ":"
                + String.format("%02d", task.getDateAndTime().getMinute())
                + ") (Priority: "
                + task.getPriority()
                + ")");
    }

    /**
     * This private method is a helper function which prints the selected task to the terminal. It is reused multiple
     * times in the other public methods.
     *
     * @param task This is the task to be printed.
     */
    public void printWithin(Task task) {
        System.out.println("[W]"
                + task.getDone()
                + " "
                + task.getDescription()
                + " (within: "
                + task.getStart().getDayOfMonth()
                + " "
                + task.getStart().getMonth()
                + " "
                + task.getStart().getYear()
                + ", "
                + String.format("%02d", task.getStart().getHour())
                + ":"
                + String.format("%02d", task.getStart().getMinute())
                + " to "
                + task.getEnd().getDayOfMonth()
                + " "
                + task.getEnd().getMonth()
                + " "
                + task.getEnd().getYear()
                + ", "
                + String.format("%02d", task.getEnd().getHour())
                + ":"
                + String.format("%02d", task.getEnd().getMinute())
                + ") (Priority: "
                + task.getPriority()
                + ")");
    }

    /**
     * This method prints a message when a task has been successfully added to the list.
     *
     * @param task This is the added task.
     */
    public void printAddMessage(Task task) {
        System.out.println("Got it. I've added this task:");

        SymbolEnum symbol = task.getSymbol();
        assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                : "Symbol is invalid during printing of successful task adding message.";

        switch (symbol) {
            case T:
                printTodo(task);
                break;

            case D:
                printDeadline(task);
                break;

            case E:
                printEvent(task);
                break;

            case W:
                printWithin(task);
                break;
        }
    }

    /**
     * This method prints out a list of tasks with matching descriptions to the search-word input by the user.
     * Different headers are printed depending on whether the list is empty, and whether there are any matches.
     *
     * @param input This is the input by the user in the form of "find (description)".
     * @param taskList This is the list of tasks to be combed through for matching results.
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors.
     */
    public void find(String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }

        if (taskList.getStore().size() == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        String[] headers = new String[2];
        headers[0] = "There are no matching tasks.";
        headers[1] = "Here are the matching tasks in your list:";


        String[] query = input.split(" ");

        int index = 1;
        for (Task task : taskList.getStore()) {
            SymbolEnum symbol = task.getSymbol();
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during search for matching tasks (find).";

            switch (symbol) {
                case T:
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printTodo(task);
                        index++;
                        break;
                    }

                case D:
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                        break;
                    }

                case E:
                    if (task.getDescription().contains(query[1].trim())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                        break;
                    }

                case W:
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
     * This method prints out the list of tasks to the terminal.
     *
     * @param taskList This is the list of tasks to be printed.
     */
    public void list(TaskList taskList) {
        if (taskList.getStore().size() == 0) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");

        int index = 1;
        for (Task task : taskList.getStore()) {
            SymbolEnum symbol = task.getSymbol();
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during listing out of all tasks.";

            switch (symbol) {
                case T:
                    System.out.print(index + ". ");
                    printTodo(task);
                    index++;
                    break;

                case D:
                    System.out.print(index + ". ");
                    printDeadline(task);
                    index++;
                    break;

                case E:
                    System.out.print(index + ". ");
                    printEvent(task);
                    index++;
                    break;

                case W:
                    System.out.print(index + ". ");
                    printWithin(task);
                    index++;
                    break;
            }
        }
    }

    /**
     * This method prints the tasks that fall on the same day as the search-input by user. Different headers are printed
     * depending on whether there are any matches.
     *
     * @param input This is the input by the user in the form of "Day (date e.g 10/10/2020)".
     * @param taskList This is the list of tasks to be combed through for matches.
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors.
     */
    public void listByDay (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }

        String[] headers = new String[2];
        headers[0] = "There are no tasks for that day.";
        headers[1] = "Here are the tasks for that day:";

        String dateString = input.substring(3).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        int index = 1;
        for (Task task : taskList.getStore()) {
            SymbolEnum symbol = task.getSymbol();
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during listing out of tasks of a specific day.";

            switch (symbol) {
                case D:
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

                case E:
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

                case W:
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
     * This method prints the tasks that fall on the same month as the search-input by user. Different headers are
     * printed depending on whether there are any matches.
     *
     * @param input This is the input by the user in the form of "Month (date e.g 9/2020)".
     * @param taskList This is the list of tasks to be combed through for matches.
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors.
     */
    public void listByMonth (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }

        String[] headers = new String[2];
        headers[0] = "There are no tasks for that month.";
        headers[1] = "Here are the tasks for that month:";

        String monthString = input.substring(5).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/yyyy");
        YearMonth yearMonth = YearMonth.parse(monthString, formatter);

        int index = 1;
        for (Task task : taskList.getStore()) {
            SymbolEnum symbol = task.getSymbol();
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during listing out of tasks of a specific month.";

            switch (symbol) {
                case D:
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

                case E:
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

                case W:
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
     * This method prints the tasks that fall on the same year as the search-input by user. Different headers are
     * printed depending on whether there are any matches.
     *
     * @param input This is the input by the user in the form of "Year (date e.g 2020)".
     * @param taskList This is the list of tasks to be combed through for matches.
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors.
     */
    public void listByYear (String input, TaskList taskList) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }

        String[] headers = new String[2];
        headers[0] = "There are no tasks for that year.";
        headers[1] = "Here are the tasks for that year:";

        String yearString = input.substring(4).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        Year year = Year.parse(yearString, formatter);

        int index = 1;
        for (Task task : taskList.getStore()) {
            SymbolEnum symbol = task.getSymbol();
            assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                    : "Symbol is invalid during listing out of tasks of a specific year.";

            switch (symbol) {
                case D:
                    if (year.getValue() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                    }
                    break;

                case E:
                    if (year.getValue() == (task.getDateAndTime().getYear())) {
                        if (index == 1) {
                            System.out.println (headers[1]);
                        }
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                    }
                    break;

                case W:
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
                    }
                    break;
            }
        }

        if (index == 1) {
            System.out.println (headers[0]);
        }
    }

    /**
     * This method searches for tasks of the same priority-level as input by user. Different headers are printed
     * depending on whether there are any matches.
     *
     * @param input This is the input by user which contains the priority-level to be searched, in the form of
     *              "Search_Priority (priority-level)".
     * @param tasks This is the list of tasks to be searched.
     * @throws TooManySpacesException This exception is thrown when double spacing is detected. Double spacing leads to
     *                                many errors.
     */
    public void searchPriority(String input, TaskList tasks) throws TooManySpacesException {
        if (input.contains("  ")) { throw new TooManySpacesException(); }

        String[] headers = new String[2];
        headers[0] = "There are no tasks of that priority-level.";
        headers[1] = "Here are the tasks of that priority-level:";

        String[] splitPriority = input.split(" ");
        PriorityEnum priority = PriorityEnum.valueOf(splitPriority[1].toUpperCase());

        int index = 1;
        for (Task task : tasks.getStore()) {
            if (priority == task.getPriority()) {
                if (index == 1) {
                    System.out.println (headers[1]);
                }
                SymbolEnum symbol = task.getSymbol();
                assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E ||
                        symbol == SymbolEnum.W : "Symbol is invalid during searching of tasks of a specific priority.";

                switch (symbol) {
                    case T:
                        System.out.print(index + ". ");
                        printTodo(task);
                        index++;
                        break;

                    case D:
                        System.out.print(index + ". ");
                        printDeadline(task);
                        index++;
                        break;

                    case E:
                        System.out.print(index + ". ");
                        printEvent(task);
                        index++;
                        break;

                    case W:
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
     * This method receives input from the user and returns it in string format.
     *
     * @return Returns the user input as string format.
     */
    public String receive() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
