package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
Simple server that handles the most basic HTTP request and
is compatible with TestClient. Can store a file sent from TestClient.
 */

public class TestServer extends Thread {
    private ServerSocket ss = null;
    private boolean running = true;

    public TestServer () {
        try {
            this.ss = new ServerSocket(64646, 1, InetAddress.getByName("192.168.68.107"));// local IP to server, free ports = 49152-65535
        } catch (IOException ioe) {
            System.out.println("I/O error");
        } catch (SecurityException se) {
            System.out.println("Security error");
        } catch (IllegalArgumentException iae) {
            System.out.println("Port argument error");
            iae.printStackTrace();
        }
        System.out.println("Server has started.");
        start();
    }

    @Override
    public void run() {
        try {
            while(running) {
                Socket s = ss.accept(); // holds here until a client connects
                new HandleClient(s);
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
