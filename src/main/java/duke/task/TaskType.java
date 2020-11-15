package duke.task;

/**
 * Task type enum.
 * Each enum has a matching command text and task code.
 */
public enum TaskType {

    DEFAULT("task", ""),
    EVENT("event", "E"),
    DEADLINE("deadline", "D"),
    TODO("todo", "T");

    private String command;
    private String code;

    /**
     * Get TaskType enum by code
     * @param code usually as single character char e.g. E
     * @return TaskType enum, null if code not found
     */
    public static TaskType valueOfCode(String code) {
        for (TaskType t: values()) {
            if (t.code.equals(code.toUpperCase())) {
                return t;
            }
        }
        return null;
    }

    /**
     * Getter for each enum's command text
     * @return command text
     */
    public String getCommand() {
        return command;
    }

    /**
     * Getter for each enum's task code
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Constructor for each enum
     * @param command text of enum
     * @param code for task type
     */
    TaskType(String command, String code) {
        this.command = command;
        this.code = code;
    }

}
