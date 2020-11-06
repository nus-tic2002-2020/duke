package duke.task;

import java.time.LocalDate;
import java.util.Date;

public abstract class Task {
    /**Variables of task parent class*/
    protected String Description;
    protected Boolean Completed;

    /**
     * Constructor of task parent class
     *
     * @param desc Description of task parent class
     */
    public Task(String desc){
        this.Description = desc;
        Completed = false;
    }

    public void updateDesc(String new_entry){
        this.Description = new_entry;
    }

    /**
     * Return description variable task parent class
     *
     * @return Description of task
     */
    public String getDesc(){
        return this.Description;
    }

    /**
     * Return completion status of task parent class
     *
     * @return completion status of task
     */
    public Boolean getStatus(){
        return this.Completed;
    }

    /**
     * Mark a task's completion status as true
     */
    public void mark_completed(){
        this.Completed = true;
    }
    /**Abstract class of task parent class*/
    public abstract char getCat();
    public abstract String getTime();
    public abstract LocalDate getDate();
    public abstract void updateTime(String new_date);
    public abstract void incrementTime(int days);
}
