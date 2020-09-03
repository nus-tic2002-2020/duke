import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static int count = 0;
    public static int seq = 1;
    public static Task[] list = new Task[100];

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
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
            echo();
        }
        else if (line.contains("done")) {
            String[] words = line.split(" ");
            if (words.length < 2) {
                System.out.println("Error: Please enter which task is done\n");
                echo();
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                System.out.println("Error: Please enter a valid task number\n");
                echo();
            }
            if (list[n-1].getStatusIcon().equals("\u2713")) {
                System.out.println("Error: Task has already been completed");
                System.out.println(list[n-1] + "\n");
                echo();
            }

            else {
                System.out.println("Nice! I've marked this task as done:");
                list[n-1].setStatus(true);
                System.out.println(list[n-1] + "\n");
                echo();
            }
        }
        else {
            System.out.println("added: " + line + "\n");
            list[count] = new Task(line);
            count++;
            echo();
        }
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        echo();
    }
}
