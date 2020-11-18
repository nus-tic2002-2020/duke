package tasks;

import java.time.LocalDate;

/**
 * This is the mother class for all task types. It is also the default class for
 * type 'todo'.
 */

public class Task {
	protected boolean isDone;
	protected String title;
	protected String userInput;

	public Task(String title, String userInput) {
		this.isDone = false;
		this.title = title;
		this.userInput = userInput;
	}

	public void markDone() {
		this.isDone = true;
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

	public int getTime() {
		return 0;
	}

	public String getIcon() {
		return (isDone ? "Done" : "X");
	}

	public String printTask() {
		return "[T]" + "[" + getIcon() + "] " + getTitle();
	}

}