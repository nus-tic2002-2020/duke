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

    //method to get taskname
    public String getTaskName() {

        return taskName;
    }

    //method to set taskname
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    //method to get taskdone
    public boolean getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    //return input as a converted string output
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + taskName;
        return s;
    }

    //method to get status icon
    public String getStatusIcon() {
        return (taskDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    // set task done to true
    public void markAsDone() {
        taskDone = true;
    }

    //write tasks into storage
    @Override
    public void write(FileWriter storage) throws IOException {
        storage.write(taskName + "\n");
        if (taskDone) {
            storage.write("true\n");
        } else {
            storage.write("false\n");
        }

    }

    //read tasks from storage
    @Override
    public void read(BufferedReader fileRead) throws IOException {
        taskName = fileRead.readLine();
        String done = fileRead.readLine();
        taskDone = done.equals("true");
    }
}