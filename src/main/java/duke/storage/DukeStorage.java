package duke.storage;

import duke.Duke;
import duke.budget.Budget;
import duke.notes.Task;
import duke.notes.event.Birthday;
import duke.notes.event.Event;
import duke.notes.event.Wedding;
import duke.notes.todo.Bill;
import duke.notes.todo.Deadline;
import duke.notes.todo.Shoplist;
import duke.notes.todo.Todo;
import duke.ui.DukeUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

/**
 * An object that manages the storage of the {@code DukeList} object in saved files.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DukeStorage implements DukeUI {

    //VARIABLES-----------------------------------------
    private File file;
    private File lastSave = null;
    private File last2Save = null;
    private File last3Save = null;
    private String path;


    //CONSTRUCTORS--------------------------------------
    /**
     * This method is used to construct a {@code DukeStorage} object.
     *
     * @param path The path to the saved files in the hard drive.
     */
    public DukeStorage(String path){

        int lastSlash = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                lastSlash = i;
            }
        }

        File fileDir = new File(path.substring(0, lastSlash));
        if(!fileDir.exists()){ fileDir.mkdirs(); }

        this.file = new File(path);
        this.path = path;
        this.lastSave = null;
        this.last2Save = null;
        this.last3Save = null;
    }

    /**
     * This method is used to initialise a {@code DukeStorage} object.
     *
     */
    public DukeStorage(){}

    //GET STATEMENTS----------------------------------
    /**
     * This method is used to retrieve the path to the saved files in the hard drive.
     *
     * @return String The path to the saved files in the hard drive.
     */
    public String getPath() { return this.path; }

    /**
     * This method is used to undo previous save operations by reloading the last version
     * of the {@code DukeList} object in the hard drive.
     *
     * @return int The number of archived {@code DukeList} objects that could be reloaded.
     * @throws IOException If the saved file could not be found via the file path.
     * @throws ParseException If the saved file could not be read and understood.
     */
    public int revertToLastSave(DukeList dukeNotes) throws IOException, ParseException {

        if(this.lastSave == null) {
            return -1;

        } else if(this.last2Save == null){
            copyFile(this.lastSave, this.file);
            this.lastSave = null;
            dukeNotes.replaceNotes(readFromFile());
            return 0;

        } else if(this.last3Save == null){
            copyFile(this.lastSave, this.file);
            this.lastSave = this.last2Save;
            this.last2Save = null;
            dukeNotes.replaceNotes(readFromFile());
            return 1;

        } else {
            copyFile(this.lastSave, this.file);
            this.lastSave = this.last2Save;
            this.last2Save = this.last3Save;
            this.last3Save = null;
            dukeNotes.replaceNotes(readFromFile());
            return 2;
        }
    }


    //SET STATEMENTS----------------------------------
    /**
     * This method is used to replace the primary file for the saving of the {@code DukeList} object.
     *
     * @param file The new file to be used to save the {@code DukeList} object.
     */
    public void setFile(File file) { this.file = file; }

    public void deleteFiles() {
        if(this.file != null) { this.file.delete(); }
        if(this.lastSave != null) { this.lastSave.delete(); }
        if(this.last2Save != null) { this.last2Save.delete(); }
        if(this.last3Save != null) { this.last3Save.delete(); }
    }


    //ENCODE & DECODE METHODS---------------------------
    /**
     * This method is used to encode a {@code String} of text using Base64.
     *
     * @param text The text to be encoded using Base64.
     * @return String The {@code String} representing the Base64 encoded text.
     */
    String encodeText(String text) {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] byteArray = text.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(byteArray);
    }

    /**
     * This method is used to decode a {@code String} of Base64 encoded text.
     *
     * @param text The Base64 encoded text to be decoded.
     * @return String The {@code String} of text decoded from the Base64 encoded text.
     */
    String decodeText(String text) {

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] byteArray = decoder.decode(text);
        return new String(byteArray, StandardCharsets.UTF_8);
    }

    //WRITE METHODS-------------------------------------
    /**
     * This method is used to write to file, the ArrayList of {@code Task} objects held by the {@code DukeList} object.
     *
     * @param dukeNotes The {@code DukeList} object to be written to file.
     * @throws IOException If the saved file could not be found via the file path.
     */
    public void writeToFile(DukeList dukeNotes) throws IOException {
        FileWriter fw = new FileWriter(this.path, false);
        for(Task task: dukeNotes.getNotes()){
            String text = task.getSaveText();
            text = encodeText(text) + "\n";
            fw.write(text);
        }
        fw.close();
    }

    /**
     * This method is used to archive the current version the {@code DukeList} object
     * before it is overwritten by a new save.
     *
     * @throws IOException If the saved file could not be found via the file path.
     */
    public void archiveToFile() throws IOException {

        this.last3Save = this.last2Save;
        this.last2Save = this.lastSave;

        Date archiveDate = new Date();
        int lastSlash = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                lastSlash = i;
            }
        }

        String archivePath = this.path.substring(0, lastSlash) +
                "/archive_" + archiveDate.getTime() + ".txt";
        this.lastSave = new File(archivePath);

        copyFile(this.file, this.lastSave);
    }

    /**
     * This method is used to copy data from the file holding a previous version the {@code DukeList} object
     * to the primary file holding the {@code DukeList} object in memory.
     *
     * @param fromFile The file holding a previous version the {@code DukeList} object.
     * @param toFile The primary file holding the {@code DukeList} object in memory.
     * @throws IOException If the saved file could not be found via the file path.
     */
    public void copyFile(File fromFile, File toFile) throws IOException {

        Scanner read = new Scanner(fromFile);
        FileWriter fw = new FileWriter(toFile, false);

        while(read.hasNext()) {
            fw.write(read.nextLine() + "\n");
        }
        fw.close();
    }


    //LOAD METHODS--------------------------------------
    /**
     * This method is used to read data from saved files and reconstruct the {@code Note} objects,
     * thereafter holding them in an ArrayList.
     *
     * @return ArrayList<Task> The ArrayList of reconstructed {@code Note} objects from the saved file.
     * @throws FileNotFoundException If the saved file could not be found via the file path.
     * @throws ParseException If there are errors reading from saved files.
     */
    public ArrayList<Task> readFromFile() throws FileNotFoundException, ParseException {

        ArrayList<Task> notes = new ArrayList<>();

        Task note = null;
        Scanner read = new Scanner(this.file);

        if(!read.hasNext()) {
            throw new FileNotFoundException();
        } else {
            Duke.resetStaticVariables();
        }

        while (read.hasNext()) {
            String nextLine = read.nextLine();
            nextLine = decodeText(nextLine);
            String[] readIndexes = nextLine.split("/");
            switch (readIndexes[0]) {
                case "Bill" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date targetDate = INPUT_TIME.parse(readIndexes[5]);
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
                        Date doneDate = INPUT_TIME.parse(readIndexes[13]);
                        note = new Bill(serialNum, description, addDate, doneDate,
                                true, targetDate, doneAhead, itemBudget);
                    } else {
                        note = new Bill(serialNum, description, addDate,
                                false, targetDate, doneAhead, itemBudget);
                    }
                }
                case "Birthday" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date startDate = INPUT_TIME.parse(readIndexes[5]);
                    Date endDate = INPUT_TIME.parse(readIndexes[6]);
                    long durationMinutes = Long.parseLong(readIndexes[7]);

                    if(isDone) {
                        Date doneDate = INPUT_TIME.parse(readIndexes[8]);
                        note = new Birthday(serialNum, description, addDate, doneDate,
                                true, startDate, endDate, durationMinutes);
                    } else {
                        note = new Birthday(serialNum, description, addDate,
                                false, startDate, endDate, durationMinutes);
                    }
                }
                case "Deadline" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date targetDate = INPUT_TIME.parse(readIndexes[5]);
                    boolean doneAhead = Boolean.parseBoolean(readIndexes[6]);

                    if(isDone) {
                        Date doneDate = INPUT_TIME.parse(readIndexes[7]);
                        note = new Deadline(serialNum, description, addDate, doneDate,
                                true, targetDate, doneAhead);
                    } else {
                        note = new Deadline(serialNum, description, addDate,
                                false, targetDate, doneAhead);
                    }
                }
                case "Event" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date startDate = INPUT_TIME.parse(readIndexes[5]);
                    Date endDate = INPUT_TIME.parse(readIndexes[6]);
                    long durationMinutes = Long.parseLong(readIndexes[7]);

                    if(isDone) {
                        Date doneDate = INPUT_TIME.parse(readIndexes[8]);
                        note = new Event(serialNum, description, addDate, doneDate,
                                true, startDate, endDate, durationMinutes);
                    } else {
                        note = new Event(serialNum, description, addDate,
                                false, startDate, endDate, durationMinutes);
                    }
                }
                case "Shoplist" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
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
                        Date doneDate = INPUT_TIME.parse(readIndexes[11]);
                        note = new Shoplist(serialNum, description, addDate, doneDate,
                                true, itemBudget);
                    } else {
                        note = new Shoplist(serialNum, description, addDate,
                                false, itemBudget);
                    }
                }
                case "Todo" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);

                    if(isDone) {
                        Date doneDate = INPUT_TIME.parse(readIndexes[5]);
                        note = new Todo(serialNum, description, addDate, doneDate,
                                true);
                    } else {
                        note = new Todo(serialNum, description, addDate,
                                false);
                    }
                }
                case "Wedding" -> {
                    int serialNum = Integer.parseInt(readIndexes[1]);
                    String description = readIndexes[2];
                    Date addDate = INPUT_TIME.parse(readIndexes[3]);
                    boolean isDone = Boolean.parseBoolean(readIndexes[4]);
                    Date startDate = INPUT_TIME.parse(readIndexes[5]);
                    Date endDate = INPUT_TIME.parse(readIndexes[6]);
                    long durationMinutes = Long.parseLong(readIndexes[7]);
                    double budgetSet = Double.parseDouble(readIndexes[8]);
                    double budgetRevised = Double.parseDouble(readIndexes[9]);
                    double budgetUsed = Double.parseDouble(readIndexes[10]);
                    double budgetBalance = Double.parseDouble(readIndexes[11]);
                    boolean isRevised = Boolean.parseBoolean(readIndexes[12]);
                    boolean isOverBudget = Boolean.parseBoolean(readIndexes[13]);
                    Budget itemBudget = new Budget(budgetSet, budgetRevised, budgetUsed,
                            budgetBalance, isRevised, isOverBudget);

                    if(isDone) {
                        Date doneDate = INPUT_TIME.parse(readIndexes[14]);
                        note = new Wedding(serialNum, description, addDate, doneDate,
                                true, startDate, endDate, durationMinutes, itemBudget);
                    } else {
                        note = new Wedding(serialNum, description, addDate,
                                false, startDate, endDate, durationMinutes, itemBudget);
                    }
                }
            }
            notes.add(note);
        }
        return notes;
    }
}
