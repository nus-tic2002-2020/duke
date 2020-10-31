import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Task [] store = new Task [100];
    private static int count =0;

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
            flag = text(line.trim());
        }
    }

    public  static int text(String input) {
        String[] num = input.split(" ");
        if (num.length > 0 && !num[0].isBlank()) {
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
                case "bye":
                    System.out.print("Bye. Hope to see you again soon!");
                    return 1;
                default:
                    addTask(input);
            }
        }
        return 0;
    }


    public static void addTask (String description) {
        Task t = new Task(description);
        store[count++] = t;
        System.out.println("added: " + t);
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int a = 0; a < count; a++) {
            Task t = store[a];
            System.out.println(a + 1 + ". " + store[a]);
        }
    }

    public static void Taskdone(String[] num){
        System.out.println("Nice! I've marked this task as done: ");
        for (int i = 1; i < num.length; i++){ //skip 0 as text, looking for the number
            int TaskNo = Integer.parseInt(num[i]); //change string to No
            if (store[TaskNo-1] != null ){ //to check that input is in list
                Task t = store[TaskNo-1];
                t.markAsDone();
                System.out.println( store[TaskNo-1]);
            }
        }
    }

    public static void ToDoTask(String description){
        System.out.println("Got it. I've added this task: : ");
        String[] td = description.split("todo "); //omit the first word
        Task t = new ToDo(td[1]);
        store[count++] = t;
        System.out.println(store[count-1]);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public static void DeadlineTask(String description){
        System.out.println("Got it. I've added this task: : ");
        String[] due = description.split("/by ");
        Task t = new Deadline(due[0],due[1]);
        store[count++] = t;
        System.out.println( store[count-1]);
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    public static void EventTask(String description){
        System.out.println("Got it. I've added this task: : ");
        String[] due = description.split("/at ");
        Task t = new Event(due[0],due[1]);
        store[count++] = t;
        System.out.println( store[count-1]);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}


