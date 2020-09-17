package duke.ui;

import duke.LunarMonth;
import duke.MercuryMovement;
import duke.SunSign;
import duke.ZodiacYear;
import duke.budget.Budget;
import duke.commands.CmdType;
import duke.commands.CommandException;
import duke.notes.NoteType;
import duke.notes.event.Event;
import duke.notes.todo.Todo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public interface DukeUI {

    //VARIABLES-----------------------------------------
    //Characters to encase Duke's dialogue
    String DUKE_DIVIDER = "\t----------------------------------------------------------------------------";

    //Date formats to be used by Duke
    SimpleDateFormat TASK_TIME = new SimpleDateFormat("dd-MMM-yyyy (E), hh:mm a");
    SimpleDateFormat TASK_DATE = new SimpleDateFormat("dd-MMM-yyyy (E)");
    SimpleDateFormat DATE_TODAY = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat DAY_TODAY = new SimpleDateFormat("EEEE");
    SimpleDateFormat YEAR_TODAY = new SimpleDateFormat("yyyy");
    SimpleDateFormat INPUT_TIME = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    SimpleDateFormat INPUT_DATE = new SimpleDateFormat("dd-MMM-yyyy");

    //List of commands for Duke
    String DUKE_COMMANDS =
                    "\t\t*** Generic Commands ***\n" +
                    "\t\t#commands        >>> List all available Duke's commands.\n" +
                    "\t\t\tE.g. #commands\n" +
                    "\t\t#delete          >>> Delete notes, then renumber the rest.\n" +
                    "\t\t\tE.g. #delete <Note#>\n" +
                    "\t\t\tE.g. #delete <Note#> /and <Note#> /and <Note#> \n" +
                    "\t\t#editdate        >>> Edit the dates of a note.\n" +
                    "\t\t\tE.g. #editdate <Note#> <Date Type> /to <New Date-Time>\n" +
                    "\t\t#editdesc        >>> Edit the description of a note.\n" +
                    "\t\t\tE.g. #editdesc <Note#> /to <New Description>\n" +
                    "\t\t#exitduke        >>> Exit Project Duke.\n" +
                    "\t\t\tE.g. #exitduke\n" +
                    "\t\t#extend          >>> Extend a deadline by days, hours and minutes.\n" +
                    "\t\t\tE.g. #extend <Note#> /by <days>d <hours>h <minutes>m\n" +
                    "\t\t#markdone        >>> Mark notes as done.\n" +
                    "\t\t\tE.g. #markdone <Note#>\n" +
                    "\t\t\tE.g. #markdone <Note#> /and <Note#> /and <Note#>\n" +
                    "\t\t#savenotes       >>> Perform a new save while archiving the last.\n" +
                    "\t\t\tE.g. #savenotes\n" +
                    "\t\t#transfer        >>> Transfer budgets from one note to another.\n" +
                    "\t\t\tE.g. #transfer /from <Note#> /to <Note#> /for <Budget/Price>\n" +
                    "\t\t#wipeduke        >>> Wipe all of Duke's memories and files.\n" +
                    "\t\t\tE.g. #wipeduke\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t*** New Note Creation ***\n" +
                    "\t\t@bill      / @bl >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g. @bill <Description> /by <Date-Time> /for <Budget/Price>\n" +
                    "\t\t@birthday  / @bd >>> Add a new birthday event.\n" +
                    "\t\t\tE.g. @birthday <Person> /from <Date-Time> /to <Date-Time>\n" +
                    "\t\t\t\t/for <Budget/Price>\n" +
                    "\t\t@deadline  / @dl >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g. @deadline <Description> /by <Date-Time>\n" +
                    "\t\t@event     / @ev >>> Add a new event.\n" +
                    "\t\t\tE.g. @event <Description> /from <Date-Time> /to <Date-Time>\n" +
                    "\t\t@shoplist  / @sl >>> Add a new shopping list item.\n" +
                    "\t\t\tE.g. @shoplist <Description> /for <Budget/Price>\n" +
                    "\t\t@todo      / @td >>> Add a new todo task without a deadline.\n" +
                    "\t\t\tE.g. @todo <Description>\n" +
                    "\t\t@wedding   / @wd >>> Add a new todo task without a deadline.\n" +
                    "\t\t\tE.g. @wedding <Person>/from <Date-Time> /to <Date-Time>\n" +
                    "\t\t\t\t/for <Budget/Price>\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t*** Information Extraction ***\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\t\t\t  Note-Filters:   O = Outstanding; C = Completed; A = All.\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\t\t#listbills       >>> List in order of budget, bill payments.\n" +
                    "\t\t\tE.g. #listbills <Note-Filter>\n" +
                    "\t\t\tE.g. #listbills <Note-Filter> /on <Date>\n" +
                    "\t\t#listbirthdays   >>> List in chronological order, birthdays.\n" +
                    "\t\t\tE.g. #listbirthdays <Note-Filter>\n" +
                    "\t\t\tE.g. #listbirthdays <Note-Filter> /on <Date>\n" +
                    "\t\t#listbudgets     >>> List in order of quantum, budgets set.\n" +
                    "\t\t\tE.g. #listbudgets <Note-Filter>\n" +
                    "\t\t#listdeadlines   >>> List in chronological order, deadlines.\n" +
                    "\t\t\tE.g. #listdeadlines <Note-Filter>\n" +
                    "\t\t\tE.g. #listdeadlines <Note-Filter> /on <Date>\n" +
                    "\t\t#listevents      >>> List in chronological order, events.\n" +
                    "\t\t\tE.g. #listevents <Note-Filter>\n" +
                    "\t\t\tE.g. #listevents <Note-Filter> /on <Date>\n" +
                    "\t\t#listnotes       >>> List in serial number order, notes.\n" +
                    "\t\t\tE.g. #listnotes <Note-Filter>\n" +
                    "\t\t#listnxt24       >>> List deadlines and events in the next 24 hours.\n" +
                    "\t\t\tE.g. #listnxt24 <Note-Filter>\n" +
                    "\t\t#listnxt48       >>> List deadlines and events in the next 48 hours.\n" +
                    "\t\t\tE.g. #listnxt48 <Note-Filter>\n" +
                    "\t\t#listnxt72       >>> List deadlines and events in the next 72 hours.\n" +
                    "\t\t\tE.g. #listnxt72 <Note-Filter>\n" +
                    "\t\t#listshoplists   >>> List in order of budget, shopping list items.\n" +
                    "\t\t\tE.g. #listshoplists <Note-Filter>\n" +
                    "\t\t#listtodos       >>> List in serial number order, todo tasks.\n" +
                    "\t\t\tE.g. #listtodos <Note-Filter>\n" +
                    "\t\t#listweddings    >>> List in chronological order, weddings.\n" +
                    "\t\t\tE.g. #listweddings <Note-Filter>\n" +
                    "\t\t\tE.g. #listweddings <Note-Filter> /on <Date>\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t*** Data Entry Formats ***\n" +
                    "\t\tBudget/Price     >>> $X.xx\n" +
                    "\t\tDate             >>> dd-MMM-yyyy\n" +
                    "\t\tDate Type        >>> target, start or end\n" +
                    "\t\tDate-Time        >>> dd-MMM-yyyy HH:mm\n" +
                    "\t\tNote#            >>> x\n" +
                    "\t\tNote-Filter      >>> O, C or A\n" +
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
    static void addConfirm(String typeAdded) throws CommandException {
        System.out.println("\tNoted! I've added a new "
                + NoteType.getLowercaseName(typeAdded) + " to the list.");
    }

    static void askForConfirmation() {
        System.out.println("\tReply \"Y\" to confirm or any other character(s) to abort.");
    }

    static void autoSaveConfirmation(boolean isSuccessful) {
        System.out.print(String.format("%78s", "Auto-Save "));
        if(isSuccessful){
            System.out.println("\u2615");
        } else {
            System.out.println("\u26D4");
        }
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
                    System.out.println(String.format("%1$-29s%2$29s",
                            input, "Added: " + TASK_TIME.format(addDate)));
                } else {
                    System.out.println("\t\t\t\t\t" + input);
                }
                break;
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.substring(i, i + 1).equals(" ")) {
                        lastSpace = i;
                    }
                }
                if(firstLine) {
                    System.out.println(String.format("%1$-29s%2$29s",
                            input.substring(0, lastSpace), "Added: " +
                                    TASK_TIME.format(addDate)));
                    input = input.substring(lastSpace + 1);
                    firstLine = false;

                } else {
                    System.out.println("\t\t\t\t\t" + input.substring(0, lastSpace));
                    input = input.substring(lastSpace + 1);
                }
            }
        }
    }

    static void printCompleted() {
        System.out.println("\tYou have completed " + Todo.getTasksCompleted() +
                " task(s) and " + Event.getEventsCompleted() + " event(s)!");
    }

    static void printDivider() {
        System.out.println(DUKE_DIVIDER);
    }

    static void printOutstanding() {
        System.out.println("\tYou have...");
        System.out.print("\t\t");
        System.out.print(String.format("%3d", Todo.getTasksOutstanding()));
        System.out.print(" outstanding task(s),\n");
        System.out.print("\t\t");
        System.out.print(String.format("%3d", Event.getEventsOutstanding()));
        System.out.print(" outstanding events(s), and\n");
        Budget.printBudgetReport();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t...on your list.");
    }

    static void printOnStartup(Date now, boolean isLoadedFromFile) throws ParseException, CommandException {
        System.out.print("\nWelcome to PROJECT >>>\n" + DUKE_LOGO);
        System.out.println(String.format("%1$-42s%2$42s", DATE_TODAY.format(now), DAY_TODAY.format(now)));
        System.out.println(String.format("%1$-42s%2$42s", ZodiacYear.getZodiacYear(now), SunSign.getSunSign(now)));
        System.out.println(String.format("%1$-42s%2$42s", LunarMonth.getLunarMonth(now), MercuryMovement.getMercuryMovement(now)) + "\n");

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        printDivider();
        System.out.println("\tHello! I'm Duke, your all-rounded personal assistant!");
        System.out.println("\tWhat do you need noted today?\n");
        showCommandList();
        printDivider();

        if(isLoadedFromFile){
            System.out.println("\tSaved notes were found and loaded.");
            printCompleted();
            printOutstanding();
            suggestListNotes();
        }else {
            System.out.println("\tNo saved notes were found.");
        }
        printDivider();
    }

    static String receiveCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static void showCommandList() {
        System.out.println(DUKE_COMMANDS);
    }

    static void suggestCommands() throws CommandException {
        System.out.println("\tUse command " + CmdType.getCommand("COMMANDS").toString() +
                " to see a list of things I can do for you.");
    }

    static void suggestFormat() throws CommandException {
        System.out.println("\tUse command " + CmdType.getCommand("COMMANDS").toString() +
                " to see the correct format for command attributes.");
    }

    static void suggestListNotes() throws CommandException {
        System.out.println("\tEnter command " + CmdType.getCommand("LISTNOTES").toString() +
                " to see them all.");
    }
}
