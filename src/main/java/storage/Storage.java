package storage;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class Storage {

    private String filePath;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void updateFile(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter("tasks.txt",false);

        if (tasks.size() != 0) {
            writer.write(tasks.getTask(0).formatForFile());
            for (int i = 1; i < tasks.size(); i++) {
                writer.write(System.lineSeparator() + tasks.getTask(i).formatForFile());
            }
        }

        writer.close();
    }

    /***
     * To load the saved tasks in the text file
     *
     * @return Displays the tasks that are saved previously in the text file
     * @throws FileNotFoundException To show error message when file could not be found
     */
    public ArrayList<Task> loadFile () throws FileNotFoundException {
        File file = new File ("tasks.txt");
        Scanner readFile = new Scanner (file);

        while (readFile.hasNext()) {
            String fileInput = readFile.nextLine();
            String inputs[] = fileInput.split("\\|");
            String taskType = inputs[0];
            String isDone = inputs[1];
            String description = inputs[2];


            switch (taskType){
                case "E":
                    String eventDate = inputs[3];
                    String eventTime = inputs[4];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate localDate = LocalDate.parse(eventDate, formatter);

                    formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime localTime = LocalTime.parse(eventTime, formatter);

                    if (isDone.equals("✘")) {
                        tasks.add(new Event(description, taskType, localDate, localTime, false));
                    } else {
                        tasks.add(new Event(description, taskType, localDate, localTime, true));
                    }

                    break;
                case "D":
                    String deadlineDate = inputs[3];
                    String deadlineTime = inputs[4];

                    DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate deadlineLocalDate = LocalDate.parse(deadlineDate, deadlineFormatter);

                    deadlineFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime deadlineLocalTime = LocalTime.parse(deadlineTime, deadlineFormatter);


                    if (isDone.equals("✘")) {
                        tasks.add(new Deadline(description, taskType, deadlineLocalDate, deadlineLocalTime,false));
                    } else {
                        tasks.add(new Deadline(description, taskType, deadlineLocalDate, deadlineLocalTime, true));
                    }

                    break;
                case "P":
                    String fromDate = inputs[3];
                    String toDate = inputs[4];

                    DateTimeFormatter fromDateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate fromLocalDate = LocalDate.parse(fromDate, fromDateFormatter);

                    DateTimeFormatter toDateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate toLocalDate = LocalDate.parse(toDate, toDateFormatter);


                    if (isDone.equals("✘")) {
                        tasks.add(new DoWithinPeriod(description, taskType, fromLocalDate, toLocalDate,false));
                    } else {
                        tasks.add(new DoWithinPeriod(description, taskType, fromLocalDate, toLocalDate, true));
                    }

                    break;
                case "T":
                    if (isDone.equals("✘")) {
                        tasks.add(new ToDo(description, taskType, false));
                    } else {
                        tasks.add(new ToDo(description, taskType, true));
                    }
                    break;
            }

        }
        return tasks;
    }

}
