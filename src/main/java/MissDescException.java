
public class MissDescException extends Exception {
	
	


	public MissDescException(String msg) {
		   System.out.println ("OOPS!!! The description of a "+msg+" cannot be empty.");
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
