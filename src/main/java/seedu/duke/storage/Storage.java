package seedu.duke.storage;

import seedu.duke.base.Deadline;
import seedu.duke.base.Event;
import seedu.duke.base.Task;
import seedu.duke.base.TaskList;
import seedu.duke.base.Todo;
import seedu.duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
    private String filePath;
    private File file;
    private Scanner scan;

    /**
     * Creates a storage and initialise with the file path, catch FileNotFoundException and create new file and directory.
     *
     * @param filePath The file path to store the task list.
     */
    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            createFileAndDirectory();
        }
    }

    /**
     * Creates new file and directory, catch IOException if error in creating the file.
     */
    public void createFileAndDirectory() {
        try {
            file.createNewFile();
            throw new IOException();
        } catch (IOException e) {
            System.out.println("\t☹ OOPS!!! Error creating file.");
        }
    }

    /**
     * Saves the tasks in the task list in the specified format.
     *
     * @throws DukeException To handle error and exception, if error in saving the file.
     */
    public void save() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < TaskList.length(); ++i) {
                String taskType = "";
                String date = "";
                int isDone = 0;
                Task task = TaskList.getATask(i);

                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Event) {
                    taskType = "E";
                    date = dateToString(((Event) task).at); //casting
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    date = dateToString(((Deadline) task).by);
                }

                if (task.isDone) {
                    isDone = 1;
                } else {
                    isDone = 0;
                }

                if (date.equals("")) {
                    fw.write(taskType + " | " + Integer.toString(isDone) + " | " + TaskList.getATask(i).description + System.lineSeparator());
                } else {
                    fw.write(taskType + " | " + Integer.toString(isDone) + " | " + TaskList.getATask(i).description + " | " + date + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("\t☹ OOPS!!! Error in saving the file.");
        }
    }

    /**
     * Loads the task list from the file and initialise it.
     *
     * @return ArrayList<Task>     The task list from the file.
     * @throws DukeException To handle error and exception, if the task list is empty.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists() || !file.isFile()) {
            return taskList;
        }
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] splitLine = line.split(" \\| ");
            switch (splitLine[0]) {
            case "T":
                Todo newTodo = new Todo(splitLine[2]);
                if (splitLine[1].equals("1")) {
                    newTodo.setDone();
                }
                taskList.add(newTodo);
            case "E":
                Event newEvent = new Event(splitLine[2], stringToDate(splitLine[3]));
                if (splitLine[1].equals("1")) {
                    newEvent.setDone();
                }
                taskList.add(newEvent);
                break;
            case "D":
                Deadline newDeadline = new Deadline(splitLine[2], stringToDate(splitLine[3]));
                if (splitLine[1].equals("1")) {
                    newDeadline.setDone();
                }
                taskList.add(newDeadline);
                break;
            }
        }
        return taskList;
    }

    /**
     * Converts the date and time stated by user into the LocalDateTime format: (d/MM/yyyy HHmm).
     *
     * @param date The date stated by user.
     * @return LocalDateTime       The date and time in a LocalDateTime format.
     * @throws DukeException To handle error and exception, if the date loaded from file is not the format (dd/mm/yyyy HHmm).
     */
    public LocalDateTime stringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date loaded from the file is invalid.");
        }
    }

    /**
     * Converts the LocalDateTime object to a string object with the format (d/MM/yyyy HHmm).
     *
     * @param dateTime The date and time as a LocalDateTime object.
     * @return String              The date and time as a string object.
     */
    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}