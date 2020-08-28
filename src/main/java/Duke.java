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
                "\t\t***New Task Commands***\n" +
                "\t\t@deadline     >>> Add a new todo task with a deadline.\n" +
                "\t\t\tE.g. \"@deadline <todo description>/<deadline>\"\n" +
                "\t\t@event        >>> Add a new event.\n" +
                "\t\t\tE.g. \"@event <event description>/<from>/<to>\"\n" +
                "\t\t@shoplist     >>> Add a new shopping list item.\n" +
                "\t\t\tE.g. \"@shoplist <item description>/<item budget>\"\n" +
                "\t\t@todo         >>> Add a new todo task without a deadline.\n" +
                "\t\t\tE.g. \"@todo <todo description>\"\n" +
                "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "\n\n" +
                "\t\t***Generic Commands***\n" +
                "\t\t#commands     >>> List all available Duke's commands.\n" +
                "\t\t#listnotes    >>> List all the notes in memory.\n" +
                "\t\t#markdone X   >>> Mark note X as done.\n" +
                "\t\t#quitduke     >>> Exit Project Duke.\n" +
                "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "\n\n" +
                "\t\t***Data Entry Formats***\n" +
                "\t\tDate-Time     >>> dd-MMM-yyyy HH:mm\n" +
                "\t\tBudget/Price  >>> $X.xx\n" +
                "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "\n";

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
        int taskOutstanding = 0;

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
                        Scanner markDone = new Scanner(System.in);

                        System.out.println(dukeDivider);
                        System.out.println("\tWhat is the price you paid for " +
                                tasks[taskIndex].getDescription() + "?");
                        System.out.println(dukeDivider);

                        inputPrice = markDone.nextLine();
                        Double itemPrice = Double.parseDouble(inputPrice.substring(1));
                        tasks[taskIndex].markAsDone(doneDate, itemPrice);

                    } else {
                        tasks[taskIndex].markAsDone(doneDate);
                    }

                    System.out.println(dukeDivider);
                    System.out.println("\tNoted! I've marked Task #" + (taskIndex + 1) + " as done.");
                    System.out.print("\t" + String.format("%3d", (taskIndex + 1)) + ". ");
                    tasks[taskIndex].printList();
                    taskOutstanding--;
                    System.out.println("\tYou have " + taskOutstanding +
                            " outstanding task(s) in your list.");
                    System.out.println("\tEnter command \"#listnotes\" to see them all.");
                    System.out.println(dukeDivider);

                } else if(input.substring(0,9).equals("#quitduke")) { //Exit Project Duke.
                    String confirmQuit;
                    Scanner quitDuke = new Scanner(System.in);

                    System.out.println(dukeDivider);
                    System.out.println("\tYou have " + taskOutstanding +
                            " outstanding task(s) in your list.");
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
                        System.out.print("\t" + String.format("%3d", (i + 1)) + ". ");
                        tasks[i].printList();
                    }
                    System.out.println("\tYou have " + taskOutstanding +
                            " outstanding task(s) in your list.");
                    System.out.println("\tEnter command \"#listnotes\" to see them all.");
                    System.out.println(dukeDivider);
                }

            } else if (input.substring(0,1).equals("@")) { //New Note Commands
                Date addDate = new Date();
                System.out.println(dukeDivider);

                if(input.substring(0,5).equals("@todo")) { //Add a new todo task without a deadline.
                    Task t = new Todo(input.substring(6), addDate);
                    tasks[taskCount] = t;
                    System.out.println("\tNoted! I've added a new todo task to the list.");

                } else if(input.substring(0,6).equals("@event")){ //Add a new event.
                    input = input.substring(7);
                    String[] inputTokens = input.split("/", 3);
                    String description = inputTokens[0];
                    Date startDate = inputDate.parse(inputTokens[1]);
                    Date endDate = inputDate.parse(inputTokens[2]);

                    Task t = new Event(description, startDate, endDate, addDate);
                    tasks[taskCount] = t;
                    System.out.println("\tNoted! I've added a new event to the list.");

                } else if(input.substring(0,9).equals("@deadline")){ //Add a new todo task with a deadline.
                    input = input.substring(10);
                    String[] inputTokens = input.split("/",2);
                    String description = inputTokens[0];
                    Date targetDate = inputDate.parse(inputTokens[1]);

                    Task t = new Deadline(description, targetDate, addDate);
                    tasks[taskCount] = t;
                    System.out.println("\tNoted! I've added a new deadline task to the list.");

                } else if(input.substring(0,9).equals("@shoplist")){ //Add a new shopping list item.
                    input = input.substring(10);
                    String[] inputTokens = input.split("/\\$",2);
                    String description = inputTokens[0];
                    Double itemBudget = Double.parseDouble(inputTokens[1]);

                    Task t = new Shoplist(description, itemBudget, addDate);
                    tasks[taskCount] = t;
                    System.out.println("\tNoted! I've added a new shopping item to the list.");
                }

                System.out.print("\t" + String.format("%3d", (taskCount + 1)) + ". ");
                tasks[taskCount].printList();
                taskCount++;
                taskOutstanding++;
                System.out.println("\tYou have " + taskOutstanding + " outstanding task(s) in your list.");
                System.out.println("\tEnter command \"#listnotes\" to see them all.");
                System.out.println(dukeDivider);
            }
        }
    }
}
