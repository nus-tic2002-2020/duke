public class ToDo extends Task{

    public ToDo(String description) { // description of Task description
        super(description);
    }

    @Override
    public String toString() {
        return ( "[T]" + super.toString()); // T acronym = t0d0
    }
}
