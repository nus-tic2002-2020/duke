package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList implements Serializable{
    //new arraylist
    ArrayList<Task> task = new ArrayList<Task>();
    int count = 0;
    public int getCount() {
        return count;
    }
    public Task get(int index)
    {
        return task.get(index);
    }
    public void add(Task t)
    {
        task.add(t);
    }
    public void newTodoTask(String x, boolean done) {
        ToDo t = new ToDo(x, done);
        task.add(t);
        count++;
    }
    public void newDeadlineTask(String name, boolean done, String by) {
        Deadline d = new Deadline(name, false, by);
        task.add(d);
        count++;
    }
    public void newEventTask(String name, boolean done, String at)
    {
        Event e = new Event(name, false, at);
        task.add(e);
        count ++;
    }
    public Task removeTask(int index) {
        count --;
        return task.remove(index-1);
    }
    @Override
    public void write(FileWriter storage) throws IOException {
        for(int i=0; i<task.size(); i++)
        {
            task.get(i).write(storage); //write entire task list to storage
        }
    }

    @Override
    public void read(BufferedReader fileRead) throws IOException {
        String type = null;
        task.clear();
        while ((type = fileRead.readLine()) != null) {
            if (type.equals("T")) {
                ToDo t = new ToDo();
                t.read(fileRead);
                task.add(t);
                count++;
            } else if (type.equals("D")) {
                Deadline d = new Deadline();
                d.read(fileRead);
                task.add(d);
                count++;
            } else if (type.equals("E")) {
                Event e;
                e = new Event();
                e.read(fileRead);
                task.add(e);
                count++;
            }
        }
    }
}