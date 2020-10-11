import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static boolean containsInMemo(String value, Task[] memo){
        int size = memo.length;
        for(int i = 0; i < size; i ++){
            if(memo[i].getDescription().equals(value)){
                return true;
            }
        }
        return false;
    }

    public static Task[] command(String input, Task[] memo) throws DukeException{
        if(input.equals("blah")){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new DukeException();
            //return memo;
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
                    memo = makeDone(input,memo);
                    break;
                }
                if(input.contains("todo")){
                    memo = addMemo(input, memo,2);
                    break;
                }
                if(input.contains("event")){
                    memo = addMemo(input, memo,3);
                    break;
                }
                if(input.contains("deadline")){
                    memo = addMemo(input, memo,4);
                    break;
                }

                memo = addMemo(input, memo,1);

        }

        return memo;
    }

    //option 1 for Task, 2 for Todos, 3 for Events, 4 for Deadlines
    public static Task[] addMemo(String input, Task[] memo, int option) throws DukeException{





        String secondPart;
        int index;
        int newSize = memo.length + 1;
        if(containsInMemo(input, memo)){
            System.out.println("Your task is already in the memory.");
            return memo;
        }
        memo = Arrays.copyOf(memo, newSize);

        switch(option){
            case 1:
                memo[newSize - 1] = new Task(input);
                break;

            case 2:
                input = input.replaceFirst("todo", "").stripLeading();
                if(input.isEmpty() == true){
                    System.out.println("☹ OOPS!!! The description of a Todo cannot be empty.");
                    throw new DukeException();
                    //return memo;
                }
                memo[newSize - 1] = new ToDo(input);
                break;

            case 3:
                index = input.indexOf("/at");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/at", "").stripLeading();

                input = input.substring(0,index - 1);
                input = input.replaceFirst("event", "").stripLeading();
                memo[newSize - 1] = new Event(input, secondPart);
                break;

            case 4:
                index = input.indexOf("/by");
                secondPart = input.substring(index);
                secondPart = secondPart.replaceFirst( "/by", "").stripLeading();
                input = input.substring(0,index - 1);
                input = input.replaceFirst("deadline", "").stripLeading();
                memo[newSize - 1] = new Deadline(input, secondPart);
                break;
        }


        System.out.println(System.lineSeparator() + "Got it. I've added this task:" +
                System.lineSeparator() + memo[newSize - 1].toString());

        memo[newSize - 1].printTotalTasks();
        return memo;
    }

    public static void printMemo(Task[] memo){
        int size = memo.length;
        if(size == 0){
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Here are the tasks in your list");
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + "." + memo[i].toString());
        }

    }


    public static Task[] makeDone(String input, Task[] memo){
        int option = 0;
        option = Integer.parseInt(input.replace("done", "").trim());

        if(option <= memo.length && option >= 1){
            memo[option - 1].changeCompletedTo(true);
            System.out.println(System.lineSeparator() + "Nice! I've marked this task as done:" + System.lineSeparator() + memo[option - 1].toString());

        }else{
            System.out.println("Input Invalid");
        }
        return memo;
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task[] memo = new Task[0];

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
                memo = command(input,memo);
            }
            catch (DukeException ex){
                System.out.println("Please input again.");
            }

        }


    }
}
