package duke.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.io.Savable;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public String getFilePath() {
        return filePath;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readAll() throws IOException{
        ArrayList<String> arrFile = new ArrayList<String>();
        File f = new File(filePath);

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String entry = s.nextLine();
            arrFile.add(entry);
        }

        return arrFile;
    }

    public void writeln(Savable s) throws IOException {
        this.write(s, true);
    }

    public void write(Savable s, boolean newLine) throws IOException {
        String str = s.toSavableString();
        this.write(str, true);
    }

    public void write(String text, boolean newLine) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text);
        if (newLine) {
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public void create() throws IOException {
        this.write("", false);
    }

}
