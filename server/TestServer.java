package server;

import server.operations.Save;
import server.operations.Get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
Simple server that handles the most basic HTTP request and
is compatible with TestClient. Can store a file sent from TestClient.
 */

public class TestServer implements Runnable {
    private ServerSocket ss = null;
    private boolean running = true;
    private HandleClient server;

    public TestServer() {
        try {
            this.ss = new ServerSocket(5000, 1, InetAddress.getByName("127.0.0.1"));// local IP to server, free ports =
                                                                                    // 49152-65535
        } catch (IOException ioe) {
            System.out.println("I/O error");
        } catch (SecurityException se) {
            System.out.println("Security error");
        } catch (IllegalArgumentException iae) {
            System.out.println("Port argument error");
            iae.printStackTrace();
        }
        System.out.println("Server has started.");
    }

    public void setupServer(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Reads from client
        PrintWriter pr = new PrintWriter(socket.getOutputStream()); // send to client
        this.server = new HandleClient(socket, pr, br);
        server.addRoute("SAVE", new Save(pr, br));
        server.addRoute("TEST", new Get(pr, br));
        server.addRoute("GET", new Get(pr, br));
    }

    @Override
    public void run() {
        try {
            while (running) {
                Socket s = ss.accept(); // holds here until a client connects
                setupServer(s);
                server.run();
            }
        } catch (IOException e) {
            System.out.println("Closed? Something happened while trying to accept new client.");
        } catch (Exception e) {
            System.out.println("Something happened when running server.");
        }
    }

    public void closeServer() {
        running = false;
        try {
            ss.close();
            System.out.println("Server closed.");
        } catch (IOException e) {
            System.out.println("Something happened when closing server.");
        }
    }

}
