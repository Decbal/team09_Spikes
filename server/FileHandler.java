package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public String readFile() {
        String fileContent = "ERROR";

        try {
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();

            while (line != null) {
                sb.append("\n" + line);
                line = br.readLine();
            }

            fileContent = sb.toString();
            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("\nOOPS!!!");
        }
        return fileContent;
    }

    public void writeFile(BufferedReader br, boolean append) throws IOException {
        FileWriter fw = new FileWriter(this.filename, append);
        StringBuilder sb = new StringBuilder();
        String line = br.readLine(); // reads next line after request

        while (!"done".equals(line)) {
            sb.append(line + "\n");
            line = br.readLine();
        }

        fw.write(sb.toString()); // doesn't append, just writes over
        fw.close();

        // pr.println("saved"); // tells client input was saved
        // pr.flush();
    }
}
