package test.week10.client;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.client.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author chrisjackson
 */
public class ClientTest extends Thread {
    ConcurrentHashMap<String,String> args;

    public ClientTest() {
    }

    @BeforeEach
    public void init() {
        args = new ConcurrentHashMap<>();
        args.put("CUSTOMER", "Name=jon");
        args.put("CAR", "License=123license Owner=jon");
    }

    @Test
    public void runCommandTest() throws Exception {
        //Use return from runCommand to assert success
        assertEquals("{\"CAR\":\"License=123license Owner=jon\",\"Command\":\"CUSTOMER\",\"CUSTOMER\":\"Name=jon\"}", Client.runCommand("CUSTOMER", args).toString());
    }

    @Test
    public void testMultiThread() {
        //Create multiple maps to store different payloads
        ConcurrentHashMap<String, String> mapTest = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> mapTest2 = new ConcurrentHashMap<>();

        MultipleClientsTest instance;
        MultipleClientsTest instance2;

        mapTest.put("CUSTOMER", "name=jon");
        mapTest.put("CAR", "license=1234 customer=jon");

        mapTest2.put("CUSTOMER", "name=jan");
        mapTest2.put("CAR", "license=1224 customer=jan");

        //Loop through and create new threads each time.
        //This test can handle 0 - Integer.MAX_VALUE except for outofmemory error
        // due to creating more than 2k threads.
        //To test MAX_VALUE, assertEquals must be removed due to run time.
        for (int i =0; i<70; ++i) {
            instance = new MultipleClientsTest(mapTest);
            instance.run();
            instance.run();
            assertEquals("{\"CAR\":\"license=1234 customer=jon\",\"Command\":\"CUSTOMER\",\"CUSTOMER\":\"name=jon\"}",instance.getResponses().get(0));
            instance2 = new MultipleClientsTest(mapTest2);
            instance2.run();
            assertEquals("{\"CAR\":\"license=1224 customer=jan\",\"Command\":\"CUSTOMER\",\"CUSTOMER\":\"name=jan\"}",instance2.getResponses().get(0));
        }

    }
}
