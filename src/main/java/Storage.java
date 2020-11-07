
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage  {
    //variable
    private String pathname;
    private String userSentence;
    //constructor
    public Storage(String path){
        this.pathname = path;
    }
    //accessor and modifier
    public void load(Ui ui) { //saved tasklist was not loaded.
        File f = new File(pathname);
        try {
            Scanner s = new Scanner(f);
            int counter = 0;
            while (s.hasNext()) {
                userSentence = s.nextLine();
                String[] splitSentence = userSentence.split("\\|", 4);
                switch (splitSentence[0].trim()) {
                    case "[T]":
                        userSentence = "todo " + splitSentence[2].trim();
                        break;
                    case "[D]":
                        userSentence = "deadline " + splitSentence[2].trim() + "/by " + splitSentence[3].trim();
                        break;
                    case "[E]":
                       userSentence = "event " + splitSentence[2].trim() + "/at " + splitSentence[3].trim();
                        break;
                }
                ui.response(userSentence);
                counter++;
                if (splitSentence[1].trim().equals("\u2713")) {
                    userSentence = "done " + counter;
                    ui.response(userSentence);

                }
            }
        }
        catch (FileNotFoundException | IllegalInputException e) {
            System.out.println("Error loading saved file");
        }
    }
    public void save(TaskList currentList){
        File taskFile = new File(pathname);
        if(taskFile.exists() == false){
            try{
                taskFile.createNewFile();
                saveList(currentList);
            }
            catch(IOException e){
                System.out.println("Error creating file");
            }
        }
        else{
            try{
                saveList(currentList);
            }
            catch(IOException e){
                System.out.println("Error writing to file");
            }
        }
    }
    private void saveList(TaskList currentList) throws IOException{
        FileWriter fw = new FileWriter(pathname);
        for(int i = 1 ; i <= Duke.numberOftask ; i++){
            fw.write(currentList.processtoTextfile(i) + System.lineSeparator());
        }
        fw.close();
    }
}
