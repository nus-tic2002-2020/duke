import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _       ___     ____       _____      \n"
                + "| |     |_ _|   | ___|     /  _  \\        \n"
                + "| |      | |    | |__     /  /_\\  \\      \n"
                + "| |      | |    |___ |   /  ____   \\      \n"
                + "| |__   _| |_    __| |  /  /     \\  \\    \n"
                + "|____| |_____|  |____| /__/       \\__\\   \n";

        String input = "blank";
        Scanner in = new Scanner(System.in);
        System.out.println(logo + "\nHello, Lisa here~\nHow can I help you today?");

        while (input.equalsIgnoreCase("Bye") == false) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("Bye") == false) {
                System.out.println(input);
            }
            else {
                System.out.println("Goodbye~ Hope to see you again soon! :3");
            }
        }
    }
}
