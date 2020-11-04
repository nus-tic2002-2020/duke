package DukeTask;

public class ToDo extends Task{
    /**
     * DukeTask.Deadline class syntax format - deadline /by
     * DukeTask.Event class syntax format - event /at
     * Todo class syntax format - todo
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean status) {
         super(description, status);
    }

    @Override
    public String toString() {
         return ( "[T]" + super.toString());
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

}
