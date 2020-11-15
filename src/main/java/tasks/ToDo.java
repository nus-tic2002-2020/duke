package tasks;
import tasks.ToDo;

/**
 * ToDo task which does not have detail date and time.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is todo.
     */
    public ToDo (String description,String taskType){
        super(description, taskType);
    }

    /**
     * Constructor.
     *
     * @param description Task description.
     * @param taskType Task type is todo.
     * @param isDone Whether task is completed or not.
     */
    public ToDo(String description, String taskType, boolean isDone) {
        super(description, taskType, isDone);
    }

    /**
     * Return the information of the task.
     *
     * @return Information of task.
     */
    @Override
    public String getTaskListInfo() {
        return super.getTaskListInfo();
    }

}
