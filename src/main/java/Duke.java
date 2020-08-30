import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static boolean contains(String value, String[] memo){
        int size = memo.length;
        for(int i = 0; i < size; i ++){
            if(memo[i].equals(value)){
                return true;
            }
        }
        return false;
    }

    public static String[] command(String input, String[] memo){
        switch(input){
            case "list":
                printMemo(memo);
                break;
            case "bye":
                System.out.println(System.lineSeparator() + "Bye. Hope to see you again soon!");
                break;
            default:
                memo = addMemo(input, memo);
        }

        return memo;
    }

    public static String[] addMemo(String newAddition, String[] memo){
        int size = memo.length + 1;
        if(contains(newAddition, memo)){
            System.out.println("Your phrase is already in the memory.");
            return memo;
        }
        memo = Arrays.copyOf(memo, size);
        memo[size - 1] = newAddition;
        System.out.println(System.lineSeparator() + "added: " + newAddition);
        return memo;
    }

    public static void printMemo(String[] memo){
        int size = memo.length;
        if(size == 0){
            System.out.println(System.lineSeparator() + "List is empty.");
            return;
        }
        for(int i = 0; i < size; i ++){
            System.out.println(System.lineSeparator() + (i+1) + ". " + memo[i]);
        }

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String[] memo = new String[0];

        String input;
        Scanner scan = new Scanner(System.in);


        int start = 1;

        while(start == 1){
            System.out.println(System.lineSeparator() + "What can I do for you?");
            input = scan.nextLine();
            if(input.equals("bye")){
                start = 0;
            }
            memo = command(input,memo);
        }


    }
}
