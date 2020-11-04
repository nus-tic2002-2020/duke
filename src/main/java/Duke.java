import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner; //
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//import DukeException;
public class Duke {

	public static void main(String[] args) throws Exception {

		Storage.main();
		Chat.main();
	}
}
