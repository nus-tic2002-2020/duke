import java.time.*;
import java.time.format.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public String description;
    public boolean isDone;
    public String whatTag;
    public String timeDate;
    public CharSequence charDate;
    public LocalDate formattedDate;

    /**
     *Handles parsing of user input based on todo, deadline and event
     *
     * uses pattern matching and used in TaskList.java
     */

    public Task(String description) throws IllegalInputException {


         this.isDone = false;
         Pattern pattern = Pattern.compile((".*" + "todo" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(description);
         Pattern pattern2 = Pattern.compile((".*" + "deadline" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher2 = pattern2.matcher(description);
         Pattern pattern3 = Pattern.compile((".*" + "Event" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher3 = pattern3.matcher(description);
         if (matcher.find()) { //todo
             String[] temp;
             temp = description.split("\\s",2);
             if (temp.length == 1){
                 System.out.println("Description cannot be empty");
                 throw new IllegalInputException();
             }
             this.description = temp[1];
             this.whatTag = "[T]";
             this.timeDate =" ";

             if (this.description.isBlank()){
                 System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                 throw new IllegalInputException();
             }
         }
        else if (matcher2.find()) { //deadline
             String[] temp;
             temp = description.split("\\s",2);
             System.out.println(temp);
             this.description = temp[1];
             System.out.println(temp[1]);
             this.whatTag = "[D]";
             temp = this.description.split("/by",2);
             this.description = temp[0];
             this.timeDate=temp[1];
             //timeDate prints 25/11/2020
             charDate = this.timeDate;
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yyyy");
             formattedDate = LocalDate.parse(charDate, formatter);
             this.timeDate ="(by: "+formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
             //this.timeDate="(by:"+this.timeDate+")";
         }
         else if (matcher3.find()) { //event
             String[] temp;
             temp = description.split("\\s",2);
             this.description = temp[1];
             this.whatTag = "[E]";
             temp = this.description.split("/at",2);
             this.description = temp[0];
             this.timeDate=temp[1];
            // this.timeDate="(at:"+this.timeDate+")";
             charDate = this.timeDate;
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yyyy");
             formattedDate = LocalDate.parse(charDate, formatter);
             this.timeDate ="(at: "+formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
         }
         else {
             System.out.println("from task.java ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
             throw new IllegalInputException();
         }
    }
    /**
     *Return tick or cross to denote done or not done
     *
     * @return tick or cross symbols
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    /**
     *Categorises user input 'T', 'D', 'E'
     *
     * @return Completion time of deadline
     */
    public String getTag(){
        return (whatTag);
    }

    /**
     *Return completion time of a for events
     *
     * @return Completion time
     */
    public String getTimeDate(){
        return (timeDate);
    }

}