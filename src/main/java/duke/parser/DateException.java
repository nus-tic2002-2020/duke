package duke.parser;

import duke.commands.CommandException;
import duke.notes.event.Event;
import duke.ui.DukeUI;
import java.util.Date;

/**
 * An extension of the {@code Exception} object that addresses exceptions in operations
 * pertaining to {@code Date} objects.
 *
 * @author tanqiuyu
 * @since 2020-09-16
 */
public class DateException extends Exception {

    Date date;
    String message;
    Event event = null;

    /**
     * This method constructs a {@code DateException} object.
     *
     * @param date The {@code Date} object that caused the exception.
     * @param message The code indicating the reason behind the exception.
     */
    public DateException(Date date, String message){
        super();
        this.date = date;
        this.message = message;
    }

    /**
     * This method constructs a {@code DateException} object.
     *
     * @param date The {@code Date} object that caused the exception.
     * @param message The code indicating the reason behind the exception.
     * @param event The {@code Event} object that is relevant to the {@code Date} object causing the exception.
     */
    public DateException(Date date, String message, Event event){
        super();
        this.date = date;
        this.message = message;
        this.event = event;
    }

    /**
     * This method initialises a {@code DateException} object.
     */
    @SuppressWarnings("unused")
    public DateException(){
        super();
    }

    /**
     * This method returns the code indicating the reason behind the exception.
     *
     * @return String The code indicating the reason behind the exception.
     */
    public String getMessage(){
        return message;
    }

    /**
     * This method prints an explanation specifying the {@code Date} object in question and
     * reason behind the exception.
     *
     * @exception CommandException If there are errors in the command input.
     */
    public void printExplanation()
            throws CommandException {

        System.out.println("    I understand what you meant by...\n");
        DukeUI.commandWrap(DukeUI.INPUT_TIME.format(date), 66);

        switch (this.message) {
            case "TargetDate" -> {
                DukeUI.printDivider();
                System.out.println("    But the target date you are trying to set has already passed.");
                System.out.println("    I can't send you back in time, yet.");
                System.out.println("    Please enter another date & time in the future.");
            }
            case "StartB4Now" -> {
                DukeUI.printDivider();
                System.out.println("    But the Event Start Date-Time you are trying to set has already passed.");
                System.out.println("    I can't send you back in time, yet.");
                System.out.println("    Please enter another date & time in the future.");
            }
            case "StartAFEnd" -> {
                DukeUI.printDivider();
                System.out.println("    But the event start you are trying to set is after the event end.");
                System.out.println("    I can't turn time backwards, yet.");
                System.out.println("    Please enter another date & time earlier than the event end.");
            }
            case "NoStartDate" -> {
                DukeUI.printDivider();
                System.out.println("    But the event start date & time hasn't been set yet.");
                System.out.println("    I can't predict the start of events, yet.");
                System.out.println("    Please set the Event Start Date-Time first, before the Event End Date-Time.");
            }
            case "EndB4Now" -> {
                DukeUI.printDivider();
                System.out.println("    But the Event End Date-Time you are trying to set has already passed.");
                System.out.println("    I can't send you back in time, yet.");
                System.out.println("    Please enter another date & time in the future.");
            }
            case "EndB4Start" -> {
                DukeUI.printDivider();
                System.out.println("    But the event end you are trying to set is before the event start.");
                System.out.println("    I can't turn time backwards, yet.");
                System.out.println("    Please enter another date & time later than the event start.");
            }
            case "EventsClash" -> {
                DukeUI.printDivider();
                System.out.println("    But the event you are trying to set clashes with:");
                this.event.printList();
                System.out.println("    Please consider only either event to attend.");
            }
            case "DoneB4Start" -> {
                System.out.println("    But the event you are trying to set done hasn't even started.");
                this.event.printList();
                System.out.println("    Please consider deleting the event instead.");
            }
            case "NoStart" -> {
                DukeUI.printDivider();
                System.out.println("    But the note you are trying to edit does not have a start date.");
                System.out.println("    There might have been an error in the command.");
                System.out.println("    Please identify the correct note or correct date to edit.");
            }
            case "NoEnd" -> {
                DukeUI.printDivider();
                System.out.println("    But the note you are trying to edit does not have an end date.");
                System.out.println("    There might have been an error in the command.");
                System.out.println("    Please identify the correct note or correct date to edit.");
            }
            case "NoTarget" -> {
                DukeUI.printDivider();
                System.out.println("    But the note you are trying to edit does not have a target date.");
                System.out.println("    There might have been an error in the command.");
                System.out.println("    Please identify the correct note or correct date to edit.");
            }
            case "NoDate" -> {
                DukeUI.printDivider();
                System.out.println("    But the note you are trying to access does not have dates.");
                System.out.println("    There might have been an error in the command.");
                System.out.println("    Please identify the correct note to access.");
            }
            default -> System.out.println("    " + this.message);
        }
        DukeUI.printDivider();
    }
}

