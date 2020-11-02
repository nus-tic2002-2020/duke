/*************************************************************
 *
 *         Public class by factionsypho
 *
 * *************************************************************/

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Arrays;

public class DeadlineCommand extends Command{
    protected Deadline deadline;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public DeadlineCommand(boolean isExit, String commandLine){
        super(isExit,commandLine);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeIOException, DukeException, IOException{
        int index = 0;
        if((commandLine.substring(8).trim()).isEmpty()){
            throw new DukeIOException("deadline");
        }
        if(!commandLine.contains("by")){
            throw new DukeException("The date of a deadline cannot be empty.");
        }
        for(String word : commandLine.split(" ")){
            ++index;
            if(word.equals("by")){
                if(String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)).isEmpty()){
                    throw new DukeException("The date of a deadline cannot be empty.");
                }
                /*
                String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length)).isEmpty() -> checks for the second half of the input string.
                need to convert from format dd-mm-yyyy to 2nd of december 2019 or something
                 */
                else {
                    String description = String.join(" ", Arrays.copyOfRange(commandLine.split(" "), 1, index-1));
                    String date = String.join(" ", Arrays.copyOfRange(commandLine.split(" "), index, commandLine.split(" ").length));
                    try{
                        LocalDateTime.parse(date,dtf);
                    }catch(DateTimeParseException e){
                        throw new DukeException("Please provide a valid date in the following format: dd/MM/yyyy HHmm");
                    }
                    deadline = new Deadline(description, date);
                    taskList.addToTaskList(deadline);
                    ui.showOutput("Got it. I've added this task:\n\t  " + deadline.toString() + "\n\t Now you have " + taskList.getSize() + " tasks in the list.");
                    storage.saveToFile();
                }
            }
        }
    }
}
