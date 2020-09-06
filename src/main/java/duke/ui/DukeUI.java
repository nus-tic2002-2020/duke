package duke.ui;

import duke.*;
import duke.budget.*;
import duke.commands.*;
import duke.notes.*;
import duke.notes.event.*;
import duke.notes.todo.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
                    "\t\t*** New Note Commands ***\n" +
                    "\t\t@bill            >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g. @bill <Description> /by <Date-Time> /for <Budget/Price>\n" +
                    "\t\t@birthday        >>> Add a new birthday event.\n" +
                    "\t\t\tE.g. @birthday <Person> /from <Date-Time> /to <Date-Time>\n" +
                    "\t\t\t\t/for <Budget/Price>\n" +
                    "\t\t@deadline        >>> Add a new todo task with a deadline.\n" +
                    "\t\t\tE.g. @deadline <Description> /by <Date-Time>\n" +
                    "\t\t@event           >>> Add a new event.\n" +
                    "\t\t\tE.g. @event <Description> /from <Date-Time> /to <Date-Time>\n" +
                    "\t\t@shoplist        >>> Add a new shopping list item.\n" +
                    "\t\t\tE.g. @shoplist <Description> /for <Budget/Price>\n" +
                    "\t\t@todo            >>> Add a new todo task without a deadline.\n" +
                    "\t\t\tE.g. @todo <Description>\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t*** Generic Commands ***\n" +
                    "\t\t#commands        >>> List all available Duke's commands.\n" +
                    "\t\t\tE.g. #commands\n" +
                    "\t\t#delete          >>> Delete notes, then renumber the rest.\n" +
                    "\t\t\tE.g. #delete <Note#>\n" +
                    "\t\t\tE.g. #delete <Note#> /and <Note#> /and <Note#> \n" +
                    "\t\t#editdesc        >>> Edit the description of a note.\n" +
                    "\t\t\tE.g. #editdesc <Note#> /to <New Description>\n" +
                    "\t\t#exitduke        >>> Exit Project Duke.\n" +
                    "\t\t\tE.g. #exitduke\n" +
                    "\t\t#extend          >>> Extend a deadline by days, hours and minutes.\n" +
                    "\t\t\tE.g. #extend <Note#> /by <days>d <hours>h <minutes>m\n" +
                    "\t\t#listnotes       >>> List all the notes in memory.\n" +
                    "\t\t\tE.g. #listnotes\n" +
                    "\t\t#markdone x      >>> Mark notes as done.\n" +
                    "\t\t\tE.g. #markdone <Note#>\n" +
                    "\t\t\tE.g. #markdone <Note#> /and <Note#> /and <Note#>\n" +
                    "\t\t#transfer        >>> Mark tasks x, y and z as done.\n" +
                    "\t\t\tE.g. #transfer /from <Note#> /to <Note#> /for <Budget/Price>\n" +
                    "\t\t#wipeduke        >>> List all available Duke's commands.\n" +
                    "\t\t\tE.g. #wipeduke\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n" +
                    "\t\t*** Data Entry Formats ***\n" +
                    "\t\tBudget/Price     >>> $X.xx\n" +
                    "\t\tDate-Time        >>> dd-MMM-yyyy HH:mm\n" +
                    "\t\tNote#            >>> x\n" +
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
                            input, "Added: " + TASK_DATE.format(addDate)));
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
                                    TASK_DATE.format(addDate)));
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
        System.out.println(String.format("%1$-42s%2$42s", ZodiacYear.getZodiacYear(now), SunSign.getSunSign(now)) + "\n");

        printDivider();
        System.out.println("\tHello! I'm Duke, your all-rounded personal assistant!");
        System.out.println("\tWhat do you need noted today?\n");
        showCommandList();
        printDivider();

        if(isLoadedFromFile){
            System.out.println("\tSaved notes were found and loaded.");
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
