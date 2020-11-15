package exception;

/**
 * Exceptions for Duke.
 */
public class DukeException extends Exception{
    String message;

    /**
     * Constructor.
     *
     * @param message error message.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Constructor.
     *
     * @return error message.
     */
    public String toString(){
        return message;
    }
}
