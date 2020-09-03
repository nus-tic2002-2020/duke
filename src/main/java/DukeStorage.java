import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DukeStorage implements DukeUI {

    //VARIABLES-----------------------------------------
    private File file;
    private String path;


    //CONSTRUCTORS--------------------------------------
    public DukeStorage(File file, String path){
        this.file = file;
        this.path = path;
    }


    //WRITE STATEMENTS----------------------------------
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.path, false);
        for(Task task: tasks){
            String text = noteToText(task);
            fw.write(text);
        }
        fw.close();
    }

    public String noteToText(Task task) {
        String text = task.getSaveText();
        return text;
    }

    //LOAD STATEMENTS-----------------------------------
    public ArrayList<Task> readFromFile() throws FileNotFoundException, ParseException {

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] readIndexes = read.nextLine().split("/");
            switch (readIndexes[0]) {
                case "Deadline" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date targetDate = INPUT_DATE.parse(readIndexes[5]);
                    boolean doneAhead = Boolean.parseBoolean(readIndexes[6]);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[7]);
                        Task task = new Deadline(serialNum, description, addDate, doneDate,
                                isDone, targetDate, doneAhead);
                        tasks.add(task);
                    } else {
                        Task task = new Deadline(serialNum, description, addDate,
                                isDone, targetDate, doneAhead);
                        tasks.add(task);
                    }
                }
                case "Event" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date startDate = INPUT_DATE.parse(readIndexes[5]);
                    Date endDate = INPUT_DATE.parse(readIndexes[6]);
                    long durationMinutes = Long.parseLong(readIndexes[7]);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[8]);
                        Task task = new Event(serialNum, description, addDate, doneDate,
                                isDone, startDate, endDate, durationMinutes);
                        tasks.add(task);
                    } else {
                        Task task = new Event(serialNum, description, addDate,
                                isDone, startDate, endDate, durationMinutes);
                        tasks.add(task);
                    }
                }
                case "Shoplist" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    double budgetSet = Double.parseDouble(readIndexes[5]);
                    double budgetRevised = Double.parseDouble(readIndexes[6]);
                    double budgetUsed = Double.parseDouble(readIndexes[7]);
                    double budgetBalance = Double.parseDouble(readIndexes[8]);
                    boolean isRevised = Boolean.parseBoolean(readIndexes[9]);
                    boolean isOverBudget = Boolean.parseBoolean(readIndexes[10]);
                    Budget itemBudget = new Budget(budgetSet, budgetRevised, budgetUsed,
                            budgetBalance, isRevised, isOverBudget);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[11]);
                        Task task = new Shoplist(serialNum, description, addDate, doneDate,
                                isDone, itemBudget);
                        tasks.add(task);
                    } else {
                        Task task = new Shoplist(serialNum, description, addDate,
                                isDone, itemBudget);
                        tasks.add(task);
                    }
                }
                case "Todo" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[5]);
                        Task task = new Todo(serialNum, description, addDate, doneDate,
                                isDone);
                        tasks.add(task);
                    } else {
                        Task task = new Todo(serialNum, description, addDate,
                                isDone);
                        tasks.add(task);
                    }
                }
            }
        }
        return tasks;
    }
}
