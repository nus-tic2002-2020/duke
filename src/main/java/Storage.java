
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

public class Storage  {
    //variable
    private String pathname;
    private String userSentence;
    private CharSequence charDate;
    private LocalDate formattedDate;
    //constructor
    public Storage(String path){
        this.pathname = path;
    }

    /**
     * loads saved task list
     */
    public void load(Ui ui) {
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
                        String temp;
                        String[] tempArray;
                        temp = splitSentence[3];
                        tempArray = temp.split("by:",2);
                        temp=tempArray[1];
                        tempArray = temp.split("\\)",2);
                        temp = tempArray[0];
                        charDate = temp;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMM dd yyyy");
                        formattedDate = LocalDate.parse(charDate, formatter);
                        temp = formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        userSentence = "deadline " + splitSentence[2].trim() + " /by "+temp;
                        break;
                    case "[E]":
                        String temp2;
                        String[] tempArray2;
                        temp2 = splitSentence[3];
                        tempArray2 = temp2.split("at:",2);
                        temp2=tempArray2[1];
                        tempArray2 = temp2.split("\\)",2);
                        temp2 = tempArray2[0];
                        charDate = temp2;
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(" MMM dd yyyy");
                        formattedDate = LocalDate.parse(charDate, formatter2);
                        temp = formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        userSentence = "event " + splitSentence[2].trim() + " /at "+temp;
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

    /**
     * invokes saving call
     * If unable to save it will throw exception
     */
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

    /**
     * Does the write to file
     */
    private void saveList(TaskList currentList) throws IOException{
        FileWriter fw = new FileWriter(pathname);
        for(int i = 1 ; i <= Duke.numberOftask ; i++){
            fw.write(currentList.processtoTextfile(i) + System.lineSeparator());
        }
        fw.close();
    }
}