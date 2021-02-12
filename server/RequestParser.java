package server;

import java.io.BufferedReader;
import java.io.PrintWriter;

import server.operations.*;

public class RequestParser {
    private String op;
    private String type;

    public RequestParser(String request) {
        parse(request);
    }

    private void parse(String request) {
        String[] args = request.split(" ");
        this.op = args[0];
        this.type = args[1];
    }

    public OP operation(BufferedReader br, PrintWriter pr) {
        Filename filename = this.filename();
        switch (op) {
            case "SAVE":
                return new Save(filename, br, true);
            case "SAVEFILE":
                return new Save(filename, br, false);
            case "READ":
                return new Read(filename, pr);
            case "CONFIG":
                return OP.CONFIG;
            case "GET":
                return OP.GET;
        }
        return null;
    }

    private Filename filename() {
        switch (type) {
            case "NAME":
                return Filename.namnfil;
            case "START":
                return Filename.starttid;
            case "END":
                return Filename.maltid;
            case "RESULT":
                return Filename.resultat;
        }
        return null;
    }
}