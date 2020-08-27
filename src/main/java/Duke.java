import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws ParseException {
        //Characters to encase Duke's dialogue
        String dukeDivider = "\t----------------------------------------------------------------------------";

        //Date formats
        Date now = new Date();
        SimpleDateFormat dateToday = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat dayToday = new SimpleDateFormat("EEEE");
        SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");
        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

        //List of commands for Duke
        String dukeCommands =
                "\t\t***New Note Commands***\n" +
                "\t\t@deadline     >>> Add a new task with a deadline.\n" +
                "\t\t\tE.g. \"@deadline <task description>/<deadline>\"\n" +
                "\t\t@event        >>> Add a new event.\n" +
                "\t\t\tE.g. \"@event <event description>/<from>/<to>\"\n" +
                "\t\t@shoplist     >>> Add a new shopping list item.\n" +
                "\t\t\tE.g. \"@shoplist <item description>/<item budget>\"\n" +
                "\t\t@task         >>> Add a new task without a deadline.\n" +
                "\t\t\tE.g. \"@task <task description>\"\n" +
                "\t\t********************************************************************" +
                "\n" +
                "\t\t***Generic Commands***\n" +
                "\t\t#commands     >>> List all available Duke's commands.\n" +
                "\t\t#listnotes    >>> List all the notes in memory.\n" +
                "\t\t#markdone X   >>> Mark note X as done.\n" +
                "\t\t#quitduke     >>> Exit Project Duke.\n" +
                "\t\t********************************************************************" +
                "\n" +
                "\t\t***Data Entry Formats***\n" +
                "\t\tDate-Time     >>> dd-MMM-yyyy HH:mm\n" +
                "\t\tPrice         >>> $X.xx\n" +
                "\t\t********************************************************************";

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
                "===  DDD      DDD  UUU      UUU  KKKKKK        EEE           NOTE KEEPER,        ===\n" +
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

            if (input.substring(0,1).equals("#")){   //Generic Commands
                if(input.substring(0,9).equals("#commands")) { //List all available Duke's commands.
                    System.out.println(dukeDivider);
                    System.out.println(dukeCommands);
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#markdone")) { //Mark a note as done.
                    int taskIndex = Integer.parseInt(input.substring(10)) - 1;
                    Date doneDate = new Date();


                    if(tasks[taskIndex] instanceof Shoplist) {
                        String inputPrice;
                        Scanner markdone = new Scanner(System.in);

                        System.out.println(dukeDivider);
                        System.out.println("\tWhat is the price you paid for " +
                                tasks[taskIndex].getDescription() + "?");
                        System.out.println(dukeDivider);

                        inputPrice = markdone.nextLine();
                        Double itemPrice = Double.parseDouble(inputPrice.substring(1));
                        tasks[taskIndex].markAsDone(doneDate, itemPrice);

                    } else {
                        tasks[taskIndex].markAsDone(doneDate);
                    }

                    System.out.println(dukeDivider);
                    System.out.print("\t" + (taskIndex + 1) + ". ");
                    System.out.print(tasks[taskIndex].getTaskIcon());
                    System.out.print(tasks[taskIndex].getStatusIcon() + " ");
                    System.out.println(String.format("%1$-35s%2$34s",
                            tasks[taskIndex].getDescription(), "Added: " +
                                    taskDate.format(tasks[taskIndex].getAddDate())));

                    if(tasks[taskIndex] instanceof Event) {
                        System.out.println("\t\t\tFrom     : " +
                                taskDate.format(tasks[taskIndex].getStartDate()));
                        System.out.println("\t\t\tTo       : " +
                                taskDate.format(tasks[taskIndex].getEndDate()));
                        System.out.println("\t\t\tDone     : " +
                                taskDate.format(tasks[taskIndex].getDoneDate()));
                    } else if(tasks[taskIndex] instanceof Deadline) {
                        System.out.println("\t\t\tDeadline : " +
                                taskDate.format(tasks[taskIndex].getTargetDate()));
                        System.out.println("\t\t\tDone     : " +
                                taskDate.format(tasks[taskIndex].getDoneDate()) + " " +
                                tasks[taskIndex].getDoneAhead());
                    } else if(tasks[taskIndex] instanceof Shoplist) {
                        System.out.println("\t\t\tBudget   : $" +
                                String.format("%10.2f", tasks[taskIndex].getItemBudget()));
                        System.out.println("\t\t\tActual   : $" +
                                String.format("%10.2f", tasks[taskIndex].getItemPrice()) + " " +
                                tasks[taskIndex].getWithinBudget());
                        System.out.println("\t\t\tDone     : " +
                                taskDate.format(tasks[taskIndex].getDoneDate()));
                    }
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#quitduke")) { //Exit Project Duke.
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

                } else if(input.substring(0,10).equals("#listnotes")) { //List all the notes in memory.
                    System.out.println(dukeDivider);
                    System.out.println("\tHere are the things you told me to note:-");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.print("\t" + (i + 1) + ". ");
                        System.out.print(tasks[i].getTaskIcon());
                        System.out.print(tasks[i].getStatusIcon() + " ");
                        System.out.println(String.format("%1$-35s%2$34s",
                                tasks[i].getDescription(), "Added: " +
                                taskDate.format(tasks[i].getAddDate())));

                        if(tasks[i] instanceof Event) {
                            System.out.println("\t\t\tFrom     : " +
                                    taskDate.format(tasks[i].getStartDate()));
                            System.out.println("\t\t\tTo       : " +
                                    taskDate.format(tasks[i].getEndDate()));
                            if (tasks[i].isDone) {
                                System.out.println("\t\t\tDone     : " +
                                        taskDate.format(tasks[i].getDoneDate()));
                            }
                        } else if(tasks[i] instanceof Deadline) {
                            System.out.println("\t\t\tDeadline : " +
                                    taskDate.format(tasks[i].getTargetDate()));
                            if (tasks[i].isDone) {
                                System.out.println("\t\t\tDone     : " +
                                        taskDate.format(tasks[i].getDoneDate()) + " " +
                                        tasks[i].getDoneAhead());
                            }
                        } else if(tasks[i] instanceof Shoplist) {
                            System.out.println("\t\t\tBudget   : $" +
                                    String.format("%10.2f", tasks[i].getItemBudget()));
                            if (tasks[i].isDone) {
                                System.out.println("\t\t\tActual   : $" +
                                        String.format("%10.2f", tasks[i].getItemPrice()) + " " +
                                        tasks[i].getWithinBudget());
                                System.out.println("\t\t\tDone     : " +
                                        taskDate.format(tasks[i].getDoneDate()));
                            }
                        } else if (tasks[i].isDone) {
                            System.out.println("\t\t\tDone     : " +
                                    taskDate.format(tasks[i].getDoneDate()));
                        }
                    }
                    System.out.println(dukeDivider);
                }

            } else if (input.substring(0,1).equals("@")) { //New Note Commands
                Date addDate = new Date();

                if(input.substring(0,5).equals("@task")) { //Add a new task without a deadline.
                    System.out.println(dukeDivider);
                    Task t = new Todo(input.substring(6), addDate);
                    tasks[taskCount] = t;
                    System.out.println(String.format("%1$-38s%2$39s", "\tAdded: " +
                            tasks[taskCount].getTaskIcon() + " " +
                            tasks[taskCount].getDescription(),
                            taskDate.format(tasks[taskCount].getAddDate())));
                    taskCount++;
                    System.out.println(dukeDivider);

                } else if(input.substring(0,6).equals("@event")){ //Add a new event.
                    input = input.substring(7);
                    String[] inputTokens = input.split("/", 3);
                    String description = inputTokens[0];
                    Date startDate = inputDate.parse(inputTokens[1]);
                    Date endDate = inputDate.parse(inputTokens[2]);

                    System.out.println(dukeDivider);
                    Task t = new Event(description, startDate, endDate, addDate);
                    tasks[taskCount] = t;
                    System.out.println(String.format("%1$-38s%2$39s", "\tAdded: " +
                            tasks[taskCount].getTaskIcon() + " " +
                            tasks[taskCount].getDescription() + " (" +
                            tasks[taskCount].getDurationMinutes() + "mins)",
                            taskDate.format(tasks[taskCount].getAddDate())));
                    System.out.println("\t\t\tFrom     : " +
                            taskDate.format(tasks[taskCount].getStartDate()));
                    System.out.println("\t\t\tTo       : " +
                            taskDate.format(tasks[taskCount].getEndDate()));
                    taskCount++;
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("@deadline")){ //Add a new task with a deadline.
                    input = input.substring(10);
                    String[] inputTokens = input.split("/",2);
                    String description = inputTokens[0];
                    Date targetDate = inputDate.parse(inputTokens[1]);

                    System.out.println(dukeDivider);
                    Task t = new Deadline(description, targetDate, addDate);
                    tasks[taskCount] = t;
                    System.out.println(String.format("%1$-38s%2$39s", "\tAdded: " +
                            tasks[taskCount].getTaskIcon() + " " +
                            tasks[taskCount].getDescription(),
                            taskDate.format(tasks[taskCount].getAddDate())));
                    System.out.println("\t\t\tDeadline : " +
                            taskDate.format(tasks[taskCount].getTargetDate()));
                    taskCount++;
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("@shoplist")){ //Add a new shopping list item.
                    input = input.substring(10);
                    String[] inputTokens = input.split("/\\$",2);
                    String description = inputTokens[0];
                    Double itemBudget = Double.parseDouble(inputTokens[1]);

                    System.out.println(dukeDivider);
                    Task t = new Shoplist(description, itemBudget, addDate);
                    tasks[taskCount] = t;
                    System.out.println(String.format("%1$-38s%2$39s", "\tAdded: " +
                            tasks[taskCount].getTaskIcon() + " " +
                            tasks[taskCount].getDescription(),
                            taskDate.format(tasks[taskCount].getAddDate())));
                    System.out.print("\t\t\tBudget   : $");
                    System.out.println(String.format("%10.2f", tasks[taskCount].getItemBudget()));
                    taskCount++;
                    System.out.println(dukeDivider);

                }
            }
        }
    }
}
