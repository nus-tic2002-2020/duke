import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Characters to encase Duke's dialogue
        String dukeDivider = "\t----------------------------------------------------------------------------";

        //Date formats
        Date now = new Date();
        SimpleDateFormat dateToday = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat dayToday = new SimpleDateFormat("EEEE");
        SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");

        //List of commands for Duke
        String dukeCommands =
                "\t\t#commands     >>> List Duke's commands.\n" +
                "\t\t#listtask     >>> List all the tasks in memory.\n" +
                "\t\t#markdone X   >>> Mark task X as done.\n" +
                "\t\t#quitduke     >>> Exit Project Duke.\n" +
                "\t\t\t......more coming soon!";

        //Project Duke's opening logo
        String logo =
                "====================================================================================\n" +
                "====================================================================================\n" +
                "===  DDDDDDDDD     UUU      UUU  KKK      KKK  EEEEEEEEEEEE            A0177803Y ===\n" +
                "===  DDDDDDDDDD    UUU      UUU  KKK     KKK   EEEEEEEEEEE                       ===\n" +
                "===  DDD     DDD   UUU      UUU  KKK    KKK    EEE                               ===\n" +
                "===  DDD      DDD  UUU      UUU  KKK   KKK     EEEEEEEEEE                        ===\n" +
                "===  DDD      DDD  UUU      UUU  KKK  KKK      EEEEEEEEEE                        ===\n" +
                "===  DDD      DDD  UUU      UUU  KKKKKKK       EEE                               ===\n" +
                "===  DDD      DDD  UUU      UUU  KKKKKK        EEE           NOTEPAD,            ===\n" +
                "===  DDD     DDD   UUU      UUU  KKK KKKK      EEE           TASK MANAGER,       ===\n" +
                "===  DDDDDDDDDD     UUUUUUUUUU   KKK   KKKK    EEEEEEEEEEE   BUDGET ASSISTANT,   ===\n" +
                "===  DDDDDDDDD       UUUUUUUU    KKK     KKKK  EEEEEEEEEEEE  AND MORE...!        ===\n" +
                "====================================================================================\n" +
                "====================================================================================\n";
        System.out.print("\nWelcome to PROJECT >>>\n" + logo);
        System.out.print(String.format("%1$-42s%2$42s", dateToday.format(now), dayToday.format(now)) + "\n\n");

        System.out.println(dukeDivider);
        System.out.println("\tHello! I'm Duke, your all-rounded personal assistant!");
        System.out.println("\tWhat do you need done today?");
        System.out.println("\n" + dukeCommands);
        System.out.println(dukeDivider);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            if (input.substring(0,1).equals("#")){   //commands section
                if(input.substring(0,9).equals("#commands")) { //List Commands
                    System.out.println(dukeDivider);
                    System.out.println(dukeCommands);
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#listtask")) { //List tasks
                    System.out.println(dukeDivider);
                    System.out.println("\tHere are the things you told me to note:-");
                    for(int i = 0; i < taskCount; i++) {
                        System.out.print("\t" + (i + 1) + ". ");
                        System.out.print(tasks[i].getStatusIcon() + " ");
                        System.out.println(String.format("%1$-35s%2$34s",
                                tasks[i].getDescription(), "Added: " +
                                        taskDate.format(tasks[i].getAddDate())));

                        if(tasks[i].isDone){
                            System.out.println("\t\t\tDone: " +
                                    taskDate.format(tasks[i].getDoneDate()));
                        }
                    }
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#markdone")) { //Mark tasks as done
                    int taskIndex = Integer.parseInt(input.substring(10,input.length())) - 1;
                    System.out.println(dukeDivider);
                    Date doneDate = new Date();
                    tasks[taskIndex].markAsDone(doneDate);
                    System.out.print("\t" + (taskIndex + 1) + ". ");
                    System.out.print(tasks[taskIndex].getStatusIcon() + " ");
                    System.out.println(String.format("%1$-35s%2$34s",
                            tasks[taskIndex].getDescription(), "Added: " +
                                    taskDate.format(tasks[taskIndex].getAddDate())));
                    System.out.println("\t\t\tDone: " +
                            taskDate.format(tasks[taskIndex].getDoneDate()));
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#quitduke")) { //Quit Duke
                    String confirmQuit;
                    Scanner quitDuke = new Scanner(System.in);

                    System.out.println(dukeDivider);
                    System.out.println("\tAre you sure you want to quit?");
                    System.out.println("\tAll data would be lost.");
                    System.out.println("\tReply \"Y\" to confirm.");
                    System.out.println(dukeDivider);

                    confirmQuit = quitDuke.nextLine();

                    if(confirmQuit.equals("Y")) {
                        System.out.println(dukeDivider);
                        System.out.println("\tGood Bye! Hope to see you again soon!");
                        System.out.println(dukeDivider);
                        break;
                    } else {
                        System.out.println(dukeDivider);
                        System.out.println("\tYay! Thanks for staying!");
                        System.out.println(dukeDivider);
                        continue;
                    }
                }

            } else {    //treat all other texts as new tasks
                System.out.println(dukeDivider);
                Date addDate = new Date();
                Task t = new Task(input, addDate);
                tasks[taskCount] = t;
                System.out.println(String.format("%1$-38s%2$39s", "\tAdded: " +
                                tasks[taskCount].getDescription(),
                                taskDate.format(tasks[taskCount].getAddDate())));
                taskCount++;
                System.out.println(dukeDivider);
            }
        }
    }
}
