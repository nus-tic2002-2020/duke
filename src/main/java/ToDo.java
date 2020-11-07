public class ToDo extends Task{
    public ToDo (String description,String taskType){
        super(description, taskType);
    }

    public ToDo(String description, String taskType, boolean isDone) {
        super(description, taskType, isDone);
    }

    //@Override
    public String getTaskListInfo() {
        return super.getTaskListInfo();
    }

    /*public String formatForFile() {
        return "T" + super.formatForFile() + super.getDescription();
    }
*/
}
