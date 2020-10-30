import java.io.*;

public class Command {
    public boolean isExit;
    protected String commandLine;

    public Command(boolean isExit, String commandLine){
        this.isExit = isExit;
        this.commandLine = commandLine;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, DukeIOException, IOException {
        throw new UnsupportedOperationException("Requested operation is not supported.");
    };

}
