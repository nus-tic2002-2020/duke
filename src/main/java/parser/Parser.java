package parser;

import exception.DukeException;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Handle user inputs.
 */
public class Parser {
    private String[] inputs;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param input User's command.
     */
    public Parser(String input) {
        this.inputs = input.split(" ");
    }

    /**
     * Return user input as command.
     *
     * @return Command of user input.
     */
    public String getUserCommand() {
        return inputs[0];
    }

    /**
     * Return index of specific task.
     *
     * @param size number of tasks in the list.
     * @return Index of that specific task.
     * @throws DukeException Exception message.
     */
    public int getTaskIndex(int size) throws DukeException{
        if (Integer.valueOf(inputs[1]) > size) {
            throw new DukeException("  ☹ OOPS!!! You have entered an invalid index.");
        }

        return Integer.valueOf(inputs[1]);
    }

    /**
     * Return the task's description.
     *
     * @return Description of task.
     * @throws DukeException Exception message.
     */
    public String getDescription() throws DukeException{
        if (inputs.length == 1) {
            throw new DukeException("  ☹ OOPS!!! The description of a " + inputs[0] + " cannot be empty!");
        }

        String description = inputs[1];
        int dateIndex = this.getDateIndex();

        for (int i = 2; i < dateIndex; i++) {
            description = description.concat(" " + inputs[i]);
        }

        return description;
    }

    /**
     * Return index of the date based on user input (deadline/event).
     *
     * @return Index of date.
     */
    public int getDateIndex() {
        int i = 2;

        while (i != inputs.length && inputs[i].charAt(0) != '/') {
            i++;
        }

        return i;
    }

    /**
     * Return date of deadline or event.
     *
     * @return Date of deadline or event.
     * @throws DukeException Exception message.
     */
    public LocalDate getDate() throws DukeException {
        int dateIndex = this.getDateIndex() + 1;

        if (dateIndex == (inputs.length + 1) || dateIndex == inputs.length) {
            throw new DukeException("  ☹ OOPS!!! Please input a timing for this task. " +
                    "Please follow the format: yyyy-mm-dd HH:mm");
        }

        LocalDate localDate = LocalDate.parse(inputs[dateIndex]);

        return localDate;
    }

    /**
     * Return 'to date' of Do within the period task.
     *
     * @return 'To date' of Do within the period task.
     * @throws DukeException Exception message.
     */
    public LocalDate getToDate() throws DukeException {
        int dateIndex = this.getDateIndex() + 1;

        if (dateIndex == (inputs.length + 1) || dateIndex == inputs.length) {
            throw new DukeException("  ☹ OOPS!!! Please input a timing for this task. " +
                    "Please follow the format: yyyy-mm-dd");
        }

        String toDate = inputs[inputs.length-1];
        LocalDate localToDate = LocalDate.parse(toDate);

        return localToDate;
    }

    /**
     * Return 'from date' of Do within the period task.
     *
     * @return 'From date' of Do within the period task.
     * @throws DukeException Exception message.
     */
    public LocalDate getFromDate() throws DukeException {
        int dateIndex = this.getDateIndex() + 1;

        if (dateIndex == (inputs.length + 1) || dateIndex == inputs.length) {
            throw new DukeException("  ☹ OOPS!!! Please input a timing for this task. " +
                    "Please follow the format: yyyy-mm-dd");
        }
        String fromDate = inputs[inputs.length-2];
        LocalDate localFromDate = LocalDate.parse(fromDate);

        return localFromDate;
    }

    /**
     * Return time of deadline/event.
     *
     * @return Time of deadline/event.
     * @throws DukeException Exception message.
     */
    public LocalTime getTime() throws DukeException {
        int timeIndex = this.getDateIndex() + 2;

        if (timeIndex == inputs.length) {
            throw new DukeException("  ☹ OOPS!!! Please input a timing for this task. " +
                    "Please follow this format: HH:mm (12:30)");
        }

        LocalTime timing = LocalTime.parse(inputs[timeIndex]);

        return timing;
    }
}
