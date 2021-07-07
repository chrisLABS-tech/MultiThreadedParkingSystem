package week10.server;
import week10.client.Command;
import week10.client.ResponseData;


import week10.parkingsystem.Customer;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingOffice;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import week10.parkingsystem.Vehicle;

/**
 *
 * @author Christopher Jackson
 */
public class Server implements Runnable{

    static {
        System.setProperty(
                "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
    }

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final int PORT = 7767;

    private final ParkingService service;

    private Thread runningThread = null;

    private ServerSocket serverSocket = null;

    protected boolean isStopped = true;

    public Server(ParkingService service) {
        this.service = service;
    }

    public void startServer() throws IOException {
        logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);
            while (true) {
                Socket client = serverSocket.accept();
                handleClient(client);
            }
        }
    }

    protected void handleClient(Socket client) {
        try {
            // Using os to return output to client
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ResponseData output;
            try {
                // Process requestData from client and return output as responseData
                output = service.handleInput(client.getInputStream());
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                output = new ResponseData(false, ex.getLocalizedMessage(), null);
            }

            os.writeObject(output);
            os.flush();

        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to read from client.", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close client socket.", e);
            }
        }
    }

    /**
     * Run this as: $ java ict4300.week8.server.Server
     */
    public static void main(String[] args) throws Exception {

        ParkingOffice parkingOffice = new ParkingOffice("Office", "123UniversityAve",
                new HashMap<String, Customer>(), new HashMap<String, Vehicle>(), new HashMap<String, ParkingLot>());
        ParkingService service = new ParkingService(parkingOffice);


        new Server(service).startServer();
    }

    @Override
    public void run() {
        try {
            logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        runningThread = Thread.currentThread();
        try {
            openServerSocket();
            serverSocket.setReuseAddress(true);
            while (isStopped) {
                Socket client = serverSocket.accept();
                new Thread(
                        new ClientHandler (
                                client, "ThreadedServer", service)
                ).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 7777", e);
        }
    }
}