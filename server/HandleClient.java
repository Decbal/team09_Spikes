package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

class HandleClient extends Thread {
    private Socket client;
    private String clientAddress;

    public HandleClient (Socket client) {
        this.client = client;
        this.clientAddress = client.getInetAddress().toString();
        System.out.println("Client " + clientAddress + ": connected"); // General info about
        System.out.println("New TestServer-thread running.");           //connection
        start();
    }
    
    @Override
    public void run() {
        try {
            this.client.setSoTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream())); // Reads from client
            String line = null;

            PrintWriter pr = new PrintWriter(client.getOutputStream()); // send to client

            do {
                line = br.readLine();
                System.out.println("Client " + clientAddress + ": " + line);
                
                if(line.equals("GET / HTTP/1.1")) { // Requesting main page
                    pr.println("HTTP/1.1 200 OK"); // Request-answere: OK
                    pr.println(WebReader.read());
                    System.out.println("Page printed");
                    pr.flush();
                    System.out.println("Page sent");
                    line = "done"; // request handled: done
                }
            } while(!"done".equals(line));

            pr.close();
            client.close();
            System.out.println("Socket to " + clientAddress + " closed.");
        } catch(SocketTimeoutException ste) { // Socket is not closed because of TIME_OUT
            System.out.println("TIME_OUT happened");
            ste.printStackTrace();
            run();
            //sendTimeOut(s);
        } catch(Exception e) {
            System.out.println("\nSomething went wrong while running.");
            e.printStackTrace();
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