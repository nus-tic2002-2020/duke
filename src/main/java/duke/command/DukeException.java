package duke.command;

/**
 * Duke exception handling
 */
public class DukeException extends Exception {

    /**
     * DukeException error enum
     */
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

    /**
     * Constructor for new Duke exceptions. {@link Throwable} is default to null.
     * @param message of error
     * @param code of error
     */
    public DukeException(String message, DukeError code) {
        this(message, null, code);
    }

    /**
     * Constructor for new Duke exceptions. {@link Throwable} is default to null.
     * @param message of error as String
     * @param cause of error as {@link Throwable}
     * @param code of error as {@link DukeError} enum
     */
    public DukeException(String message, Throwable cause, DukeError code) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Print and get error from superclass {@link Exception}.
     * @return error message
     */
    public String printError() {
        //System.out.println("    ____________________________________________________________");
        System.out.printf("     ☹ OOPS!!! %s\n", super.getMessage());
        //System.out.println("    ____________________________________________________________");
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }

    /**
     * Getter of error code as {@link DukeError} enum
     * @return DukeError enum
     */
    public DukeError getErrorCode() {
        return this.code;
    }

}
