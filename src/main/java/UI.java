package duke.ui;

import duke.task.*;
import duke.storage.*;
import duke.command.*;

import java.util.Scanner;
import java.util.ArrayList;


//taking input and giving responses
public class UI {

    /**
     * To keep checking for next line of input and handle exception messages
     * @Param list of tasks in TaskList
     * @Param store is where the filename is at
     */
    public void read (TaskList tasks, Storage store){
        String input;
        Scanner in = new Scanner(System.in);
        int flag = 0;
      //  while (flag == 0) {
            String line = in.nextLine();
            input = line.trim();
            try {
                text(tasks, store, input);

            } catch (DukeException e) { //unknown input
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + line.trim() + " cannot be empty.");
            }
        }


    /**
     * To direct which method the line of input should pass to
     * @Param tasklist of tasks
     * @Param store file location
     * @Param input of each line
     */
    public static int text(TaskList tasks, Storage store, String input) throws DukeException {
        String[] num = input.split(" ");
        if (/*num.length > 0 && */!num[0].isBlank()) {
            String command = num[0];
            switch (command) {
            case "bye":
                    store.save(tasks);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
            case "list":
                    tasks.printList();
                    break;
            case "done":
                    tasks.Taskdone(input);
                    break;
            case "todo":
                    tasks.AddTask(input);
                    break;
            case "deadline":
                    tasks.AddTask(input);
                    break;
            case "event":
                    tasks.AddTask(input);
                    break;
            case "delete":
                    tasks.AddTask(input);
                    break;
            case "find":
                    tasks.FindTask(input);
                    break;
            default:
                    throw new DukeException();
            }
        }
        return 0;
    }

    /**
     * To initalize tasks into List when there is a saved .txt file with content inside
     * @Param tasks is the list of task in the List
     * @Param input is the String on each line
     */
    public void saved_list(TaskList tasks, String input) {
        if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            tasks.AddTask(input);
        } else {
            tasks.Taskdone(input);
        }
    }
}
