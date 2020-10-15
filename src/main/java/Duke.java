import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


//TODO: Should refactor exceptions. Refactor addMemo[need to check for copies] and deleteMemo[need to reduce total task size when delete]

public class Duke {


    public static void command(String input, ArrayList<Task> memo) throws DukeException{
        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
        }

        switch(input){
            case "list":
                printMemo(memo);
                break;
            case "bye":
                System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
                break;
            default:
                if(input.contains("done")){
                    makeDone(input,memo);
                    break;
                }
                if(input.contains("todo")){
                    addMemo(input, memo,2);
                    break;
                }
                if(input.contains("event")){
                    addMemo(input, memo,3);
                    break;
                }
                if(input.contains("deadline")){
                    addMemo(input, memo,4);
                    break;
                }

                if(input.contains("delete")){
                    deleteMemo(input,memo);
                    break;
                }

                //default add task.
                addMemo(input, memo,1);

        }

        //return memo;
    }

    public static void deleteMemo(String input, ArrayList<Task> memo) throws DukeException{
        int option = 0;
        input = input.replaceFirst("delete", "").strip();
        if(input.isEmpty() == true){
            throw new DukeException();
        }

        try{
            option = Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("Please enter an integer for your delete command.");
        }


        memo.remove(option - 1);

        return;
    }


    //TODO: Need to check for copies
    //option 1 for Task, 2 for Todos, 3 for Events, 4 for Deadlines
    public static void addMemo(String input, ArrayList<Task> memo, int option) throws DukeException{

        String secondPart;
        int index = 0;
        int size = 0;

        //if(memo.contains(Task(input)) == true){
            //System.out.println("Your task is already in the memory.");
            //return;
        //}


        switch(option){
            case 1:
                memo.add( new Task (input) );
                break;

            case 2:
                input = input.replaceFirst("todo", "").stripLeading();
                if(input.isEmpty() == true){
                    System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                    throw new DukeException();
                    //return memo;
                }
                memo.add(new ToDo(input) );
                break;

            case 3:
                index = input.indexOf("/at");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/at", "").stripLeading();

                input = input.substring(0,index - 1);
                input = input.replaceFirst("event", "").stripLeading();
                memo.add( new Event(input, secondPart) );
                break;

            case 4:
                index = input.indexOf("/by");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/by", "").stripLeading();
                input = input.substring(0,index - 1);
                input = input.replaceFirst("deadline", "").stripLeading();
                memo.add(new Deadline(input, secondPart) );
                break;
        }

        size = memo.size();

        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + memo.get(size - 1).toString());

        memo.get(size - 1).printTotalTasks();
        return;
    }

    public static void printMemo(ArrayList<Task> memo){
        int size = memo.size();
        if(size == 0){
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Here are the tasks in your list");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + memo.get(i).toString());
        }

    }


    public static void makeDone(String input, ArrayList<Task> memo){
        int option = 0;
        option = Integer.parseInt(input.replace("done", "").trim());

        if(option <= memo.size() && option >= 1){
            memo.get(option - 1).changeCompletedTo(true);
            System.out.println(System.lineSeparator() + "Nice! I've marked this task as done:" + System.lineSeparator() + memo.get(option - 1).toString());

        }else{
            System.out.println("Input Invalid");
        }
        return;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ArrayList<Task> memo = new ArrayList<Task> ();

        String input;
        Scanner scan = new Scanner(System.in);


        int start = 1;

        while(start == 1){
            try{
                System.out.println(System.lineSeparator() + "What can I do for you?");
                input = scan.nextLine();
                if(input.equals("bye")){
                    start = 0;
                }
                command(input,memo);
            }
            catch (Exception ex){
                System.out.println("Please input again.");
            }

        }


    }
}
