import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
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
                "\t\t@deadline          >>> Add a new todo task with a deadline.\n" +
                "\t\t\tE.g. \"@deadline <todo description>/<deadline>\"\n" +
                "\t\t@event             >>> Add a new event.\n" +
                "\t\t\tE.g. \"@event <event description>/<from>/<to>\"\n" +
                "\t\t@shoplist          >>> Add a new shopping list item.\n" +
                "\t\t\tE.g. \"@shoplist <item description>/<item budget>\"\n" +
                "\t\t@todo              >>> Add a new todo task without a deadline.\n" +
                "\t\t\tE.g. \"@todo <todo description>\"\n" +
                "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "\n\n" +
                "\t\t***Generic Commands***\n" +
                "\t\t#commands          >>> List all available Duke's commands.\n" +
                "\t\t#listnotes         >>> List all the notes in memory.\n" +
                "\t\t#markdone x        >>> Mark task x as done.\n" +
                "\t\t#markdone x/y/z    >>> Mark tasks x, y and z as done.\n" +
                "\t\t#quitduke          >>> Exit Project Duke.\n" +
                "\t\t++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
                "\n\n" +
                "\t\t***Data Entry Formats***\n" +
                "\t\tBudget/Price       >>> $X.xx\n" +
                "\t\tDate-Time          >>> dd-MMM-yyyy HH:mm\n" +
                "\t\tDelimiter          >>> /\n" +
                "\t\tTask Number        >>> x\n" +
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

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            try {

                //Generic Commands
                if (input.substring(0,1).equals("#")){

                    if(input.substring(0,9).equals("#commands")) { //List all available Duke's commands.
                        if(input.substring(9).equals("")) {
                            System.out.println(dukeDivider);
                            System.out.println(dukeCommands);
                            System.out.println(dukeDivider);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #commands.");
                        }

                    //Exit Project Duke.
                    } else if(input.substring(0,9).equals("#quitduke")) {
                        if(input.substring(9).equals("")) {
                            String confirmQuit;
                            Scanner quitDuke = new Scanner(System.in);

                            System.out.println(dukeDivider);
                            System.out.println("\tYou have " + Task.getTasksOutstanding() +
                                    " outstanding task(s) in your list.");
                            System.out.println("\tAre you sure you want to quit?");
                            System.out.println("\tAll data would be lost.");
                            System.out.println("\tReply \"Y\" to confirm or any character(s) to cancel.");
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
                            }

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #quitduke.");
                        }

                    //List all the notes in memory.
                    } else if(input.substring(0,10).equals("#listnotes")) {
                        if(input.substring(10).equals("")) {
                            System.out.println(dukeDivider);

                            if(taskCount == 0){
                                System.out.println("\tYou haven't asked me to take note of anything yet.");

                            } else {
                                System.out.println("\tHere are the things you told me to note:-");
                                for (int i = 0; i < taskCount; i++) {
                                    tasks[i].printList();
                                }
                                System.out.println("\tYou have " + Task.getTasksOutstanding() +
                                        " outstanding task(s) in your list.");
                            }
                            System.out.println(dukeDivider);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #listnotes.");
                        }

                    //Mark a note as done.
                    } else if(input.substring(0,9).equals("#markdone")) {
                        if(input.substring(9,10).equals(" ")) {
                            Date doneDate = new Date();
                            String[] doneIndexes = input.substring(10).split("/");
                            int[] taskIndexes = new int[doneIndexes.length];
                            for(int i=0; i< doneIndexes.length; i++){
                                  taskIndexes[i] = Integer.parseInt(doneIndexes[i]) - 1;
                            }

                            System.out.println(dukeDivider);
                            for(int taskIndex: taskIndexes){
                                tasks[taskIndex].markAsDone(doneDate);
                            }
                            System.out.println("\tYou have completed " + Task.getTasksCompleted() +
                                    " task(s)! " + Task.getTasksOutstanding() +
                                    " more task(s) outstanding in your list.");
                            System.out.println("\tUse command \"#listnotes\" to see them all.");
                            System.out.println(dukeDivider);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #markdone.");
                        }

                    } else {
                        throw new CommandException("It seems to be an invalid Generic Command.");
                    }

                //New Note Commands
                } else if (input.substring(0,1).equals("@")) {

                    Date addDate = new Date();
                    System.out.println(dukeDivider);

                    //Add a new todo task without a deadline.
                    if (input.substring(0, 6).equals("@todo ")) {
                        Task t = new Todo((taskCount + 1), input.substring(6), addDate);
                        tasks[taskCount] = t;
                        System.out.println("\tNoted! I've added a new todo task to the list.");

                    } else if (input.substring(0, 7).equals("@event ")) { //Add a new event.
                        String[] inputTokens = input.substring(7).split("/", 3);
                        String description = inputTokens[0];
                        Date startDate = inputDate.parse(inputTokens[1]);
                        Date endDate = inputDate.parse(inputTokens[2]);

                        Task t = new Event((taskCount + 1), description, startDate, endDate, addDate);
                        tasks[taskCount] = t;
                        System.out.println("\tNoted! I've added a new event to the list.");

                    //Add a new todo task with a deadline.
                    } else if (input.substring(0, 10).equals("@deadline ")) {
                        String[] inputTokens = input.substring(10).split("/", 2);
                        String description = inputTokens[0];
                        Date targetDate = inputDate.parse(inputTokens[1]);

                        Task t = new Deadline((taskCount + 1), description, targetDate, addDate);
                        tasks[taskCount] = t;
                        System.out.println("\tNoted! I've added a new deadline task to the list.");

                    //Add a new shopping list item.
                    } else if (input.substring(0, 10).equals("@shoplist ")) {
                        String[] inputTokens = input.substring(10).split("/\\$", 2);
                        String description = inputTokens[0];
                        Double itemBudget = Double.parseDouble(inputTokens[1]);

                        Task t = new Shoplist((taskCount + 1), description, itemBudget, addDate);
                        tasks[taskCount] = t;
                        System.out.println("\tNoted! I've added a new shopping item to the list.");

                    } else {
                        throw new CommandException("It seems to be an invalid New Task Command.");
                    }

                    tasks[taskCount].printList();
                    taskCount++;
                    System.out.println("\tYou have " + Task.getTasksOutstanding() + " outstanding task(s) in your list.");
                    System.out.println("\tEnter command \"#listnotes\" to see them all.");
                    System.out.println(dukeDivider);

                } else {
                    throw new PrefixException();
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI don't understand what you mean by...\n");
                Echo.commandWrap(input, 66);

                if(input.substring(0,1).equals("#")) {
                    System.out.println("\tIt seems to be an invalid Generic Command.");
                } else if(input.substring(0,1).equals("@")) {
                    System.out.println("\tIt seems to be an invalid New Task Command.");
                }

                System.out.println("\tUse command #commands to see a list of things I can do for you.");
                System.out.println(dukeDivider);

            } catch (NullPointerException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI don't understand what you mean by...\n");
                Echo.commandWrap(input, 66);
                System.out.println("\tThe task(s) you mentioned cannot be found.");
                System.out.println("\tThere could be errors in the data entry, format or delimiters.");
                System.out.println("\tUse command #commands to see the correct format for command attributes.");
                System.out.println(dukeDivider);

            } catch (ParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI don't understand what you mean by...\n");
                Echo.commandWrap(input, 66);
                System.out.println("\tThe attribute(s) you mentioned cannot be understood.");
                System.out.println("\tThere could be errors in the data entry, format or delimiters.");
                System.out.println("\tUse command #commands to see the correct format for command attributes.");
                System.out.println(dukeDivider);

            } catch (CommandException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI don't understand what you mean by...\n");
                Echo.commandWrap(input, 66);
                System.out.println("\t" + e.getMessage());
                System.out.println("\tUse command #commands to see a list of things I can do for you.");
                System.out.println(dukeDivider);

            } catch (PrefixException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI don't understand what you mean by...\n");
                Echo.commandWrap(input, 66);
                System.out.println("\tI'd love to hold a conversation, but I can't, yet.");
                System.out.println("\tPlease include the required command prefix to activate a command.");
                System.out.println("\tUse command #commands to see a list of things I can do for you.");
                System.out.println(dukeDivider);

            } catch (DateException e) {
                System.out.println(dukeDivider);
                System.out.println("\tI understand what you meant...\n");
                Echo.commandWrap(input, 66);

                if(e.cancelConstruct){
                    Task.deleteNewTask();
                }

                switch (e.getMessage()) {
                    case "TargetDate" -> {
                        System.out.println("\tBut the Deadline has already passed.");
                        System.out.println("\tI can't send you back in time, yet.");
                        System.out.println("\tPlease enter as the Deadline, another date & time in the future.");
                    }
                    case "StartB4Now" -> {
                        System.out.println("\tBut the Event Start Date-Time has already passed.");
                        System.out.println("\tI can't send you back in time, yet.");
                        System.out.println("\tPlease enter as the Event Start Date-Time, another date & time in the future.");
                    }
                    case "StartAFEnd" -> {
                        System.out.println("\tBut the Event Start Date-Time is after the Event End Date-Time.");
                        System.out.println("\tI can't turn time backwards, yet.");
                        System.out.println("\tPlease enter as the Event Start Date-Time, another date & time earlier \nhan the Event End Date-Time.");
                    }
                    case "NoStartDate" -> {
                        System.out.println("\tBut the Event Start Date-Time hasn't been set yet.");
                        System.out.println("\tI can't predict the start of events, yet.");
                        System.out.println("\tPlease set the Event Start Date-Time first, before the Event End Date-Time.");
                    }
                    case "EndB4Start" -> {
                        System.out.println("\tBut the Event End Date-Time is before the Event Start Date-Time.");
                        System.out.println("\tI can't turn time backwards, yet.");
                        System.out.println("\tPlease enter as the Event End Date-Time, another date & time later \nthan the Event Start Date-Time.");
                    }
                }
                System.out.println(dukeDivider);
            }
        }
    }
}
