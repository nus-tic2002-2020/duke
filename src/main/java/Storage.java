import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage  {
    //variable
    private String pathname;
    //constructor
    public Storage(String path){
        this.pathname = path;
    }
    //accessor and modifier
    public void load(TaskList newList, UI ui) {
        File f = new File(pathname);
        try {
            Scanner s = new Scanner(f);
            int counter = 0;
            while (s.hasNext()) {
                String line_item = s.nextLine();
                String[] split_line_item = line_item.split("\\|", 4);
                switch (split_line_item[0].trim()) {
                    case "T":
                        line_item = "todo " + split_line_item[2].trim();
                        break;
                    case "D":
                        line_item = "deadline " + split_line_item[2].trim() + "/by " + split_line_item[3].trim();
                        break;
                    case "E":
                        line_item = "event " + split_line_item[2].trim() + "/at " + split_line_item[3].trim();
                        break;
                }
                ui.response(line_item);
                //new_list.reader(line_item);
                counter++;
                if (split_line_item[1].trim().equals("1")) {
                    ui.response( "done " + counter);
                    //new_list.reader("done " + counter);
                }
            }
        }
        catch (FileNotFoundException e) {}
    }
    public void save(TaskList cur_list){
        File taskFile = new File(pathname);
        if(taskFile.exists() == false){
            try{
                taskFile.createNewFile();
                saveList(cur_list);
            }
            catch(IOException e){
                System.out.println("Error creating file");
            }
        }
        else{
            try{
                saveList(cur_list);
            }
            catch(IOException e){
                System.out.println("Error writing to file");
            }
        }
    }
    private void saveList(TaskList cur_list) throws IOException{
        FileWriter fw = new FileWriter(pathname);
        for(int i = 1 ; i <= Duke.numberOftask ; i++){
            fw.write(cur_list.convert_lineItem(i) + System.lineSeparator());
        }
        fw.close();
    }
}
