public class DukeException extends Exception {

    public enum DukeError {
        NONE,
        MISSING_ARGUMENT,
        MEMORY_FULL,
        UNKNOWN_COMMAND
    }

    private DukeError code;

    public DukeException(String message, DukeError code) {
        this(message, null, code);
    }

    public DukeException(String message, Throwable cause, DukeError code) {
        super(message, cause);
        this.code = code;
    }

    public void printError(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     ☹ OOPS!!! %s\n", super.getMessage());
        System.out.println("    ____________________________________________________________");
    }

    public DukeError getErrorCode() {
        return this.code;
    }

}
