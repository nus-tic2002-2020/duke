import java.util.Scanner;  //
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String list[]=new String[100];
        chat(list,0);
    }
    public static void chat(String[] list,int number) {
    	Scanner userinput = new Scanner(System.in);  // Create a Scanner object
        String value=(userinput.nextLine()).trim();
        String ln="  ____________________________________________________________\n";
        
        if(value.equals("bye")) {
        	System.out.println(ln+"   Bye. Hope to see you again soon!\n"+ln);  // Output user input
        }else if(value.equals("list")) {        	
            for (int a = 0; a<number; a++){
                System.out.println(a+1 + ". " + list[a]);
            }
        }else {
        	list[number]=value;
        	number++;
        	System.out.println(ln+"  added: " +value+"\n"+ln);
        	chat(list,number);
        	// Output user input
        }
          }
}
