import java.util.Scanner;

public class Duke {
    public static void add(Task[] store, String input, int index) {
        store[index] = new Task(input, false);
        System.out.println("Added: " + input);
    }

    public static void list(Task[] store) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task i : store) {
            if (i == null) {
                return;
            }
            System.out.println(index + ". " + i.getDone() + " " + i.getDescription());
            index ++;
        }
    }

    public static int getIndex(String input) {
        String index = input.substring(5);
        return (Integer.parseInt(index) - 1);
    }

    public static void main(String[] args) {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";

        String input = "blank";
        Task[] store = new Task[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");

        while (input.equalsIgnoreCase("Bye") == false) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("Bye") == true) {
                System.out.println("Goodbye~ Hope to see you again soon! :3");
            }
            else if (input.equalsIgnoreCase("List") == true) {
                list(store);
            }
            else if (input.contains("done") == true) {
                int tempIndex = getIndex(input);
                System.out.println("Nice! I've marked this task as done:");
                store[tempIndex].setDone();
                System.out.println(  store[tempIndex].getDone() + " " + store[tempIndex].getDescription());
            }
            else {
                add(store, input, index);
                index ++;
            }
        }
    }
}
