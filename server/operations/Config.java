package server.operations;

import server.Filename;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Config extends Operation {
    private final BufferedReader br;

    public Config(PrintWriter pr, BufferedReader br) {
        super(pr);
        this.br = br;
    }

    private void parse() {

    }

    @Override
    public void eval(String filename) throws Exception {

    }
}
