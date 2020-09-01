import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String [] store = new String [100];
        String line;
        int i =0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            store[i] = line;


            if (line.equals("list")){
                for (int a = 0; a<i; a++){
                    System.out.println(a+1 + ". " + store[a]);
                }
            }
            i++;
            if (!line.equals("list")) {
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.print("Bye. Hope to see you again soon!");
    }
}
