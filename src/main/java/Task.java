public abstract class Task {
    protected String Description;
    protected Boolean Completed;

    public Task(String desc){
        this.Description = desc;
        Completed = false;
    }
    public String getDesc(){
        return this.Description;
    }
    public Boolean getStatus(){
        return this.Completed;
    }
    public abstract char getCat();
    public void mark_completed(){
        this.Completed = true;
    }
}
