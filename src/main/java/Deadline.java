public class Deadline extends Task{

    protected String timecheck;

    public Deadline (String description, String timecheck) {
        super(description);
        this.timecheck = timecheck;
    }

    @Override
    public String toString(){
        return ( "[D]" + super.toString() + " (by: " + timecheck + ")");
    }

}
