package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import task.Task;

/**
 * Storage class for handling interactions with task_list.txt file
 */
public class Storage {

    /**
     * Constructor. Checks whether data folder exists, creates it with task_list.txt if it does not
     *
     * @param filePath filePath to task_list.txt
     * @throws IOException Error for input-output
     */
    public Storage(String filePath) throws IOException {
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        }
    }

    /**
     * Access task_list.txt to facilitate loading of tasks to ArrayList
     *
     * @return Returns the tasks as an ArrayList of strings
     * @throws IOException Error for input-output
     */
    public static ArrayList<String> load() throws IOException {
        ArrayList tasks = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("data/task_list.txt"));
        while (scanner.hasNextLine()) {
            tasks.add(scanner.nextLine());
        }
        return tasks;
    }

    /**
     * Access task_list.txt to facilitate deleting of selected task
     *
     * @param input User-input
     * @throws IOException Error for input-output
     */
    public void delete(String input) throws IOException {
        int index = Integer.parseInt(input.substring(7));
        int counter = 1;
        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));
        while (scanner.hasNextLine()) {
            if (counter == index ) {
                scanner.nextLine();
            }
            else {
                fw.write(scanner.nextLine() + "\n");
            }
            counter++;
        }
        fw.close();
        scanner.close();
        Files.delete(Paths.get("data/temp.txt"));
    }

    public void setDone(String input) throws IOException {
        int index = Integer.parseInt(input.substring(5));
        int counter = 1;
        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));
        while (scanner.hasNextLine()) {
            if (counter == index ) {
                fw.write(scanner.nextLine().replaceAll("✗", "✓") + "\n");
            }
            else {
                fw.write(scanner.nextLine() + "\n");
            }
            counter++;
        }
        fw.close();
        scanner.close();
        Files.delete(Paths.get("data/temp.txt"));
    }

    public void setPriority(String input) throws IOException {
        int index = Integer.parseInt(input.substring(9, 10));
        int counter = 1;
        int counter2 = 0;
        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));
        while (scanner.hasNextLine()) {
            if (counter == index ) {
                String editLine[] = scanner.nextLine().split("\\|");
                editLine[editLine.length - 1]  = " " + input.substring(11).toUpperCase();
                for (String part : editLine) {
                    fw.write(part);
                    counter2++;
                    if (counter2 != editLine.length) {
                        fw.write("|");
                    }
                }
                fw.write("\n");
            }
            else {
                fw.write(scanner.nextLine() + "\n");
            }
            counter++;
        }
        fw.close();
        scanner.close();
        Files.delete(Paths.get("data/temp.txt"));
    }

    /**
     * Access task_list.txt to facilitate insertion of task
     *
     * @param task Task-object to be inserted
     * @throws IOException Error for input-output
     */
    public void insert(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/task_list.txt", true);
        String function = task.getSymbol();
        switch (function) {
            case "[T]":
                fw.write("T | ✗ | " + task.getDescription() + " | " + task.getPriority() + "\n");
                break;
            case "[D]":
                fw.write("D | ✗ | " + task.getDescription() + " | " + task.getDateAndTime().getDayOfMonth()
                        + "/" + task.getDateAndTime().getMonthValue() + "/" + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute()
                        + " | " + task.getPriority() + "\n");
                break;
            case "[E]":
                fw.write("E | ✗ | " + task.getDescription() + " | " + task.getDateAndTime().getDayOfMonth()
                        + "/" + task.getDateAndTime().getMonthValue() + "/" + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute()
                        + " | " + task.getPriority() + "\n");
                break;
            case "[W]":
                fw.write("W | ✗ | " + task.getDescription() + " | " + task.getStart().getDayOfMonth()
                        + "/" + task.getStart().getMonthValue() + "/" + task.getStart().getYear() + ", "
                        + task.getStart().getHour() + ":" + task.getStart().getMinute() + " - " + task.getEnd().getDayOfMonth()
                        + "/" + task.getEnd().getMonthValue() + "/" + task.getEnd().getYear() + ", "
                        + task.getEnd().getHour() + ":" + task.getEnd().getMinute()
                        + " | " + task.getPriority() + "\n");
                break;
        }
        fw.close();
    }
}
