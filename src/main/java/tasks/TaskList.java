package tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import exceptions.DukeException;


/**
 * Represents the Task List. It contains a list of tasks.
 * It has operations to add, delete, find and print the tasks from the list.
 */

public class TaskList {


    protected ArrayList<Task> tasks;


    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String[]> parsedFile) throws DukeException{
        this.tasks = new ArrayList<Task>();
        int size = 0;
        try{
            size = parsedFile.size();
        }catch(NullPointerException e){
            System.out.println("Task List is loading empty storage");
        }


        for(int i = 0; i < size; i++){
            switch(parsedFile.get(i)[0]){
                case "O":
                    this.tasks.add(new Task(parsedFile.get(i)[2]));
                    break;
                case "T":
                    this.tasks.add(new ToDo(parsedFile.get(i)[2]));
                    break;
                case "D":
                    this.tasks.add(new Deadline(parsedFile.get(i)[2], LocalDateTime.parse(parsedFile.get(i)[3], DateTimeFormatter.ofPattern("d MMM yyyy H:m:s")).toString() ) );
                    break;
                case "E":
                    this.tasks.add(new Event(parsedFile.get(i)[2],parsedFile.get(i)[3]) );
                    break;
                default:
                    throw new DukeException("Error: File did not show task format.");
            }

            if(parsedFile.get(i)[1].equals("1")){
                this.tasks.get(this.tasks.size()-1).changeDoneTo(true);
            }

        }

    }


    public int getSize(){
        return this.tasks.size();
    }

    public Task get(int index){
        return this.tasks.get(index);
    }

    //throws exceptions.DukeException
    public void deleteFromList(int option)  {
        this.tasks.remove(option - 1);
    }

    //TODO: Need to check for copies
    //option 1 for tasks.Task, 2 for Todos, 3 for Events, 4 for Deadlines
    //throws exceptions.DukeException
    public void addToList(String taskType, String description, String secondPart) throws DukeException {
        int index = 0;
        int size = 0;

        //if(memo.contains(tasks.Task(input)) == true){
        //System.out.println("Your task is already in the memory.");
        //return;
        //}

        switch(taskType){
            case "task":
                this.tasks.add( new Task (description) );
                break;

            case "todo":
                this.tasks.add(new ToDo(description) );
                break;

            case "event":
                this.tasks.add( new Event(description, secondPart) );
                break;

            case "deadline":
                this.tasks.add(new Deadline(description, secondPart) );
                break;

            default:
                throw new DukeException("Error: Incorrect Task Type in addToList Method");
        }

    }

    public void findList(String keyword){
        int size = this.tasks.size();
        int total = 0;

        System.out.println(System.lineSeparator() + "Tasks that contain the keyword \"" + keyword + "\":");
        for(int i = 0; i < size; i ++){

            if(this.tasks.get(i).toString().contains(keyword)){
                System.out.println(System.lineSeparator() + (total+1) + "." + this.tasks.get(i).toString());
            }

        }
    }

    public void printList() {
        int size = 0;
        size = this.tasks.size();
        if(size == 0){
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Task List:");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + this.tasks.get(i).toString());
        }

    }

    public void printTotalTasks(){
        int total = tasks.size();
        String ending;
        if(total != 1){
            ending = " tasks in the list.";
        }else{
            ending = " task in the list";
        }

        System.out.println("Now you have " + total + ending);

    }

}
