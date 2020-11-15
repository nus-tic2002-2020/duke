package duke.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.io.Savable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class for writing and reading disk text data
 */
public class Storage {

    protected String filePath;

    /**
     * Getting for file path
     * @return file path as string
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Constructor with file path
     * @param filePath of text data in disk
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load all lines from file, create file if do not yet exist.
     * @return all lines in file
     * @throws IOException if error reading or writing to file path
     */
    public List<String> load() throws IOException {
        return readAll(true);
    }

    /**
     * Read all lines from file
     * @param createIfNotExist automatically create file, do not create if false
     * @return all lines in file
     * @throws IOException if error reading or writing to file path
     */
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

    /**
     * Write list of {@link Savable} objects to file path, existing file will be overwritten.
     * @param savableList list of savable objects
     * @throws IOException if error reading or writing to file path
     */
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
