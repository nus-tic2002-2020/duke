package duke;
import ui.Chat;
import ui.Storage;

/**
* This is my TIC2201 homework project.
* It is a personal task manager that you can arrange and store your personal schedule.
* It recognizes commands by reading your input.
* Schedule types: 'deadline', 'todo', 'event'.
* To exit and save program, type 'bye'.
* @author SilvermistVV
* @version 1.0
* @since August 2020
*/

public class Duke {
	
	public static void main(String[] args) throws Exception {
		Storage.main();
		Chat.main();
	}
}
