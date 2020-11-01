package task;

/**
 * Todo-subclass of Task-superclass
 */
public class ToDo extends Task {
    /**
     * Constructs Todo-class object
     *
     * @param description Description of task
     */
    public ToDo(String description) {
        super(description);
        this.symbol = "[T]";
    }

    /**
     *
     * @return Returns symbol of task
     */
    public String getSymbol() {
        return symbol;
    }
}
