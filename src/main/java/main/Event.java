package main;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    protected String at;
    public Event (String taskName, boolean taskDone, String at)
    {
        super(taskName, taskDone); // calls the parent constructor
        this.at = at;
    }

    public Event() {
        super();
    }

    public String toString()
    {
        return "[E]" + super.toString() + " (at: "+ at + ")";
    }

    public void write(FileWriter storage) throws IOException {
        storage.write("E\n"); //to represent as todo
        super.write(storage);
        storage.write(at + "\n");
    }

    public void read(BufferedReader fileRead) throws IOException
    {
        super.read(fileRead);
        at = fileRead.readLine();
    }
}