package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public Storage(String filePath) throws IOException {
        File dataFolder = new File("data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        }
    }

    public static ArrayList<String> load() throws IOException {
        ArrayList tasks = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("data/task_list.txt"));
        while (scanner.hasNextLine()) {
            tasks.add(scanner.nextLine());
        }
        return tasks;
    }

    public static void delete(String input) throws IOException {
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

    public static void insert(String input) throws IOException {
        FileWriter fw = new FileWriter("data/task_list.txt", true);
        String[] temp = input.split(" ");
        int function = 0;
        if (input.toLowerCase().startsWith("t")) { function = 1; }
        if (input.toLowerCase().startsWith("d")) { function = 2; }
        if (input.toLowerCase().startsWith("e")) { function = 3; }
        switch (function) {
            case 1: {
                fw.write("T | X | " + temp[1] + "\n");
                break;
            }
            case 2: {
                fw.write("D | X | " + temp[1] + " | " + temp[3] + "\n");
                break;
            }
            case 3: {
                fw.write("E | X | " + temp[1] + " | " + temp[3] + "\n");
                break;
            }
        }
        fw.close();
    }
}
