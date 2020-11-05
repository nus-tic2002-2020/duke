package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//class created to store event tasks
public class Event extends Task {
    protected LocalDate at;

    public Event(String taskName, boolean taskDone, String at) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.at = LocalDate.parse(at, formatter);
    }

    public Event() {
        super();
    }

    //to return input as a converted string output
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    //write event task into storage
    public void write(FileWriter storage) throws IOException {
        storage.write("E\n"); //to represent as todo
        super.write(storage);
        storage.write(at + "\n");
    }

    //read event task into storage
    public void read(BufferedReader fileRead) throws IOException {
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.at = LocalDate.parse(date, formatter);
    }
}