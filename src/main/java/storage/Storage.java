package storage;

import java.io.*;
import java.util.ArrayList;
import tasks.*;


//deals with loading tasks from the file and saving tasks in the file
public class Storage {

    private File directory;
    private File f;
    private String filePath;

    //For reading files
    private FileReader fr;
    private BufferedReader br;

    ArrayList<String[]> parsedFile;


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

    public void writeToFile(TaskList list) throws IOException {
        System.out.println("Loading from File to List");

        FileWriter fw = new FileWriter(this.filePath);

        int size = list.getSize();
        if(size == 0){
            System.out.println(System.lineSeparator() + "tasks.Task List is empty.");
            return;
        }

        String temp = "";
        String text = "";
        String firstPart;
        String secPart = "";
        int done = 0;

        System.out.println(System.lineSeparator() + "Writing Tasks List from Memo to File");
        for(int i = 0; i < size; i ++){
            temp = list.get(i).getClass().toString();
            switch(temp){
                case "class tasks.Task":
                    firstPart = "O | ";
                    break;
                case "class tasks.ToDo":
                    firstPart = "T | ";
                    break;
                case "class tasks.Deadline":
                    firstPart = "D | ";
                    secPart = " | " + ((Deadline)list.get(i)).getByDeadline();
                    break;
                case "class tasks.Event":
                    firstPart = "E | ";
                    secPart = " | " + ((Event)list.get(i)).getAt();
                    break;
                default:
                    System.out.println("Can't get Class from tasks.Task Array");
                    return;
            }

            done = (list.get(i).getIsDone()) ? 1:0;
            text = firstPart + String.valueOf(done) + " | " + list.get(i).getDescription() + secPart;
            fw.write(text + System.lineSeparator());
            text = "";
            secPart = "";

        }
        fw.close();
    }

    public ArrayList<String[]> load() throws IOException{
        this.f = new File(this.filePath);
        this.fr =new FileReader(this.f);
        this.br = new BufferedReader(this.fr);
        String line;

        while((line = this.br.readLine())!=null){
            this.parsedFile.add(line.split(" \\| ") );
        }
        this.fr.close();
        return this.parsedFile;
    }

}
