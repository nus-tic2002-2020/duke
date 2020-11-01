public class ToDo extends Task{
    /**
     * Deadline class syntax format - deadline /by
     * Event class syntax format - event /at
     * Todo class syntax format - todo
     */
    public ToDo(String description) { // description of Task description
        super(description);
    }

    public ToDo(String description, Boolean status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return ( "[T]" + super.toString());
    }


}
