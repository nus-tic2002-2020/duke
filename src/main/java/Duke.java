import java.util.ArrayList;
import java.util.Scanner; //

//import DukeException;
public class Duke {
	private static ArrayList<Task> task = new ArrayList<Task>();
	private static int count = 0;
	private static String ln = "  ____________________________________________________________\n";
	private static boolean going = true;

	public static void main(String[] args) throws Exception {
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

		if (userinput.length() == 0) {
			throw new DukeException("Invalid input");
		}

		String[] _userinput = userinput.split(" ", 2);
		String input_type = (_userinput[0]).trim();

		switch (input_type) {
		case "bye": {
			System.out.println(ln + "   Bye. Hope to see you again soon!\n" + ln);
			going = false;
		}
			break;
		case "list": {
			System.out.println(ln + "Here are the tasks in your list:");
			listALL();
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
			System.out.println(ln + " Nice! I've marked this task as done:\n" + "[" + task.get(Tasknum - 1).icon() + "] "
					+ task.get(Tasknum - 1).getTitle() + "\n" + ln);
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
			System.out.println(ln + "Noted. I've removed this task: \n[" + task.get(Tasknum - 1).icon() + "] " + task.get(Tasknum - 1).getTitle());
			task.remove(Tasknum - 1);
			count--;
			listALL();
			System.out.println("Now you have "+count+" tasks in the list.");
			System.out.println(ln);
		}
			break;
		case "deadline": {
			try {
				String[] dl = _userinput[1].split("/");
				String by[] = (dl[1].split(" ", 2));
				task.add(count,new Deadline((dl[0]).trim(), by[1].trim()));
				printAdded(task.get(count).toString());
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
				task.add(count,new Event((dl[0]).trim(), at[1].trim()));
				printAdded(task.get(count).toString());
				count++;
			} catch (RuntimeException e) {
				throw new MissDescException(input_type);
			}

		}
			break;
		case "todo": {
			try {
				task.add(count,new Task(_userinput[1]));
				printAdded(task.get(count).toString());
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
			System.out.println(n + ". [" + task.get(a).icon() + "] " + task.get(a).getTitle());
			n++;
		}
	}

}
