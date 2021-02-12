package server.operations;

import java.io.PrintWriter;

import server.FileHandler;
import server.Filename;

public class Read extends Operation  {

    public Read(PrintWriter pr) {
        super(pr);
    }

    @Override
    public void eval(String filename) {
            FileHandler fh = new FileHandler(filename);
            String content = fh.readFile();
            pr.println(content);
    }
}
