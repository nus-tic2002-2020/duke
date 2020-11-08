import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void deletedTask(int taskIndex) {
        tasks.remove(taskIndex);
    }


    public void addTask(Task task) {
        tasks.add(task);
    }


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
