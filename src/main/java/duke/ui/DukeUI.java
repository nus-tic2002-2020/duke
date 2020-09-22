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
                    "\n" +
                    "\t\t*** Generic Commands ***\n" +
                    "\n" +
                    "\t\t#autosave      / #ats >>> Toggle auto-save on or off.\n" +
                    "\t\t#commands      / #cmd >>> List all available Duke's commands.\n" +
                    "\t\t#exitduke      / #xit >>> Exit Project Duke.\n" +
                    "\t\t#saveduke      / #sav >>> Save Notes to file and archive the last.\n" +
                    "\t\t#undo          / #und >>> Undo the last save operation.\n" +
                    "\t\t#wipeduke      / #wpe >>> Wipe all of Duke's memories and files.\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n\n" +
                    "\t\t*** New Note Creation ***\n" +
                    "\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\t\t\t  Attributes Available:\n" +
                    "\t\t\t   Budget            \t-->\t/for $   <$X.xx>\n" +
                    "\t\t\t   Description       \t-->\t         <Description>\n" +
                    "\t\t\t   End Date-Time     \t-->\t/to      <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t\t   Start Date-Time   \t-->\t/from    <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t\t   Target Date-Time  \t-->\t/by      <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\n" +
                    "\t\t@bill          / @bl  >>> Add a new bill payment.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Target Date-Time  \u23E9  Budget\n" +
                    "\t\t@birthday      / @bd  >>> Add a new birthday event.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Start Date-Time  \u23E9  End Date-Time  \u23E9  Budget\n" +
                    "\t\t@deadline      / @dl  >>> Add a new todo task with a deadline.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Target Date-Time\n" +
                    "\t\t@event         / @ev  >>> Add a new event.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Start Date-Time  \u23E9  End Date-Time\n" +
                    "\t\t@shoplist      / @sl  >>> Add a new shopping list item.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Budget\n" +
                    "\t\t@todo          / @td  >>> Add a new todo task without a deadline.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description\n" +
                    "\t\t@wedding       / @wd  >>> Add a new wedding event.\n" +
                            "\t\t\t[\u2714] Required Attributes in Order of:\n" +
                            "\t\t\t Description  \u23E9  Start Date-Time  \u23E9  End Date-Time  \u23E9  Budget\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n\n" +
                    "\t\t*** Information Extraction ***\n" +
                    "\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\t\t\t  Filters Available:\n" +
                    "\t\t\t   Completion Status \t-->\t/nf      <O> or <C>\n" +
                    "\t\t\t                     \t   \t          O for Outstanding\n" +
                    "\t\t\t                     \t   \t          C for Completed\n" +
                    "\t\t\t   Date              \t-->\t/on      <dd-MMM-yyyy>\n" +
                    "\t\t\t   Description Text  \t-->\t/with    <Search Text>\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\t\t\t\t   \u2714 Required     \u271C Optional     \u2718 Not Available\n" +
                    "\t\t\t  --------------------------------------------------------\n" +
                    "\n" +
                    "\t\t#listbills     / #lbp >>> List bill payments.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listbirthdays / #lbd >>> List birthdays.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listbudgets   / #lbg >>> List budgets set.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listdeadlines / #ldl >>> List deadlines.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listevents    / #lev >>> List events.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listnotes     / #lnt >>> List notes.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listnxt24     / #n24 >>> List deadlines & events in 24 hours.\n" +
                            "\t\t\t[\u271C] Completion Status [\u2718] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listnxt48     / #n48 >>> List deadlines & events in 48 hours.\n" +
                            "\t\t\t[\u271C] Completion Status [\u2718] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listnxt72     / #n72 >>> List deadlines & events in 72 hours.\n" +
                            "\t\t\t[\u271C] Completion Status [\u2718] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listshoplists / #lsl >>> List shopping list items.\n" +
                            "\t\t\t[\u271C] Completion Status [\u2718] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listtodos     / #ltd >>> List todo tasks.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t#listweddings  / #lwd >>> List weddings.\n" +
                            "\t\t\t[\u271C] Completion Status [\u271C] Date\n" +
                            "\t\t\t[\u271C] Description Text\n" +
                    "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                    "\n\n\n" +
                    "\t\t*** Existing Note Management ***\n" +
                    "\n" +
                    "\t\t#delete        / #del >>> Delete notes, then renumber the rest.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t[\u271C] Optional Attributes\n" +
                            "\t\t\t Serial Number of Notes       \t-->\t/n      <Note#> & <Note#>\n" +
                    "\t\t#editdesc      / #edd >>> Edit the description of a note.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t New Description              \t-->\t/to     <New Description>\n" +
                    "\t\t#editend       / #ede >>> Edit the end date-time of an Event.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t New Event End Date-Time      \t-->\t/to     <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t#editstart     / #eds >>> Edit the start date-time of an Event.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t New Event Start Date-Time    \t-->\t/to     <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t#edittarget    / #edt >>> Edit the target date-time of a Deadline.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t New Deadline Target Date-Time\t-->\t/to     <dd-MMM-yyyy HH:mm>\n" +
                    "\t\t#extend        / #xtd >>> Extend a deadline.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note        \t-->\t/n      <Note#>\n" +
                            "\t\t\t Extend by at least one of the following:\n" +
                            "\t\t\t   by Days                    \t-->\t/d      <Number of Days>\n" +
                            "\t\t\t   by Hours                   \t-->\t/h      <Number of Hours>\n" +
                            "\t\t\t   by Minutes                 \t-->\t/m      <Number of Minutes>\n" +
                    "\t\t#markdone      / #mkd >>> Mark notes as done.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Single Serial Number         \t-->\t/n      <Note#>\n" +
                            "\t\t\t[\u271C] Optional Attributes\n" +
                            "\t\t\t Multiple Serial Numbers      \t-->\t/n      <Note#> & <Note#>\n" +
                    "\t\t#transfer      / #txf >>> Transfer budgets from one note to another.\n" +
                            "\t\t\t[\u2714] Required Attributes\n" +
                            "\t\t\t Serial Number of Note\n" +
                            "\t\t\t   to Transfer Budget Out     \t-->\t/from   <Note#>\n" +
                            "\t\t\t   to Transfer Budget In      \t-->\t/to     <Note#>\n" +
                            "\t\t\t Budget Amount to Transfer    \t-->\t/for $  <X.xx>\n" +
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
     * This method is used to print a {@code String} object wrapped within the standard width for {@code Duke}.
     *
     * @param input The (@code String} object to be printed.
     */
    static void standardWrap(String input) {

        int limit = 76;
        System.out.print("\t");
        while(true) {

            if (input.length() < limit) {
                System.out.print(input);
                System.out.print("\n");
                break;
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.charAt(i) == ' ') {
                        lastSpace = i;
                    }
                }
                System.out.print(input.substring(0, lastSpace) + "\n\t");
                input = input.substring(lastSpace+1);
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
