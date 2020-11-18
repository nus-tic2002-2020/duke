import java.util.Scanner;

//taking input and giving responses
public class UI {

    public void read (TaskList tasks, Storage store){
        String input;
        Scanner in = new Scanner(System.in);
        int flag = 0;
      //  while (flag == 0) {
            String line = in.nextLine();
            input = line.trim();
            try {
                text(tasks, store, input);

            } catch (DukeException e) { //unknown input
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + line.trim() + " cannot be empty.");
            }
        }


    public static int text(TaskList tasks, Storage store, String input) throws DukeException {
        String[] num = input.split(" ");
        if (/*num.length > 0 && */!num[0].isBlank()) {
            String command = num[0];
            switch (command) {
                case "bye":
                    store.save(tasks);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                case "list":
                    tasks.printList();
                    break;
                case "done":
                    tasks.Taskdone(input);
                    break;
                case "todo":
                    tasks.AddTask(input);
                    break;
                case "deadline":
                    tasks.AddTask(input);
                    break;
                case "event":
                    tasks.AddTask(input);
                    break;
                case "delete":
                    tasks.AddTask(input);
                    break;
                default:
                    throw new DukeException();
            }
        }
        return 0;
    }

    public void saved_list(TaskList tasks, String input) {
        if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            tasks.AddTask(input);
        } else {
            tasks.Taskdone(input);
        }
    }
}
