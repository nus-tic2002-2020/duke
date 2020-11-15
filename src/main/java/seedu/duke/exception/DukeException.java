package seedu.duke.exception;

public class DukeException extends Exception {

    /**
     * Shows the DukeException with detailed exception/error message.
     *
     * @param message The error message thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}