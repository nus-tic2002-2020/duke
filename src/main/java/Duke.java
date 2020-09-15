import java.util.Scanner;  //
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
       chat();
    }
    public static void chat() {
    	Scanner userinput = new Scanner(System.in);  // Create a Scanner object
        String value=(userinput.nextLine()).trim();
        String ln="  ____________________________________________________________\n";
        
        if(value.equals("bye")) {
        	System.out.println(ln+"   Bye. Hope to see you again soon!\n"+ln);  // Output user input
        }else {
        	System.out.println(ln+"  " +value+"\n"+ln);
        	chat();
        	// Output user input
        }
          }
}
