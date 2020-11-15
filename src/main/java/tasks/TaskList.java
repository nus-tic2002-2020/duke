package tasks;
import ui.Ui;
import tasks.Task;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The class contains task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui;
    private int snoozedIndex;

    /**
     * Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor.
     *
     * @param tasks List of tasks that were previously saved in text file.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Return number of tasks in list.
     *
     * @return Number of tasks in list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return a specific task.
     *
     * @param taskIndex Index of the task.
     * @return That task's index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Remove a task from task list.
     *
     * @param taskIndex Index of task to be removed from list.
     */
    public void deletedTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Add a task to the list.
     *
     * @param task Task to be added in the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Return index of the postponed task.
     *
     * @param snoozedIndex Index of postponed task.
     */
    public void snoozedTask(int snoozedIndex) {
        this.snoozedIndex = snoozedIndex;
    }

    /**
     * Set a new date (7 days later) for postponed task.
     *
     * @param overdueTask Overdue task in task list.
     */
    public void snoozePostpone(Task overdueTask) {
        if (overdueTask instanceof Deadline) {
            ((Deadline)overdueTask).getDate();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        String oldDateFormatted = ((Deadline)overdueTask).getDate();
        LocalDate oldLocalDate = LocalDate.parse(oldDateFormatted, formatter);
        LocalDate newLocalDate = oldLocalDate.plusDays(7);
        ((Deadline)overdueTask).setDate(newLocalDate);

        ui.snooze(overdueTask);

    }


    /**
     * Search for the task that user wants to find in the list.
     *
     * @param keyword The task that user is looking for.
     */
    public void find (String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }
        ui.printFoundKeyword(results);
    }


}
