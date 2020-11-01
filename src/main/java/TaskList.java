import java.util.ArrayList;
public class TaskList {

    /**
     * Array for tasks; add/delete task in the list
     */
    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public Task get(int i){
        return taskList.get(i);
    }

    public void removeTask(int i){
        taskList.remove(i);
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     *
     * @return the number of tasks in the list
     */
    public int getNumOfTasks(){
        return taskList.size();
    }
} 