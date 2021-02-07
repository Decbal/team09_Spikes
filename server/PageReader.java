package server;

import java.io.BufferedReader;
import java.io.FileReader;

public class PageReader {
    public static String read() {
        String site = "ERROR";

        try {
            FileReader fr = new FileReader("site.html");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            
            while(line != null) {
                sb.append("\n" + line);
                line = br.readLine();
            }

            site = sb.toString();
            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("\nOOPS!!!");
        }
        return site;
    }

    public static String readCopy() {
        String site = "ERROR";

        try {
            FileReader fr = new FileReader("site_copy.html");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            
            while(line != null) {
                sb.append("\n" + line);
                line = br.readLine();
            }

            site = sb.toString();
            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("\nOOPS!!!");
        }
        return site;
    }
}
