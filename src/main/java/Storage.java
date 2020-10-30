import java.io.*;
import java.util.*;

public class Storage {
    private String filePath;
    private File file;
    private Scanner fileReader;

    public Storage(String filePath) throws IOException {
        try{
            this.filePath = filePath;
            this.file = new File(filePath);
            fileReader = new Scanner(file);
        }catch(FileNotFoundException e){
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        try{
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] split_Value = line.split(" \\| ");

                switch (split_Value[0]) {
                    case "E":
                        Event event = new Event(split_Value[2], split_Value[3]);
                        if (split_Value[1].equals("1")) {
                            event.setDone();
                        }
                        taskList.add(event);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(split_Value[2], split_Value[3]);
                        if (split_Value[1].equals("1")) {
                            deadline.setDone();
                        }
                        taskList.add(deadline);
                        break;
                    case "T":
                        Todo todo = new Todo(split_Value[2]);
                        if (split_Value[1].equals("1")) {
                            todo.setDone();
                        }
                        taskList.add(todo);
                }
            }
        }catch(NullPointerException e){
            this.file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        return taskList;
    }

    public void saveToFile() throws IOException{
        PrintWriter printWriter = new PrintWriter(file);
        String output = "";

        for (int i = 0; i < TaskList.getSize(); i++) {
            Task task = TaskList.getTasks(i);
            String description = task.description;
            String taskCommand = "";
            String when = "";
            boolean done = false;

            if(task instanceof Todo){
                taskCommand = "T";
            }
            else if(task instanceof Event){
                taskCommand = "E";
                when = ((Event)task).at;
            }
            else if(task instanceof Deadline){
                taskCommand = "D";
                when = ((Deadline)task).by;
            }

            if (task.isDone){
                done = true;
            }

            int val = (done) ? 1 : 0;

            if (when.equals("")){
                output += taskCommand + " | " + val + " | " + description + "\n";
            }
            else {
                output += taskCommand + " | " + val + " | " + description + " | " + when + "\n";
            }
        }
        printWriter.write(output);
        printWriter.close();
    }
}
