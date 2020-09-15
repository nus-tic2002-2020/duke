public class Task {
	protected boolean isDone;
	protected String title;
//	protected int count;
	
	public Task(String title) {
		this.isDone=false;
		this.title=title;
	}
	
	public void markDone() {
		this.isDone=true;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String icon() {
		 return (isDone ? "¡Ì" : "X");
	}

}