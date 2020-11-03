package main;

import java.io.FileWriter;
import java.io.IOException;
public class ToDo extends Task {
    public ToDo (String taskName, boolean taskDone)
    {
        super(taskName, taskDone); // calls the parent constructor
    }

    public ToDo() {
        super();
    }

    public String toString()
    {
        return "[T]" + super.toString();
    }
    public void write(FileWriter storage) throws IOException {
        storage.write("T\n"); //to represent as todo
        super.write(storage);
    }
}