public class DukeIOException extends Exception{
    public DukeIOException(String message){
        super("The input of " + message + " cannot be empty");
    }
}
