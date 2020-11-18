package duke.storage;
import duke.ui.*;
import duke.task.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;




public class Storage {

    private String location;

    /**
     *constructor of Storage class
     */
    public Storage(String path){
        this.location = path;
    }

    /**
     *Load existing saved .txt file for Duke and add to List
     *
     * @param list of TaskList
     * @param UI for Duke
     *
     */
    public void LoadFile (TaskList new_tasks, UI ui){
        File f = new File(location);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                String a_line = s.nextLine();
                String[] split_line_item = a_line.split("\\|", 4);
                switch (split_line_item[0].trim()){
                    case "T":
                        a_line = "todo " + split_line_item[2].trim();
                        break;
                    case "D":
                        a_line = "deadline " + split_line_item[2].trim() + "/by " + split_line_item[3].trim();
                        break;
                    case "E":
                        a_line = "event " + split_line_item[2].trim() + "/at " + split_line_item[3].trim();
                        break;
                }
                ui.saved_list(new_tasks, a_line);
            }
        }
        catch (FileNotFoundException e) {
        }
    }

    /**
     * save TaskList into .txt file
     *
     * @param List of task in ArrayList
     *
     */
    public  void save (TaskList current_tasks){
        File f = new File(location);
        if (f.exists()) {
            try {
                writeToFile(current_tasks);
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
        else{
            try{
                f.createNewFile();
                writeToFile(current_tasks);
            }
            catch (IOException e){
                System.out.println("Error creating new file");
            }
        }
    }

    private void writeToFile(TaskList current_tasks) throws IOException {
        FileWriter fw = new FileWriter(location);
        for (int i = 0; i<current_tasks.getCount();i++ ){
            fw.write(current_tasks.writetoList(i) + System.lineSeparator());
        }
        fw.close();
    }

}
