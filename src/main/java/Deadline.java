public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String getByDeadline(){
        return this.by;
    }


    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
