package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ScheduleCommand extends Command{
    public static final String COMMAND_WORD = "schedule";
    public static final String SCHEDULE_MSG = "\tHere are the schedule tasks in your list: ";
    public static final String SCHEDULE_MSG2 = "\t☹ OOPS!!! You have nothing on that day yet.";

    /**
     * Creates ScheduleCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit              The boolean value if the exit condition is true.
     * @param   description         The task description/command from user.
     */
    public ScheduleCommand(boolean isExit, String description){
        super(isExit, description);
    }

    /**
     * Searches the task if the task date matched with user input date, and prints the matched tasks in a list format.
     * @param   taskList            The array of tasks stored in as an ArrayList.
     * @param   ui                  The User Interface (UI) to handle the interaction with user.
     * @param   storage             The storage to handle storing and reading of task from the file.
     * @throws  DukeException       To handle error and exception, if the user inputs an empty description.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        LocalDate date;
        boolean isMatch = false;
        List<String> matchedTasksList = new ArrayList<>();

        if (description.substring(8).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The date of schedule cannot be empty.\n");
        }
        description = description.substring(9);
        date = stringToDate(description);  //date from user
        System.out.println("\t____________________________________________________________");
        for(int i=0;i<taskList.length();i++){
            Task task = taskList.getATask(i);
            if(task.getDate().toLocalDate().equals(date)){
                matchedTasksList.add(task.getDescription());
                isMatch = true;
            }
        }
        if (isMatch){
            System.out.println(SCHEDULE_MSG);
            for (int i=0; i< matchedTasksList.size(); i++){
                System.out.println( "\t" + (i+1) + ". " + matchedTasksList.get(i));
            }
        }else{
            System.out.println(SCHEDULE_MSG2);
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Converts the date stated by user into the LocalDate format: (dd/MM/yyyy).
     * @param   date                The date stated by user.
     * @return  LocalDate           The date in a LocalDateTime format.
     * @throws  DukeException       To handle error and exception, if the input from user is not the format (dd/mm/yyyy HHmm).
     */
    private LocalDate stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e){
            throw new DukeException("The format of the date and time must be in this format: DD/MM/YYYY");
        }
    }
}