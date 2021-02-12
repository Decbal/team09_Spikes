package server.operations;

import server.Filename;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class SaveFile extends Save {

    public SaveFile(PrintWriter pr, BufferedReader br) {
        super(pr, br);
        append = false;
    }
}
