
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public String description;
    public boolean isDone;
    public String whatTag;
    public String timeDate;



    public Task(String description) throws IllegalInputException {


         this.isDone = false;
         Pattern pattern = Pattern.compile((".*" + "todo" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(description);
         Pattern pattern2 = Pattern.compile((".*" + "deadline" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher2 = pattern2.matcher(description);
         Pattern pattern3 = Pattern.compile((".*" + "Event" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher3 = pattern3.matcher(description);
         if (matcher.find()) {
             //todo
            //implement a split
             String[] temp;
             temp = description.split("\\s",2);
             if (temp.length == 1){
                 System.out.println("Description cannot be empty");
                 throw new IllegalInputException();
             }
             this.description = temp[1];
             this.whatTag = "[T]";
             this.timeDate ="";
             if (this.description.isBlank() == true){
                 System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                 throw new IllegalInputException();
             }
         }
        else if (matcher2.find()) {
            //deadline
             String[] temp;
             temp = description.split("\\s",2);
             this.description = temp[1];
             this.whatTag = "[D]";
             temp = this.description.split("/by",2);
             this.description = temp[0];
             this.timeDate=temp[1];
             this.timeDate="(by:"+this.timeDate+")";
         }
         else if (matcher3.find()) { //event
             String[] temp;
             temp = description.split("\\s",2);
             this.description = temp[1];
             this.whatTag = "[E]";
             temp = this.description.split("/at",2);
             this.description = temp[0];
             this.timeDate=temp[1];
             this.timeDate="(at:"+this.timeDate+")";
         }
         else {
             System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
             throw new IllegalInputException();
         }
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String getTag(){
        return (whatTag);
    }

}