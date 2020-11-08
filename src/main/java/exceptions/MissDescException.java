package exceptions;

import javax.swing.text.BadLocationException;

import ui.GUI;

public class MissDescException extends Exception {
	
	/**
	 *  This exception is used to check missing descriptions from message given from _userinput[1].
	 */
	private static final long serialVersionUID = 1L;

	public MissDescException(String msg) throws DukeException, BadLocationException {
		GUI.guiOutput ("OOPS!!! The description of a "+msg+" cannot be empty.");
	    }
}
