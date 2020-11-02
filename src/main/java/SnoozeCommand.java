/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Arrays;

public class SnoozeCommand extends Command{
    protected Deadline deadline;
    private int index;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private int taskIndex;
    private final static String FORMAT_ERROR_MESSAGE = "Cannot snooze empty values. \r\n\tPlease provide the following format: \r\n\t\t\"snooze <task number> by <dd/MM/yyyy HHmm>\"";

    public SnoozeCommand(boolean isExit, String input){
        super(isExit, input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        if((commandLine.substring(6).trim()).isEmpty() || (!commandLine.contains("by"))) {
            throw new DukeException(FORMAT_ERROR_MESSAGE); // snooze 1 by 10/11/2020 1900
        }
        for(String word : commandLine.split(" ")){
            ++index;
            if(word.equals("by")){
                taskIndex = Integer.parseInt(commandLine.split(" ")[1])-1;
                if(taskList.getTasks(taskIndex) instanceof Deadline){
                    try{
                        String date = String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length));
                        try{
                            LocalDateTime.parse(date,dtf);
                        }catch(DateTimeParseException e){
                            throw new DukeException(FORMAT_ERROR_MESSAGE);
                        }
                        String newTaskDescription = taskList.getTasks(taskIndex).description;
                        taskList.deleteFromTaskList(taskIndex);
                        deadline = new Deadline(newTaskDescription, date);
                        taskList.addToTaskList(deadline);
                        ui.showOutput("Nice! I've snoozed this task:\n\t  " + deadline.toString());
                        storage.saveToFile();
                    }catch (IndexOutOfBoundsException e){
                        throw new DukeException("The list cannot be empty.");
                    }catch (NumberFormatException e){
                        throw new DukeException("Please input an integer.");
                    }
                }else{
                    throw new DukeException("You can only snooze deadlines!!!!");
                }
            }
        }
    }
}