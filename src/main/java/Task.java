public abstract class Task {
    protected String description;
    protected boolean done;
    protected String symbol;


    public Task(String description) {
        setDescription(description);
        this.done = false;
        setSymbol();
    }

    public String getDone() {
        if (done == false) {
            return ("[✗]");
        }
        else {
            return ("[✓]");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return(description);
    }

    //empty method for overriding
    public String getDateAndTime() {
        return null;
    };

    public abstract void printAdd(int storeSize);
    public abstract void printDelete(int storeSize);
    public abstract void setDone();
    public abstract String getSymbol();
    public abstract void setSymbol();

}
