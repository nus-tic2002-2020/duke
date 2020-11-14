package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/***
 * class created to store deadline tasks
 */
public class Deadline extends Task{
    protected LocalDateTime by;

    /***
     * constructor with by string
     * @param taskName name of the task
     * @param taskDone whether the task is done
     * @param by date and time of deadline task
     */
    public Deadline (String taskName, boolean taskDone, String by) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d hh:mma");
        this.by = LocalDateTime.parse(by, formatter);
        //to ensure that the event date is in the future
        assert this.by.compareTo(LocalDateTime.now()) > 0 : "Cannot put deadline in the past";
    }

    /***
     * constructor using LocalDateTime to parse in date
     * @param taskName name of the task
     * @param taskDone whether the task is done
     * @param date date and time of deadline task in LocalDateTime format
     */
    public Deadline (String taskName, boolean taskDone, LocalDateTime date) {
        super(taskName, taskDone); // calls the parent constructor
        by = date;
    }
    /***
     * default constructor
     */
    public Deadline() {
        super();
    }
    /***
     * to return input as a converted string output
     * @return
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[D]" + super.toString() + " (by: "+ by.format(formatter) + ")";
    }
    /***
     * write deadline task into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("D\n"); //to represent as todo
        super.write(storage);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mma");
        storage.write(by.format(formatter) + "\n");
    }
    /***
     * to read deadline task from storage file
     * @param fileRead
     * @throws IOException
     */
    public void read(BufferedReader fileRead) throws IOException {
        super.read(fileRead);
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mma");
        this.by = LocalDateTime.parse(date, formatter);
    }
}