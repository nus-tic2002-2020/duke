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

/**
 * Represents the file used to store address book data.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "data/taskList.txt";
    public final Path path;

    private String filePath;
    private File file;
    private Scanner scan;

    public Storage () throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) {//throws InvalidStorageFilePathException{
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            createFileAndDirectory();
            //throw new InvalidStorageFilePathException("New file is created.\n Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
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

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save() throws StorageOperationException {
        //FileWriter fw = new FileWriter(filePath, true);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            for (int i = 0; i< TaskList.length(); i++){
                fw.write(TaskList.getATask(i) + System.lineSeparator());
            }
            fw.close();
            throw new IOException();
        }catch (IOException ioe) {
            System.out.println("\t☹ OOPS!!! Error saving the file.");
        }//catch (StorageOperationException ioe) {
        //    System.out.println("\t☹ OOPS!!! Error writing the file.");
        //}
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
//    public static class InvalidStorageFilePathException extends IllegalValueException {
//        public InvalidStorageFilePathException(String message) {
//            super(message);
//        }
//    }
    /**
     * Loads the {@code AddressBook} data from this storage file, and then returns it.
     * Returns an empty {@code AddressBook} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public ArrayList<Task> load(String filepath) {//throws StorageOperationException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new ArrayList<Task>();
        }
        while (scan.hasNext()){
            System.out.println(scan.nextLine());
        }
        return TaskList.taskList;

//        try {
//            while (scan.hasNext()){
//                System.out.println(scan.nextLine());
//            }
//            throw new IllegalValueException("");
//        } catch (IllegalValueException ive) {
//            System.out.println("\t☹ OOPS!!! Error saving the file.");
//        }
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