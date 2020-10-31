import java.time.LocalDate;

public class Task {
	protected boolean isDone;
	protected String title;
	protected String userInput;
//	protected int count;
	
	public Task(String title,String userInput) {
		this.isDone=false;
		this.title=title;
		this.userInput=userInput;
	}
	
	public void markDone() {
		this.isDone=true;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getUserInput() {
		return this.userInput;
	}
	
	public LocalDate getDate() {
		return null;
	}
	
	public String icon() {
		 return (isDone ? "¡Ì" : "X");
	}
    public String printTask() {
        return "[T]"+"["+icon()+"] " + getTitle();
    }

}