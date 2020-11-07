package duke.command;

public class DukeException extends Exception {

    public enum DukeError {
        NONE,
        MISSING_ARGUMENT,
        ANOMALY_ARGUMENT,
        MEMORY_FULL,
        TASK_NOT_FOUND,
        UNKNOWN_COMMAND,
        WRONG_DATA_FORMAT,
        WRITE_ERROR
    }

    private DukeError code;

    public DukeException(String message, DukeError code) {
        this(message, null, code);
    }

    public DukeException(String message, Throwable cause, DukeError code) {
        super(message, cause);
        this.code = code;
    }

    public void printError() {
        //System.out.println("    ____________________________________________________________");
        System.out.printf("     ☹ OOPS!!! %s\n", super.getMessage());
        //System.out.println("    ____________________________________________________________");
    }

    public DukeError getErrorCode() {
        return this.code;
    }

}
