import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        List<String> storeText = new ArrayList<String>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner scan = new Scanner(System.in);
            String echo = scan.nextLine();

            if (!echo.equals("list")) {
                storeText.add(echo);
            }

            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (echo.equals("list")) {
                for (int i = 0; i < storeText.size(); i++) {
                    System.out.println(i+1 + ". " + storeText.get(i));
                }
            }
            else {
                System.out.println(echo);
            }
        }
    }
}
