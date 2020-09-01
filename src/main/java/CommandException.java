public class CommandException extends Exception{

    String message;

    public CommandException(){
        super();
    }

    public CommandException(String message){
        super();
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
