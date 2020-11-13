package ui;

public class helpPage {


    public static void printHelpPage1 () {
        System.out.println("Duke understands only certain command.");
        System.out.println("Type h or help for help page.");
        UI.printLine();
        System.out.println("Please key in your command :");
    }

    public static void printHelpPage2 () {
        System.out.println("You may use the following commands to communicate with me");
        System.out.println("1. todo -> eg. todo read a book ");
        System.out.println("2. event ---/at dd/MM/yyyy HHmm -> eg. event attend meeting /at 22/04/2021 1900");
        System.out.println("3. deadline ---/by dd/MM/yyyy HHmm -> eg. deadline return a book /by 22/12/2020 0900");
        System.out.println("4. list -> eg. list  " + "** This will print out all the task." );
        System.out.println("5. delete -> eg. delete 2  " + " ** Delete task listed as number 2.");
        System.out.println("6. done -> eg. done 2  " + "** Mark task listed as number 2 as done.");
        System.out.println("7. find -> eg. find book  " + "** Find task with keyword book.");
        System.out.println("8. save -> eg. save  " + "** Save the task entered as text file");
        System.out.println("9. bye -> eg. bye  " + "** To terminate the programme");
        System.out.println("Type help to retrieve this help page again.");
        UI.printLine();
        System.out.println("Please key in your command :");
    }
}
