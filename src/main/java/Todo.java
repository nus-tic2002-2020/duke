import java.util.Date;

public class Todo extends Note {

    //VARIABLES-----------------------------------------
    protected static int tasksOutstanding;
    protected static int tasksCompleted;


    //CONSTRUCTORS--------------------------------------
    public Todo(int serialNum, String description, Date addDate) {
        super(serialNum, description, addDate);
        tasksOutstanding++;
    }

    public Todo() {
        tasksOutstanding++;
    }


    //SET STATEMENTS------------------------------------
    @Override
    public boolean markAsDone(Date doneDate) {
        if(super.markAsDone(doneDate)) {
            tasksOutstanding--;
            tasksCompleted++;
            this.printList();
            return true;
        } else {
            this.printList();
            return false;
        }
    }

    public static void deleteNewNote(){
        tasksOutstanding--;
    }

    public void deleteExistingNote() {
        if(isDone){
            System.out.print("\tTask #" + this.serialNum + " was already done!");
            System.out.println("\t...deleting the task anyway.");
            tasksCompleted--;
        } else {
            System.out.println("\tNoted! I've deleted Task #" + this.serialNum + ".");
            tasksOutstanding--;
        }
    }


    //GET STATEMENTS------------------------------------
    public static int getTasksOutstanding() {
        return (tasksOutstanding);
    }

    public static int getTasksCompleted() {
        return (tasksCompleted);
    }
}
