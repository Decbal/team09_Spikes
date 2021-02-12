package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
A simple client to tests how a text-file can be sent to a server
that the server then saves as per request.
*/
public class TestClient {

    public TestClient() {
        try {
            Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 5000);
            run(s);
        } catch (IOException e) {
            System.out.println("Could not connect");
        }
    }

    public void run(Socket s) {

        try {
            PrintWriter pr = new PrintWriter(s.getOutputStream());

            pr.println("SAVE sentFile.txt");
            pr.println("this;will;be;written;in;new;file;"); // TODO: could use readFile() here
            pr.println("done");
            pr.flush();

            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String line = bf.readLine();
            if (line.equals("saved")) {
                pr.println("done");
                pr.flush();
            }
        } catch (IOException e) {

        }
    }

    public String readFile(String fileName) {
        // TODO: Reads a file and saves it to a string. Easy with FileReader and
        // StringBuilder

        return null;
    }
}
