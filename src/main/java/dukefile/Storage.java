package dukefile;

import dukeexception.DukeException;
import dukelist.TaskList;
import duketask.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private File list;
    private Path path;

    public Storage(String filepath) throws DukeException {
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
                throw new DukeException(e.getMessage());
            }
            System.out.println("   File did not exist in the directory, creating file\n"
                    + "     " + list.getName());
        } else {
            FileWriter fw = null;
            try {
                fw = new FileWriter(String.valueOf(path));
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeTask(Task task) {
        try {
            FileWriter fw = new FileWriter(String.valueOf(path), true);
            fw.write(String.valueOf(task));
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateTasks(TaskList tasks) {
        try {
            ArrayList<String> text = new ArrayList<>();

            for (int i = 0; i < tasks.size(); i++) text.add(tasks.getTask(i).toString());

            FileWriter fw = new FileWriter(list);
            BufferedWriter wr1 = new BufferedWriter(fw);

            for (int i = 0; i < text.size(); i++) {
                wr1.write(text.get(i));
                wr1.write("\n");
            }

            wr1.flush();
            wr1.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
