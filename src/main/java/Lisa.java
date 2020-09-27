import exceptions.*;
import classes.*;
import enumerations.*;
import exceptions.Exception;

import java.util.Scanner;
import java.util.ArrayList;

public class Lisa {
    public static int getIndex(String input)  {
        String[] temp = input.split(" ");
        return (Integer.parseInt(temp[1]) - 1);
    }

    public static void list(ArrayList<Task> store) {
        if (store.size() == 0) {
            System.out.println("There are no tasks in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task i : store) {
            String Symbol = i.getSymbol();
            switch (Symbol) {
                case "[T]": {
                    System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription());
                    index++;
                    break;
                }
                case "[D]": {
                    System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (by: " + i.getDateAndTime() +")");
                    index++;
                    break;
                }
                case "[E]": {
                    System.out.println(index + ". " + i.getSymbol() + i.getDone() + " " + i.getDescription() + " (at: " + i.getDateAndTime() +")");
                    index++;
                    break;
                }
            }
        }
    }

    public static boolean catcher(String input) {
        input = input.toLowerCase();
        String[] split = input.split(" ");
        return input.trim().length() == split[0].length();
    }

    public static void add(ArrayList<Task> store, String input) throws Exception {
        int function = 0;
        if (input.toLowerCase().startsWith("todo")) { function = 1; }
        if (input.toLowerCase().startsWith("deadline")) { function = 2; }
        if (input.toLowerCase().startsWith("event")) { function = 3; }
        switch (function) {
            case 1: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                input = input.substring(4).trim();
                store.add(new ToDo(input));
                store.get(store.size()-1).printAdd(store.size());
                break;
            }
            case 2: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                String[] split = input.split("/by");
                if (catcher(split[1])) { throw new NoDeadlineException(); }
                split[0] = split[0].substring(8).trim();
                split[1] = split[1].trim();
                store.add(new Deadline(split[0], split[1]));
                store.get(store.size()-1).printAdd(store.size());
                break;
            }
            case 3: {
                if (catcher(input)) { throw new NoDescriptionException(); }
                String[] split = input.split("/at");
                if (catcher(split[1])) { throw new NoTimeframeException(); }
                split[0] = split[0].substring(5).trim();
                split[1] = split[1].trim();
                store.add(new Event(split[0], split[1]));
                store.get(store.size()-1).printAdd(store.size());
                break;
            }
        }
    }

    public static void runProgramme(String input, ArrayList<Task> store) throws Exception {
        Scanner in = new Scanner(System.in);
        while (!input.toLowerCase().equals("bye")) {
            input = in.nextLine();
            String[] temp = input.split(" ", 2);
            try {
                CommandEnum cmd = CommandEnum.valueOf(temp[0].toUpperCase());
                switch (cmd) {
                    case TODO:
                    case DEADLINE:
                    case EVENT: {
                        try {
                            add(store, input);
                        } catch (NoDescriptionException e) {
                            System.out.println("Error~ The description can't be left blank!");
                        } catch (NoDeadlineException e) {
                            System.out.println("Error~ The deadline can't be left blank!");
                        } catch (NoTimeframeException e) {
                            System.out.println("Error~ The time frame can't be left blank!");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error~ Check identifier \"/by\" or \"/at\" is not misspelled");
                        }
                        break;
                    }
                    case LIST: {
                        list(store);
                        break;
                    }
                    case DELETE: {
                        try {
                            store.get(getIndex(input)).printDelete(store.size()-1);
                            store.remove(getIndex(input));
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            System.out.println("Error~ Invalid function number.");
                        }
                        break;
                    }
                    case DONE: {
                        try {
                            store.get(getIndex(input)).setDone();
                        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                            System.out.println("Error~ Invalid function number.");
                        }
                        break;
                    }
                    case BYE: {
                        System.out.println("Goodbye~ Hope to see you again soon! :3");
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input :( Please try again and follow the template closely!\n" +
                        "-ToDo (description)\n" +
                        "-Deadline (description) /by (date)\n" +
                        "-List\n" +
                        "-Delete (item number)\n" +
                        "-Done (item number)\n" +
                        "-Bye");
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
        ArrayList<Task> store = new ArrayList<>();
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");

        runProgramme(input, store);
    }
}
