public class ToDo extends Task{

    public ToDo(String description) { // description of Task description
        super(description);
    }

    public ToDo(String description, Boolean status) {
        super(description, status);
    }

    @Override
    public String toString() {
        return ( "[T]" + super.toString()); // T acronym = t0d0
    }
}
