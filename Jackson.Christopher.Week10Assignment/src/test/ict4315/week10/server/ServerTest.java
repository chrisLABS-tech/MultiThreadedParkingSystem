package week10.server;


import org.junit.jupiter.api.Test;

import week10.parkingsystem.Address;
import week10.parkingsystem.Customer;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingOffice;
import week10.parkingsystem.Vehicle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Jackson
 */
public class ServerTest {
    String streetAddress1 = "2199 S University Blvd, ";
    String streetAddress2 = "";
    String city = "Denver, ";
    String state = "CO";
    String zipcode = "80208";
    Address address = new Address(streetAddress1, streetAddress2, city, state,
            zipcode);
    ParkingOffice parkingOffice = new ParkingOffice("Office", "123UniversityAve",
            new HashMap<String, Customer>(), new HashMap<String, Vehicle>(), new HashMap<String, ParkingLot>());
    ParkingService service = new ParkingService(parkingOffice);
    private static final String SERVER = "localhost";
    private static final int PORT = 7777;
    private static final int PORT2 = 7778;

    public ServerTest() {
    }
    class TestConnect implements Runnable {
        int port = 0;
        TestConnect(int portValue) {
            this.port = portValue;
        }
        public void run() {
            try {
                testConnectPort();
            } catch (IOException ex) {
                Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void testConnectPort () throws IOException {
            InetAddress host;
            try {
                host = InetAddress.getByName(SERVER);
                Socket link = new Socket(host, port);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Connection Successful");
        }

    }

    @Test
    public void testThreadedServer() throws Exception {
        Thread server = new Thread(
                new Server(service));
        server.start();
        Thread connect1 = new Thread(new TestConnect(PORT));
        Thread connect2 = new Thread(new TestConnect(PORT2));
        connect1.start();
        connect2.start();
    }



}