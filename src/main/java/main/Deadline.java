package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * class created to store deadline tasks
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String taskName, boolean taskDone, String by) {
        super(taskName, taskDone); // calls the parent constructor
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.by = LocalDate.parse(by, formatter);
        assert this.by.compareTo(LocalDate.now()) > 0 : "Deadline must not be before today's date.";
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /***
     * write deadline task into storage
     * @param storage
     * @throws IOException
     */
    public void write(FileWriter storage) throws IOException {
        storage.write("D\n"); //to represent as todo
        super.write(storage);
        storage.write(by + "\n");
    }

    /***
     * to read deadline task from storage file
     * @param fileRead
     * @throws IOException
     */
    public void read(BufferedReader fileRead) throws IOException {
        super.read(fileRead);
        String date = fileRead.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.by = LocalDate.parse(date, formatter);
    }

}