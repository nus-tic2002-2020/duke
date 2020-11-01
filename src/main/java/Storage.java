import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    //Write tasks to file

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

    //Load tasks from file
    /****
     *
     * @throws FileNotFoundException if file can't be found
     */
    static void loadFile() throws FileNotFoundException {
        File f = new File("D:\\ZaZa's Programming\\TIC2002\\Clone\\data\\duke.txt");

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            loadFormat(sc.nextLine());
        }
    }

    //Add tasks to taskList from file
    /****
     *
     * @param fileContent the tasks stored in the file
     * Convert from LocalDate time format to string from txt file back to Duke
     */
    static void loadFormat(String fileContent) {
        System.out.println(fileContent);
        String str = fileContent;
        String[] storeArray = str.split(" \\| ");
        try {
            if (storeArray[0].equals("T")){
                Task t= new ToDo(storeArray[2], Boolean.valueOf(storeArray[1]));
                Duke.tasks.addTask(t);
            } else if (storeArray[0].equals("D")){
                try {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime date = LocalDateTime.of(2020, Month.APRIL,3,12,30);
                    String formattedDate = date.format(inputFormatter);

                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]),date);
                    Duke.tasks.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e){
                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]));
                    Duke.tasks.addTask(t);
                }
            } else if (storeArray[0].equals("E")){
                try {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime date = LocalDateTime.of(2020, Month.APRIL,3,12,30);
                    String formattedDate = date.format(inputFormatter);
                    Task t= new Event(storeArray[2], Boolean.valueOf(storeArray[1]),date);
                    Duke.tasks.addTask(t);
                }catch (ArrayIndexOutOfBoundsException e){
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