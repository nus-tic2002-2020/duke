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
import seedu.duke.commands.*;
import seedu.duke.exception.*;

public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "data/taskList.txt";
    //public final Path path;

    private String filePath;
    private File file;
    Scanner s;

    public Storage () throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) {//throws InvalidStorageFilePathException{
        try {
            if (!isValidPath(filePath)) {
                //createFileAndDirectory();
                //throw new InvalidStorageFilePathException("New file is created.\n Storage file should end with '.txt'");
            } else {
                this.filePath = filePath;
                this.file = new File(filePath);
                s = new Scanner(file);
            }
        }catch(FileNotFoundException e){
            createFileAndDirectory();
        }
    }

    private static boolean isValidPath(String filePath) {
        return filePath.toString().endsWith(".txt");
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
            //throw new IOException();
        }catch (IOException ioe) {
            System.out.println("\t☹ OOPS!!! Error saving the file.");
        }//catch (StorageOperationException ioe) {
        //    System.out.println("\t☹ OOPS!!! Error writing the file.");
        //}
    }

    public ArrayList<Task> load() {//throws StorageOperationException {
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists() || !file.isFile()) {
            return new ArrayList<Task>();
        }else{
            while (s.hasNext()){
                System.out.println(s.nextLine());
            }
        }
        return taskList;
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