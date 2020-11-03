package main;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task implements Serializable {
    protected String taskName;
    protected boolean taskDone;
    public Task (String taskName, boolean taskDone)
    {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public Task() {

    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
    public boolean getTaskDone()
    {
        return taskDone;
    }
    public void setTaskDone(boolean taskDone)
    {
        this.taskDone = taskDone;
    }
    public String toString()
    {
        return "[" + getStatusIcon() + "] " + taskName;}
    public String getStatusIcon()
    {
        return (taskDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markAsDone()
    {
        taskDone = true;
    }
    @Override
    public void write(FileWriter storage) throws IOException {
        storage.write(taskName + "\n");
        if(taskDone)
        {
            storage.write("true\n");
        }
        else
        {
            storage.write("false\n");
        }

    }

    @Override
    public void read(BufferedReader fileRead) throws IOException
    {
        taskName = fileRead.readLine();
        String done = fileRead.readLine();
        if(done.equals("true"))
        {
            taskDone = true;
        }
        else
        {
            taskDone = false;
        }
    }
}