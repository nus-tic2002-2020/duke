package duke.command;

import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * This class allows for a user to undo the last action executed and restore it to a previous state. It allows for multiple undo and also erases it from the history.
 */

public class Undo {
    protected static int action = 0;
    protected static ArrayList<ArrayList<Task>> Queue = new ArrayList<>();

    /**
     * This method adds an entire TaskList into one ArrayList slot to capture both the history changes and allow for undo to previous states.
     */

    public static void addQueue() {
        Queue.add(new ArrayList<>(TaskList.UpdatedList()));
        action++;
    }

    /**
     * This method allows to print the entire history of TaskLists through the user commands that have been made. Also, if there is an undo made, the history gets deleted.
     */

    public static void printQueue() {
        int count = 0;
        for (ArrayList<Task> tasks : Queue) {
            if (count == 0) {
                System.out.println("List at initialization:");
                if (Queue.get(0).size() == 0) { System.out.println("NULL");}
                else {
                    for (Task task : tasks) { System.out.println(task); }
                }
            }
            else {
                System.out.println("List after action " + count + ":");
                if (Queue.get(count).size() == 0 ) { System.out.println("NULL");}
                else {
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                }
            }
            count++;
        }
    }

    /**
     * This method returns the previous TaskList that existed before the latest user action was executed.
     * @return Previous TaskList
     * @throws DukeException Error displayed if trying to undo at base case
     */

    public static ArrayList<Task> undo() throws DukeException {
        if (Queue.size() == 1) { throw new DukeException("Error, undo not applicable as no user-input yet or at baseline initialized information\n");}
        Queue.remove(action - 1);
        action--;
        return Queue.get(action - 1);
    }
}

