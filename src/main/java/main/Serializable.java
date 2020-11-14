package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public interface Serializable {

    /***
     * create write function so that task list is able to be saved into storage
     * @param storage
     * @throws IOException
     */
    void write(FileWriter storage) throws IOException;

    /***
     * create read function so that task list is able to be read from storage
     * @param fileRead
     * @throws IOException
     */
    void read(BufferedReader fileRead) throws IOException;
}
