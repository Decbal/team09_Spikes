package server.operations;

import java.io.BufferedReader;
import java.io.PrintWriter;

import server.FileHandler;
import server.Filename;

public class Save extends Operation {
    protected final BufferedReader br;
    protected boolean append;

    public Save(PrintWriter pr, BufferedReader br) {
        super(pr);
        this.br = br;
        append = true;
    }

    @Override
    public void eval(String filename) throws Exception {
        FileHandler fh = new FileHandler(filename);
        fh.writeFile(br, append);
    }
}
