package server.operations;

import java.io.PrintWriter;

import server.GlobalStrings;

/**
 * Abstract class for server operations
 */
public abstract class Operation {
    protected PrintWriter pr;

    public Operation(PrintWriter pr) {
        this.pr = pr;
    }

    /**
     * Notify client that operation succeeded
     */
    public void success() {
        pr.println("success");
    }

    /**
     * Sends an error message if one is caught
     * @param e error message
     */
    public void error(String e) {
        pr.println("ERROR" + e);
    }

    /**
     *
     * @param path The path requested from HTTP request sent over socket
     * @throws Exception throws an Exception if the requested operation fails
     */
    public abstract void eval(String path) throws Exception;

    public void execute(String path) {
        try {
            if(path.equals(GlobalStrings.FILE_INVALID)) {
                throw new Exception();
            }
            eval(path);
            System.out.println("exec");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            error(e.getMessage());
        }
    };
}