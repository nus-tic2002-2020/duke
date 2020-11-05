package main;

/***
 * method for invalidcommandexception to be called
 */

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}