package server.operations;

import java.io.BufferedReader;

import server.FileHandler;
import server.Filename;

public class Save implements OP {
    private Filename filename;
    private BufferedReader br;
    private boolean append;

    public Save(Filename filename, BufferedReader br, boolean append) {
        this.filename = filename;
        this.br = br;
        this.append = append;
    }

    public void execute() {
        try {
            FileHandler fh = new FileHandler(filename.toString());
            fh.writeFile(br, append);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
