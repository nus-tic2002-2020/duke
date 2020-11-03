package main;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task{
    protected String by;
    public Deadline (String taskName, boolean taskDone, String by)
    {
        super(taskName, taskDone); // calls the parent constructor
        this.by = by;
    }

    public Deadline() {
        super();
    }

    public String toString()
    {
        return "[D]" + super.toString() + " (by: "+ by + ")";
    }
    public void write(FileWriter storage) throws IOException {
        storage.write("D\n"); //to represent as todo
        super.write(storage);
        storage.write(by + "\n");
    }

    public void read(BufferedReader fileRead) throws IOException
    {
        super.read(fileRead);
        by = fileRead.readLine();
    }

}