public class DukeException extends Exception{
    String message;

    public DukeException(String x){
        message = x;
    }

    public String toString() {
        String exceptionMessage;
        if ((message.equals("event"))
                || (message.equals("deadline"))
                || (message.equals("todo"))){
            exceptionMessage = "  ☹ OOPS!!! The description of a " + message + " cannot be empty.";
        }else if((message.endsWith("/at")) || (message.endsWith("/by"))){
            exceptionMessage = "  ☹ OOPS!!! Please input a timing for this task";
        }
        else{
            exceptionMessage = "  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return exceptionMessage;
    }

}
