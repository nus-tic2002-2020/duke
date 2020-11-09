package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import enumerations.SymbolEnum;
import task.Task;

/**
 * This is the storage class for handling interactions with 'task_list.txt' file.
 * Available functions include reading from the file for loading into the ArrayList, deleting a task from the list,
 * changing the status of the task to 'done' (from ✗ -> ✓), editing the priority of the task and insertion of a new
 * task.
 *
 * @author Aloysius Wong
 * @Version 1.0
 * @since 08-11-2020
 */
public class Storage {

    /**
     * This creates the Storage-class object. It checks whether the 'data' folder (and effectively the task_list.txt
     * file) exists. If it does not, it creates both the folder and text file.
     *
     * @param filePath This is the path to the task_list.txt file from the root directory.
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
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
     * This method reads from the text file and loads each line into an ArrayList during initialization. The ArrayList
     * is returned as the list of tasks.
     *
     * @return Task list as an ArrayList of strings.
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public ArrayList<String> load() throws IOException {
        ArrayList tasks = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("data/task_list.txt"));
        while (scanner.hasNextLine()) {
            tasks.add(scanner.nextLine());
        }
        return tasks;
    }

    /**
     * This method deletes a task from the text file. It copies the contents of the text file to a temp file, and
     * re-writes the text file by copying back the temp file, with the exception of the task to be deleted (it skips the
     * line). To do this, it utilizes counters.
     *
     * @param input This is the input from the user in the form of "delete (index)".
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public void delete(String input) throws IOException {
        int index = Integer.parseInt(input.substring(7));
        int counter = 1; // To know when the desired line has been reached.

        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));

        while (scanner.hasNextLine()) {
            if (counter == index ) { // Cursor reached the desired line.
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

    /**
     * This method changes the status of a task to 'done' (from ✗ -> ✓). It copies the contents of the text file to a
     * temp file, and re-writes the text file by copying back the temp file, with the exception of the task to be
     * edited (it replaces the content after copying and before writing). To do this, it utilizes counters.
     *
     * @param input This is the input from the user in the form of "done (index)".
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public void setDone(String input) throws IOException {
        int index = Integer.parseInt(input.substring(5));
        int counter = 1; // To know when the desired line has been reached.

        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));

        while (scanner.hasNextLine()) {
            if (counter == index ) { // Cursor reached the desired line.
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

    /**
     * This method changes the priority level of a task. It copies the contents of the text file to a temp file, and
     * re-writes the text file by copying back the temp file, with the exception of the task to be edited (it replaces
     * the content after copying and before writing). To do this, it utilizes counters.
     *
     * @param input This is the input from the user in the form of "Change_priority (index) (priority-level)".
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public void setPriority(String input) throws IOException {
        int index = Integer.parseInt(input.substring(16, 17));
        int counter = 1; // To know when the desired line has been reached.
        int counter2 = 0; // To know when the end of the line has been reached after writing, so that no '|' will be
                          // written.

        Files.copy(Paths.get("data/task_list.txt"), Paths.get("data/temp.txt"));
        FileWriter fw = new FileWriter("data/task_list.txt");
        Scanner scanner = new Scanner(new File("data/temp.txt"));

        while (scanner.hasNextLine()) {
            if (counter == index ) { // Cursor reached the desired line.
                String[] editLine = scanner.nextLine().split("\\|");
                editLine[editLine.length - 1]  = " " + input.substring(18).toUpperCase();

                for (String part : editLine) {
                    fw.write(part);
                    counter2++;
                    if (counter2 != editLine.length) { // Will write "|" until last word of line has been reached.
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
     * This method adds a task to the text file. It checks what category of task it is, and writes accordingly.
     *
     * @param task This is the task-object to be inserted.
     * @throws IOException This is the error for input-output. Has to be thrown or programme will not run.
     */
    public void insert(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/task_list.txt", true);

        SymbolEnum symbol = task.getSymbol();
        assert symbol == SymbolEnum.T || symbol == SymbolEnum.D || symbol == SymbolEnum.E || symbol == SymbolEnum.W
                : "Symbol is invalid during loading from insertion of task to text file.";

        switch (symbol) {
            case T:
                fw.write("T | ✗ | "
                        + task.getDescription()
                        + " | "
                        + task.getPriority()
                        + "\n");
                break;

            case D:
                fw.write("D | ✗ | " + task.getDescription()
                        + " | "
                        + String.format("%02d", task.getDateAndTime().getDayOfMonth())
                        + "/"
                        + String.format("%02d", task.getDateAndTime().getMonthValue())
                        + "/"
                        + task.getDateAndTime().getYear()
                        + ", "
                        + String.format("%02d", task.getDateAndTime().getHour())
                        + ":"
                        + String.format("%02d", task.getDateAndTime().getMinute())
                        + " | "
                        + task.getPriority()
                        + "\n");
                break;

            case E:
                fw.write("E | ✗ | " + task.getDescription()
                        + " | "
                        + String.format("%02d", task.getDateAndTime().getDayOfMonth())
                        + "/"
                        + String.format("%02d", task.getDateAndTime().getMonthValue())
                        + "/"
                        + task.getDateAndTime().getYear()
                        + ", "
                        + String.format("%02d", task.getDateAndTime().getHour())
                        + ":"
                        + String.format("%02d", task.getDateAndTime().getMinute())
                        + " | "
                        + task.getPriority()
                        + "\n");
                break;

            case W:
                fw.write("W | ✗ | "
                        + task.getDescription()
                        + " | "
                        + String.format("%02d", task.getStart().getDayOfMonth())
                        + "/"
                        + String.format("%02d", task.getStart().getMonthValue())
                        + "/"
                        + task.getStart().getYear()
                        + ", "
                        + String.format("%02d", task.getStart().getHour())
                        + ":"
                        + String.format("%02d", task.getStart().getMinute())
                        + " - "
                        + String.format("%02d", task.getEnd().getDayOfMonth())
                        + "/"
                        + String.format("%02d", task.getEnd().getMonthValue())
                        + "/"
                        + task.getEnd().getYear()
                        + ", "
                        + String.format("%02d", task.getEnd().getHour())
                        + ":"
                        + String.format("%02d", task.getEnd().getMinute())
                        + " | "
                        + task.getPriority()
                        + "\n");
                break;
        }

        fw.close();
    }
}
