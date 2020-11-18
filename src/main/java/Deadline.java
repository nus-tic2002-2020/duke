public class Deadline extends Task {

    protected String by;
    private char category;

    public Deadline(String description, String by) {
        super(description);
       /* String[] due = description.split("/by ");
        //String[] a = due[0].split("");
        this.description = due[1];
        this.by = due[1];*/
       String[] time = by.split(" ",2);
       this.by = time[1];
        this.category = 'D';
    }

    public  String getTime(){
        return this.by;
    }

    public char getD(){

        return this.category;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

