package duke.commands;

import duke.notes.NoteType;
import duke.notes.Note;
import duke.notes.event.Event;
import duke.notes.event.Birthday;
import duke.notes.event.Wedding;
import duke.notes.task.Bill;
import duke.notes.task.Deadline;
import duke.notes.task.Shoplist;
import duke.notes.task.Task;
import duke.parser.DateException;
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
    private String noteFilter = null;
    private String textFilter = null;
    private Date dateFilter = null;
    private Date addedFilter = null;
    private int timelineDays = 0;

    //CONSTRUCTORS--------------------------------------
    /**
     * This method constructs a {@code ListCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @exception CommandException If there are errors in the command input.
     */
    public ListCommand(String cmdType) throws CommandException {
        super(cmdType);
        this.noteType = CmdType.getNoteType(cmdType);
    }

    /**
     * This method constructs a {@code ListCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param dateFilter The date specified for {@code Note} objects to be displayed.
     * @param timelineDays The window based on number of days for {@code Note} objects to be displayed.
     * @exception CommandException If there are errors in the command input.
     */
    public ListCommand(String cmdType, Date dateFilter, int timelineDays)
            throws CommandException {
        super(cmdType);
        this.noteType = CmdType.getNoteType(cmdType);
        this.dateFilter = dateFilter;
        this.timelineDays = timelineDays;
    }

    /**
     * This method constructs a {@code ListCommand} object.
     *
     * @param cmdType The type of {@code DukeCommand} being constructed.
     * @param noteFilter The selection to filter {@code Note} objects based on their completion statuses.
     * @param textFilter The description text specified for {@code Note} objects to be displayed.
     * @param dateFilter The date specified for {@code Note} objects to be displayed.
     * @param timelineDays The window based on number of days for {@code Note} objects to be displayed.
     * @exception CommandException If there are errors in the command input.
     */
    public ListCommand(String cmdType, String noteFilter, String textFilter,
                       Date dateFilter, Date addedFilter, int timelineDays)
            throws CommandException {
        super(cmdType);
        this.noteType = CmdType.getNoteType(cmdType);
        this.noteFilter = noteFilter;
        this.textFilter = textFilter;
        this.dateFilter = dateFilter;
        this.addedFilter = addedFilter;
        this.timelineDays = timelineDays;
    }

    /**
     * This method initialises a {@code ListCommand} object.
     */
    @SuppressWarnings("unused")
    public ListCommand() {
        super();
    }

    //METHODS-------------------------------------------
    /**
     * This method sorts {@code Note} object based on their attached {@code Budget} object amounts.
     *
     * @param budgets The {@code ArrayList} of {@code Note} objects with {@code Budget}
     *                objects attached that is to be sorted.
     */
    void selectionSortBudgets(ArrayList<Note> budgets) {

        double budgetI;
        double budgetJ;

        if(budgets.size()>1){
            budgetI = budgets.get(budgets.size()-1).getBudgetObject().getBudgetRevised();
            for (int i=budgets.size()-1; i>0; i--) {
                budgetJ = budgets.get(i-1).getBudgetObject().getBudgetRevised();
                if (budgetI < budgetJ) {
                    Note temp = budgets.get(i);
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
    void selectionSortDates(ArrayList<Note> notes)
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
                    dateJ = ((Event) notes.get(i-1)).getStartDate();
                } else {
                    throw new DateException(dateJ, "NoDate");
                }

                if (dateI.before(dateJ)) {
                    Note temp = notes.get(i);
                    notes.set(i, notes.get(i-1));
                    notes.set(i-1, temp);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * This method assesses and filters {@code Note} objects based on their start or target {@code Date} objects.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByStartTargetDate(Note note) {

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
     * This method assesses and filters {@code Note} objects based on their added {@code Date} objects.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByAddedDate(Note note) {

        if(this.addedFilter == null) {
            return true;
        }else {
            long duration = this.timelineDays * 86400000;
            Date start = this.addedFilter;
            Date end = new Date(this.addedFilter.getTime() + duration);
            return note.getAddDate().after(start) && note.getAddDate().before(end);
        }
    }

    /**
     * This method assesses and filters {@code Note} objects based on their completion statuses.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByStatus(Note note) {

        if(this.noteFilter == null) {
            return true;
        } else {
            return switch (this.noteFilter) {
                case "O" -> !note.getIsDone();
                case "C" -> note.getIsDone();
                default -> false;
            };
        }
    }

    /**
     * This method assesses and filters {@code Note} objects based on their text descriptions.
     *
     * @param note The {@code Note} object that is to be assessed.
     * @return boolean True if the {@code Note} object fulfils the criteria and is to be included.
     */
    private boolean filterByText(Note note) {

        if(this.textFilter == null) {
            return true;
        } else {
            return note.getDescription().contains(textFilter);
        }
    }

    /**
     * This method presents the results from the filtration and prints the selected {@code Note} objects if any.
     *
     * @param notes The {@code ArrayList} of {@code Note} objects with that is to be printed.
     */
    private void printResults(ArrayList<Note> notes)
            throws CommandException {

        String noteName = NoteType.getLowercaseNamePlural(this.noteType.toString());
        String noteVerb = NoteType.getVerb(this.noteType.toString());

        if(notes.size() == 0) {

            String noteReport = "";
            if(this.noteFilter == null) {
                noteReport = "You haven't asked me to keep any " + noteName;
            } else if(this.noteFilter.equals("O")) {
                noteReport = "You have no outstanding " + noteName;
            } else if(this.noteFilter.equals("C")) {
                noteReport = "You have no completed " + noteName;
            }

            String textReport = "";
            if(this.textFilter != null) {
                textReport = " with the words \"" + this.textFilter + "\" in its description";
            }

            String dateReport = "";
            if(this.dateFilter != null) {
                dateReport = switch (CmdType.getKey(cmdType).toString()) {
                    case "LISTNXT24" -> " in the next 24 hours";
                    case "LISTNXT48" -> " in the next 48 hours";
                    case "LISTNXT72" -> " in the next 72 hours";
                    default -> " " + noteVerb + " " + NOTE_DATE.format(this.dateFilter);
                };
            }

            String addedReport = "";
            if(this.addedFilter != null) {
                addedReport = " that was added on " + NOTE_DATE.format(this.addedFilter);
            }

            DukeUI.standardWrap(noteReport + textReport + dateReport + addedReport + ".");

        } else {
            System.out.println("    Here are the " + noteName + " you told me to note:-");
            for (Note note: notes) {
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

        ArrayList<Note> notes = new ArrayList<>();
        DukeUI.printDivider();

        for(Note note : dukeNotes.getNotes()) {
            if(filterByStatus(note)) {
                if(filterByText(note)) {
                    if(filterByStartTargetDate(note)) {
                        if(filterByAddedDate(note)) {
                            if (CmdType.getKey(this.cmdType).toString().equals("LISTBUDGETS")) {
                                if (note.getBudgetObject() != null) {
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
                                    case "Task" -> {
                                        if (note instanceof Task) {
                                            notes.add(note);
                                        }
                                    }
                                    case "Wedding" -> {
                                        if (note instanceof Wedding) {
                                            notes.add(note);
                                            selectionSortDates(notes);
                                        }
                                    }
                                    case "Note" -> notes.add(note);
                                }
                            }
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
