/***************************************************************
 *
 *         Custom error handling class.
 *
 * @throws DukeException        Catching errors
 * *************************************************************/

public class DukeException extends Exception{
    public DukeException(String message){
        super(message);
    }
}