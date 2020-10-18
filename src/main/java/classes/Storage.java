package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
            if (scanner.hasNextLine()) {
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
            case "[T]": {
                fw.write("T | X | " + task.getDescription() + "\n");
                break;
            }
            case "[D]": {
                fw.write("D | X | " + task.getDescription() + " | " + task.getDateAndTime().getDayOfMonth()
                        + "/" + task.getDateAndTime().getMonthValue() + "/" + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute() + "\n");
                break;
            }
            case "[E]": {
                fw.write("E | X | " + task.getDescription() + " | " + task.getDateAndTime().getDayOfMonth()
                        + "/" + task.getDateAndTime().getMonthValue() + "/" + task.getDateAndTime().getYear() + ", "
                        + task.getDateAndTime().getHour() + ":" + task.getDateAndTime().getMinute() + "\n");
                break;
            }
        }
        fw.close();
    }
}
