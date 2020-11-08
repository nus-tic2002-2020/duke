package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task implements Serializable {
    protected String taskName;
    protected boolean taskDone;

    public Task(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public Task() {

    }

    public Task(String taskName, boolean taskDone, String frequency) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    /***
     * method to get taskname variable
     * @return
     */
    public String getTaskName() {

        return taskName;
    }

    /***
     * method to set task name
     * @param taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /***
     * method to get taskdone variable
     * @return
     */
    public boolean getTaskDone() {
        return taskDone;
    }

    /***
     * method to set task done variable
     * @param taskDone
     */
    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    /***
     * to return input as a converted string output
     * @return
     */
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + taskName;
        return s;
    }

    /***
     * method to get status icon
     * @return task done
     */
    public String getStatusIcon() {
        return (taskDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /***
     * method to set task done to true
     */
    public void markAsDone() {
        taskDone = true;
    }

    /***
     *write tasks into storage
     * @param storage
     * @throws IOException
     */


    @Override
    public void write(FileWriter storage) throws IOException {
        storage.write(taskName + "\n");
        if (taskDone) {
            storage.write("true\n");
        } else {
            storage.write("false\n");
        }

    }

    /***
     * read tasks in storage
     * @param fileRead
     * @throws IOException
     */
    @Override
    public void read(BufferedReader fileRead) throws IOException {
        taskName = fileRead.readLine();
        String done = fileRead.readLine();
        taskDone = done.equals("true");
    }

    /***
     * method to search keyword
     * @param keyword
     * @return
     */
    public boolean contains(String keyword) {
        return taskName.contains(keyword);
    }
}