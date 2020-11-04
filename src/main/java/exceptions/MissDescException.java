package exceptions;

/**
* This exception is used to check missing descriptions from message given from _userinput[1]. 
*/
public class MissDescException extends Exception {
	
	public MissDescException(String msg) {
		   System.out.println ("OOPS!!! The description of a "+msg+" cannot be empty.");
	    }
}
