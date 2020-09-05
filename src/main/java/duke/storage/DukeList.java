package duke.storage;

import duke.notes.*;
import java.util.ArrayList;

public class DukeList {

    private ArrayList<Task> notes;

    public DukeList(ArrayList<Task> tasks) {
        this.notes = tasks;
    }

    public DukeList() {
        notes = new ArrayList<Task>();
    }

    public ArrayList<Task> getNotes() {
        return this.notes;
    }

    public void addToList(Task task) {
        this.notes.add(task);
    }

    public void removeFromList(Task task) {
        this.notes.remove(task);
    }
}
