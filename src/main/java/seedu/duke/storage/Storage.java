package seedu.duke.storage;

import java.io.File;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import seedu.duke.commands.*;
import seedu.duke.exception.*;

public class Storage {
    private String filePath;
    private File file;
    private Scanner s;

    public Storage (String filePath){
        try{
        this.filePath = filePath;
        this.file = new File(filePath);
        s = new Scanner(file);
        }catch (FileNotFoundException e){
            createFileAndDirectory();
        }
    }

    public void createFileAndDirectory(){
        try{
            file.createNewFile();
            throw new IOException();
        }catch (IOException e){
            System.out.println("\t☹ OOPS!!! Error creating file.");
        }
    }

    public void save() throws StorageOperationException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            for (int i = 0; i< TaskList.length(); ++i){
                fw.write(TaskList.getATask(i) + System.lineSeparator());
            }
            fw.close();
        }catch (IOException ioe) {
            System.out.println("\t☹ OOPS!!! Error saving the file.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists() || !file.isFile()){
            return new ArrayList<Task>();
        }else{
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] splitLine = line.split(" \\| ");
                switch (splitLine[0]){
                    case "T ":
                        Todo newTodo = new Todo(splitLine[2]);
                        if(splitLine[1].equals("1")){
                            newTodo.setDone();
                        }
                        taskList.add(newTodo);
                    case "E ":
                        Event newEvent = new Event(splitLine[2], stringToDate(splitLine[3]));
                        if(splitLine[1].equals("1")){
                            newEvent.setDone();
                        }
                        taskList.add(newEvent);
                        break;
                    case "D ":
                        Deadline newDeadline = new Deadline(splitLine[2], stringToDate(splitLine[3]));
                        if(splitLine[1].equals("1")){
                            newDeadline.setDone();
                        }
                        taskList.add(newDeadline);
                        //break;
                }
            }
        }
        return taskList;
    }

    private LocalDateTime stringToDate(String date) throws DukeException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYY HHmm");
            return LocalDateTime.parse(date, formatter);
        }
        catch (DateTimeParseException e){
            throw new DukeException("The date " + date + " loaded from the file is invalid.");
        }
    }

    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYY HHmm");
        return dateTime.format(formatter);
    }

    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}