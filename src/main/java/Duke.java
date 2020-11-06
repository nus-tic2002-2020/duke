import ui.UI;
import tasklist.Task;
import tasklist.Todo;
import tasklist.Events;
import tasklist.Deadline;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> listTask = new ArrayList<Task>();

        UI.showWelcome();

        while (true) {
            listTask = printUserInput(listTask);

        }
    }

    public static ArrayList<Task> printUserInput(ArrayList<Task> listItems) {
        //user input
        String userInput;
        Scanner input = new Scanner(System.in);
        int pointer = 0;

        while (true) {
            userInput = input.nextLine();
            //bye to exit
            if (userInput.equals("bye")) {
                UI.bye();
                System.exit(0);
            }
            //when type list
            else if (userInput.equals("list")) {
                printUserList(listItems);
            }
            // when type done
            else if (userInput.startsWith("done")) {
                doneMessage(listItems, userInput);
            }
            // add event task
            else if (userInput.startsWith("event")) {
                int divPosition = userInput.indexOf("/");
                String eventDescription = userInput.substring(6, divPosition - 1);
                String dateTime = userInput.substring(divPosition + 4);
                Task t = new Events(eventDescription, dateTime);
                listItems.add(t);

                UI.printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems.get(listItems.size() - 1).toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                UI.printLine();

            }
            // add deadline task
            else if (userInput.startsWith("deadline")) {
                int divPosition = userInput.indexOf("/");
                String deadlineDescription = userInput.substring(9, divPosition - 1);
                String filteredDate = userInput.substring(divPosition + 4);
                Task t = new Deadline(deadlineDescription, filteredDate);
                listItems.add(t);

                UI.printLine();
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t" + listItems.get(listItems.size() - 1).toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                UI.printLine();
            }
            // add to do task
            else if (userInput.startsWith("todo")) {
                try {
                    String todoDescription = userInput.substring(5);
                    CheckEmpty(todoDescription);
                    Task t = new Todo(todoDescription);
                    listItems.add(t);

                    UI.printLine();
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t" + listItems.get(listItems.size() - 1).toString());
                    System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                    UI.printLine();
                } catch (StringIndexOutOfBoundsException e) {
                    UI.printEmptyToDoException();
                }
            } else if (userInput.startsWith("delete")) {
                int numTask = Integer.valueOf(userInput.substring(7, userInput.length()));
                Task t = listItems.get(numTask - 1);
                listItems.remove(numTask - 1);
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t" + t.toString());
                System.out.println("\tNow you have " + (listItems.size()) + " tasks in the list.");
                UI.printLine();
            }  else if (userInput.startsWith("save")) {
                save(listItems);
            }
        }
    }

    static void save(ArrayList<Task> listItems){
        String list = "";
        for (int i = 0; i < listItems.size(); i++) {
            list += listItems.get(i).saveFormat() + "\n";
        }
        Storage.writeToFile(list);
    }

    //print items in list
    public static void printUserList(ArrayList<Task> listItems) {
        if (listItems.size() == 0) {
            UI.printListEmpty();
            return;
        }
        UI.printLine();

        for (int i = 0; i < listItems.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + listItems.get(i).toString());
        }

        UI.printLine();
    }


    public static ArrayList<Task> doneMessage(ArrayList<Task> listItems, String userInput) {

        try {
            int filteredNumber = Integer.parseInt(userInput.substring(5));
            listItems.get(filteredNumber - 1).markDone();

            UI.printLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + listItems.get(filteredNumber - 1).toString());
            UI.printLine();

        } catch (NumberFormatException e) {
            UI.printLine();
            System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            UI.printLine();
        } catch (NullPointerException e) {
            UI.printLine();
            System.out.println("\t☹ OOPS!!! Numbers out of range :-(");
            UI.printLine();
        }
        return listItems;
    }

    private static void CheckEmpty(String description) throws StringIndexOutOfBoundsException {
        if (description.isEmpty()) {
            throw new StringIndexOutOfBoundsException();
        }
    }


}
