package server.operations;

import server.FileHandler;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Get extends Operation {
        public Get(PrintWriter pr, BufferedReader br) {
            super(pr);
        }

    @Override
    public void eval(String filename) throws Exception {
        FileHandler fh = new FileHandler("site.html");
        pr.println("HTTP/1.1 200 OK"); // Request-answer: OK
        // TODO: Implement some sort of mapping logic between route and file
        if(filename.equals("/data")) {
            pr.println("\n" + LocalDateTime.now() + "\n");
        } else {
            pr.println(fh.readFile());
        }

        pr.flush();
    }
}
