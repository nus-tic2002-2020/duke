package exceptions;

/**
 * This represents the DukeException, meant for exceptions that run in Duke.
 * It will return the error message to the ui for it to be printed.
 */

public class DukeException extends Exception {

    String errorMessage;

    /**
     * This is the constructor for DukeException
     *
     * @param errorMessage This is the error message for the specific exception when caught.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * This is an accessor for the error message of the exception
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

}
