package DukeUtils;

import DukeTask.Deadline;
import DukeTask.Event;
import DukeTask.Task;
import DukeTask.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    /****
     *
     * @param fileContent the tasks stored in the file
     * @throws FileNotFoundException if file can't be found
     */
    static void writeToFile(String fileContent) throws FileNotFoundException {
        File f = new File("D:\\ZaZa's Programming\\TIC2002\\Clone\\data\\duke.txt");
        PrintWriter pw = new PrintWriter(f);
        pw.println(fileContent);
        pw.close();
    }
    /****
     *load from file
     * @throws FileNotFoundException if file can't be found
     */
    static void loadFile() throws FileNotFoundException {
        File f = new File("D:\\ZaZa's Programming\\TIC2002\\Clone\\data\\duke.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            loadFormat(sc.nextLine());
        }
    }
    /****
     *
     * @param fileContent the tasks stored in the file
     * Convert from LocalDate time format to string from txt file back to DukeUtils.Duke
     */
    static void loadFormat(String fileContent) {
        String str = fileContent;
        String[] storeArray = str.split(" \\| ");

        for (String i : storeArray){
            System.out.println(i);
        }

        try {
            if (storeArray[0].equals("T")) {
                Task t= new ToDo(storeArray[2], Boolean.valueOf(storeArray[1]));
                Duke.tasks.addTask(t);
            } else if (storeArray[0].equals("D")) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(storeArray[3]);
                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]),dateTime);
                    Duke.tasks.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("HelloWorldFROM HERE");
                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]));
                    Duke.tasks.addTask(t);
                }
            } else if (storeArray[0].equals("E")) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(storeArray[3]);
                    Task t= new Event(storeArray[2], Boolean.valueOf(storeArray[1]),dateTime);
                    Duke.tasks.addTask(t);
                }catch (ArrayIndexOutOfBoundsException e) {
                    Task t= new Event(storeArray[2], Boolean.valueOf(storeArray[1]));
                    Duke.tasks.addTask(t);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}