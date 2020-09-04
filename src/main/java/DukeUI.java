import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DukeUI {

    //VARIABLES-----------------------------------------
    //Characters to encase Duke's dialogue
    String DUKE_DIVIDER = "\t----------------------------------------------------------------------------";

    //Date formats to be used by Duke
    SimpleDateFormat TASK_DATE = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");
    SimpleDateFormat DATE_TODAY = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat DAY_TODAY = new SimpleDateFormat("EEEE");
    SimpleDateFormat YEAR_TODAY = new SimpleDateFormat("yyyy");
    SimpleDateFormat INPUT_DATE = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

    //List of commands for Duke
    String DUKE_COMMANDS =
                    "\t\t***New Note Commands***\n" +
                    "\t\t@bill            >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g.\t@bill <bill description>/<deadline>/<amount payable>\n" +
                    "\t\t@deadline        >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g.\t@deadline <todo description>/<deadline>\n" +
                    "\t\t@event           >>> Add a new event.\n" +
                    "\t\t\tE.g.\t@event <event description>/<from>/<to>\n" +
                    "\t\t@shoplist        >>> Add a new shopping list item.\n" +
                    "\t\t\tE.g.\t@shoplist <item description>/<item budget>\n" +
                    "\t\t@todo            >>> Add a new todo task without a deadline.\n" +
                    "\t\t\tE.g.\t@todo <todo description>\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t***Generic Commands***\n" +
                    "\t\t#commands        >>> List all available Duke's commands.\n" +
                    "\t\t#delete x        >>> Delete task x, then renumber the rest.\n" +
                    "\t\t#delete x/y/z    >>> Delete tasks x, y and z then renumber the rest.\n" +
                    "\t\t#listnotes       >>> List all the notes in memory.\n" +
                    "\t\t#markdone x      >>> Mark task x as done.\n" +
                    "\t\t#markdone x/y/z  >>> Mark tasks x, y and z as done.\n" +
                    "\t\t#quitduke        >>> Exit Project Duke.\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t***Data Entry Formats***\n" +
                    "\t\tBudget/Price     >>> $X.xx\n" +
                    "\t\tDate-Time        >>> dd-MMM-yyyy HH:mm\n" +
                    "\t\tDelimiter        >>> /\n" +
                    "\t\tTask Number      >>> x\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n";

    //Project Duke's opening logo
    String DUKE_LOGO =
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


    //METHODS-------------------------------------------
    static void printOnStartup(Date now) throws ParseException {
        System.out.print("\nWelcome to PROJECT >>>\n" + DUKE_LOGO);
        System.out.println(String.format("%1$-42s%2$42s", DATE_TODAY.format(now), DAY_TODAY.format(now)));
        System.out.println(String.format("%1$-42s%2$42s", ZodiacYear.getZodiacYear(now), SunSign.getSunSign(now)) + "\n");

        System.out.println(DUKE_DIVIDER);
        System.out.println("\tHello! I'm Duke, your all-rounded personal assistant!");
        System.out.println("\tWhat do you need done today?");
        System.out.println("\n" + DUKE_COMMANDS);
        System.out.println(DUKE_DIVIDER);
    }

    static void printCompleted() {
        System.out.println("\tYou have completed " + Todo.getTasksCompleted() +
                " task(s) and " + Event.getEventsCompleted() + " event(s)!");
    }

    static void printOutstanding() {
        System.out.println("\tYou have...");
        System.out.print("\t\t");
        System.out.print(String.format("%3d", Todo.getTasksOutstanding()));
        System.out.print(" outstanding task(s),\n");
        System.out.print("\t\t");
        System.out.print(String.format("%3d", Event.getEventsOutstanding()));
        System.out.print(" outstanding events(s), and\n");
        if(Budget.getIsTotalOverBudget()){
            System.out.print("\t\t  a budget overrun of $");
        } else {
            System.out.print("\t\t  a healthy budget balance of $");
        }
        System.out.println(String.format("%,14.2f", Math.abs(Budget.getTotalBudgetBalance())));
        System.out.println("\t\t\t\t\t\t\t\t...in your list.");
    }

    static void commandWrap(String input, int limit) {
        System.out.print("\t\t\"");
        while(true) {

            if (input.length() < limit) {
                System.out.print(input);
                System.out.print("\"\n\n");
                break;
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.substring(i, i + 1).equals(" ")) {
                        lastSpace = i;
                    }
                }
                System.out.print(input.substring(0, lastSpace) + "\n\t\t ");
                input = input.substring(lastSpace+1);
            }
        }
    }

    static void listWrap(String input, int limit, Date addDate) {

        boolean firstLine = true;
        while(true) {

            if (input.length() < limit) {
                if(firstLine) {
                    System.out.println(String.format("%1$-30s%2$29s",
                            input, "Added: " + TASK_DATE.format(addDate)));
                    break;
                } else {
                    System.out.println("\t\t\t\t " + input);
                    break;
                }
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.substring(i, i + 1).equals(" ")) {
                        lastSpace = i;
                    }
                }
                if(firstLine) {
                    System.out.println(String.format("%1$-30s%2$29s",
                            input.substring(0, lastSpace), "Added: " +
                                    TASK_DATE.format(addDate)));
                    input = input.substring(lastSpace + 1);
                    firstLine = false;

                } else {
                    System.out.println("\t\t\t\t " + input.substring(0, lastSpace));
                    input = input.substring(lastSpace + 1);
                }
            }
        }
    }
}
