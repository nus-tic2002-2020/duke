import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        List<Task> taskList = new ArrayList<Task>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner scan = new Scanner(System.in);
            String echo = scan.nextLine();

            if (!echo.equals("list") && !echo.contains("done")) {
                Task newTask = new Task(echo);
                taskList.add(newTask);
            }

            if (echo.contains("done")) {
                String doneSplit[] = echo.split(" ");
                int item = Integer.parseInt(doneSplit[1]) - 1;
                taskList.get(item).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + taskList.get(item).getStatusIcon() + "] " + taskList.get(item).toString());
            }
            else if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (echo.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i+1 + "." + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i));
                }
            }
            else {
                System.out.println(echo);
            }
        }
    }
}
