package tasks;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import exceptions.DukeException;


/**
 * Represents the Task List. It contains a list of tasks.
 * It has operations to add, delete, find and print the tasks from the list.
 */

public class TaskList {


    protected ArrayList<Task> tasks;


    /**
     * This is the constructor of the TaskList. It initialises the ArrayList for tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }


    /**
     * This is another constructor of the TaskList. This constructor loads from
     * the storage file. It has been parsed to an ArrayList of strings for loading.
     *
     * @param parsedFile This is the ArrayList of Strings
     * @throws DukeException This is thrown when the parsedFile does not follow the expected format
     *                       for the class Type section.
     */
    public TaskList(ArrayList<String[]> parsedFile) throws DukeException {
        this.tasks = new ArrayList<Task>();
        int size = 0;
        try {
            size = parsedFile.size();
        } catch (NullPointerException e) {
            System.out.println("Error: Null pointer from parsedFile in TaskList initialisation");
        }

        for (int i = 0; i < size; i++) {
            switch (parsedFile.get(i)[0]) {
                case "O" -> this.tasks.add(new Task(parsedFile.get(i)[2]));
                case "T" -> this.tasks.add(new ToDo(parsedFile.get(i)[2]));
                case "D" -> {
                    try {
                        this.tasks.add(new Deadline(parsedFile.get(i)[2],
                                (LocalDateTime.parse(parsedFile.get(i)[3], DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"))
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))));
                    } catch (DateTimeParseException e) {
                        System.out.println("Timing for added deadline invalid," + " using the time now");
                        this.tasks.add(new Deadline(parsedFile.get(i)[2],
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
                    }
                }

                case "E" -> {
                    try {
                        int index;
                        String at;
                        String end;
                        index = parsedFile.get(i)[3].lastIndexOf(" - ");
                        end = parsedFile.get(i)[3].substring(index);
                        at = parsedFile.get(i)[3].substring(0, index);

                        this.tasks.add(new Event(parsedFile.get(i)[2], (LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))) + end));

                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        System.out.println("Timing for added event invalid," + " using the time now");
                        this.tasks.add(new Event(parsedFile.get(i)[2],
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " - " +
                                        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))));
                    }

                }
                default -> throw new DukeException("Error: File did not show task format.");
            }

            if (parsedFile.get(i)[1].equals("1")) {
                this.tasks.get(this.tasks.size() - 1).changeDoneTo(true);
            }

        }

    }


    /**
     * This is used to get the size of the Task List.
     *
     * @return size of Task List
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * This method returns the Task Object at the TaskList index
     *
     * @param index Starts from 0 till (size - 1).  Meant to indicate the Task's index to return
     * @return Task at the specific index of the ArrayList
     */
    //Maybe throw exception
    public Task get(int index) {
        return this.tasks.get(index);
    }


    /**
     * Delete the Task from Task List at the specific index.
     *
     * @param index tarts from 0 till (size - 1).  Meant to indicate the Task's index to delete.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.tasks.remove(index);
    }

    /**
     * This is to add a Task to the Task List. The type of task will depend on the taskType parameter
     *
     * @param taskType    This is meant for the different task subclasses
     * @param description This is meant for the description/activity of the task
     * @param secondPart  This is meant for Deadlines and Events tasks.
     * @throws DukeException This happens when the taskType is not one of the subclasses.
     */
    public void addTask(String taskType, String description, String secondPart) throws DukeException {

        switch (taskType) {
            case "task" -> this.tasks.add(new Task(description));
            case "todo" -> this.tasks.add(new ToDo(description));
            case "event" -> this.tasks.add(new Event(description, secondPart));
            case "deadline" -> this.tasks.add(new Deadline(description, secondPart));
            default -> throw new DukeException("Error: Incorrect Task Type in addTask Method");
        }

    }


    /**
     * This is to find Tasks that contain the keyword from the Task List
     *
     * @param keyword This is the keyword which the Task List will search for containing the keyword
     */
    public void findList(String keyword) {
        int size = this.tasks.size();
        int total = 0;
        String classType = switch (keyword) {
            case "Task" -> Task.class.toString();
            case "ToDo" -> ToDo.class.toString();
            case "Deadline" -> Deadline.class.toString();
            case "Event" -> Event.class.toString();
            default -> "";
        };

        System.out.println(System.lineSeparator() + "Tasks that contain the keyword \"" + keyword + "\":");
        for (int i = 0; i < size; i++) {

            if (this.tasks.get(i).toString().contains(keyword) || classType.equals(this.tasks.get(i).getClass().toString())) {
                System.out.println(System.lineSeparator() + (total + 1) + "." + this.tasks.get(i).toString());
            }

        }
    }


    /**
     * This prints the Tasks in the Task List
     */
    public void printList() {
        int size = this.tasks.size();
        if (size == 0) {
            System.out.println(System.lineSeparator() + "Task List is empty.");
            return;
        }

        System.out.println(System.lineSeparator() + "Task List:");
        for (int i = 0; i < size; i++) {
            System.out.println(System.lineSeparator() + (i + 1) + "." + this.tasks.get(i).toString());
        }

    }


    /**
     * This prints the total number of tasks in the task list
     */
    public void printNumberOfTasks() {
        int total = tasks.size();
        String ending;
        if (total != 1) {
            ending = " tasks in the list.";
        } else {
            ending = " task in the list";
        }

        System.out.println("Now you have " + total + ending);

    }

    /**
     * This clears all the tasks in the list.
     */
    public void clearAllTasks() {
        this.tasks.clear();
    }

}
