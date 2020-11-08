package duke;

import java.io.IOException;

import javax.swing.text.BadLocationException;

import exceptions.DukeException;
import exceptions.MissDescException;
import ui.Chat;
import ui.GUI;
import ui.Storage;

/**
 * This is my TIC2201 homework project. It is a personal task manager that you
 * can arrange and store your personal schedule. It recognizes commands by
 * reading your input. Schedule types: 'deadline', 'todo', 'event'. To exit and
 * save program, type 'bye'.
 * 
 * @author SilvermistVV
 * @version 1.0
 * @since August 2020
 */

public class Duke {

	public static void main(String[] args) throws Exception {
		GUI.main(null);
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				try {
					Storage.main();
					Chat.main();
				} catch (DukeException | BadLocationException | IOException | MissDescException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 800);
	}
}
