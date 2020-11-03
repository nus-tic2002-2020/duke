import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> store = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        int flag = 0;
        while (flag == 0) {
            String line = in.nextLine();
            try {
                flag = text(line.trim());
            } catch (DukeException e) { //unknown input
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + line.trim() + " cannot be empty.");
            }
        }
    }

    public static int text(String input) throws DukeException {
        String[] num = input.split(" ");
        if (/*num.length > 0 && */!num[0].isBlank()) {
            String command = num[0];
            switch (command) {
                case "list":
                    listTask();
                    break;
                case "done":
                    Taskdone(num);
                    break;
                case "todo":
                    ToDoTask(input);
                    break;
                case "deadline":
                    DeadlineTask(input);
                    break;
                case "event":
                    EventTask(input);
                    break;
                case "delete":
                    deleteTask(num);
                    break;
                case "bye":
                    System.out.print("Bye. Hope to see you again soon!");
                    return 1;
                default:
                    throw new DukeException();
            }
        }
        return 0;
    }

    /*
    public static void addTask(String description) {
        Task t = new Task(description);
        store[count++] = t;
        System.out.println("added: " + t);
    }*/

    public static void listTask() {
        System.out.println("Here are the tasks in your list: ");
        int count = store.size();
        for (int a = 0; a < count; a++) {
            Task t = store.get(a);
            System.out.println(a + 1 + ". " + t);
        }
    }

    public static void Taskdone(String[] num) {
        System.out.println("Nice! I've marked this task as done: ");
        for (int i = 1; i < num.length; i++) { //skip 0 as text, looking for the number
            int TaskNo = Integer.parseInt(num[i])-1; //change string to No
            if (store.size() > TaskNo) { //to check that input is in list
                Task t = store.get(TaskNo);
                t.markAsDone();
                System.out.println(t);
            }
        }
    }

    public static void ToDoTask(String description) {
        String[] td = description.split("todo "); //omit the first word
        Task t = new ToDo(td[1]);
        store.add(t);
        System.out.println("Got it. I've added this task: : ");
        System.out.println(t);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public static void DeadlineTask(String description) {
        String[] due = description.split("/by ");
        Task t = new Deadline(due[0], due[1]);
        store.add(t);
        System.out.println("Got it. I've added this task: : ");
        System.out.println(t);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public static void EventTask(String description) {
        System.out.println("Got it. I've added this task: : ");
        String[] due = description.split("/at ");
        Task t = new Event(due[0], due[1]);
        store.add(t);
        System.out.println("Got it. I've added this task: : ");
        System.out.println(t);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public static void deleteTask(String[] num) {
        System.out.println("Noted. I've removed this task: ");
        for (int i = 1; i < num.length; i++) { //skip 0 as text, looking for the number
            int deleteTaskNo = Integer.parseInt(num[i])-1; //change string to No
            if (store.size() > deleteTaskNo ) { //to check that input is in list
                Task t = store.get(deleteTaskNo); //get the item to be deleted
                System.out.println(t); //print out task to be deleted
                store.remove(deleteTaskNo);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
            }
        }
    }
}


