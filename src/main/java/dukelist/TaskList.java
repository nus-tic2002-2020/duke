package dukelist;

import duketask.Deadline;
import duketask.Event;
import duketask.Task;
import duketask.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of <code>TaskList</code> class, initialize a new ArrayList task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Return the <code>size</code> of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return the <code>task</code> based on the index in the list.
     *
     * @param index the index of the task to be returned
     * @return the task based on the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a <code>new task</code> to the tasks.
     *
     * @param task the task which is going to be added into the task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete a <code>task</code> to the tasks.
     *
     * @param taskIndex the index of the task in the task list
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Change the <code>task</code> status to done.
     *
     * @param taskIndex the index of the task in the task list
     */
    public void doneTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Find the tasks contains the <code>keyword</code> in the task list.
     *
     * @param key the String of the find keyword
     * @return the task list of the tasks with the keyword
     */
    public TaskList findTask(String key) {
        TaskList results = new TaskList();

        for (Task task : tasks) {
            if (task.find(key)) {
                results.addTask(task);
            }
        }

        return results;
    }

    /**
     * Update tasks based on the <code>update</code> information to the task list.
     *
     * @param update the String of the information to be updated
     * @return the task list of the tasks with the keyword
     */
    public Task updateTask(String update) {
        String[] input = update.split("\\s", 3);
        switch (input[1]) {
            case "/des":
                tasks.get(Integer.parseInt(input[0]) - 1).setDescription(input[2]);
                break;
            case "/by":
            case "/at":
            case "/takes":
                tasks.get(Integer.parseInt(input[0]) - 1).setSchedule(input[2]);
                break;
            case "/reset":
                tasks.get(Integer.parseInt(input[0]) - 1).reset(input[2]);
                break;
        }

        return tasks.get(Integer.parseInt(input[0]) - 1);
    }

    /**
     * Copy the tasks and add to the task list.
     *
     * @param index the sequence number of the Task being copied
     */
    public void copyTask(int index) {
        String copy = tasks.get(index).copy();
        switch (tasks.get(index).getClass().getSimpleName()) {
            case "Todo":
                this.addTask(new Todo(copy));
                break;
            case "Deadline":
                this.addTask(new Deadline(copy));
                break;
            case "Event":
                this.addTask(new Event(copy));
                break;
        }
    }
}
