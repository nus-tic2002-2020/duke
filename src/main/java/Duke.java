import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/

        /* A greeting message */

        String logo = " ____  _      ,-.___,-.     \n"
                     + "|  _ \\|_|     \\_/_ _\\_/ \n"
                     + "| |_| | |       )O_O(      \n"
                     + "|  __/| |      { (_) }     \n"
                     + "|_|   |_|       `-^-'      \n";

        System.out.println("Hello,\n" + logo + "I'm Pi,\n" + "What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            loadFile(tasks);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ");

            if (input.equals("list")) {
                printList(tasks);
            } else if (input.contains("done")) {
                int finishedTask = Integer.valueOf(inputs[1]);
                tasks.get(finishedTask - 1).markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(finishedTask - 1).getTaskListInfo());

                try {
                    updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else if (input.contains("delete")) {
                int removedTask = Integer.valueOf(inputs[1]);
                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + tasks.get(removedTask - 1).getTaskListInfo());

                tasks.remove(removedTask - 1);

                System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

                try {
                    updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("☹ OOPS: " + exception.getMessage());
                }

            } else {
                try {
                    addTask(input, inputs, tasks);

                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

                    try {
                        updateFile(tasks);
                    } catch (IOException exception) {
                        System.out.println("☹ OOPS: " + exception.getMessage());
                    }

                } catch (DukeException exception) {
                    System.out.println(exception);
                }
            }
              input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }



    public static void updateFile(ArrayList<Task> tasks) throws IOException {
            FileWriter writer = new FileWriter("tasks.txt");
            writer.write(tasks.get(0).getTaskListInfo());
            writer.close();

            FileWriter appender = new FileWriter("tasks.txt", true);

            for (int i = 1; i < tasks.size(); i++) {
                appender.write(System.lineSeparator() + tasks.get(i).getTaskListInfo());
            }

            appender.close();

    }

    public static void loadFile (ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File ("tasks.txt");
        Scanner readFile = new Scanner (file);

        while (readFile.hasNext()) {
            String fileInput = readFile.nextLine();
            String inputs[] = fileInput.split(" \\| ");
            String taskType = inputs[0];
            String isDone = inputs[1];
            String description = inputs[2];


            switch (taskType){
            case "E":
                String timing = inputs[3];
                if (isDone.equals("0")) {
                    tasks.add(new Event(description, taskType, timing, false));
                } else {
                    tasks.add(new Event(description, taskType, timing, true));
                }

                break;
            case "D":
                String timeBy = inputs[3];
                if (isDone.equals("0")) {
                    tasks.add(new Deadline(description, taskType, timeBy, false));
                } else {
                    tasks.add(new Deadline(description, taskType, timeBy, true));
                }

                break;
            case "T":
                if (isDone.equals("0")) {
                    tasks.add(new ToDo(description, taskType, false));
                } else {
                    tasks.add(new ToDo(description, taskType, true));
                }
                break;
            }

            /*if (taskType.equals("D") || taskType.equals("E")) {
                String dateAndTime = inputs[3];
                if (taskType.equals("D")) {
                    tasks.add(new Deadline(description, dateAndTime));
                } else {
                    tasks.add(new Event(description, dateAndTime));
                }

            }*/

        }

    }



    /*Add new Task*/
    public static void addTask(String userInput,String[] inputs, ArrayList<Task> tasks) throws DukeException{

        if (inputs[0].startsWith("deadline")) {
            if (inputs.length == 1) {
                throw new DukeException(userInput);
            } else if (userInput.endsWith("/by")) {
                throw new DukeException(userInput);
            } else if ((inputs.length > 1) && (!userInput.contains("/by"))) {
                throw new DukeException(userInput);
            }

            int findPosition = userInput.indexOf("/by");
            String taskName = userInput.substring(9, findPosition);
            String taskTime = userInput.substring(findPosition + 4, userInput.length());
//            if (taskTime.equals(" ")) {
//                throw new DukeException("  ☹ OOPS!!! Please input a timing for this task");
//            }
            tasks.add(new Deadline(taskName, "D", taskTime));

        } else if (inputs[0].startsWith("event")) {
            if (inputs.length == 1){
                throw new DukeException(userInput);
            } else if (userInput.endsWith("/at")) {
                throw new DukeException(userInput);
            } else if ((inputs.length > 1) && (!userInput.contains("/at"))) {
                throw new DukeException(userInput);
            }

            int findPosition = userInput.indexOf("/at");

            String taskName = userInput.substring(6, findPosition);
            String taskTime = userInput.substring(findPosition + 4, userInput.length());

            tasks.add(new Event(taskName, "E", taskTime));

        } else if (inputs[0].startsWith("todo")) {
            if (inputs.length == 1) {
                throw new DukeException(userInput);
            }
            String taskName = userInput.substring(5);
            tasks.add(new ToDo(taskName,"T"));
        } else {
            throw new DukeException(userInput);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).getTaskListInfo());

    }


    /*print out the user input from the list*/
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        if (tasks.size() == 0){
            System.out.println("Currently your list is empty.");
        }

        for (int i = 0; i < tasks.size(); i++ ) {
            System.out.println((i + 1) + ". " + tasks.get(i).getTaskListInfo());
        }
    }


}
