package duke.task;

public enum TaskType {

    DEFAULT("task", ""),
    EVENT("event", "E"),
    DEADLINE("deadline", "D"),
    TODO("todo", "T");

    private String command;
    private String code;

    public static TaskType valueOfCode(String code) {
        for (TaskType t: values()) {
            if (t.code.equals(code.toUpperCase())) {
                return t;
            }
        }
        return null;
    }

    public String getCommand() {
        return command;
    }

    public String getCode() {
        return code;
    }

    TaskType(String command, String code) {
        this.command = command;
        this.code = code;
    }


}
