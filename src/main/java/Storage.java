import tasklist.Deadline;
import tasklist.Events;
import tasklist.Task;
import tasklist.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    public static void writeToFile(String fileContent) {
        try {
            File f = new File("C:\\data\\duke.txt");
            PrintWriter pw = new PrintWriter(f);
            pw.println(fileContent);
            pw.close();
            System.out.println("Successfully wrote to the file.");

        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");

        }
    }
}
