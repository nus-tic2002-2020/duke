package classes;

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
        setSymbol();
    }

    /**
     * Sets the symbol of task to "[T]" to signify Todo-subclass
     */
    public void setSymbol() {
        symbol = "[T]";
    }

    /**
     *
     * @return Returns symbol of task
     */
    public String getSymbol() {
        return symbol;
    }
}
