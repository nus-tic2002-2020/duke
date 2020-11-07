package storage;

import java.io.*;
import java.util.ArrayList;
import tasks.*;


/**
 * This represents the Files access. The various objects are used to access the file/directory
 * location for either writing or reading on the file.
 */
public class Storage {

    private File directory;
    private File f;
    private String filePath;

    //For reading files
    private FileReader fr;
    private BufferedReader br;

    ArrayList<String[]> parsedFile;


    /**
     * This is the constructor for the Storage Class.
     * @param filePath This is the filePath of the text file.
     */
    public Storage(String filePath) throws IOException {

        this.directory = new File("data");
        if(this.directory.isDirectory() == false || this.directory.exists() == false){
            System.out.println("Directory \"data\" is not found, creating a new one");
            this.directory.mkdir();
        }

        this.f = new File(filePath);

        if(this.f.isFile() == false || this.f.exists() == false){
            System.out.println("File \"duke.text\" is not found, creating a new one");
            this.f.createNewFile();
        }

        this.filePath = filePath;
    }

    /**
     * This is a mutator. It writes the Task List details into a file.
     * @param tasks This is the list of tasks that the user has entered.
     */
    public void save(TaskList tasks) throws IOException {

        FileWriter fw = new FileWriter(this.filePath);

        int size = tasks.getSize();
        if(size == 0){
            System.out.println("Task List is empty. Nothing to save.");
            return;
        }

        String temp = "";
        String text = "";
        String firstPart;
        String secPart = "";
        int done = 0;

        for(int i = 0; i < size; i ++){
            temp = tasks.get(i).getClass().toString();
            switch(temp){
                case "class tasks.Task":
                    firstPart = "O | ";
                    break;
                case "class tasks.ToDo":
                    firstPart = "T | ";
                    break;
                case "class tasks.Deadline":
                    firstPart = "D | ";
                    secPart = " | " + ((Deadline)tasks.get(i)).getBy();
                    break;
                case "class tasks.Event":
                    firstPart = "E | ";
                    secPart = " | " + ((Event)tasks.get(i)).getAt();
                    break;
                default:
                    System.out.println("Can't get Class from Task List");
                    return;
            }

            done = (tasks.get(i).getIsDone()) ? 1:0;
            text = firstPart + done + " | " + tasks.get(i).getDescription() + secPart;
            fw.write(text + System.lineSeparator());
            text = "";
            secPart = "";

        }
        fw.close();
    }

    /**
     * This is a mutator that loads the task lists from the file.
     * It will return a list of tasks in form of an ArrayList of Strings. These Strings will be used for
     * the initialisation of Task List
     * @return a list of tasks that are written as strings
     */
    public ArrayList<String[]> load() throws IOException{
        this.f = new File(this.filePath);
        this.fr =new FileReader(this.f);
        this.br = new BufferedReader(this.fr);
        this.parsedFile =  new ArrayList<String[]> ();
        String line;

        while((line = this.br.readLine())!=null){
            this.parsedFile.add(line.split(" \\| ") );
        }
        this.fr.close();
        return this.parsedFile;
    }

}
