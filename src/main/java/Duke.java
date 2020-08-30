import java.util.Scanner;

public class Duke {
    public static void add(String[] store, String input, int index) {
        store[index] = input;
        System.out.println("Added: " + input);
    }

    public static void list(String[] store) {
        int index = 1;
        for (String i : store) {
            if (i == null) {
                return;
            }
            System.out.println(index + ". " + i);
            index ++;
        }
    }

    public static void main(String[] args) {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";

        String input = "blank";
        String[] store = new String[100];
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
            else {
                add(store, input, index);
                index ++;
            }
        }
    }
}
