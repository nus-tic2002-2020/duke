public class CommandException extends Exception {

    String message;

    public CommandException(){
        super();
    }

    public CommandException(String message){
        super();
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void printExplanation(String input) {

        DukeUI.printDivider();
        System.out.println("\tI don't understand what you mean by...\n");
        DukeUI.commandWrap(input, 66);
        System.out.println("\t" + this.message);
        DukeUI.suggestCommands();
        DukeUI.printDivider();
    }
}
