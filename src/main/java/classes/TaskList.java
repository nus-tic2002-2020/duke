package classes;

import exceptions.*;
import exceptions.Exception;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> store;

    public TaskList(ArrayList<String> tasks) {
        this.store = new ArrayList<>();
        for (String i : tasks) {
            String[] temp = i.split("\\|");
            String function = temp[0].trim();
            switch(function) {
                case "T": {
                    store.add(new ToDo(temp[2].trim()));
                    break;
                }
                case "D": {
                    store.add(new Deadline(temp[2].trim(), temp[3].trim()));
                    break;
                }
                case "E": {
                    store.add(new Event(temp[2].trim(), temp[3].trim()));
                    break;
                }
            }
        }
    }

    public static ArrayList<Task> getStore() {
        return store;
    }

    public static boolean catcher(String input) { // exception helper method
        input = input.toLowerCase();
        String[] split = input.split(" ");
        return input.trim().length() == split[0].length();
    }

    public static void done(String input) {
        String[] temp = input.split(" ");
        int index = Integer.parseInt(temp[1]) - 1;
        store.get(index).setDone();
    }

    public static void delete(String input) throws Exception {
        String[] temp = input.split(" ");
        if (input.contains("  ")) {
            throw new TooManySpacesException();
        }
        store.remove(Integer.parseInt(temp[1]) - 1);
    }

    public static void add(String input) throws Exception {
        int function = 0;
        String[] s = input.split(" ");
        if (input.toLowerCase().startsWith("t")) { function = 1; }
        if (input.toLowerCase().startsWith("d")) { function = 2; }
        if (input.toLowerCase().startsWith("e")) { function = 3; }
        switch (function) {
            case 1: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                store.add(new ToDo(input.substring(4).trim()));
                break;
            }
            case 2: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] split = input.split("/by");
                if (catcher(split[1])) {
                    throw new NoDeadlineException();
                }
                store.add(new Deadline(split[0].trim(), split[1].trim()));
                break;
            }
            case 3: {
                if (catcher(input)) {
                    throw new NoDescriptionException();
                }
                if (input.contains("  ")) {
                    throw new TooManySpacesException();
                }
                String[] split = input.split("/at");
                if (catcher(split[1])) {
                    throw new NoTimeframeException();
                }
                store.add(new Event(split[0].trim(), split[1].trim()));
                break;
            }
        }
    }
}
