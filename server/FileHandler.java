package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A FileHandler which can read a write to files
 */
public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Reads a file and returns read data as a String
     * @return A string of the content read from the file
     */
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

    /**
     *
     * @param br BufferedReader containing data from Socket inputStream
     * @param append boolean deciding if file should appended to (true), else overwritten (false)
     * @throws IOException if an error occurred with the file written an error will be thrown
     */
    public void writeFile(BufferedReader br, boolean append) throws IOException {
        FileWriter fw = new FileWriter(this.filename, append);
        StringBuilder sb = new StringBuilder();
        String line = br.readLine(); // reads next line after header

        while (!"done".equals(line)) { // client should tell server when the entire content has
                                        // been sent
            sb.append(line).append("\n");
            line = br.readLine();
        }

        fw.write(sb.toString());
        fw.close();

        // pr.println("saved"); // tells client input was saved
        // pr.flush();
    }
}
