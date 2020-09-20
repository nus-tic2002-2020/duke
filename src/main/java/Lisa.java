import java.util.Scanner;

public class Lisa {
    public static int getIndex(String input) throws Exception {
        String[] temp = input.split(" ");
        return (Integer.parseInt(temp[1]) - 1);
    }

    public static void list(Task[] store) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task i : store) {
            if (i == null) {
                return;
            }
            else if (i.getSymbol().equals("[T]")) {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription());
                index++;
            }
            else if (i.getSymbol().equals("[D]")) {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (by: " + i.getDateAndTime() +")");
                index++;
            }
            else if (i.getSymbol().equals("[E]")) {
                System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (at: " + i.getDateAndTime() +")");
                index++;
            }
        }
    }

    public static boolean catcher(String input) {
        input = input.toLowerCase();
        String[] split = input.split(" ");
        return input.trim().length() == split[0].length();
    }

    public static void add(Task[] store, String input, int index) throws Exception {
        int function = 0;
        if (input.toLowerCase().startsWith("todo")) { function = 1; }
        if (input.toLowerCase().startsWith("deadline")) { function = 2; }
        if (input.toLowerCase().startsWith("event")) { function = 3; }
        switch (function) {
            case 1: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                input = input.substring(4).trim();
                store[index] = new ToDo(input);
                store[index].print(index);
                break;
            }
            case 2: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                String[] split = input.split("/by");
                if (catcher(split[1])) { throw new NoDeadlineException(); }
                split[0] = split[0].substring(8).trim();
                split[1] = split[1].trim();
                store[index] = new Deadline(split[0], split[1]);
                store[index].print(index);
                break;
            }
            case 3: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                String[] split = input.split("/at");
                if (catcher(split[1])) { throw new NoTimeframeException(); }
                split[0] = split[0].substring(5).trim();
                split[1] = split[1].trim();
                store[index] = new Event(split[0], split[1]);
                store[index].print(index);
                break;
            }
        }
    }

    public static void runProgramme(String input, int index, Task[] store) throws Exception {
        Scanner in = new Scanner(System.in);
        while (!input.toLowerCase().equals("bye")) {
            input = in.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.println("Goodbye~ Hope to see you again soon! :3");
            }
            else if (input.toLowerCase().equals("list")) {
                list(store);
            }
            else if (input.toLowerCase().startsWith("done")) {
                try {
                    int tempIndex = getIndex(input);
                    store[tempIndex].setDone();
                } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error~ Invalid function number.");
                }
            }
            else if (input.toLowerCase().startsWith("todo")
                    || input.toLowerCase().startsWith("deadline")
                    || input.toLowerCase().startsWith("event")) {
                try {
                    add(store, input, index);
                    index ++;
                } catch (NoDescriptionException e) {
                    System.out.println("Error~ The description can't be left blank!");
                } catch (NoDeadlineException e) {
                    System.out.println("Error~ The deadline can't be left blank!");
                } catch (NoTimeframeException e) {
                    System.out.println("Error~ The time frame can't be left blank!");
                }
            }
            else {
                System.out.println("Invalid input :( Please try again and follow the template closely!");
                System.out.println("-ToDo (description)");
                System.out.println("-Deadline (description) /by (date)");
                System.out.println("-Event (description) /at (date&time)");
                System.out.println("-List");
                System.out.println("-Done (function number)");
                System.out.println("-Bye");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";

        String input = "blank";
        Task[] store = new Task[100];
        int index = 0;
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");

        runProgramme(input, index, store);
    }
}
