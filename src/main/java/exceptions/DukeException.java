package exceptions;

/**
 * This represents the DukeException, meant for exceptions that run in Duke.
 * It will return the error message to the ui for it to be printed.
 */

public class DukeException extends Exception{

    String errorMessage;

    public DukeException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}
