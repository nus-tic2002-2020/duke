package duke.commands;

import duke.notes.*;
import duke.notes.event.*;
import duke.notes.todo.*;
import duke.storage.*;
import duke.ui.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ListCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------
    private String filterType;
    private Date dateFilter = null;

    //CONSTRUCTORS--------------------------------------
    public ListCommand(ArrayList<String> inputs) throws ParseException {
        super(inputs);
        this.filterType = inputs.get(1);
        if(inputs.size() > 2){
            this.dateFilter = INPUT_DATE.parse(inputs.get(2) + " 00:00");
        }
    }

    //METHODS-------------------------------------------
    private void bubbleSortBudgets(ArrayList<Task> budgets) throws CommandException {

        for(int i=0; i<budgets.size()-1; i++) {
            for(int j=1; j<budgets.size()-i; j++) {
                if(budgets.get(j-1).getBudgetObject().getBudgetRevised() >
                        budgets.get(j).getBudgetObject().getBudgetRevised()) {
                    Task temp = budgets.get(j);
                    budgets.set(j, budgets.get(j-1));
                    budgets.set(j-1, temp);
                }
            }
        }
    }

    private void bubbleSortDeadlines(ArrayList<Deadline> deadlines) throws CommandException {

        for(int i=0; i<deadlines.size()-1; i++) {
            for(int j=1; j<deadlines.size()-i; j++) {
                if(deadlines.get(j-1).getTargetDate().after(deadlines.get(j).getTargetDate())) {
                    Deadline temp = deadlines.get(j);
                    deadlines.set(j, deadlines.get(j-1));
                    deadlines.set(j-1, temp);
                }
            }
        }
    }

    private void bubbleSortEvents(ArrayList<Event> events) throws CommandException {

        for(int i=0; i<events.size()-1; i++) {
            for(int j=1; j<events.size()-i; j++) {
                if(events.get(j-1).getStartDate().after(events.get(j).getStartDate())) {
                    Event temp = events.get(j);
                    events.set(j, events.get(j-1));
                    events.set(j-1, temp);
                }
            }
        }
    }

    private void sortBetweenDeadlinesEvents(ArrayList<Deadline> deadlines,
                                            ArrayList<Event> events, ArrayList<Task> upcomings) {

        int dIdx = 0; int eIdx = 0;
        while(dIdx<deadlines.size() && eIdx<events.size()){
            if(events.get(eIdx).getStartDate().before(deadlines.get(dIdx).getTargetDate())){
                upcomings.add(events.get(eIdx));
                eIdx++;
            } else {
                upcomings.add(deadlines.get(dIdx));
                dIdx++;
            }
        }
        while(dIdx<deadlines.size()){
            upcomings.add(deadlines.get(dIdx));
            dIdx++;
        }
        while(eIdx<events.size()){
            upcomings.add(events.get(eIdx));
            eIdx++;
        }
    }

    private boolean filterByStatus(Task note) {

        return switch (this.filterType) {
            case "O" -> !note.getIsDone();
            case "C" -> note.getIsDone();
            case "A" -> true;
            default -> false;
        };
    }

    private boolean filterByDate(Task note, Date start, long duration) {

        Date end = new Date(start.getTime()+duration);
        if(note instanceof Deadline) {
            return ((Deadline) note).getTargetDate().after(start) &&
                    ((Deadline) note).getTargetDate().before(end);
        } else if(note instanceof Event) {
            return ((Event)note).getStartDate().after(start) &&
                    ((Event)note).getStartDate().before(end);
        } else {
            return false;
        }
    }

    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, ParseException {

        DukeUI.printDivider();
        switch (CmdType.getKey(this.cmdType).toString()) {
            case "LISTBILLS" -> {
                ArrayList<Task> bills = new ArrayList<Task>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Bill){
                        if(filterByStatus(note)){
                            if(this.dateFilter == null || filterByDate(note, this.dateFilter, 86400000)) {
                                bills.add(note);
                            }
                        }
                    }
                }
                if(bills.size() == 0) {
                    if(this.dateFilter == null) {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                        }
                        System.out.println("bill payments.");
                    } else {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou have no ");
                        }
                        System.out.println("bill payments due on " + TASK_DATE.format(this.dateFilter) + ".");
                    }
                } else {
                    bubbleSortBudgets(bills);
                    System.out.println("\tHere are the bill payments you told me to note:-");
                    for (Task bill: bills) {
                        bill.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTBIRTHDAYS" -> {
                ArrayList<Event> birthdays = new ArrayList<Event>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Birthday){
                        if(filterByStatus(note)) {
                            if(this.dateFilter == null || filterByDate(note, this.dateFilter, 86400000)) {
                                birthdays.add((Birthday) note);
                            }
                        }
                    }
                }
                if(birthdays.size() == 0) {
                    if(this.dateFilter == null) {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                        }
                        System.out.println("birthdays.");
                    } else {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tThere are no outstanding ");
                            case "C" -> System.out.print("\tThere are no completed ");
                            case "A" -> System.out.print("\tThere are no ");
                        }
                        System.out.println("birthdays on " + TASK_DATE.format(this.dateFilter) + ".");
                    }
                } else {
                    bubbleSortEvents(birthdays);
                    System.out.println("\tHere are the birthdays you told me to note:-");
                    for (Task birthday: birthdays) {
                        birthday.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTBUDGETS" -> {
                ArrayList<Task> budgets = new ArrayList<Task>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note.getBudgetObject() != null){
                        if(filterByStatus(note)) {
                                budgets.add(note);
                        }
                    }
                }
                if(budgets.size() == 0) {
                    switch (this.filterType) {
                        case "O" -> System.out.print("\tYou have no outstanding ");
                        case "C" -> System.out.print("\tYou have no completed ");
                        case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                    }
                    System.out.println("budgets");
                } else {
                    bubbleSortBudgets(budgets);
                    System.out.println("\tHere are the budgets you told me to note:-");
                    for (Task budget: budgets) {
                        budget.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTDEADLINES" -> {
                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Deadline){
                        if(filterByStatus(note)) {
                            if(this.dateFilter == null || filterByDate(note, this.dateFilter, 86400000)) {
                                deadlines.add((Deadline)note);
                            }
                        }
                    }
                }
                if(deadlines.size() == 0) {
                    if(this.dateFilter == null) {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                        }
                        System.out.println("deadlines.");
                    } else {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou have no ");
                        }
                        System.out.println("deadlines on " + TASK_DATE.format(this.dateFilter) + ".");
                    }
                } else {
                    bubbleSortDeadlines(deadlines);
                    System.out.println("\tHere are the deadlines you told me to note:-");
                    for (Task deadline: deadlines) {
                        deadline.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTEVENTS" -> {
                ArrayList<Event> events = new ArrayList<Event>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Event){
                        if(filterByStatus(note)) {
                            if(this.dateFilter == null || filterByDate(note, this.dateFilter, 86400000)) {
                                events.add((Event)note);
                            }
                        }
                    }
                }
                if(events.size() == 0) {
                    if(this.dateFilter == null) {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                        }
                        System.out.println("events.");
                    } else {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tYou have no outstanding ");
                            case "C" -> System.out.print("\tYou have no completed ");
                            case "A" -> System.out.print("\tYou have no ");
                        }
                        System.out.println("events on " + TASK_DATE.format(this.dateFilter) + ".");
                    }
                } else {
                    bubbleSortEvents(events);
                    System.out.println("\tHere are the events you told me to note:-");
                    for (Task event: events) {
                        event.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTSHOPLISTS" -> {
                ArrayList<Task> shoplists = new ArrayList<Task>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Shoplist){
                        if(filterByStatus(note)) {
                            shoplists.add((Todo)note);
                        }
                    }
                }
                if(shoplists.size() == 0) {
                    switch (this.filterType) {
                        case "O" -> System.out.print("\tYou have no outstanding ");
                        case "C" -> System.out.print("\tYou have no completed ");
                        case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                    }
                    System.out.println("shopping list items.");
                } else {
                    bubbleSortBudgets(shoplists);
                    System.out.println("\tHere are the shopping list items you told me to note:-");
                    for (Task shoplist: shoplists) {
                        shoplist.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTTODOS" -> {
                ArrayList<Todo> todos = new ArrayList<Todo>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Todo){
                        if(filterByStatus(note)) {
                                todos.add((Todo)note);
                        }
                    }
                }
                if(todos.size() == 0) {
                    switch (this.filterType) {
                        case "O" -> System.out.print("\tYou have no outstanding ");
                        case "C" -> System.out.print("\tYou have no completed ");
                        case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                    }
                    System.out.println("todo tasks.");
                } else {
                    System.out.println("\tHere are the todo tasks you told me to note:-");
                    for (Task todo: todos) {
                        todo.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTWEDDINGS" -> {
                ArrayList<Event> weddings = new ArrayList<Event>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Wedding){
                        if(filterByStatus(note)) {
                            if(this.dateFilter == null || filterByDate(note, this.dateFilter, 86400000)) {
                                weddings.add((Wedding)note);
                            }
                        }
                    }
                }
                if(weddings.size() == 0) {
                    if(this.dateFilter == null) {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tThere are no outstanding ");
                            case "C" -> System.out.print("\tThere are no completed ");
                            case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                        }
                        System.out.println("weddings.");
                    } else {
                        switch (this.filterType) {
                            case "O" -> System.out.print("\tThere are no outstanding ");
                            case "C" -> System.out.print("\tThere are no completed ");
                            case "A" -> System.out.print("\tThere are no ");
                        }
                        System.out.println("weddings on " + TASK_DATE.format(this.dateFilter) + ".");
                    }
                } else {
                    bubbleSortEvents(weddings);
                    System.out.println("\tHere are the weddings you told me to note:-");
                    for (Task wedding: weddings) {
                        wedding.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTNOTES" -> {
                ArrayList<Task> notes = new ArrayList<Task>();
                for (Task note: dukeNotes.getNotes()) {
                    if(filterByStatus(note)) {
                            notes.add(note);
                    }
                }
                if (notes.size() == 0) {
                    switch (this.filterType) {
                        case "O" -> System.out.print("\tThere are no outstanding ");
                        case "C" -> System.out.print("\tThere are no completed ");
                        case "A" -> System.out.print("\tYou haven't asked me to take note of any ");
                    }
                    System.out.println("notes.");
                } else {
                    System.out.println("\tHere are the things you told me to note:-");
                    for (Task note: notes) {
                        note.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTNXT24" -> {
                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();
                ArrayList<Event> events = new ArrayList<Event>();
                ArrayList<Task> upcomings = new ArrayList<Task>();
                Date now = new Date();
                for(Task note: dukeNotes.getNotes()) {
                    if(filterByDate(note, now, 86400000)) {
                        if(filterByStatus(note)) {
                            if(note instanceof Deadline){
                                deadlines.add((Deadline)note);
                            } else if(note instanceof Event) {
                                events.add((Event)note);
                            }
                        }
                    }
                }
                if(deadlines.size() == 0 && events.size() == 0) {
                    System.out.println("\tYou have no upcoming deadlines or events in the next 24 hours.");
                } else {
                    bubbleSortDeadlines(deadlines);
                    bubbleSortEvents(events);
                    sortBetweenDeadlinesEvents(deadlines, events, upcomings);
                    System.out.println("\tHere are the upcoming deadlines and events in the next 24 hours:-");
                    for (Task upcoming : upcomings) {
                        upcoming.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTNXT48" -> {
                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();
                ArrayList<Event> events = new ArrayList<Event>();
                ArrayList<Task> upcomings = new ArrayList<Task>();
                Date now = new Date();
                for(Task note: dukeNotes.getNotes()) {
                    if(filterByDate(note, now, 172800000)) {
                        if(filterByStatus(note)) {
                            if(note instanceof Deadline){
                                deadlines.add((Deadline)note);
                            } else if(note instanceof Event) {
                                events.add((Event)note);
                            }
                        }
                    }
                }
                if(deadlines.size() == 0 && events.size() == 0) {
                    System.out.println("\tYou have no upcoming deadlines or events in the next 48 hours.");
                } else {
                    bubbleSortDeadlines(deadlines);
                    bubbleSortEvents(events);
                    sortBetweenDeadlinesEvents(deadlines, events, upcomings);
                    System.out.println("\tHere are the upcoming deadlines and events in the next 48 hours:-");
                    for (Task upcoming : upcomings) {
                        upcoming.printList();
                    }
                    System.out.println("\n");
                }
            }
            case "LISTNXT72" -> {
                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();
                ArrayList<Event> events = new ArrayList<Event>();
                ArrayList<Task> upcomings = new ArrayList<Task>();
                Date now = new Date();
                for(Task note: dukeNotes.getNotes()) {
                    if(filterByDate(note, now, 259200000)) {
                        if(filterByStatus(note)) {
                            if(note instanceof Deadline){
                                deadlines.add((Deadline)note);
                            } else if(note instanceof Event) {
                                events.add((Event)note);
                            }
                        }
                    }
                }
                if(deadlines.size() == 0 && events.size() == 0) {
                    System.out.println("\tYou have no upcoming deadlines or events in the next 72 hours.");
                } else {
                    bubbleSortDeadlines(deadlines);
                    bubbleSortEvents(events);
                    sortBetweenDeadlinesEvents(deadlines, events, upcomings);
                    System.out.println("\tHere are the upcoming deadlines and events in the next 72 hours:-");
                    for (Task upcoming : upcomings) {
                        upcoming.printList();
                    }
                    System.out.println("\n");
                }
            }
        }
        DukeUI.printDivider();
        DukeUI.printCompleted();
        DukeUI.printOutstanding();
        DukeUI.printDivider();
    }
}
