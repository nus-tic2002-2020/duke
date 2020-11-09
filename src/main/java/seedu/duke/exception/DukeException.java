package seedu.duke.exception;

public class DukeException extends Exception {

    /**
     * To show the DukeException with more detailed exception message
     * @param message The error message thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}