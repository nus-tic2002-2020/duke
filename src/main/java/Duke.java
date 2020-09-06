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

    public static Task[] command(String input, Task[] memo){
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
                memo = addMemo(input, memo);
        }

        return memo;
    }

    public static Task[] addMemo(String newAddition, Task[] memo){
        int size = memo.length + 1;
        if(containsInMemo(newAddition, memo)){
            System.out.println("Your task is already in the memory.");
            return memo;
        }
        memo = Arrays.copyOf(memo, size);
        memo[size - 1] = new Task(newAddition);
        System.out.println(System.lineSeparator() + "added: " + newAddition);
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
            System.out.println(System.lineSeparator() + "What can I do for you?");
            input = scan.nextLine();
            if(input.equals("bye")){
                start = 0;
                break;
            }
            memo = command(input,memo);
        }


    }
}
