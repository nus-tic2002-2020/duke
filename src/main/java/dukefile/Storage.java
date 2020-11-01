package dukefile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private File list;
    private Path path;

    public Storage(String filepath) {
        path = Paths.get(filepath);
        list = new File(String.valueOf(path));

        if (!list.exists()) {
            File dir = new File(list.getParent());

            if (!dir.isDirectory()) {
                System.out.println("   Directory did not exist, creating directory\n"
                        + "     " + dir.getAbsolutePath());
                dir.mkdir();
            }

            try {
                list.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("   File did not exist in the directory, creating file\n"
                    + "     " + list.getName());
        }else {
            FileWriter fw = null;
            try {
                fw = new FileWriter(String.valueOf(path));
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeTask (String task) {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path), true);
            fw.write(task);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteTask (String taskString) {
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(String.valueOf(path)));

            String line1 = br1.readLine();
            ArrayList<String> text = new ArrayList<>();

            while (line1 != null){
                if(!line1.equals(taskString)) text.add(line1);
                line1 = br1.readLine();
            }

            br1.close();

            FileWriter fd = new FileWriter(String.valueOf(path));
            fd.close();

            FileWriter fw = new FileWriter(String.valueOf(path), true);

            for(int i = 0; i < text.size(); i++){
                fw.write(text.get(i));
                fw.write("\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
