package duke.command;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected static int count = 0;
    protected static String Directory = "./data/";
    protected static String FileLocation = "data/duke.txt";
    protected static ArrayList<Task> list = new ArrayList<>();

    private static void printFileContents(String filePath) throws DukeException , IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String current = s.nextLine();
            if (current.contains("[T]")) {
                String description = current.substring(7);
                list.add(new Todo(description));
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else if (current.contains("[D]") || current.contains("[E]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);

                LocalDateTime localDateTime = Parser.parse(current.substring(0,n));
                assert localDateTime != null;
                if (current.contains("[D]")) {
                    list.add(new Deadline(description,localDateTime));
                }
                else {
                    list.add(new Event(description,localDateTime));
                }
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else { throw new DukeException("Error: Task in existing data is incompatible\n"); }
            count++;
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FileLocation);
        list = TaskList.UpdatedList();
        for (Task l : list) {
            fw.write(l + System.lineSeparator());
        }
        fw.close();
    }

    public static void main() {
        try {
            Files.createDirectories(Paths.get(Directory));
            printFileContents(FileLocation);
            new TaskList(list, count); //Initialize data with existing file
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
