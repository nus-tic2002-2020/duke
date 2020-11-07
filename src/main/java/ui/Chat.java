package ui;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

import duke.Duke;
import exceptions.DukeException;
import exceptions.MissDescException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
* This is the major class.
* It initializes chat, read commands and process storage.
*/

public class Chat extends Duke {
	protected static ArrayList<Task> task = new ArrayList<Task>();
	protected static int count = 0;
	protected static boolean going = true;

	/**
	* @param going decides whether Duke will still scan user input. If user says 'bye' then, bye. 
	 * @throws MissDescException 
	 * @throws IOException 
	 * @throws BadLocationException 
	*/

	public static void main() throws DukeException, BadLocationException, IOException, MissDescException {
		GUI.guiOutput("Hello! I'm Duke. What can I do for you?");
	}
	
	public static void printAdded(String content) throws DukeException, BadLocationException, IOException, MissDescException {
		int a = count + 1;
		GUI.guiOutput("Got it. I've added this task: ");
		GUI.guiOutput(content);
		GUI.guiOutput("Now you have " + a + " tasks in the list.");
	}

	public static void processScanner(String userinput, boolean print) throws DukeException, IOException, MissDescException, BadLocationException {

		if (userinput.length() == 0) {
			throw new DukeException("Invalid input");
		}

		String[] arr_userinputSplit = userinput.split(" ", 2);
		String input_type = (arr_userinputSplit[0]).trim().toLowerCase();

		switch (input_type) {
			case "bye": {
				try {
					Storage.saveFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				GUI.guiOutput( "Current tasks are saved. Bye. Hope to see you again soon!\n");
				going = false;
			}
				break;
			case "list": {
				
				/**
				* Two ways: list & list+date. 
				* Example: list.
				* Example: list 2020-01-01 1800.
				*/
				
				if(arr_userinputSplit.length==1) {
					GUI.guiOutput("Here are the tasks in your list:");
					listALL();			
				} else {
					try {
						LocalDate d = processDate(arr_userinputSplit[1]);
						GUI.guiOutput("Here are the tasks in your list on "+arr_userinputSplit[1]+":");
						listALLwithDate(d);
					} catch (NumberFormatException e) {
						throw new DukeException("Invalid date");
					}
				}	
			}
				break;
			case "find": {
				if(arr_userinputSplit[1]=="") {
					GUI.guiOutput("Key "+arr_userinputSplit[1]+" not found.");
				} else {
					try {						
						listALLwithKey(arr_userinputSplit[1]);
					} catch (NumberFormatException e) {
						throw new DukeException("Invalid number");
					}
				}		
			}
				break;
			case "done": {
				int Tasknum = 0;
				try {
					Tasknum = Integer.parseInt(arr_userinputSplit[1]);
				} catch (NumberFormatException e) {
					throw new DukeException("Invalid number");
				}
				if (Tasknum > count)
					throw new DukeException("Invalid task number");
				task.get(Tasknum - 1).markDone();
				GUI.guiOutput(" Nice! I've marked this task as done:\n" + "[" + task.get(Tasknum - 1).icon()
						+ "] " + task.get(Tasknum - 1).getTitle() + "\n");
			}
				break;
			case "delete": {
				int Tasknum = 0;
				try {
					Tasknum = Integer.parseInt(arr_userinputSplit[1]);
				} catch (NumberFormatException e) {
					throw new DukeException("Invalid number");
				}
				if (Tasknum > count)
					throw new DukeException("Invalid task number");
				GUI.guiOutput("Noted. I've removed this task: \n[" + task.get(Tasknum - 1).icon() + "] "
						+ task.get(Tasknum - 1).getTitle());
				task.remove(Tasknum - 1);
				count--;
				listALL();
				GUI.guiOutput("Now you have " + count + " tasks in the list.");
			}
				break;
			case "event":
			case "deadline": {
				try {
					String[] content = arr_userinputSplit[1].split("/");
					String datetime[] = content[1].split(" ", 3);
					LocalDate date= processDate(datetime[1]);
					int time=Integer.parseInt(datetime[2]);
					if(!chkDateTime(date,time)) return;
					switch(input_type) {
						case "event":
							task.add(count, new Event(content[0].trim(), date, time, userinput));
							break;
						case "deadline":
							task.add(count, new Deadline(content[0].trim(), date, time, userinput));
							break;
						default: return;
					}
					
					if(print) {
						printAdded(task.get(count).printTask());
					}
					count++;
				} catch (RuntimeException e) {
					throw new DukeException("Please input datetime in correct format. Example: 2020-01-01 1300");
				}
	
			}
				break;
			case "todo": {
				try {
					task.add(count, new Task(arr_userinputSplit[1],userinput));
					if(print) {
						printAdded(task.get(count).toString());
					}
					count++;
				} catch (RuntimeException e) {
					throw new MissDescException(input_type);
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
     * @throws DukeException Throws an error if the list is empty.
	 * @throws MissDescException 
	 * @throws IOException 
	 * @throws BadLocationException 
     */

	public static void listALL() throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
            throw new DukeException("There are no items currently in the list");
        }
		for (int a = 0; a < count; a++) {
			GUI.guiOutput(n+". "+task.get(a).printTask());
			n++;
		}
	}

	public static void listALLwithDate(LocalDate d) throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
            throw new DukeException("There are no items currently in the list");
        }
		for (int a = 0; a < count; a++) {
			LocalDate date=task.get(a).getDate();
			if(date!=null && date.equals(d)) {
				GUI.guiOutput(n+". "+task.get(a).printTask());
				n++;
			}
		}
		if(n==1) GUI.guiOutput("Oh you dont have a task on that day");
	}

	public static void listALLwithKey(String keyword) throws DukeException, BadLocationException, IOException, MissDescException {
		int n = 1;
		if (count == 0) {
            throw new DukeException("There are no items currently in the list");
        }
		for (int a = 0; a < count; a++) {
			String title=task.get(a).getTitle();
			if(title.toLowerCase().contains(keyword.toLowerCase())) {
				GUI.guiOutput(n+". "+task.get(a).printTask());
				n++;
			}
		}
		if(n==1) GUI.guiOutput("Oh you dont have a task on that day");
	}
	
	
	public static LocalDate processDate(String date) {
		LocalDate d=LocalDate.parse(date.trim());
		return d;
	}
	
	
	public static boolean chkDateTime(LocalDate date, int time) throws DukeException, BadLocationException {
		if(count==0||time==0) return true;
		for (int a = 0; a < count; a++) {
			Task t=task.get(a);
			if(t.getTime()==0 || t.getDate()==null) return true;
			if(t.getDate().isEqual(date) && t.getTime()==time) {				
				GUI.guiOutputWarning("New task was not added because it corrupt with "+t.printTask());
				return false;
			}
		}
		return true;
	}
}
