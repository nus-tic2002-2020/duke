package Duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    TaskList() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task newTask) {
        taskList.add(newTask);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public String listTasks() {
        String listOutput = "Task(s) in your list:";
        for (Task task : taskList) {
            listOutput += "\r\n"+((taskList.indexOf(task) + 1)) + "." + task.toString();
        }
        System.out.println(listOutput);
        return listOutput;
    }
    public String deleteTask(int option) {
            Task currTask = taskList.get(option);
            String deleteMsg = "Noted. Task removed: \r\n" + currTask.toString() + "\r\nNow you have "
                    + (taskList.size() - 1) + " task(s) in the list";
            System.out.println(deleteMsg);
            taskList.remove(option);
            return deleteMsg;
    }
    public String markTaskAsDone(int option) {
            Task currTask = taskList.get(option);
            currTask.isDone = true;
            String doneMsg = "Task marked as done: \r\n" + currTask.toString();
            System.out.println(doneMsg);
            return doneMsg;

    }
    public String assignPriority(int option, int priority) {
        Task currTask = taskList.get(option);
        currTask.priority = priority;
        String doneMsg = "Priority: " + priority + " assigned to task." + "\r\n" + currTask.toString();
        System.out.println(doneMsg);
        return doneMsg;

    }
}
