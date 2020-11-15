package duke.task;

import duke.command.DukeException;
import duke.io.Savable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager for managing all tasks for Duke
 */
public class TaskManager {

    private List<Task> tasks;

    /**
     * Default Constructor to initialise task list
     */
    public TaskManager() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Getter for task list
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Setter for task list
     * @param tasks as list
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Setter for task list from list of raw char separated task data
     * @param rawList of char separated task data
     * @param separator character
     * @throws DukeException if raw data format is wrong
     */
    public void setTasksFromRaw(List<String> rawList, String separator) throws DukeException {
        assert rawList != null: "List of raw tasks cannot be null";
        assert separator != null: "Separator for splitting raw tasks cannot be null";

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

    /**
     * Delete method for tasks by task number, supports mass delete operation
     * @param args from delete command containing task numbers
     * @return deleted tasks
     * @throws DukeException if task(s) not found
     */
    public List<Task> delete(String[] args) throws DukeException {

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

        // Delete all tasks in collection
        tasks.removeAll(tasksToRemove);

        if (errorIndices.size() > 0) { // raise exception for wrong index
            throw new DukeException(String.format("Err... cannot find these task(s) leh - %s", errorIndices.toString()),
                    DukeException.DukeError.TASK_NOT_FOUND);
        }

        return tasksToRemove;
    }

    /**
     * Done method for tasks by task number, supports mass done operation
     * @param args from done command containing task numbers
     * @return tasks marked as done
     */
    public List<Task> done(String[] args) {

        ArrayList<Task> listDoneTasks = new ArrayList<Task>();

        for (int i = 1; i < args.length; i++) { // Skip first: command

            try {

                int intTask = Integer.parseInt(args[i])-1;

                if (tasks.size() > intTask) { // Has task at list index
                    Task t = tasks.get(intTask);

                    t.markAsDone();

                    listDoneTasks.add(t);
                }

            } catch (NumberFormatException ex) {
                // Do nothing, skip number
            }

        }

        return listDoneTasks;
    }

    /**
     * Adds a task to task list
     * @param task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Find tasks by matching keyword string. Case insensitive.
     * @param keyword string
     * @return list of tasks found
     */
    public List<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task t: this.tasks) {
            if (t.getDescription().toUpperCase().contains(keyword.toUpperCase())) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /**
     * Find tasks by {@link LocalDate}. Tasks must implement {@link LocalDate} interface.
     * @param date
     * @return list of Task as {@link LocalDate}
     */
    public List<DatedTask> findTasks(LocalDate date) {
        ArrayList<DatedTask> foundTasks = new ArrayList<DatedTask>();
        for (Task t: this.tasks) {

            LocalDate taskDate;

            switch (t.getType()) {
            case DEADLINE:
                Deadline deadline = (Deadline) t;
                taskDate = deadline.getBy().toLocalDate();
                break;
            case EVENT:
                Event event = (Event) t;
                taskDate = event.getAt().toLocalDate();
                break;
            default:
                continue;
            }

            if (date.isEqual(taskDate)) {
                foundTasks.add((DatedTask) t);
            }
        }
        return foundTasks;
    }

    /**
     * Get list of tasks as string for display
     * @return list of tasks as friendly string
     */
    public List<String> getPrintableTasks() {

        ArrayList<String> listString = new ArrayList<String>();

        int intCount = tasks.size();
        for(int i = 0; i < intCount; i++) {
            Task t = tasks.get(i);
            listString.add(String.format("%d.%s", i + 1, t));
        }

        return listString;
    }

}
