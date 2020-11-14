package main;

import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {

    /***
     * overloaded constructor from task name and task done
     * @param taskName
     * @param taskDone
     */
    public ToDo(String taskName, boolean taskDone) {
        super(taskName, taskDone); // calls the parent constructor
    }

    /***
     * default constructor
     */
    public ToDo() {
        super();
    }

    /***
     * converting todo task input into string format
     * @return
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /***
     * add task list into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("T\n"); //to represent as todo
        super.write(storage);
    }
}