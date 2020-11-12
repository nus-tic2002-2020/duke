package tasklist;

import command.command;
import ui.UI;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    public static ArrayList<Task> listTask = new ArrayList<Task>();

    public static ArrayList<Task> printUserInput(ArrayList<Task> listTask) {

        //user input
        String userInput;
        Scanner input = new Scanner(System.in);


        while (true) {
            userInput = input.nextLine();
            // to exit
            if (userInput.equals("bye")) {
                UI.bye();
                System.exit(0);
            }
            // to list task
            else if (userInput.equals("list")) {
                command.list();
            }
            // when task is done
            else if (userInput.startsWith("done")) {
                command.doneTask(userInput);
            }
            // add event task
            else if (userInput.startsWith("event")) {
                command.eventTask(userInput);
            }
            // add deadline task
            else if (userInput.startsWith("deadline")) {
                command.deadlineTask(userInput);
            }
            // add to do task
            else if (userInput.startsWith("todo")) {
                command.todoTask(userInput);
            }
            // delete task
            else if (userInput.startsWith("delete")) {
                command.deleteTask(userInput);
            }
            // find task
            else if (userInput.startsWith("find")) {
                command.findTask(userInput);
            }
            // save task on duke.txt
            else if (userInput.startsWith("save")) {
                command.save();
            }
        }
    }
}
