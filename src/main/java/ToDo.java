package duke.task;


public class ToDo extends Task {

    /**
     * Variables of ToDo class
     */
    protected String description;
    private char category;

    public ToDo(String description) {
        super(description);
        this.category = 'T';

    }

    /**
     * constructor of getTime which is null for ToDo
     */
    public String getTime(){
        return null;
    }

    /**
     * getter of category which will give 'T'
     */
    public char getD(){
        return this.category;
    }

    /**
     * Override of Print statement to return ToDo format
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}

