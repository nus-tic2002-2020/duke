package main;

public class EmptyDescriptionException extends Exception {
    /***
     * method for emptydescriptionexception to be called
     * this exception is used when descriptions of tasks are empty
     * @param message
     */

    public EmptyDescriptionException(String message) {
        super(message);
    }
}