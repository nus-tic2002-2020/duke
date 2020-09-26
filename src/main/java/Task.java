import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String whatTag;
    protected String timeDate;



    public Task(String description) {

         this.isDone = false;
         Pattern pattern = Pattern.compile((".*" + "todo" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(description);
         Pattern pattern2 = Pattern.compile((".*" + "deadline" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher2 = pattern2.matcher(description);
         Pattern pattern3 = Pattern.compile((".*" + "Event" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher3 = pattern3.matcher(description);
         if (matcher.find()) {
            //implement a split
             String[] temp;
             temp = description.split("\\s",2);
             this.description = temp[1];
             this.whatTag = "[T]";
             this.timeDate ="";
         }
        else if (matcher2.find()) {
             String[] temp;
             temp = description.split("\\s",2);
             this.description = temp[1];
             this.whatTag = "[D]";
             temp = this.description.split("/by",2);
             this.description = temp[0];
             this.timeDate=temp[1];
             this.timeDate="(by:"+this.timeDate+")";
         }
         else if (matcher3.find()) {
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

             this.whatTag = "[undefined tag]";
         }
    }

   // public Task() {
     //   this.list = new String[100];
       // this.done = new boolean[100];
    //}

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String getTag(){
        return (whatTag);
    }
    //... do i need anything here?
    public void setDone (boolean state){
        this.isDone = state;
    }
}