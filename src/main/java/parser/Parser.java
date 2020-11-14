package parser;

import exception.DukeException;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {
    private String[] inputs;
    private Ui ui;

    public Parser(String input) {
        this.inputs = input.split(" ");
    }

    public String getUserCommand() {
        return inputs[0];
    }


    public int getTaskIndex(int size) throws DukeException{
        if (Integer.valueOf(inputs[1]) > size) {
            throw new DukeException("  ☹ OOPS!!! You have entered an invalid index.");
        }

        return Integer.valueOf(inputs[1]);
    }

    public String getDescription() throws DukeException{
        if (inputs.length == 1) {
            //ui.showLine();
            throw new DukeException("  ☹ OOPS!!! The description of a " + inputs[0] + " cannot be empty!");
        }

        String description = inputs[1];
        int dateIndex = this.getDateIndex();

        for (int i = 2; i < dateIndex; i++) {
            description = description.concat(" " + inputs[i]);
        }

        return description;
    }

    public int getDateIndex() {
        int i = 2;

        while (i != inputs.length && inputs[i].charAt(0) != '/') {
            i++;
        }

        return i;
    }

    public LocalDate getDate() throws DukeException {
        int dateIndex = this.getDateIndex() + 1;

        if (dateIndex == (inputs.length + 1) || dateIndex == inputs.length) {
            throw new DukeException("  ☹ OOPS!!! Please input a timing for this task. " +
                    "Please follow the format: yyyy-mm-dd HH:mm");
        }

        LocalDate localDate = LocalDate.parse(inputs[dateIndex]);

        return localDate;
    }

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
