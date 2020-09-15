import java.util.Scanner;  //
public class Duke {
	private static Task[] task= new Task[100];
	private static int count=0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        chat();
    }
    
    public static void chat() {
    	Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String userinput=(scan.nextLine()).trim();
        String ln="  ____________________________________________________________\n";
        
        if(userinput.length()==0) {
        	System.out.println("Invalid input");
        	chat();
        }
        
        String[] _userinput = userinput.split(" ");
        
        switch(_userinput[0]) {
        	case "bye":{
        		System.out.println(ln+"   Bye. Hope to see you again soon!\n"+ln);
        	} break;
        	case "done":{
        		int Tasknum=0;
        		try {
        			Tasknum = Integer.parseInt(_userinput[1]);
    			}
    			catch (NumberFormatException e)
    			{
    				System.out.println("Invalid task number");
    				chat();
    			}
        		if(Tasknum>count)
        			System.out.println("Invalid task number");
        		task[Tasknum-1].markDone();
        		System.out.println(ln+" Nice! I've marked this task as done:\n"
        				+"[" + task[Tasknum-1].icon()+"] "+ task[Tasknum-1].getTitle()
        				+"\n"
        				+ln);
            	chat();
        	} break;
        	case "list":{
        		int n=1;
        		for (int a = 0; a<count; a++) {
                    System.out.println(n + ". [" + task[a].icon()+"] "+ task[a].getTitle());
                    n++;
                }
            	chat();
        	} break;
        	default:{
        		
        		task[count]=new Task(userinput);
            	count++;
            	System.out.println(ln+"  added: " +userinput+"\n"+ln);        		
            	chat();
        	}        	
        }
     }
}
