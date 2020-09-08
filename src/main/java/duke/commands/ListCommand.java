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

    //CONSTRUCTORS--------------------------------------
    public ListCommand(ArrayList<String> inputs) {
        super(inputs);
        this.filterType = inputs.get(1);
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

    private void sortBetweenDeadlinesEvents(ArrayList<Deadline> deadlines, ArrayList<Event> events, ArrayList<Task> upcomings) throws CommandException {

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

    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException, ParseException {

        DukeUI.printDivider();
        switch (CmdType.getKey(this.cmdType).toString()) {
            case "LISTBILLS" -> {
                ArrayList<Task> bills = new ArrayList<Task>();
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Bill){
                        switch (this.filterType) {
                            case "A":
                                bills.add((Bill) note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    bills.add((Bill) note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    bills.add((Bill) note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(bills.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any bill payments yet.");
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
                        switch (this.filterType) {
                            case "A":
                                birthdays.add((Birthday)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    birthdays.add((Birthday)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    birthdays.add((Birthday)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(birthdays.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any birthdays yet.");
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
                        switch (this.filterType) {
                            case "A":
                                budgets.add(note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    budgets.add(note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    budgets.add(note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(budgets.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any budgets yet.");
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
                        switch (this.filterType) {
                            case "A":
                                deadlines.add((Deadline)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    deadlines.add((Deadline)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    deadlines.add((Deadline)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(deadlines.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any deadlines yet.");
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
                        switch (this.filterType) {
                            case "A":
                                events.add((Event)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    events.add((Event)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    events.add((Event)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(events.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any events yet.");
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
                        switch (this.filterType) {
                            case "A":
                                shoplists.add((Todo)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    shoplists.add((Todo)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    shoplists.add((Todo)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(shoplists.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any shopping list items yet.");
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
                        switch (this.filterType) {
                            case "A":
                                todos.add((Todo)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    todos.add((Todo)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    todos.add((Todo)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(todos.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any todo tasks yet.");
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
                        switch (this.filterType) {
                            case "A":
                                weddings.add((Wedding)note);
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    weddings.add((Wedding)note);
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    weddings.add((Wedding)note);
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                }
                if(weddings.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any weddings yet.");
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
                if (dukeNotes.getNotes().size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of anything yet.");
                } else {
                    System.out.println("\tHere are the things you told me to note:-");
                    for (Task note: dukeNotes.getNotes()) {
                        switch (this.filterType) {
                            case "A":
                                note.printList();
                                break;
                            case "O":
                                if (!note.getIsDone()) {
                                    note.printList();
                                }
                                break;
                            case "C":
                                if (note.getIsDone()) {
                                    note.printList();
                                }
                                break;
                            default:
                                throw new ParseException("Insufficient Attributes", 0);
                        }
                    }
                    System.out.println("\n");
                }
            }
            case "LISTNXT24" -> {
                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();
                ArrayList<Event> events = new ArrayList<Event>();
                ArrayList<Task> upcomings = new ArrayList<Task>();
                Date now = new Date();
                Date then = new Date(now.getTime()+86400000);
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Deadline){
                        if(((Deadline)note).getTargetDate().after(now) &&
                                ((Deadline)note).getTargetDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    deadlines.add((Deadline)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
                            }
                        }
                    } else if(note instanceof Event){
                        if(((Event)note).getStartDate().after(now) &&
                                ((Event)note).getStartDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    events.add((Event)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
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
                Date then = new Date(now.getTime()+172800000);
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Deadline){
                        if(((Deadline)note).getTargetDate().after(now) &&
                                ((Deadline)note).getTargetDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    deadlines.add((Deadline)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
                            }
                        }
                    } else if(note instanceof Event){
                        if(((Event)note).getStartDate().after(now) &&
                                ((Event)note).getStartDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    events.add((Event)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
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
                Date then = new Date(now.getTime()+259200000);
                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Deadline){
                        if(((Deadline)note).getTargetDate().after(now) &&
                                ((Deadline)note).getTargetDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    deadlines.add((Deadline)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        deadlines.add((Deadline)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
                            }
                        }
                    } else if(note instanceof Event){
                        if(((Event)note).getStartDate().after(now) &&
                                ((Event)note).getStartDate().before(then)){
                            switch (this.filterType) {
                                case "A":
                                    events.add((Event)note);
                                    break;
                                case "O":
                                    if (!note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                case "C":
                                    if (note.getIsDone()) {
                                        events.add((Event)note);
                                    }
                                    break;
                                default:
                                    throw new ParseException("Insufficient Attributes", 0);
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
