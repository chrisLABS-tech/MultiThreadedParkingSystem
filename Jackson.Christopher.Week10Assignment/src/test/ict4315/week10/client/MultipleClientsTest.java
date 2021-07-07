package test.week10.client;

import week10.client.Client;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author chrisjackson
 */
public class MultipleClientsTest extends Thread {
    ConcurrentHashMap<String, String> mapTest;
    CopyOnWriteArrayList<String> response;

    public MultipleClientsTest(ConcurrentHashMap<String, String> mapTest) {
        this.mapTest = mapTest;
        response = new CopyOnWriteArrayList<>();
    }


    @Override
    public synchronized void run() {
        try {
            response.add(Client.runCommand("CUSTOMER", mapTest));
            response.add(Client.runCommand("CUSTOMER", mapTest));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public CopyOnWriteArrayList<String> getResponses() {
        return response;
    }
}

