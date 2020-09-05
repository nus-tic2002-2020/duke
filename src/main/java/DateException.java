import java.util.Date;

public class DateException extends Exception {

    Date date;
    String message;

    public DateException(){
        super();
    }

    public DateException(Date date, String message){
        super();
        this.date = date;
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void printExplanation() {
        DukeUI.printDivider();
        System.out.println("\tI understand what you meant by...\n");
        DukeUI.commandWrap(DukeUI.INPUT_DATE.format(date), 66);

        switch (this.message) {
            case "TargetDate" -> {
                System.out.println("\tBut the deadline you are trying to set has already passed.");
                System.out.println("\tI can't send you back in time, yet.");
                System.out.println("\tPlease enter as the deadline, another date & time in the future.");
            }
            case "StartB4Now" -> {
                System.out.println("\tBut the Event Start Date-Time you are trying to set has already passed.");
                System.out.println("\tI can't send you back in time, yet.");
                System.out.println("\tPlease enter as the Event Start Date-Time, another date & time in the future.");
            }
            case "StartAFEnd" -> {
                System.out.println("\tBut the event start you are trying to set is after the event end");
                System.out.println("\tI can't turn time backwards, yet.");
                System.out.println("\tPlease enter as the event start, another date & time earlier than the event end.");
            }
            case "NoStartDate" -> {
                System.out.println("\tBut the event start date & time hasn't been set yet.");
                System.out.println("\tI can't predict the start of events, yet.");
                System.out.println("\tPlease set the Event Start Date-Time first, before the Event End Date-Time.");
            }
            case "EndB4Start" -> {
                System.out.println("\tBut the event end you are trying to set is before the event start.");
                System.out.println("\tI can't turn time backwards, yet.");
                System.out.println("\tPlease enter as the event end, another date & time later than the event start.");
            }
            default -> {
                System.out.println("\t" + this.message);
            }
        }
        DukeUI.printDivider();
    }
}

