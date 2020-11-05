package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/***
 * created class for task actions
 */
public class TaskList implements Serializable {
    //new arraylist
    ArrayList<Task> task = new ArrayList<Task>();
    int count = 0;

    public int getCount() {
        return count;
    }

    public Task get(int index) {
        return task.get(index);
    }

    public void add(Task t) {
        task.add(t);
    }

    /***
     * create new task
     * @param x
     * @param done
     */
    public void newTodoTask(String x, boolean done) {
        ToDo t = new ToDo(x, done);
        task.add(t);
        count++;
    }

    /***
     * create new deadline task
     * @param name
     * @param done
     * @param by
     */
    public void newDeadlineTask(String name, boolean done, String by) {
        Deadline d = new Deadline(name, false, by);
        task.add(d);
        count++;
    }

    /***
     * create new event task
     * @param name
     * @param done
     * @param at
     */
    public void newEventTask(String name, boolean done, String at) {
        Event e = new Event(name, false, at);
        task.add(e);
        count++;
    }

    /***
     * new event task
     * @param name
     * @param done
     * @param date
     */
    public void newEventTask(String name, boolean done, LocalDate date)
    {
        Event e = new Event(name, false, date);
        task.add(e);
        count ++;
    }

    /***
     * remove task from task list
     * @param index
     * @return
     */
    public Task removeTask(int index) {
        count--;
        return task.remove(index - 1);
    }

    /***
     * add task list into storage
     * @param storage
     * @throws IOException
     */
    @Override
    public void write(FileWriter storage) throws IOException {
        for (int i = 0; i < task.size(); i++) {
            task.get(i).write(storage); //write entire task list to storage
        }
    }

    /***
     * read task list
     * @param fileRead
     * @throws IOException
     */
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