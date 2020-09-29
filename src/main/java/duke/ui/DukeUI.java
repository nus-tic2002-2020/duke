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
    String DUKE_DIVIDER = "    ----------------------------------------------------------------------------";

    //Date formats to be used by Duke
    SimpleDateFormat TASK_TIME = new SimpleDateFormat("dd-MMM-yyyy (E), hh:mm a");
    SimpleDateFormat TASK_DATE = new SimpleDateFormat("dd-MMM-yyyy (E)");
    SimpleDateFormat DATE_TODAY = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat DAY_TODAY = new SimpleDateFormat("EEEE");
    SimpleDateFormat YEAR_TODAY = new SimpleDateFormat("yyyy");
    SimpleDateFormat INPUT_TIME = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    SimpleDateFormat INPUT_DATE = new SimpleDateFormat("dd-MMM-yyyy");


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


    //List of commands for Duke
    String DUKE_COMMANDS =
                    "        \u27A4 Perform Generic Operations    \u279C Enter #commands gen\n" +
                    "        \u27A4 Create New Notes              \u279C Enter #commands new\n" +
                    "        \u27A4 Extract Information           \u279C Enter #commands info\n" +
                    "        \u27A4 Manage Existing Notes         \u279C Enter #commands mgmt\n" +
                    "\n";

    String DUKE_GENERIC =
                    "        *** Perform Generic Operations ***\n" +
                    "\n" +
                    "        #autosave      / #ats >>> Toggle auto-save on or off.\n" +
                    "        #commands      / #cmd >>> List all available Duke's commands.\n" +
                    "        #exitduke      / #xit >>> Exit Project Duke.\n" +
                    "        #saveduke      / #sav >>> Save Notes to file and archive the last.\n" +
                    "        #undo          / #und >>> Undo the last save operation.\n" +
                    "        #wipeduke      / #wpe >>> Wipe all of Duke's memories and files.\n" +
                    "\n";

    String DUKE_NEW =
                    "        *** Create New Notes ***\n" +
                    "\n" +
                    "              --------------------------------------------------------\n" +
                    "              Attributes Available:\n" +
                    "               Budget            -->    /for $   <$X.xx>\n" +
                    "               Description       -->             <Description>\n" +
                    "               End Date-Time     -->    /to      <dd-MMM-yyyy HH:mm>\n" +
                    "               Start Date-Time   -->    /from    <dd-MMM-yyyy HH:mm>\n" +
                    "               Target Date-Time  -->    /by      <dd-MMM-yyyy HH:mm>\n" +
                    "              --------------------------------------------------------\n" +
                    "\n" +
                    "        @bill          / @bl  >>> Add a new bill payment.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Target Date-Time  \u23E9  Budget\n" +
                    "        @birthday      / @bd  >>> Add a new birthday event.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Start Date-Time  \u23E9  End Date-Time  \u23E9  Budget\n" +
                    "        @deadline      / @dl  >>> Add a new todo task with a deadline.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Target Date-Time\n" +
                    "        @event         / @ev  >>> Add a new event.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Start Date-Time  \u23E9  End Date-Time\n" +
                    "        @shoplist      / @sl  >>> Add a new shopping list item.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Budget\n" +
                    "        @todo          / @td  >>> Add a new todo task without a deadline.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description\n" +
                    "        @wedding       / @wd  >>> Add a new wedding event.\n" +
                    "          [\u2714] Required Attributes in Order of:\n" +
                    "            Description  \u23E9  Start Date-Time  \u23E9  End Date-Time  \u23E9  Budget\n" +
                    "\n";

    String DUKE_INFO =
                    "        *** Extract Information ***\n" +
                    "\n" +
                    "              --------------------------------------------------------\n" +
                    "              Filters Available:\n" +
                    "               Completion Status -->    /nf      <O> or <C>\n" +
                    "                                                      O for Outstanding\n" +
                    "                                                      C for Completed\n" +
                    "               Added Date        -->    /added   <dd-MMM-yyyy>\n" +
                    "               Start/Target Date -->    /on      <dd-MMM-yyyy>\n" +
                    "               Description Text  -->    /with    <Search Text>\n" +
                    "              --------------------------------------------------------\n" +
                    "                   \u2714 Required     \u271C Optional     \u2718 Not Available\n" +
                    "              --------------------------------------------------------\n" +
                    "\n" +
                    "        #listbills     / #lbp >>> List bill payments.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listbirthdays / #lbd >>> List birthdays.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listbudgets   / #lbg >>> List budgets set.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listdeadlines / #ldl >>> List deadlines.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listevents    / #lev >>> List events.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listnotes     / #lnt >>> List notes.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listnxt24     / #n24 >>> List deadlines & events in 24 hours.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u2718] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listnxt48     / #n48 >>> List deadlines & events in 48 hours.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u2718] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listnxt72     / #n72 >>> List deadlines & events in 72 hours.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listshoplists / #lsl >>> List shopping list items.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u2718] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listtodos     / #ltd >>> List todo tasks.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "        #listweddings  / #lwd >>> List weddings.\n" +
                    "          [\u271C] Completion Status  [\u271C] Added Date\n" +
                    "          [\u271C] Start/Target Date  [\u271C] Description Text\n" +
                    "\n";

    String DUKE_MGMT =
                    "        *** Manage Existing Notes ***\n" +
                    "\n" +
                    "        #delete        / #del >>> Delete notes, then renumber the rest.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "          [\u271C] Optional Attributes\n" +
                    "            Serial Number of Notes        -->  /n      <Note#> & <Note#>\n" +
                    "        #editdesc      / #edd >>> Edit the description of a note.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "            New Description               -->  /to     <New Description>\n" +
                    "        #editend       / #ede >>> Edit the end date-time of an Event.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "            New Event End Date-Time       -->  /to     <dd-MMM-yyyy HH:mm>\n" +
                    "        #editstart     / #eds >>> Edit the start date-time of an Event.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "            New Event Start Date-Time     -->  /to     <dd-MMM-yyyy HH:mm>\n" +
                    "        #edittarget    / #edt >>> Edit the target date-time of a Deadline.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "            New Deadline Target Date-Time -->  /to     <dd-MMM-yyyy HH:mm>\n" +
                    "        #extend        / #xtd >>> Extend a deadline.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note         -->  /n      <Note#>\n" +
                    "            Extend by at least one of the following:\n" +
                    "              by Days                     -->  /d      <Number of Days>\n" +
                    "              by Hours                    -->  /h      <Number of Hours>\n" +
                    "              by Minutes                  -->  /m      <Number of Minutes>\n" +
                    "        #markdone      / #mkd >>> Mark notes as done.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Single Serial Number          -->  /n      <Note#>\n" +
                    "          [\u271C] Optional Attributes\n" +
                    "            Multiple Serial Numbers       -->  /n      <Note#> & <Note#>\n" +
                    "        #transfer      / #txf >>> Transfer budgets from one note to another.\n" +
                    "          [\u2714] Required Attributes\n" +
                    "            Serial Number of Note\n" +
                    "              to Transfer Budget Out      -->  /from   <Note#>\n" +
                    "              to Transfer Budget In       -->  /to     <Note#>\n" +
                    "            Budget Amount to Transfer     -->  /for $  <X.xx>\n" +
                    "\n";


    //METHODS-------------------------------------------
    /**
     * This method is used to print a confirmation statement when a new {@code Note} object
     * has been successfully created.
     *
     * @param typeAdded The textual input provided by the user in verbatim.
     * @exception CommandException If there are errors in the command input.
     */
    static void addConfirm(String typeAdded) throws CommandException {
        System.out.println("    Noted! I've added a new "
                + NoteType.getLowercaseName(typeAdded) + " to the list.");
    }

    /**
     * This method is used to print a statement seeking confirmation for a major operation.
     */
    static void askForConfirmation() {
        System.out.println("    Reply \"Y\" to confirm or any other character(s) to abort.");
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
        System.out.print("        \"");
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
                System.out.print(input.substring(0, lastSpace) + "\n         ");
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
                    System.out.printf("%1$-28s%2$28s%n",
                            input, "Added: " + TASK_TIME.format(addDate));
                } else {
                    System.out.println("                    " + input);
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
                    System.out.printf("%1$-28s%2$28s%n",
                            input.substring(0, lastSpace), "Added: " +
                                    TASK_TIME.format(addDate));
                    input = input.substring(lastSpace + 1);
                    firstLine = false;

                } else {
                    System.out.println("                    " + input.substring(0, lastSpace));
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
        System.out.print("    ");
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
                System.out.print(input.substring(0, lastSpace) + "\n    ");
                input = input.substring(lastSpace+1);
            }
        }
    }

    /**
     * This method is used to print a report on the number of completed {@code Note} objects.
     */
    static void printCompleted() {
        System.out.println("    You have completed " + Todo.getTasksCompleted() +
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
        System.out.println("    You have...");
        System.out.print("        ");
        System.out.printf("%3d", Todo.getTasksOutstanding());
        System.out.print(" outstanding task(s),\n");
        System.out.print("        ");
        System.out.printf("%3d", Event.getEventsOutstanding());
        System.out.print(" outstanding events(s), and\n");
        Budget.printBudgetReport();
        System.out.println("                                                    ...on your list.");
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
        System.out.println("    Hello! I'm Duke, your all-rounded personal assistant!");
        showCommandList("all");
        printDivider();

        if(isLoadedFromFile){
            System.out.println("    Saved notes were found and loaded.");
            printCompleted();
            printOutstanding();
            suggestListNotes();
        }else {
            System.out.println("    No saved notes were found.");
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
    static void showCommandList(String infoType) {
        System.out.println("    What do you need done today?\n");
        switch (infoType) {
            case "gen" -> System.out.println(DUKE_GENERIC);
            case "new" -> System.out.println(DUKE_NEW);
            case "info" -> System.out.println(DUKE_INFO);
            case "mgmt" -> System.out.println(DUKE_MGMT);
            case "all" -> System.out.println(DUKE_COMMANDS);
        }

    }

    /**
     * This method is used to print a statement suggesting the user to consult the command list
     * for a full list of available (@code DukeCommand} object types.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestCommands() throws CommandException {
        System.out.println("    Use command " + CmdType.getCommand("COMMANDS") +
                " to see a list of things I can do for you.");
    }

    /**
     * This method is used to print a statement suggesting the user to consult the command list
     * for a full list of the required formats to call on commands.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestFormat() throws CommandException {
        System.out.println("    Use command " + CmdType.getCommand("COMMANDS") +
                " to see the correct format for command attributes.");
    }

    /**
     * This method is used to print a statement suggesting the user to consult the notes list
     * for a full list of the existing {@code Note} objects in memory.
     *
     * @exception CommandException If there are errors reading from the set {@code DukeCommand} enums.
     */
    static void suggestListNotes() throws CommandException {
        System.out.println("    Enter command " + CmdType.getCommand("LISTNOTES") +
                " to see them all.");
    }
}
