package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import duke.Duke;
import exceptions.DukeException;
import exceptions.MissDescException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * This is the major class. It initializes chat, read commands and process
 * storage.
 */

public class Chat extends Duke {
	protected static ArrayList<Task> task = new ArrayList<Task>();
	protected static int count = 0;
	protected static int TIME_NULL=0;
	protected static int MAX_ARRSIZE=100;
	protected static boolean isOngoing = true;
	private enum ServiceType{
		bye,list,find,done,delete,todo,event,deadline
	}

	/**
	 * @param going decides whether Duke will still scan user input. If user says
	 *              'bye' then, bye.
	 * @throws MissDescException
	 * @throws IOException
	 * @throws BadLocationException
	 */

	public static void main() throws DukeException, BadLocationException, IOException, MissDescException {
		GUI.guiOutput("Hello! I'm Duke. What can I do for you?");
	}

	public static void printAddedTasks(String content)
			throws DukeException, BadLocationException, IOException, MissDescException {
		int a = count + 1;
		GUI.guiOutput("Got it. I've added this task: ");
		GUI.guiOutput(content);
		GUI.guiOutput("Now you have " + a + " tasks in the list.");
	}

	public static void processChat(String userInput, boolean print)
			throws DukeException, IOException, MissDescException, BadLocationException {
		if (userInput.length() == 0) {
			throw new DukeException("Invalid input");
		}

		String[] userInputSplit = userInput.split(" ", 2);
		String input = (userInputSplit[0]).trim().toLowerCase();
		String data =null;
		ServiceType serviceType = null;
		if(userInputSplit.length>1) {
			data = userInputSplit[1];
		}
		try {
			serviceType= ServiceType.valueOf(input);
		} catch (Exception e) {
			throw new DukeException("Sorry I cannot understand you.");
		}

		switch (serviceType) {
		case bye: {
			try {
				Storage.saveFile();
				GUI.guiOutput("Current tasks are saved. Bye. Hope to see you again soon!\n");
				isOngoing = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			break;
		case list: {

			/**
			 * Two ways: list & list+date. Example: list. Example: list 2020-01-01 1800.
			 */

			if (userInputSplit.length == 1) {
				GUI.guiOutput("Here are the tasks in your list:");
				listAll();
			} else {
				try {
					LocalDate d = processDate(data);
					GUI.guiOutput("Here are the tasks in your list on " + data + ":");
					listAllwithDate(d);
				} catch (NumberFormatException e) {
					throw new DukeException("Invalid date");
				}
			}
		}
			break;
		case find: {
			if (data == "") {
				GUI.guiOutput("Input key.");
				return;
			} 
			try {
				listAllwithKey(data);
			} catch (NumberFormatException e) {
				throw new DukeException("Invalid number");
			}			
		}
			break;
		case done: {
			int taskID = 0;
			try {
				taskID = Integer.parseInt(data);
			} catch (NumberFormatException e) {
				throw new DukeException("Invalid number");
			}
			if (taskID > count) {
				throw new DukeException("Invalid task number");
			}
			task.get(taskID - 1).markDone();
			GUI.guiOutput(" Nice! I've marked this task as done:\n" + "[" + task.get(taskID - 1).icon() + "] "
					+ task.get(taskID - 1).getTitle() + "\n");
		}
			break;
		case delete: {
			String[] deleteArrStr = data.split(",");
			int[] deleteArr = new int[deleteArrStr.length];
			
			assert (deleteArrStr.length<count):"user removing all tasks!!";
			
			for(int a=0;a<deleteArr.length;a++) {
				int taskNum = 0;//delete 1,2
				try {
					taskNum = Integer.parseInt(deleteArrStr[a].trim());
					deleteArr[a]=taskNum;
				} catch (NumberFormatException e) {
					throw new DukeException("Sorry, I didn;t recognize this task number: "+deleteArr[a]);
				}
				if (taskNum > count) {
					throw new DukeException("Invalid task number");
				}
			}
			
			for(int a=deleteArr.length-1;a>=0;a--) {
				task.remove(deleteArr[a] - 1);
				count--;
			}			
			GUI.guiOutput("Noted. I've removed task "+data+" from your list.");
			if(count>0) {
				GUI.guiOutput("Now you have " + count + " tasks in the list.");
			}
			listAll();
		}
			break;
		case event:
		case deadline: {
			try {
				String[] content = data.split("/");
				String[] datetime = content[1].split(" ", 3);
				LocalDate date = processDate(datetime[1]);
				int time = Integer.parseInt(datetime[2]);
				boolean isAnomoly=DetectAnomalies(date, time);
				if (!isAnomoly)
					return;
				switch (serviceType) {
				case event:
					task.add(count, new Event(content[0].trim(), date, time, userInput));
					break;
				case deadline:
					task.add(count, new Deadline(content[0].trim(), date, time, userInput));
					break;
				default:
					return;
				}

				if (print) {
					printAddedTasks(task.get(count).printTask());
				}
				count++;
			} catch (RuntimeException e) {
				throw new DukeException("Please input datetime in correct format. Example: 2020-01-01 1300");
			}

		}
			break;
		case todo: {
			try {
				task.add(count, new Task(data, userInput));
				if (print) {
					printAddedTasks(task.get(count).toString());
				}
				count++;
			} catch (RuntimeException e) {
				throw new MissDescException(input);
			}

		}
			break;
		default: {
			throw new DukeException("Sorry I cannot understand you.");
		}
		}
	}

	/**
	 * The following three methods provides list of tasks with conditions.
	 * 
	 * @throws DukeException        Throws an error if the list is empty.
	 * @throws MissDescException
	 * @throws IOException
	 * @throws BadLocationException
	 */

	public static void listAll() throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
			GUI.guiOutput("There are no items currently in the list");
		}
		for (int a = 0; a < count; a++) {
			GUI.guiOutput(n + ". " + task.get(a).printTask());
			n++;
		}
		assert (n>1):"no tasks are there!";
	}

	public static void listAllwithDate(LocalDate d)
			throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
			GUI.guiOutput("There are no items currently in the list");
		}
		for (int a = 0; a < count; a++) {
			LocalDate date = task.get(a).getDate();
			if (date != null && date.equals(d)) {
				GUI.guiOutput(n + ". " + task.get(a).printTask());
				n++;
			}
		}
		if (n == 1)
			GUI.guiOutput("Oh you dont have a task on that day");
	}

	public static void listAllwithKey(String keyword)
			throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
			GUI.guiOutput("There are no items currently in the list");
		}
		for (int a = 0; a < count; a++) {
			String title = task.get(a).getTitle();
			if (title.toLowerCase().contains(keyword.toLowerCase())) {
				GUI.guiOutput(n + ". " + task.get(a).printTask());
				n++;
			}
		}
		if (n == 1) {
			GUI.guiOutput("Oh you dont have a task on that day");
		}
	}

	public static LocalDate processDate(String date) {
		LocalDate d = LocalDate.parse(date.trim());
		return d;
	}

	public static boolean DetectAnomalies(LocalDate date, int time) throws DukeException, BadLocationException {
		if (count == 0 || time == TIME_NULL)
			return true;
		for (int a = 0; a < count; a++) {
			Task t = task.get(a);
			if (t.getTime() == TIME_NULL || t.getDate() == null)
				return true;
			if (t.getDate().isEqual(date) && t.getTime() == time) {
				GUI.guiOutputWarning("New task was not added because it corrupt with " + t.printTask());
				return false;
			}
		}
		return true;
	}
}
