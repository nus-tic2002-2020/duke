package seedu.duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ScheduleCommand extends Command{
    public static final String COMMAND_WORD = "schedule";
    public static final String SCHEDULE_MSG = "\tHere are the schedule tasks in your list: ";
    public static final String SCHEDULE_MSG2 = "\t☹ OOPS!!! You have nothing on that day yet.";

    /**
     * To create ScheduleCommand and initialise the isExit boolean value and description according to user input.
     * @param   isExit          A boolean value if the exit condition is true.
     * @param   description     A task description/command from user.
     */
    public ScheduleCommand(boolean isExit, String description){
        super(isExit, description);
    }

    /**
     * To search the date and return the search result in a list format.
     * @param   taskList       The array of tasks stored in an ArrayList.
     * @param   ui             The User Interface (UI) to handle interaction with user.
     * @param   storage        The storage to handle storing and reading of tasks from a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        LocalDate date;
        boolean isMatch = false;

        if (description.substring(8).equals("")) {
            throw new DukeException("\t☹ OOPS!!! The of schedule cannot be empty.\n");
        }
        description = description.substring(9);
        date = stringToDate(description); //date from schedule
        List<String> matchedTasksList = new ArrayList<>();
        System.out.println("\t____________________________________________________________");
        System.out.println(SCHEDULE_MSG);
        for(int i=0;i<taskList.length();i++){
            Task task = taskList.getATask(i);
            if(task.getDate().toLocalDate().equals(date)){
                matchedTasksList.add(task.getDescription());
                isMatch = true;
            }
        }
        if (isMatch){
            for (int i=0; i< matchedTasksList.size(); i++){
                System.out.println( "\t" + (i+1) + ". " + matchedTasksList.get(i));
            }
        }else{
            System.out.println(SCHEDULE_MSG2);
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * To convert the date and time stated by user to a LocalDateTime format of (d/MM/yyyy HHmm)
     * @param   date            The date from user.
     * @return  LocalDateTime   The date and time in a LocalDateTime format.
     * @throws  DukeException   If the input from user is not the format (dd/mm/yyyy HHmm).
     */
    private LocalDate stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e){
            throw new DukeException("The format of the date and time must be in this format: dd/mm/yyyy (13/08/2019)");
        }
    }
}