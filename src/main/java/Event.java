public class Event extends Task {

    protected String at;
    private char category;

    public Event(String description, String by) {
        super(description);
        /*String[] due = description.split("/at ");
        this.description = due[0];
        this.at = due[1];*/
        String[] place = by.split(" ",2);
        this.at = place[1];
        this.category = 'E';
    }
    public char getD(){
        return this.category;
    }

    public String getTime(){
        return this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
