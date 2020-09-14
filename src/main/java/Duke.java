import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int wordCounter = 0;
        String[] words = new String[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");

        while(true){
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            if(userInput.equals("bye") || userInput.equals("Bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(userInput.equals("list")){
                for(int i = 0; i < wordCounter; i++){
                    System.out.println((i + 1) + ". " + words[i]);
                }
                continue;
            }

            words[wordCounter] = userInput;
            System.out.println("added: " + words[wordCounter]);
            wordCounter++;
        }
    }
}
