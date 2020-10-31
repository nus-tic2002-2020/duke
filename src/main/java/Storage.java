import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        File file = new File(filePath);
        Scanner s = null;
        try {
            s = new Scanner(file);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String taskType = taskString.substring(0, 1);
                String isDone = taskString.substring(4, 5);
                if (taskType.equals("T")) {
                    String desc = taskString.substring(8);
                    ToDo task = new ToDo(desc);
                    if (isDone.equals("1")) {
                        task.markDone();
                    }
                    list.add(task);
                } else {
                    int i = taskString.lastIndexOf("|");
                    String desc = taskString.substring(8, i - 1);
                    String time = taskString.substring(i + 2, i + 12) + taskString.substring(i + 13);
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(time, format);
                    if (taskType.equals("D")) {
                        Deadline task = new Deadline(desc, dateTime);
                        if (isDone.equals("1")) {
                            task.markDone();
                        }
                        list.add(task);
                    } else {
                        Event task = new Event(desc, dateTime);
                        if (isDone.equals("1")) {
                            task.markDone();
                        }
                        list.add(task);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading Error");
        }
        return list;
    }

    public void updateTaskInFile(int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum == taskNum) {
                s = s.substring(0, 4) + "1" + s.substring(5);
            }
            list.add(s);
            lineNum++;
        }
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i) + System.lineSeparator());
        }
        fw.write(list.get(list.size() - 1));
        fw.close();
    }
    public void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        File file = new File(filePath);
        Scanner scn = new Scanner(file);
        if (scn.hasNext()) {
            fw.write(System.lineSeparator());
        }
        fw.write(textToAdd);
        fw.close();
    }

    public void deleteFromFile(int taskNum) throws IOException {
        File file =  new File(filePath);
        Scanner scn = new Scanner(file);
        List<String> list = new ArrayList<String>();
        int lineNum = 1;
        while (scn.hasNext()) {
            String s = scn.nextLine();
            if (lineNum != taskNum) {
                list.add(s);
            }
            lineNum++;
        }
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size() - 1; i++) {
            fw.write(list.get(i) + System.lineSeparator());
        }
        fw.write(list.get(list.size() - 1));
        fw.close();
    }
}