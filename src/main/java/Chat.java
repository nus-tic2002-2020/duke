import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Chat extends Duke {
	protected static ArrayList<Task> task = new ArrayList<Task>();
	protected static int count = 0;
	private static String ln = "____________________________________________________________\n";
	protected static boolean going = true;
	
	public static void main() throws Exception {
		System.out.println("Hello! I'm Duke\nWhat can I do for you?");
		while (going) {
			try {
				chat();
			} catch (Exception e) {
			}
		}
	}
	
	public static void printAdded(String content) {
		int a = count + 1;
		System.out.println(ln + " Got it. I've added this task: ");
		System.out.println(content);
		System.out.println("Now you have " + a + " tasks in the list.");
		System.out.println(ln);
	}

	public static void chat() throws Exception {
		Scanner scan = new Scanner(System.in); // Create a Scanner object
		String userinput = (scan.nextLine()).trim();
		processScanner(userinput,true);
	}
	
	public static void processScanner(String userinput, boolean print) throws Exception {

		if (userinput.length() == 0) {
			throw new DukeException("Invalid input");
		}

		String[] _userinput = userinput.split(" ", 2);
		String input_type = (_userinput[0]).trim().toLowerCase();

		switch (input_type) {
			case "bye": {
				Storage.saveFile();
				System.out.println(ln + "   Current tasks are saved. Bye. Hope to see you again soon!\n" + ln);
				going = false;
			}
				break;
			case "list": {
				//two ways: list & list+date. exp: list 2020-01-01
				if(_userinput[1]=="") {
					System.out.println(ln + "Here are the tasks in your list:");
					listALL();			
				} else {
					try {
						LocalDate d = processDate(_userinput[1]);
						System.out.println(ln + "Here are the tasks in your list on "+_userinput[1]+":");
						listALLwithDate(d);
					} catch (NumberFormatException e) {
						throw new DukeException("Invalid number");
					}
				}
				System.out.println(ln);		
			}
				break;
			case "find": {
				//two ways: list & list+date. exp: list 2020-01-01
				if(_userinput[1]=="") {
					System.out.println(ln + "Key "+_userinput[1]+" not found.");
				} else {
					try {						
						listALLwithKey(_userinput[1]);
					} catch (NumberFormatException e) {
						throw new DukeException("Invalid number");
					}
				}
				System.out.println(ln);		
			}
				break;
			case "done": {
				int Tasknum = 0;
				try {
					Tasknum = Integer.parseInt(_userinput[1]);
				} catch (NumberFormatException e) {
					throw new DukeException("Invalid number");
				}
				if (Tasknum > count)
					throw new DukeException("Invalid task number");
				task.get(Tasknum - 1).markDone();
				System.out.println(ln + " Nice! I've marked this task as done:\n" + "[" + task.get(Tasknum - 1).icon()
						+ "] " + task.get(Tasknum - 1).getTitle() + "\n" + ln);
			}
				break;
			case "delete": {
				int Tasknum = 0;
				try {
					Tasknum = Integer.parseInt(_userinput[1]);
				} catch (NumberFormatException e) {
					throw new DukeException("Invalid number");
				}
				if (Tasknum > count)
					throw new DukeException("Invalid task number");
				System.out.println(ln + "Noted. I've removed this task: \n[" + task.get(Tasknum - 1).icon() + "] "
						+ task.get(Tasknum - 1).getTitle());
				task.remove(Tasknum - 1);
				count--;
				listALL();
				System.out.println("Now you have " + count + " tasks in the list.");
				System.out.println(ln);
			}
				break;
			case "deadline": {
				try {
					String[] dl = _userinput[1].split("/");
					String by[] = (dl[1].split(" ", 2));
					task.add(count, new Deadline((dl[0]).trim(), processDate(by[1]),userinput));
					if(print) {
						printAdded(task.get(count).toString());
					}
					count++;
				} catch (RuntimeException e) {
					throw new MissDescException(input_type);
				}
	
			}
				break;
			case "event": {
				try {
					String[] dl = _userinput[1].split("/");
					String at[] = (dl[1].split(" ", 2));
					task.add(count, new Event((dl[0]).trim(), processDate(at[1]),userinput));
					if(print) {
						printAdded(task.get(count).toString());
					}
					count++;
				} catch (RuntimeException e) {
					throw new MissDescException(input_type);
				}
	
			}
				break;
			case "todo": {
				try {
					task.add(count, new Task(_userinput[1],userinput));
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
				throw new DukeException("");
			}
		}
	}

	public static void listALL() {
		int n = 1;
		for (int a = 0; a < count; a++) {
			System.out.println(n+". "+task.get(a).printTask());
			n++;
		}
	}

	public static void listALLwithDate(LocalDate d) {
		int n = 1;
		for (int a = 0; a < count; a++) {
			LocalDate date=task.get(a).getDate();
			if(date!=null && date.equals(d)) {
				System.out.println(n+". "+task.get(a).printTask());
				n++;
			}
		}
		if(n==1) System.out.println("Oh you dont have a task on that day");
	}

	public static void listALLwithKey(String keyword) {
		int n = 1;
		for (int a = 0; a < count; a++) {
			String title=task.get(a).getTitle();
			if(title.toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(n+". "+task.get(a).printTask());
				n++;
			}
		}
		if(n==1) System.out.println("Oh you dont have a task on that day");
	}
	
	
	public static LocalDate processDate(String msg) {
		LocalDate d=LocalDate.parse(msg.trim());;
		return d;
	}
}
