package duke.commands;

import duke.budget.*;
import duke.notes.*;
import duke.notes.event.*;
import duke.notes.todo.*;
import duke.storage.*;
import duke.ui.*;
import java.util.ArrayList;

public class InfoCommand extends DukeCommand implements DukeUI {

    //VARIABLES-----------------------------------------


    //CONSTRUCTORS--------------------------------------
    public InfoCommand(ArrayList<String> inputs) {
        super(inputs);
    }

    //METHODS-------------------------------------------
    public void execute(DukeList dukeNotes, DukeStorage dukeStorage) throws CommandException {

        DukeUI.printDivider();
        switch (CmdType.getKey(this.cmdType).toString()) {
            case "COMMANDS" -> {
                DukeUI.showCommandList();
            }
            case "LISTBUDGETS" -> {

                ArrayList<Task> budgets = new ArrayList<Task>();

                for(Task note: dukeNotes.getNotes()) {
                    if(note.getBudgetObject() != null){
                        budgets.add(note);
                    }
                }

                if(budgets.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any budgets yet.");

                } else {
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
                    System.out.println("\tHere are the deadlines you told me to note:-");
                    for (Task budget : budgets) {
                        budget.printList();
                    }
                    DukeUI.printCompleted();
                    DukeUI.printOutstanding();
                }
            }
            case "LISTDEADLINES" -> {

                ArrayList<Deadline> deadlines = new ArrayList<Deadline>();

                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Deadline){
                        deadlines.add((Deadline)note);
                    }
                }

                if(deadlines.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any deadlines yet.");

                } else {
                    for(int i=0; i<deadlines.size()-1; i++) {
                        for(int j=1; j<deadlines.size()-i; j++) {
                            if(deadlines.get(j-1).getTargetDate().after(deadlines.get(j).getTargetDate())) {
                                Deadline temp = deadlines.get(j);
                                deadlines.set(j, deadlines.get(j-1));
                                deadlines.set(j-1, temp);
                            }
                        }
                    }
                    System.out.println("\tHere are the deadlines you told me to note:-");
                    for (Task deadline : deadlines) {
                        deadline.printList();
                    }
                    DukeUI.printCompleted();
                    DukeUI.printOutstanding();
                }
            }
            case "LISTEVENTS" -> {

                ArrayList<Event> events = new ArrayList<Event>();

                for(Task note: dukeNotes.getNotes()) {
                    if(note instanceof Event){
                        events.add((Event)note);
                    }
                }

                if(events.size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of any events yet.");
                } else {
                    for(int i=0; i<events.size()-1; i++) {
                        for(int j=1; j<events.size()-i; j++) {
                            if(events.get(j-1).getStartDate().after(events.get(j).getStartDate())) {
                                Event temp = events.get(j);
                                events.set(j, events.get(j-1));
                                events.set(j-1, temp);
                            }
                        }
                    }
                    System.out.println("\tHere are the events you told me to note:-");
                    for (Task event : events) {
                        event.printList();
                    }
                    DukeUI.printCompleted();
                    DukeUI.printOutstanding();
                }
            }
            case "LISTNOTES" -> {
                if (dukeNotes.getNotes().size() == 0) {
                    System.out.println("\tYou haven't asked me to take note of anything yet.");
                } else {
                    System.out.println("\tHere are the things you told me to note:-");
                    for (Task note : dukeNotes.getNotes()) {
                        note.printList();
                    }
                    DukeUI.printCompleted();
                    DukeUI.printOutstanding();
                }
            }
        }
        DukeUI.printDivider();
    }
}
