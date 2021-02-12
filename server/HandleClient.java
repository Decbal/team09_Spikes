package server;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

import server.operations.OP;

class HandleClient extends Thread {
    private Socket client;
    private String clientAddress;

    public HandleClient(Socket client) {
        this.client = client;
        this.clientAddress = client.getInetAddress().toString();
        System.out.println("Client " + clientAddress + ": connected"); // General info about
        System.out.println("New TestServer-thread running."); // connection
        start();
    }

    @Override
    public void run() {
        try {
            // this.client.setSoTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())); // Reads from client
            String line = null;

            PrintWriter pr = new PrintWriter(client.getOutputStream()); // send to client

            do {
                line = br.readLine(); // Holds untill something can be read
                System.out.println("Client " + clientAddress + ": " + line);

                String action = line.split(" ")[0];

                if (action.equals("GET")) {
                    handleHTTPReq(line, br, pr);
                    line = "done"; // HTTP request done
                } else if (action.equals("SAVE")) {
                    handleStore(line, br, pr); // if no problem: client will write "done"
                }
            } while (!"done".equals(line)); // Could also be null as null = ctrl+c

            pr.close(); // not needed since created in try- Java cloeses automatically
            client.close();
            System.out.println("Socket to " + clientAddress + " closed.");
        } catch (SocketTimeoutException ste) { // Socket is not closed because of TIME_OUT
            System.out.println("TIME_OUT happened");
            run();
            // sendTimeOut(s);
        } catch (Exception e) {
            System.out.println("\nSomething went wrong while running.");
            e.printStackTrace();
        }
    }

    private void handleHTTPReq(String request, BufferedReader br, PrintWriter pr) {
        Filename filename;

        pr.println("HTTP/1.1 200 OK"); // Request-answere: OK

        if (request.equals("GET / HTTP/1.1")) { // Requesting main page
            pr.println(PageReader.read());
            System.out.println("Page printed");
        } else { // Other form of HTTP request (xmlHTTPrequest)
            pr.println("\n" + LocalDateTime.now() + "\n"); // HTML responses are wiered with lines
        }

        pr.flush();
        System.out.println("HTTP response sent");
    }

    private void handleSave(Filename filename, BufferedReader br, PrintWriter pr) {
        try {
            String file = req.split(" ")[1]; // name of file
            FileHandler fh = new FileHandler(Filename.);
            fh.writeFile(br, true);

            pr.println("saved"); // tells client input was saved
            pr.flush();
        } catch (IOException e) {
            System.out.println("Error while creating file");
        }
    }

    // This is how you would send a time-out respons
    private void sendTimeOut(Socket s) {
        try {
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println("HTTP/1.1 408 Request Timeout");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("ERROR while trying send timeout.");
        }
    }

}