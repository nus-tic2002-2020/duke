import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke implements DukeUI {
    public static void main(String[] args) throws Exception {

        //Get Date & Time on startup
        Date now = new Date();

        //Run startup sequence
        DukeUI.printOnStartup(now);

        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            try {

                //Generic Commands
                if (input.startsWith("#")){

                    //List all available Duke's commands.
                    if(input.startsWith("#commands")) {
                        if(input.substring(9).equals("")) {
                            System.out.println(DUKE_DIVIDER);
                            System.out.println(DUKE_COMMANDS);
                            System.out.println(DUKE_DIVIDER);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #commands.");
                        }

                    //Exit Project Duke.
                    } else if(input.startsWith("#quitduke")) {
                        if(input.substring(9).equals("")) {
                            String confirmQuit;
                            Scanner quitDuke = new Scanner(System.in);

                            System.out.println(DUKE_DIVIDER);
                            DukeUI.printOutstanding();
                            System.out.println("\tAre you sure you want to quit?");
                            System.out.println("\tAll data would be lost.");
                            System.out.println("\tReply \"Y\" to confirm or any character(s) to cancel.");
                            System.out.println(DUKE_DIVIDER);

                            confirmQuit = quitDuke.nextLine();

                            if(confirmQuit.equals("Y")) {
                                System.out.println(DUKE_DIVIDER);
                                System.out.println("\tGood Bye! Hope to see you again soon!");
                                System.out.println(DUKE_DIVIDER);
                                break;

                            } else {
                                System.out.println(DUKE_DIVIDER);
                                System.out.println("\tYay! Thanks for staying!");
                                System.out.println(DUKE_DIVIDER);
                            }

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #quitduke.");
                        }

                    //List all the notes in memory.
                    } else if(input.startsWith("#listnotes")) {
                        if(input.substring(10).equals("")) {
                            System.out.println(DUKE_DIVIDER);

                            if(tasks.size() == 0){
                                System.out.println("\tYou haven't asked me to take note of anything yet.");

                            } else {
                                System.out.println("\tHere are the things you told me to note:-");
                                for (Task task : tasks) {
                                    task.printList();
                                }
                                DukeUI.printOutstanding();
                            }
                            System.out.println(DUKE_DIVIDER);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #listnotes.");
                        }

                    //Mark a note as done.
                    } else if(input.startsWith("#markdone")) {
                        if(input.substring(9,10).equals(" ")) {
                            Date doneDate = new Date();
                            String[] doneIndexes = input.substring(10).split("/");
                            int[] taskIndexes = new int[doneIndexes.length];
                            for(int i=0; i<doneIndexes.length; i++){
                                  taskIndexes[i] = Integer.parseInt(doneIndexes[i]) - 1;
                            }

                            System.out.println(DUKE_DIVIDER);
                            for(int taskIndex: taskIndexes){
                                tasks.get(taskIndex).markAsDone(doneDate);
                            }
                            DukeUI.printCompleted();
                            DukeUI.printOutstanding();
                            System.out.println("\tUse command \"#listnotes\" to see them all.");
                            System.out.println(DUKE_DIVIDER);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #markdone.");
                        }

                    //Delete a note and re-order the rest.
                    } else if(input.startsWith("#delete")) {
                        if(input.substring(7,8).equals(" ")) {
                            Date doneDate = new Date();
                            String[] doneSerials = input.substring(8).split("/");
                            int[] taskSerials = new int[doneSerials.length];
                            for(int i=0; i<doneSerials.length; i++){
                                taskSerials[i] = Integer.parseInt(doneSerials[i]);
                            }

                            System.out.println(DUKE_DIVIDER);
                            for(int taskSerial: taskSerials) {
                                for (Task task: tasks) {
                                    if (task.getSerialNum() == taskSerial) {
                                        task.deleteExistingNote();
                                        tasks.remove(task);
                                        break;
                                    }
                                }
                            }
                            System.out.println("\n\tDeletion(s) completed...");

                            System.out.println("\t...renumbering the remaining note(s)...");
                            for(int i=0; i<tasks.size(); i++){
                                System.out.print("\t#");
                                System.out.print(String.format("%3d", tasks.get(i).getSerialNum()));
                                System.out.print("\t >>> ");
                                tasks.get(i).setSerialNum(i+1);
                                System.out.print("\t#");
                                System.out.print(String.format("%3d", tasks.get(i).getSerialNum()));
                                System.out.print("\n");
                            }

                            System.out.println("\tThe remaining notes have been renumbered!\n");
                            DukeUI.printCompleted();
                            DukeUI.printOutstanding();
                            System.out.println("\tUse command \"#listnotes\" to see them all.");
                            System.out.println(DUKE_DIVIDER);

                        } else {
                            throw new CommandException("There seems to be invalid characters behind #delete.");
                        }

                    } else {
                        throw new CommandException("It seems to be an invalid Generic Command.");
                    }

                //New Note Commands
                } else if (input.startsWith("@")) {

                    Date addDate = new Date();
                    System.out.println(DUKE_DIVIDER);

                    //Add a new todo task without a deadline.
                    if (input.startsWith("@todo ")) {
                        Task t = new Todo((tasks.size() + 1), input.substring(6), addDate);
                        tasks.add(t);
                        System.out.println("\tNoted! I've added a new todo task to the list.");

                    } else if (input.startsWith("@event ")) { //Add a new event.
                        String[] inputTokens = input.substring(7).split("/", 3);
                        String description = inputTokens[0];
                        Date startDate = INPUT_DATE.parse(inputTokens[1]);
                        Date endDate = INPUT_DATE.parse(inputTokens[2]);

                        Task t = new Event((tasks.size() + 1), description, startDate, endDate, addDate);
                        tasks.add(t);
                        System.out.println("\tNoted! I've added a new event to the list.");

                    //Add a new todo task with a deadline.
                    } else if (input.startsWith("@deadline ")) {
                        String[] inputTokens = input.substring(10).split("/", 2);
                        String description = inputTokens[0];
                        Date targetDate = INPUT_DATE.parse(inputTokens[1]);

                        Task t = new Deadline((tasks.size() + 1), description, targetDate, addDate);
                        tasks.add(t);
                        System.out.println("\tNoted! I've added a new deadline task to the list.");

                    //Add a new shopping list item.
                    } else if (input.startsWith("@shoplist ")) {
                        String[] inputTokens = input.substring(10).split("/\\$", 2);
                        String description = inputTokens[0];
                        double itemBudget = Double.parseDouble(inputTokens[1]);

                        Task t = new Shoplist((tasks.size() + 1), description, itemBudget, addDate);
                        tasks.add(t);
                        System.out.println("\tNoted! I've added a new shopping item to the list.");

                    } else {
                        throw new CommandException("It seems to be an invalid New Task Command.");
                    }

                    tasks.get(tasks.size()-1).printList();
                    DukeUI.printOutstanding();
                    System.out.println("\tEnter command \"#listnotes\" to see them all.");
                    System.out.println(DUKE_DIVIDER);

                } else {
                    throw new PrefixException();
                }

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println(DUKE_DIVIDER);
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tThe task(s) you mentioned cannot be found.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                System.out.println("\tUse command #commands to see the correct format for command attributes.");
                System.out.println(DUKE_DIVIDER);

            } catch (ParseException | NumberFormatException e) {
                System.out.println(DUKE_DIVIDER);
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tThe attribute(s) you mentioned cannot be understood.");
                System.out.println("\tThere could be errors or omissions in the data entry, format or delimiters.");
                System.out.println("\tUse command #commands to see the correct format for command attributes.");
                System.out.println(DUKE_DIVIDER);

            } catch (CommandException e) {
                System.out.println(DUKE_DIVIDER);
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\t" + e.getMessage());
                System.out.println("\tUse command #commands to see a list of things I can do for you.");
                System.out.println(DUKE_DIVIDER);

            } catch (PrefixException e) {
                System.out.println(DUKE_DIVIDER);
                System.out.println("\tI don't understand what you mean by...\n");
                DukeUI.commandWrap(input, 66);
                System.out.println("\tI'd love to hold a conversation, but I can't, yet.");
                System.out.println("\tPlease include the required command prefix to activate a command.");
                System.out.println("\tUse command #commands to see a list of things I can do for you.");
                System.out.println(DUKE_DIVIDER);

            } catch (DateException e) {
                System.out.println(DUKE_DIVIDER);
                System.out.println("\tI understand what you meant by...\n");
                DukeUI.commandWrap(input, 66);

                if(e.cancelConstruct){
                    if(e.noteType.equals("Todo")){
                        Todo.deleteNewNote();
                    } else if (e.noteType.equals("Event")){
                        Event.deleteNewNote();
                    }
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
                System.out.println(DUKE_DIVIDER);
            }
        }
    }
}
