import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String whatTag;



    public Task(String description) {
        this.description = description;
        this.isDone = false;
         Pattern pattern = Pattern.compile((".*" + "todo" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(description);
         Pattern pattern2 = Pattern.compile((".*" + "deadline" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher2 = pattern2.matcher(description);
         Pattern pattern3 = Pattern.compile((".*" + "Events" + ".*"), Pattern.CASE_INSENSITIVE);
         Matcher matcher3 = pattern3.matcher(description);
         if (matcher.find()) {

             this.whatTag = "[T]";
         }
        else if (matcher2.find()) {
             this.whatTag = "[D]";
         }
         else if (matcher3.find()) {
             this.whatTag = "[E]";
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