package server;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Point;

public class Menu {

    private TestServer ts = null;

    public Menu() {
        JFrame frame = new JFrame("Test_Server");
        JButton startButton = new JButton("Start Server");
        JButton exitButton = new JButton("Close Server");

        startButton.addActionListener((action) -> startServer());
        exitButton.addActionListener((action) -> closeServer());
        frame.setLocation(new Point(300, 200));
        frame.setDefaultCloseOperation(3); // frame.EXIT_ONCLOSE = 3
        frame.add(startButton);
        frame.add(exitButton);

        frame.setSize(200, 300);
        frame.setLayout(new GridLayout(2, 1));
        frame.setVisible(true);
    }

    private void startServer() {
        closeServer(); // if one is running before, close it
        ts = new TestServer();
        new Thread(ts).start();
        // new TestClient(); // just a test-Java-client compatible with test-server
    }

    private void closeServer() {
        if (ts != null)
            ts.closeServer();
    }

}
