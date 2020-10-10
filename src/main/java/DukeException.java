
public class DukeException extends Exception {
	
	


	public DukeException(String msg) {
		if(msg=="") msg="OOPS!!! I'm sorry, but I don't know what that means :-(";
		   System.out.println (msg);

	    }

//	   public void toString() {
//		   switch(this.msg) {
//		   	case "todo":
//		   	case "event":
//		   	case "deadline":{
//		   		System.out.println ("OOPS!!! The description of a " + this.msg + " cannot be empty.");
//			   } break;
//		   	case "done":{
//		   		System.out.println ("OOPS!!! The description of a " + this.msg + " cannot be empty.");
//			   } break;
//			   default:{
//				   System.out.println ("OOPS!!! I'm sorry, but I don't know what that means :-(");
//			   }
//			   
//		   }
//		   
//		   
//		   
//	   }
}
