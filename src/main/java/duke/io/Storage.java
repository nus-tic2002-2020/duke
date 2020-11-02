package duke.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.io.Savable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public String getFilePath() {
        return filePath;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> load() throws IOException {
        return readAll(true);
    }

    public List<String> readAll(boolean createIfNotExist) throws IOException{
        ArrayList<String> arrFile = new ArrayList<String>();
        File f = new File(filePath);

        File parent = f.getParentFile();
        if (createIfNotExist) {
            if(!parent.exists()) {
                parent.mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String entry = s.nextLine();
            arrFile.add(entry);
        }

        return arrFile;
    }

    public void writeAll(List<Savable> savableList) throws IOException {
        int i = 0;
        for (Savable s: savableList) {
            if (i > 0) { // subsequent lines
                appendln(s);
            } else { // create first entry
                writeln(s);
            }
            i++;
        }
    }

    public void writeln(Savable s) throws IOException {
        this.write(s, true);
    }

    public void write(Savable s, boolean newLine) throws IOException {
        String str = s.toSavableString();
        this.write(str, true, false);
    }

    public void appendln(Savable s) throws IOException {
        this.append(s, true);
    }

    public void append(Savable s, boolean newLine) throws IOException {
        String str = s.toSavableString();
        this.write(str, true, true);
    }

    public void write(String text, boolean newLine, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(text);
        if (newLine) {
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public void create() throws IOException {
        this.write("", false, false);
    }

}
