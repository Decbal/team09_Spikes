package server.operations;

import java.io.PrintWriter;

import server.FileHandler;
import server.Filename;

public class Read extends OP {
    private Filename filename;
    private PrintWriter pr;

    public Read(Filename filename, PrintWriter pr) {
        this.filename = filename;
        this.pr = pr;
    }

    @Override
    public void execute() {
        try {
            FileHandler fh = new FileHandler(filename.toString());
            String content = fh.readFile();
            pr.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
