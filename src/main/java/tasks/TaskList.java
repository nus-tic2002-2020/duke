package tasks;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Not sure about non-static methods

public class TaskList {

// contains the task list e.g., it has operations to add/delete tasks in the list

    protected ArrayList<Task> list;


    public TaskList(){
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<String[]> parsedFile){

        int size = parsedFile.size();

        for(int i = 0; i < size; i++){
            switch(parsedFile.get(i)[0]){
                case "O":
                    this.list.add(new Task(parsedFile.get(i)[2]));
                    break;
                case "T":
                    this.list.add(new ToDo(parsedFile.get(i)[2]));
                    break;
                case "D":
                    this.list.add(new Deadline(parsedFile.get(i)[2], LocalDateTime.parse(parsedFile.get(i)[3], DateTimeFormatter.ofPattern("d MMM yyyy H:m:s")).toString() ) );
                    break;
                case "E":
                    this.list.add(new Event(parsedFile.get(i)[2],parsedFile.get(i)[3]) );
                    break;
                default:
                    System.out.println("Error in loading file to list");
                    continue;

            }

            if(parsedFile.get(i)[1].equals("1")){
                this.list.get(this.list.size()-1).changeCompletedTo(true);
            }

        }

    }


    public int getSize(){
        return this.list.size();
    }

    public Task get(int index){
        return this.list.get(index);
    }

    //throws DukeException
    public void deleteFromList(int option)  {
        this.list.remove(option - 1);
    }

    //TODO: Need to check for copies
    //option 1 for tasks.Task, 2 for Todos, 3 for Events, 4 for Deadlines
    //throws DukeException
    public void addToList(String taskType, String description, String secondPart) {
        int index = 0;
        int size = 0;

        //if(memo.contains(tasks.Task(input)) == true){
        //System.out.println("Your task is already in the memory.");
        //return;
        //}

        switch(taskType){
            case "task":
                this.list.add( new Task (description) );
                break;

            case "todo":
                this.list.add(new ToDo(description) );
                break;

            case "event":
                this.list.add( new Event(description, secondPart) );
                break;

            case "deadline":
                this.list.add(new Deadline(description, secondPart) );
                break;
        }

        size = this.list.size();


    }

    public void findList(String keyword){
        int size = this.list.size();
        int total = 0;

        System.out.println(System.lineSeparator() + "Tasks that contain the keyword \"" + keyword + "\":");
        for(int i = 0; i < size; i ++){

            if(this.list.get(i).toString().contains(keyword)){
                System.out.println(System.lineSeparator() + (total+1) + "." + this.list.get(i).toString());
            }

        }
    }

    public void printList(){
        int size = this.list.size();
        if(size == 0){
            System.out.println(System.lineSeparator() + "tasks.Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "tasks.Task List:");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + this.list.get(i).toString());
        }

    }

}
