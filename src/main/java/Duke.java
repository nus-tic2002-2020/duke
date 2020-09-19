import java.util.Scanner;  //
public class Duke {
	private static Task[] task= new Task[100];
	private static int count=0;
    private static String ln="  ____________________________________________________________\n";
    
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        chat();
    }
    public static void printAdded(String content) {
    	int a=count+1;
    	System.out.println(ln+" Got it. I've added this task: ");
		System.out.println(content);
    	System.out.println("Now you have "+a+" tasks in the list.");
		System.out.println(ln);
    }
    public static void chat() {
    	Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String userinput=(scan.nextLine()).trim();
        
        if(userinput.length()==0) {
        	System.out.println("Invalid input");
        	chat();
        }
        
        String[] _userinput = userinput.split(" ",2);
        String input_type=(_userinput[0]).trim();
        
        switch(input_type) {
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
        	case "deadline":{
        		String[] dl=_userinput[1].split("/");
        		String by[]=(dl[1].split(" ",2));
        		task[count]=new Deadline((dl[0]).trim(),by[1].trim());
        		printAdded(task[count].toString());
            	count++;
            	chat();
        	} break;
        	case "event":{
        		String[] dl=_userinput[1].split("/");
        		String at[]=(dl[1].split(" ",2));
        		task[count]=new Event((dl[0]).trim(),at[1].trim());
        		printAdded(task[count].toString());
            	count++;
            	chat();
        	} break;
        	case "todo":{
        		task[count]=new Task(_userinput[1]);
        		printAdded(task[count].toString());
            	count++;
        		chat();
        	}
        	default:{        		
        		task[count]=new Task(userinput);
            	count++;
            	System.out.println(ln+"  added: " +userinput+"\n"+ln);        		
            	chat();
        	}        	
        }
     }
}
