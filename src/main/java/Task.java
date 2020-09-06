public class Task {
    private String description;
    private boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public void setDone() {
        done = true;
    }

    public String getDone() {
        if (done == false) {
            return ("[✗]");
        }
        else {
            return ("[✓]");
        }
    }

    public String getDescription() {
        return(description);
    }
}
