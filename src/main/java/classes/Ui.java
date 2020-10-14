package classes;

import java.util.Scanner;

public class Ui {
    public Ui() {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");
    }

    public static void showNoDescriptionError() {
        System.out.println("I need a description or my code will burn!");
    }

    public static void showNoDeadlineError() {
        System.out.println("I need a deadline or my code will drown!");
    }

    public static void showNoTimeFrameError() {
        System.out.println("I need a timeframe or my code will be blown away!");
    }

    public static void showTooManySpacesError() {
        System.out.println("No double spaces or my code will crash!");
    }

    public static void showArrayIndexError() {
        System.out.println("Make sure you typed identifier \"/by\" or \"/at\" correctly!");
    }

    public static void printAddMessage(String input) {
        String[] temp = input.split(" ");
        String function = input.substring(0, 1).toUpperCase();
        switch (function) {
            case "T": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][X] " + temp[1]);
                break;
            }
            case "D": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[D][X] " + temp[1] + " (by: " + temp[3] + ")");
                break;
            }
            case "E": {
                System.out.println("Got it. I've added this task:");
                System.out.println("[E][X] " + temp[1] + " (at: " + temp[3] + ")");
                break;
            }
        }
    }

    public static void printDeleteMessage() {
        System.out.println("Noted. I've removed that task:");
    }

    public static void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public static void list(TaskList i) {
        if (i.getStore().size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task z : i.getStore()) {
            String symbol = z.getSymbol();
            switch (symbol) {
                case "[T]": {
                    System.out.println(index + ". [T]" + z.getDone() + " " + z.getDescription());
                    index++;
                    break;
                }
                case "[D]": {
                    System.out.println(index + ". [D]" + z.getDone() + " " + z.getDescription() + " (by: " + z.getDateAndTime() +")");
                    index++;
                    break;
                }
                case "[E]": {
                    System.out.println(index + ". [E]" + z.getDone() + " " + z.getDescription() + " (at: " + z.getDateAndTime() +")");
                    index++;
                    break;
                }
            }
        }
    }

    public static void showInvalidNumberError() {
        System.out.println("Error~ Invalid function number.");
    }

    public static void showIllegalArgumentError() {
        System.out.println("Invalid input :( Please try again and follow the template closely!\n" +
                "-ToDo (description)\n" +
                "-Deadline (description) /by (date)\n" +
                "-List\n" +
                "-Delete (item number)\n" +
                "-Done (item number)\n" +
                "-Bye");
    }

    public static void printByeMessage() {
        System.out.println("Goodbye~ Hope to see you again soon! :3");
    }

    public static String receive() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
