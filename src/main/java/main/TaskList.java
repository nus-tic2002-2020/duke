package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
/***
 * created class for task actions
 */
public class TaskList implements Serializable{
    ArrayList<Task> line = new ArrayList<Task>();
    int count = 0;
    public int getCount() {
        return count;
    }

    public Task get(int index)
    {
        return line.get(index);
    }

        public void add(Task t)
        {
            line.add(t);
            count++;
        }

            /***
             * create new task
             * @param x
             * @param done
             * @param x task name
             * @param done whether task is done
             */
            public void newTodoTask(String x, boolean done) {
            ToDo t = new ToDo(x, done);
            line.add(t);
            count++;
        }

            /***
             * create new deadline task
             * @param name name of the task
             * @param done whether the task is done
             * @param by date and time of deadline task
             */
            public void newDeadlineTask(String name, boolean done, String by) {
            Deadline d = new Deadline(name, false, by);
            line.add(d);
            count++;
        }

            /***
             *
             * @param name name of the task
             * @param done whether the task is done
             * @param date date and time of deadline task in LocalDateTime format
             */
            public void newDeadlineTask(String name, boolean done, LocalDateTime date) {
            Deadline d = new Deadline(name, false, date);
            line.add(d);
            count ++;
        }
            /***
             * create new event task
             * @param name name of the task
             * @param done whether the task is done
             * @param at date and time of event task
             */
            public void newEventTask(String name, boolean done, String at) {
            Event e = new Event(name, false, at);
            line.add(e);
            count ++;
        }

            /***
             * @param name name of the task
             * @param done whether the task is done
             * @param date date and time of event task using LocalDateTime format
             */
            public void newEventTask(String name, boolean done, LocalDateTime date) {
            Event e = new Event(name, false, date);
            line.add(e);
            count ++;
        }
            /***
             * remove task from task list
             * @param index
             * @return
             */
            public Task removeTask(int index) {
            //to ensure that the event date is in the future
            assert index > 0 : "Cannot delete negative index";
            assert index <= count:"Cannot choose a number more than number of tasks";
            count --;
            return line.remove(index-1);

        }

            /***
             * method to find keyword in task list
             * @param keyword keyword to be searched through the whole task list
             * @return all tasks containing the keyword
             */
            public TaskList find(String keyword) {
            System.out.println(keyword);
            TaskList results = new TaskList();
            for(int i= 0; i<line.size(); i++) {
                if(line.get(i).contains(keyword)) {
                    results.add(line.get(i));
                }
            }
            return results;
        }
            /***
             * add task list into storage
             * @param storage
             * @throws IOException
             */
            @Override
            public void write(FileWriter storage) throws IOException {
            for(int i=0; i<line.size(); i++) {
                line.get(i).write(storage); //write entire task list to storage
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
            line.clear();
            while ((type = fileRead.readLine()) != null) {
                if (type.equals("T")) {
                    ToDo t = new ToDo();
                    t.read(fileRead);
                    line.add(t);
                    count++;
                } else if (type.equals("D")) {
                    Deadline d = new Deadline();
                    d.read(fileRead);
                    line.add(d);
                    count++;
                } else if (type.equals("E")) {
                    Event e;
                    e = new Event();
                    e.read(fileRead);
                    line.add(e);
                    count++;
                }
            }
        }
        }