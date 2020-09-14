import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int taskCounter = 0;
        Task[] tasks = new Task[100];

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
                for(int i = 0; i < taskCounter; i++){
                    System.out.println(String.format("%d.[%s] %s", i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription()));
                }
                continue;
            }else if(userInput.contains("done")){
                String[] doneTask = userInput.split(" ");
                tasks[Integer.parseInt(doneTask[1]) - 1].markAsDone();
                continue;
            }

            tasks[taskCounter] = new Task(userInput);
            System.out.println("added: " + tasks[taskCounter].getDescription());
            taskCounter++;
        }
    }
}
