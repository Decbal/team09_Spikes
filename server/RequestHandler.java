package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import server.operations.*;

public class RequestHandler {
    private final Map<String, Operation> routes = new HashMap<>();


    /**
     * Adds a operation to Server that can perform an Operation
     * @param OPCode The OPCode sent from client to server {@see docs}
     * @param operation An operation instance to be performed when a certain OPCode is sent
     */
    public void addRoute(String OPCode, Operation operation) {
        routes.put(OPCode, operation);
    }

    /**
     * Gets a certain Operation object associated with the route
     * @param route an path on the servers
     * @return Operation instance
     */
    public Operation getRoute(String route) {
        return routes.get(route);
    }
}