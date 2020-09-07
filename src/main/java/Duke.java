import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static int count = 0;
    public static int seq = 1;
    public static Task[] list = new Task[100];

    public static void sort(String t) throws DukeException { //classify tasks between todo / deadline / event
        if (t.contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            String description = t.substring(5);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("Error: Task has already been added previously\n");
                }
            }
            list[count] = new Todo(description);
        }
        else if (t.contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int n = t.indexOf('/');
            String description = t.substring(9, n-1);
            String by = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("Error: Task has already been added previously\n");
                }
            }
            list[count] = new Deadline(description, by);
        }
        else if (t.contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int n = t.indexOf('/');
            String description = t.substring(6, n-1);
            String at = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                if (p.description.equals(description)) {
                    throw new DukeException("Error: Task has already been added previously\n");
                }
            }
            list[count] = new Event(description, at);
        }
        else {
            throw new DukeException("Error: Please enter a valid task such as todo / deadline / event\n");
        }
    }

    public static void echo() throws DukeException{ //classify commands such as bye / done / list / add tasks
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        else if (line.equals("list")) {
            System.out.println("Here are the tasks in your list:\n");
            Task[] print = Arrays.copyOf(list,count);
            for (Task p : print) {
                System.out.println(seq + ". " + p);
                seq++;
            }
            System.out.println("\n");
            seq = 1;
        }
        else if (line.contains("done")) {
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("Error: Please enter which task is done\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            if (list[n-1].getStatusIcon().equals("\u2713")) {
                throw new DukeException("Error: Task has already been completed\n");
            }
            else {
                System.out.println("Nice! I've marked this task as done:");
                list[n-1].setStatus(true);
                System.out.println(list[n-1] + "\n");
            }
        }
        else {
            sort(line); //Move into sort method
            System.out.println("Got it. I've added this task:");
            System.out.println(list[count]);
            count++;
            System.out.println("Now you have " + count + " tasks in the list.\n");
        }
    }

    public static void main(String[] args) throws DukeException {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        while(true) {
            try {
                echo(); //push into recursive loop echo()
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        }
}
