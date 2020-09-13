import java.util.Scanner;

public class Duke {
    public static boolean containsIgnoreCase(String input, String match) {
        input = input.toLowerCase();
        if (input.contains(match)) {
            return true;
        }
        return false;
    }

    public static void add(Task[] store, String input, int index) {
        if (containsIgnoreCase(input, "deadline")) {
            String[] split = input.split("/by");
            split[0] = split[0].substring(8).trim();
            split[1] = split[1].trim();
            store[index] = new Deadline(split[0], split[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(store[index].getSymbol() + "[✗] " + store[index].getDescription() + " (by: " + store[index].getDateAndTime() +")");
            System.out.println("Now you have " + (index + 1) + " tasks in the list");
        }
        else if (containsIgnoreCase(input, "event")) {
            String[] split = input.split("/at");
            split[0] = split[0].substring(5).trim();
            split[1] = split[1].trim();
            store[index] = new Event(split[0], split[1]);
            System.out.println("Got it. I've added this task:");
            System.out.println(store[index].getSymbol() + "[✗] " + store[index].getDescription() + " (at: " + store[index].getDateAndTime() +")");
            System.out.println("Now you have " + (index + 1) + " tasks in the list");
        }
        else {
            input = input.substring(4).trim();
            store[index] = new ToDo(input);
            System.out.println("Got it. I've added this task:");
            System.out.println(store[index].getSymbol() + "[✗] " + store[index].getDescription());
            System.out.println("Now you have " + (index + 1) + " tasks in the list");
        }
    }

    public static void list(Task[] store) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task i : store) {
            if (i == null) {
                return;
            }
            else if (i.getSymbol() == "[T]") {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription());
                index++;
            }
            else if (i.getSymbol() == "[D]") {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (by: " + i.getDateAndTime() +")");
                index++;
            }
            else if (i.getSymbol() == "[E]") {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (at: " + i.getDateAndTime() +")");
                index++;
            }
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
            else if (containsIgnoreCase(input, "done")) {
                int tempIndex = getIndex(input);
                store[tempIndex].setDone();
            }
            else if (containsIgnoreCase(input, "todo")
                    || containsIgnoreCase(input, "deadline")
                    || containsIgnoreCase(input, "event")) {
                add(store, input, index);
                index ++;
            }
            else {
                System.out.println("Invalid command given :( Please try again~");
            }
        }
    }
}
