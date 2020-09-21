package duke.commands;

import duke.notes.NoteType;
import duke.notes.Task;
import duke.notes.event.Event;
import duke.notes.event.Birthday;
import duke.notes.event.Wedding;
import duke.notes.todo.Bill;
import duke.notes.todo.Deadline;
import duke.notes.todo.Shoplist;
import duke.notes.todo.Todo;
import duke.storage.DukeList;
import duke.storage.DukeStorage;
import duke.ui.DukeUI;
import java.util.ArrayList;
import java.util.Date;

/**
 * An extension of the {@code DukeCommand} object that filters, orders and prints {@code Notes} in memory.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class ListCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private NoteType noteType;
    private String noteFilter;
    private Date dateFilter = null;
    private int timelineDays = 0;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code ListCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param noteFilter The selection to filter {@code Note} objects based on their completion statuses.
     */
    public ListCommand(String cmdType, String noteFilter) throws CommandException {
        super(cmdType);
        this.noteType = CmdType.getNoteType(cmdType);
        this.noteFilter = noteFilter;
    }

    /**
     * This method constructs a {@code ListCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param noteFilter The selection to filter {@code Note} objects based on their completion statuses.
     * @param dateFilter The date specified for {@code Note} objects to be displayed.
     * @param timelineDays The window based on number of days for {@code Note} objects to be displayed.
     */
    public ListCommand(String cmdType, String noteFilter, Date dateFilter, int timelineDays) throws CommandException {
        super(cmdType);
        this.noteType = CmdType.getNoteType(cmdType);
        this.noteFilter = noteFilter;
        this.dateFilter = dateFilter;
        this.timelineDays = timelineDays;
    }

    //METHODS-------------------------------------------
    /**
     * This method sorts {@code Note} object based on their attached {@code Budget} object amounts.
     *
     * @param budgets The {@code ArrayList} of {@code Note} objects with {@code Budget}
     *                objects attached that is to be sorted.
     */
    private void selectionSortBudgets(ArrayList<Task> budgets) {

        if(budgets.size()>1){
            for (int i=budgets.size()-1; i>0; i--) {
                if (budgets.get(i).getBudgetObject().getBudgetRevised() <
                        budgets.get(i-1).getBudgetObject().getBudgetRevised()) {
                    Task temp = budgets.get(i);
                    budgets.set(i, budgets.get(i-1));
                    budgets.set(i-1, temp);
                }
            }
        }
    }

    /**
     * This method sorts {@code Note} object based on their {@code Date} objects.
     *
     * @param notes The {@code ArrayList} of {@code Note} objects with that is to be sorted.
     */
    private void selectionSortDates(ArrayList<Task> notes)
            throws DateException {

        Date dateI = new Date();
        Date dateJ = new Date();
        if(notes.size()>1) {
            if(notes.get(notes.size()-1) instanceof Deadline) {
                dateI = ((Deadline) notes.get(notes.size()-1)).getTargetDate();
            } else if (notes.get(notes.size()-1) instanceof Event) {
                dateI = ((Event) notes.get(notes.size()-1)).getStartDate();
            } else {
                throw new DateException(dateI, "NoDate");
            }

            for (int i=notes.size()-1; i>0; i--) {

                if(notes.get(i-1) instanceof Deadline) {
                    dateJ = ((Deadline) notes.get(i-1)).getTargetDate();
                } else if (notes.get(i) instanceof Event) {
                    dateJ = ((Event) notes.get(i)).getStartDate();
                } else {
                    throw new DateException(dateJ, "NoDate");
                }

                if (dateI.before(dateJ)) {
                    Task temp = notes.get(i);
                    notes.set(i, notes.get(i - 1));
                    notes.set(i - 1, temp);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * This method assesses and filters {@code Note} objects based on their {@code Date} objects.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByDate(Task note) {

        if(this.dateFilter == null) {
            return true;
        }else {
            long duration = this.timelineDays * 86400000;
            Date start = this.dateFilter;
            Date end = new Date(this.dateFilter.getTime() + duration);
            if (note instanceof Deadline) {
                return ((Deadline) note).getTargetDate().after(start) &&
                        ((Deadline) note).getTargetDate().before(end);
            } else if (note instanceof Event) {
                return ((Event) note).getStartDate().after(start) &&
                        ((Event) note).getStartDate().before(end);
            } else {
                return false;
            }
        }
    }

    /**
     * This method assesses and filters {@code Note} objects based on their completion statuses.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByStatus(Task note) {

        return switch (this.noteFilter) {
            case "O" -> !note.getIsDone();
            case "C" -> note.getIsDone();
            case "A" -> true;
            default -> false;
        };
    }

    /**
     * This method presents the results from the filtration and prints the selected {@code Note} objects if any.
     *
     * @param notes The {@code ArrayList} of {@code Note} objects with that is to be printed.
     */
    private void printResults(ArrayList<Task> notes)
            throws CommandException {

        String noteName = NoteType.getLowercaseNamePlural(this.noteType.toString());
        String noteVerb = NoteType.getVerb(this.noteType.toString());
        if(notes.size() == 0) {
            if(this.dateFilter == null) {
                switch (this.noteFilter) {
                    case "O" -> System.out.print("\tYou have no outstanding ");
                    case "C" -> System.out.print("\tYou have no completed ");
                    case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                }
                System.out.println(noteName + ".");
            } else {
                switch (this.noteFilter) {
                    case "O" -> System.out.print("\tYou have no outstanding ");
                    case "C" -> System.out.print("\tYou have no completed ");
                    case "A" -> System.out.print("\tYou have no ");
                }
                System.out.println(noteName + " " + noteVerb + " " + TASK_DATE.format(this.dateFilter) + ".");
            }
        } else {
            System.out.println("\tHere are the " + noteName + " you told me to note:-");
            for (Task note: notes) {
                note.printList();
            }
            System.out.print("\n");
        }
    }

    /**
     * This method executes the function of the {@code ListCommand} object.
     *
     * @param dukeNotes The {@code DukeList} object that holds the notes managed by {@code Duke}.
     * @param dukeStorage The {@code DukeStorage} object that holds access to the saved files of {@code Duke}.
     */
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage)
            throws CommandException, DateException {

        ArrayList<Task> notes = new ArrayList<Task>();
        DukeUI.printDivider();

        for(Task note : dukeNotes.getNotes()) {
            if(filterByStatus(note)) {
                if(filterByDate(note)) {
                    if(CmdType.getKey(this.cmdType).toString().equals("LISTBUDGETS")) {
                        if(note.getBudgetObject() != null){
                            notes.add(note);
                            selectionSortBudgets(notes);
                        }
                    } else {
                        switch (NoteType.getConstructor(this.noteType.toString())) {
                            case "Bill" -> {
                                if (note instanceof Bill) {
                                    notes.add(note);
                                    selectionSortDates(notes);
                                }
                            }
                            case "Birthday" -> {
                                if (note instanceof Birthday) {
                                    notes.add(note);
                                    selectionSortDates(notes);
                                }
                            }
                            case "Deadline" -> {
                                if (note instanceof Deadline) {
                                    notes.add(note);
                                    selectionSortDates(notes);
                                }
                            }
                            case "Event" -> {
                                if (note instanceof Event) {
                                    notes.add(note);
                                    selectionSortDates(notes);
                                }
                            }
                            case "Shoplist" -> {
                                if (note instanceof Shoplist) {
                                    notes.add(note);
                                    selectionSortBudgets(notes);
                                }
                            }
                            case "Todo" -> {
                                if (note instanceof Todo) {
                                    notes.add(note);
                                }
                            }
                            case "Wedding" -> {
                                if (note instanceof Wedding) {
                                    notes.add(note);
                                    selectionSortDates(notes);
                                }
                            }
                            default -> notes.add(note);
                        }
                    }
                }
            }
        }
        this.printResults(notes);
        DukeUI.printDivider();
        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        DukeUI.printDivider();
    }
}
