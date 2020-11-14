package task;

import enumerations.SymbolEnum;

/**
 * This is the ToDo-class. It is a subclass of Task.
 */
public class ToDo extends Task {

    /**
     * This creates the Todo-class object. It calls the superclass constructor, as well as sets the symbol of the object
     * to "T".
     *
     * @param description This is the description of the task as input by user in string format.
     */
    public ToDo(String description) {
        super(description);
        this.symbol = SymbolEnum.valueOf("T");
    }

    /**
     *This method returns the symbol of the task.
     *
     * @return Returns symbol of task.
     */
    public SymbolEnum getSymbol() {
        return symbol;
    }
}
