package server;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Class that handles the server side of the socket connection to a client
 */
class HandleClient extends RequestHandler implements Runnable {
    private Socket client;
    private String clientAddress;
    private PrintWriter pr;
    private BufferedReader br;


    public HandleClient(Socket client, PrintWriter pr, BufferedReader br) {
        this.client = client;
        this.clientAddress = client.getInetAddress().toString();
        this.pr = pr;
        this.br = br;
        System.out.println("Client " + clientAddress + ": connected"); // General info about
        System.out.println("New TestServer-thread running."); // connection
    }

    @Override
    public void run() {
        try {
            // this.client.setSoTimeout(5000);
            String line = null;
            do {
                line = br.readLine(); // Holds until something can be read
                System.out.println("Client " + clientAddress + ": " + line);

                String[] header = line.split(" ");
                String op = header[0];
                String filename = new FilenameHandler().getFile(header[1]);
                System.out.println("Client operation: " + op);
                if (!op.equals("null")) {
                    getRoute(op).execute(filename);
                    line = "done"; // HTTP request done
                }
            } while (!"done".equals(line)); // Could also be null as null = ctrl+c

            pr.close(); // not needed since created in try- Java closes automatically
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

    /**
     *  This is how you would send a time-out response
     */
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