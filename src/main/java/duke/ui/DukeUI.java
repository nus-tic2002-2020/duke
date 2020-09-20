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

/**
 * An interface that sets out all the formats pertaining to textual user interface.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
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
    /**
     * This method is used to print a confirmation statement when a new {@code Note} object
     * has been successfully created.
     *
     * @param typeAdded The textual input provided by the user in verbatim.
     * @return DukeCommand The appropriate {@code DukeCommand} object created based on the user input.
     * @exception CommandException If there are errors in the command input.
     */
    static void addConfirm(String typeAdded) throws CommandException {
        System.out.println("\tNoted! I've added a new "
                + NoteType.getLowercaseName(typeAdded) + " to the list.");
    }

    /**
     * This method is used to print a statement seeking confirmation for a major operation.
     */
    static void askForConfirmation() {
        System.out.println("\tReply \"Y\" to confirm or any other character(s) to abort.");
    }

    /**
     * This method is used to print a icon indicating the success status of an auto-save operation.
     *
     * @param isSuccessful True if the auto-save operation has been successful.
     */
    static void autoSaveConfirmation(boolean isSuccessful) {
        System.out.printf("%78s", "Auto-Save ");
        if(isSuccessful){
            System.out.println("\u2615");
        } else {
            System.out.println("\u26D4");
        }
    }

    /**
     * This method is used to print a {@code String} object wrapped within the specified character width.
     *
     * @param input The (@code String} object to be printed.
     * @param limit The character width for which the input is to be wrapped in.
     */
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
                    if (input.charAt(i) == ' ') {
                        lastSpace = i;
                    }
                }
                System.out.print(input.substring(0, lastSpace) + "\n\t\t ");
                input = input.substring(lastSpace+1);
            }
        }
    }

    /**
     * This method is used to print, as part of a listing of {@code Note} objects,
     * a {@code String} object wrapped within the specified character width.
     *
     * @param input The (@code String} object to be printed.
     * @param limit The character width for which the input is to be wrapped in.
     * @param addDate The (@code Date} object to be printed, reflecting the date and time
     *                the {@code Note} object was added,
     */
    static void listWrap(String input, int limit, Date addDate) {

        boolean firstLine = true;
        while(true) {

            if (input.length() < limit) {
                if(firstLine) {
                    System.out.printf("%1$-29s%2$29s%n",
                            input, "Added: " + TASK_TIME.format(addDate));
                } else {
                    System.out.println("\t\t\t\t\t" + input);
                }
                break;
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.charAt(i) == ' ') {
                        lastSpace = i;
                    }
                }
                if(firstLine) {
                    System.out.printf("%1$-29s%2$29s%n",
                            input.substring(0, lastSpace), "Added: " +
                                    TASK_TIME.format(addDate));
                    input = input.substring(lastSpace + 1);
                    firstLine = false;

                } else {
                    System.out.println("\t\t\t\t\t" + input.substring(0, lastSpace));
                    input = input.substring(lastSpace + 1);
                }
            }
        }
    }

    /**
     * This method is used to print a report on the number of completed {@code Note} objects.
     */
    static void printCompleted() {
        System.out.println("\tYou have completed " + Todo.getTasksCompleted() +
                " task(s) and " + Event.getEventsCompleted() + " event(s)!");
    }

    /**
     * This method is used to print a divider to encase and organize textual user interfaces.
     */
    static void printDivider() {
        System.out.println(DUKE_DIVIDER);
    }

    /**
     * This method is used to print a report on the number of outstanding {@code Note} objects.
     */
    static void printOutstanding() {
        System.out.println("\tYou have...");
        System.out.print("\t\t");
        System.out.printf("%3d", Todo.getTasksOutstanding());
        System.out.print(" outstanding task(s),\n");
        System.out.print("\t\t");
        System.out.printf("%3d", Event.getEventsOutstanding());
        System.out.print(" outstanding events(s), and\n");
        Budget.printBudgetReport();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t...on your list.");
    }

    /**
     * This method is used to print the welcome message upon the startup of {@code Duke}.
     *
     * @param now The {@code Date} object reflecting the date and time {@code Duke} was started up.
     * @param isLoadedFromFile True if {@code Note} objects were successfully read and reconstructed from saved files.
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     * @exception ParseException If there are errors reading from the set {@code Duke} enums.
     */
    static void printOnStartup(Date now, boolean isLoadedFromFile) throws ParseException, CommandException {
        System.out.print("\nWelcome to PROJECT >>>\n" + DUKE_LOGO);
        System.out.printf("%1$-42s%2$42s%n", DATE_TODAY.format(now), DAY_TODAY.format(now));
        System.out.printf("%1$-42s%2$42s%n", ZodiacYear.getZodiacYear(now), SunSign.getSunSign(now));
        System.out.println(String.format("%1$-42s%2$42s", LunarMonth.getLunarMonth(now),
                MercuryMovement.getMercuryMovement(now)) + "\n");

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

    /**
     * This method is used to read and pass as a {@code String} object,
     * the textual input provided by the user in verbatim.
     *
     * @return String The textual input provided by the user in verbatim.
     */
    static String receiveCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * This method is used to print the full list of available (@code DukeCommand} object types,
     * as well as the required formats to call these commands.
     */
    static void showCommandList() {
        System.out.println(DUKE_COMMANDS);
    }

    /**
     * This method is used to print a statement suggesting the user to consult the command list
     * for a full list of available (@code DukeCommand} object types.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestCommands() throws CommandException {
        System.out.println("\tUse command " + CmdType.getCommand("COMMANDS").toString() +
                " to see a list of things I can do for you.");
    }

    /**
     * This method is used to print a statement suggesting the user to consult the command list
     * for a full list of the required formats to call on commands.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestFormat() throws CommandException {
        System.out.println("\tUse command " + CmdType.getCommand("COMMANDS").toString() +
                " to see the correct format for command attributes.");
    }

    /**
     * This method is used to print a statement suggesting the user to consult the notes list
     * for a full list of the existing {@code Note} objects in memory.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestListNotes() throws CommandException {
        System.out.println("\tEnter command " + CmdType.getCommand("LISTNOTES").toString() +
                " to see them all.");
    }
}
