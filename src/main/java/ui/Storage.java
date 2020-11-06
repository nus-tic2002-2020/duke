package ui;
import ui.GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

import duke.Duke;
import exceptions.DukeException;
import exceptions.MissDescException;
import tasks.Task;

public class Storage extends Duke{
	private static String path = "F:/NUS/duke_new/data/";
	private static String filename = "duke.txt";
	private static String filePath = path + filename;
	
	public static void main() throws DukeException, IOException, MissDescException, BadLocationException {
		checkFile();
		readFile();	
	}
	
	/**
	* This method checks if duke.txt is there.
	* If its not there, it will create folder and file automatically.
	 * @throws IOException 
	*/
	
	public static void checkFile() throws DukeException, IOException {
		File dir = new File(path);
		if (dir.exists() == false) {
			dir.mkdirs();
		}

		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
	}
	
	/**
	* This method reads duke.txt, load and prints previous tasks.
	 * @throws MissDescException 
	 * @throws IOException 
	 * @throws BadLocationException 
	*/
	
	public static void readFile() throws DukeException, IOException, MissDescException, BadLocationException {
		try {
			Scanner in = new Scanner(new File(filePath));
			while (in.hasNext()) {
				String str=in.nextLine();
				if(str.length()>0) {
					Chat.processScanner(str,false);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(Chat.count==0) return;
		GUI.guiOutput("=========Existing List=========");
		Chat.listALL();
		GUI.guiOutput("=========Existing List=========");
	}
	
	public static void saveFile() throws Exception {
		 PrintWriter prw= new PrintWriter (filePath);
		 String inputs="";
		 for(Task t:Chat.task) {
			 inputs+=t.getUserInput()+"\n";
		 }
	      prw.println(inputs);          
	      prw.close();
	}

}
