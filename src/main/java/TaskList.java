package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    /**variables of TaskList */
    public ArrayList<Task> List;
    public static int count = 0;

    /**constructor of TaskList*/
    public TaskList() {
        this.List = new ArrayList<Task>();
    }

    /**getter of count in TaskList*/
    public int getCount() {
        return this.count;
    }
    /**
     * Prints out items in List*/
    public void printList() {
        if (List.size() == 0) {
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int a = 0; a < List.size(); a++) {
                Task t = List.get(a);
                System.out.println(a + 1 + ". " + t);
            }
        }
    }
    /**
     * Writes items in List in certain format
     * @return the format specified
     */
    public String writetoList(int index){
        String status = List.get(index).getStatus()? "1" : "0";
        String linebyline = List.get(index).getD() + " | " + status + " | " + List.get(index).getDescription() + " | " + List.get(index).getTime();
        return linebyline;
    }

    /**
     * Checks on which method to call upon checking the first keyword of input
     */
    public void AddTask(String input) {
        String[] num = input.split(" ",2);
            String command = num[0];
            String[] split_t;
            System.out.println("Got it. I've added this task: : ");
            switch (command) {
                case "done":
                    Taskdone(input);
                    break;
                case "todo":
                    List.add(new ToDo(num[1]));
                    break;
                case "deadline":
                    split_t = num[1].split("/", 2);
                    List.add(new Deadline(split_t[0].trim(), split_t[1].trim()));
                    break;
                case "event":
                    split_t = num[1].split("/", 2);
                    List.add(new Event(split_t[0].trim(), split_t[1].trim()));
                    break;
                case "delete":
                    deleteTask(num);
                    break;
                case "bye":
                    System.out.print("Bye. Hope to see you again soon!");
                    break;
            }
            count++;
        int a = List.size();
        System.out.println(List.get(count-1));
        System.out.println("Now you have " + List.size() + " tasks in the list.");
    }

    /**
     * Tasks are marked as done
     */
    public void Taskdone(String input) {
        System.out.println("Nice! I've marked this task as done: ");
        String[] num = input.split(" ");
        for (int i = 1; i < num.length; i++) { //skip 0 as text, looking for the number
            int TaskNo = Integer.parseInt(num[i]) - 1; //change string to No
            if (List.size() > TaskNo) { //to check that input is in list
                Task t = List.get(TaskNo);
                t.markAsDone();
                System.out.println(t);
            }
        }
    }

    /**
     * Tasks are deleted from the List
     */
    public void deleteTask(String[] num) {
        System.out.println("Noted. I've removed this task: ");
        for (int i = 1; i < num.length; i++) { //skip 0 as text, looking for the number
            int deleteTaskNo = Integer.parseInt(num[i]) - 1; //change string to No
            if (List.size() > deleteTaskNo) { //to check that input is in list
                Task t = List.get(deleteTaskNo); //get the item to be deleted
                System.out.println(t); //print out task to be deleted
                List.remove(deleteTaskNo);
            }
        }
    }
}

