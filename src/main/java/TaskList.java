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

    public Task at(int index){
        return this.list.get(index);
    }


    public void deleteFromList(int option) throws DukeException{
        this.list.remove(option - 1);
    }

    //TODO: Need to check for copies
    //option 1 for Task, 2 for Todos, 3 for Events, 4 for Deadlines
    public void addToList(String description, String secondPart, int option) throws DukeException{
        int index = 0;
        int size = 0;

        //if(memo.contains(Task(input)) == true){
        //System.out.println("Your task is already in the memory.");
        //return;
        //}

        switch(option){
            case 1:
                this.list.add( new Task (description) );
                break;

            case 2:
                //input = input.replaceFirst("todo", "").stripLeading();
                //if(input.isEmpty() == true){
                    //System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                    //throw new DukeException();
                    //return memo;
                }
                this.list.add(new ToDo(description) );
                break;

            case 3:
                //index = input.indexOf("/at");
                //secondPart = input.substring(index);
                //secondPart = secondPart.replaceFirst( "/at", "").stripLeading();

                //input = input.substring(0,index - 1);
                //input = input.replaceFirst("event", "").stripLeading();
                this.list.add( new Event(description, secondPart) );
                break;

            case 4:
                //index = input.indexOf("/by");
                //secondPart = input.substring(index);
                //secondPart = secondPart.replaceFirst( "/by", "").stripLeading();
                //input = input.substring(0,index - 1);
                //input = input.replaceFirst("deadline", "").stripLeading();
                this.list.add(new Deadline(description, secondPart) );
                break;
        }

        size = this.list.size();

        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + this.list.get(size - 1).toString());

        this.list.get(size - 1).printTotalTasks();
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
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Task List:");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + this.list.get(i).toString());
        }

    }

}
