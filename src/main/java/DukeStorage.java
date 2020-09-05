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
    public DukeStorage(String path){

        int lastSlash = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.substring(i, i + 1).equals("/")) {
                lastSlash = i;
            }
        }

        File fileDir = new File(path.substring(0, lastSlash));
        if(!fileDir.exists()){ fileDir.mkdirs(); }

        this.file = new File(path);
        this.path = path;
    }


    //GET STATEMENTS----------------------------------
    public String getPath() { return this.path; }


    //SET STATEMENTS----------------------------------
    public void setFile(File file) { this.file = file; }


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
        return task.getSaveText();
    }

    //LOAD STATEMENTS-----------------------------------
    public ArrayList<Task> readFromFile() throws FileNotFoundException, ParseException {

        ArrayList<Task> notes = new ArrayList<Task>();
        Task note = null;
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] readIndexes = read.nextLine().split("/");
            switch (readIndexes[0]) {
                case "Bill" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date targetDate = INPUT_DATE.parse(readIndexes[5]);
                    boolean doneAhead = Boolean.parseBoolean(readIndexes[6]);
                    double budgetSet = Double.parseDouble(readIndexes[7]);
                    double budgetRevised = Double.parseDouble(readIndexes[8]);
                    double budgetUsed = Double.parseDouble(readIndexes[9]);
                    double budgetBalance = Double.parseDouble(readIndexes[10]);
                    boolean isRevised = Boolean.parseBoolean(readIndexes[11]);
                    boolean isOverBudget = Boolean.parseBoolean(readIndexes[12]);
                    Budget itemBudget = new Budget(budgetSet, budgetRevised, budgetUsed,
                            budgetBalance, isRevised, isOverBudget);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[13]);
                        note = new Bill(serialNum, description, addDate, doneDate,
                                isDone, targetDate, doneAhead, itemBudget);
                    } else {
                        note = new Bill(serialNum, description, addDate,
                                isDone, targetDate, doneAhead, itemBudget);
                    }
                }
                case "Deadline" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date targetDate = INPUT_DATE.parse(readIndexes[5]);
                    boolean doneAhead = Boolean.parseBoolean(readIndexes[6]);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[7]);
                        note = new Deadline(serialNum, description, addDate, doneDate,
                                isDone, targetDate, doneAhead);
                    } else {
                        note = new Deadline(serialNum, description, addDate,
                                isDone, targetDate, doneAhead);
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
                        note = new Event(serialNum, description, addDate, doneDate,
                                isDone, startDate, endDate, durationMinutes);
                    } else {
                        note = new Event(serialNum, description, addDate,
                                isDone, startDate, endDate, durationMinutes);
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
                        note = new Shoplist(serialNum, description, addDate, doneDate,
                                isDone, itemBudget);
                    } else {
                        note = new Shoplist(serialNum, description, addDate,
                                isDone, itemBudget);
                    }
                }
                case "Todo" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_DATE.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);

                    if(isDone) {
                        Date doneDate = INPUT_DATE.parse(readIndexes[5]);
                        note = new Todo(serialNum, description, addDate, doneDate,
                                isDone);
                    } else {
                        note = new Todo(serialNum, description, addDate,
                                isDone);
                    }
                }
            }
            notes.add(note);
        }
        return notes;
    }
}
