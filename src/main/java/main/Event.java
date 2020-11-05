package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * class created to store event tasks
 */
public class Event extends Task {
    protected LocalDate at;

    /***
     * constructor
     * @param taskName
     * @param taskDone
     * @param at
     */
    public Event(String taskName, boolean taskDone, String at) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.at = LocalDate.parse(at, formatter);
    }

    /***
     * constructor for recurring events
     * @param taskName
     * @param taskDone
     * @param date
     */
    public Event (String taskName, boolean taskDone, LocalDate date)
    {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        at = date;
    }

    /***
     * default constructor
     */
    public Event() {
        super();
    }

    /***
     * to return input as a converted string output
     * @return
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /***
     * write event task into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("E\n"); //to represent as todo
        super.write(storage);
        storage.write(at + "\n");
    }

    /***
     * to read event task from storage file
     * @param fileRead
     * @throws IOException
     */
    public void read(BufferedReader fileRead) throws IOException {
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.at = LocalDate.parse(date, formatter);
    }
}