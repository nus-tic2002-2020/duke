package ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import duke.Duke;
import tasks.Task;

public class Storage extends Duke{
	private static String path = "F:/NUS/duke_new/data/";
	private static String filename = "duke.txt";
	private static String filePath = path + filename;
	
	public static void main() throws Exception {
		checkFile();
		readFile();	
	}
	
	/**
	* This method checks if duke.txt is there.
	* If its not there, it will create folder and file automatically.
	*/
	
	public static void checkFile() throws IOException {
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
	*/
	
	public static void readFile() throws Exception {
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
		System.out.println("=========Existing List=========");
		Chat.listALL();
		System.out.println("=========Existing List=========\n");
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
