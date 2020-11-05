package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public interface Serializable {

    void write(FileWriter storage) throws IOException;

    void read(BufferedReader fileRead) throws IOException;
}
