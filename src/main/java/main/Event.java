package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/***
 * class created to store event tasks
 */
public class Event extends Task{
    protected LocalDateTime at;

    /***
     * constructor to create event task
     * @param taskName name of the task
     * @param taskDone whether the task is done
     * @param at date and time of event
     */
    public Event (String taskName, boolean taskDone, String at) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma");
        this.at = LocalDateTime.parse(at, formatter);
        //to ensure that the event date is in the future
        assert this.at.compareTo(LocalDateTime.now()) > 0 : "Cannot put event in the past";

    }

    /***
     * constructor to create event task with date in LocalDateTime format being parsed in
     * @param taskName name of the task
     * @param taskDone whether the task is done
     * @param date date and time of event task in LocalDateTime format
     */
        public Event (String taskName, boolean taskDone, LocalDateTime date) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"); //changing to format
        return "[E]" + super.toString() + " (at: "+ at.format(formatter) + ")";
    }
    /***
     * write event task into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("E\n"); //to represent as todo
        super.write(storage);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma"); //to format date to save file
        storage.write(at.format(formatter) + "\n");
    }
    /***
     * to read event task from storage file
     * @param fileRead
     * @throws IOException
     */
    public void read(BufferedReader fileRead) throws IOException {
        super.read(fileRead);
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma");
        this.at = LocalDateTime.parse(date, formatter);
    }
}