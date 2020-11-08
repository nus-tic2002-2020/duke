package exceptions;

import javax.swing.text.BadLocationException;

import ui.GUI;

public class DukeException extends Exception {

	/**
	 * This exception occurs when input command was not recognized. If there is no
	 * message, the default message will pop out.
	 */
	private static final long serialVersionUID = 1L;

	public DukeException(String msg) throws DukeException, BadLocationException {

		if (msg == "")
			msg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
		GUI.guiOutputWarning(msg);
	}
}
