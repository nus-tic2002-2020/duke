package duke.task;

import duke.command.DukeException;
import duke.io.Savable;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTasksFromRaw(List<String> rawList, String separator) throws DukeException {
        for (String raw: rawList) {
            String[] arrSplit = raw.split(separator);

            if (arrSplit.length < 3) { // Task must have minimum 3 fields
                break;
            }

            String code = arrSplit[0].trim().toLowerCase();
            TaskType type = TaskType.valueOfCode(code);

            String description = arrSplit[1].trim().toLowerCase();

            Task newTask = null;
            switch (type) {
                case DEADLINE:
                    newTask = new Deadline();
                    newTask.fromSavableString(raw);
                    break;
                case EVENT:
                    newTask = new Event();
                    newTask.fromSavableString(raw);
                    break;
                case TODO:
                    newTask = new Todo();
                    newTask.fromSavableString(raw);
                    break;
                case DEFAULT:
                    newTask = new Task();
                    newTask.fromSavableString(raw);
            }
            this.add(newTask);
        }
    }

    public List<Task> delete(String[] args) throws DukeException {
        //System.out.println("    ____________________________________________________________");
        //System.out.println("    Song la! Lim peh help you remove this task(s):");

        ArrayList<Task> tasksToRemove = new ArrayList<Task>();
        ArrayList<Integer> errorIndices = new ArrayList<Integer>();

        // Create a collection of index to delete
        for (int i = 1; i < args.length; i++) { // Skip first: "delete" command

            try {
                int intTask = Integer.parseInt(args[i]) - 1;
                if (tasks.size() > intTask) { // Has task at list index
                    Task t = tasks.get(intTask);
                    tasksToRemove.add(t);
                } else {
                    errorIndices.add(intTask); // add index to error collection
                }
            } catch (NumberFormatException ex) {
                // Do nothing, skip number
            }

        }

        // Print deleted task
        //for (Task t: tasksToRemove) {
        //    System.out.printf("       %s\n", t.toString());
        //}

        // Delete all tasks in collection
        tasks.removeAll(tasksToRemove);

        //System.out.printf("    Now you have %d tasks in the list.\n", tasks.size());

        if (errorIndices.size() > 0) { // raise exception for wrong index
            throw new DukeException(String.format("Err... cannot find these task(s) leh - %s", errorIndices.toString()),
                    DukeException.DukeError.TASK_NOT_FOUND);
        }

        //System.out.println("    ____________________________________________________________");
        return tasksToRemove;
    }

    public List<Task> done(String[] args) {
        //System.out.println("    ____________________________________________________________");
        //System.out.println("    Power la! I've marked this task(s) as done:");
        ArrayList<Task> listDoneTasks = new ArrayList<Task>();

        for (int i = 1; i < args.length; i++) { // Skip first: command

            try {

                int intTask = Integer.parseInt(args[i])-1;

                if (tasks.size() > intTask) { // Has task at list index
                    Task t = tasks.get(intTask);

                    t.markAsDone();

                    // Print done task
                    //System.out.printf("       %s\n", t.toString());
                    listDoneTasks.add(t);
                }

            } catch (NumberFormatException ex) {
                // Do nothing, skip number
            }

        }
        //System.out.println("    ____________________________________________________________");
        return listDoneTasks;
    }

    public void add(Task t) throws DukeException {
        //boolean ok = true;
        //nt intCount = tasks.size();
        //System.out.println("    ____________________________________________________________");
        //System.out.println("    Got it. I've added this task:");
        //if (intCount < MAX_TASKS) {
        //tasks[countTasks++] = t;
        tasks.add(t);
        //intCount = tasks.size();
        //countTasks++;
        //System.out.printf("      %s\n", t);
        //} else {
        //    ok = false;
        //}
        //System.out.printf("    Now you have %d tasks in the list.\n", intCount);
        //System.out.println("    ____________________________________________________________");

        //if (!ok) {
        //    throw new DukeException("Peiseh, my task list is full!", DukeException.DukeError.MEMORY_FULL);
        //}
    }
    public List<String> getPrintableTasks() {
        //System.out.println("    ____________________________________________________________");
        ArrayList<String> listString = new ArrayList<String>();
        //System.out.println("    Here are the tasks in your list:");
        int intCount = tasks.size();
        for(int i = 0; i < intCount; i++) {
            Task t = tasks.get(i);
            //System.out.printf("     %d.%s\n", i+1, t);
            listString.add(String.format("%d.%s", i + 1, t));
        }
        //System.out.println("    ____________________________________________________________");
        return listString;
    }

}
