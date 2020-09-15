import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner scan = new Scanner(System.in);
            String echo = scan.nextLine();

            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(echo);
            }
        }
    }
}
