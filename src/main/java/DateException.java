public class DateException extends Exception{

    String message;
    boolean cancelConstruct;

    public DateException(){
        super();
    }

    public DateException(String message, boolean cancelConstruct){
        super();
        this.message = message;
        this.cancelConstruct = cancelConstruct;
    }

    public String getMessage(){
        return message;
    }
}

