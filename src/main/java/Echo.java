import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Echo {

    protected static final SimpleDateFormat taskDate = new SimpleDateFormat("dd-MMM-yyyy (E), HH:mm:ss");

    public static void commandWrap(String input, int limit) {
        System.out.print("\t\t\"");
        while(true) {

            if (input.length() < limit) {
                System.out.print(input);
                System.out.print("\"\n\n");
                break;
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.substring(i, i + 1).equals(" ")) {
                        lastSpace = i;
                    }
                }
                System.out.print(input.substring(0, lastSpace) + "\n\t\t ");
                input = input.substring(lastSpace+1);
            }
        }
    }

    public static void listWrap(String input, int limit, Date addDate) {

        boolean firstLine = true;
        while(true) {

            if (input.length() < limit) {
                if(firstLine) {
                    System.out.println(String.format("%1$-30s%2$29s",
                            input, "Added: " + taskDate.format(addDate)));
                    break;
                } else {
                    System.out.println("\t\t\t\t " + input);
                    break;
                }
            } else {
                int lastSpace = 0;
                for (int i = 0; i < limit; i++) {
                    if (input.substring(i, i + 1).equals(" ")) {
                        lastSpace = i;
                    }
                }
                if(firstLine) {
                    System.out.println(String.format("%1$-30s%2$29s",
                            input.substring(0, lastSpace), "Added: " +
                                    taskDate.format(addDate)));
                    input = input.substring(lastSpace + 1);
                    firstLine = false;

                } else {
                    System.out.println("\t\t\t\t " + input.substring(0, lastSpace));
                    input = input.substring(lastSpace + 1);
                }
            }
        }
    }
}
