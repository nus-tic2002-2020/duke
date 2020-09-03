public class DateException extends Exception{

    String message;
    String noteType;
    boolean cancelConstruct;

    public DateException(){
        super();
    }

    public DateException(String message, String noteType, boolean cancelConstruct){
        super();
        this.message = message;
        this.noteType = noteType;
        this.cancelConstruct = cancelConstruct;
    }

    public String getMessage(){
        return message;
    }
}

