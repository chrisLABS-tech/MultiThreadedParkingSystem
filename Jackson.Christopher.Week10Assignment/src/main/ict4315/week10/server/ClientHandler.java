package week10.server;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Jackson
 */
public class ClientHandler implements Runnable{
    protected Socket client = null;
    protected String serverText   = null;
    protected ParkingService service = null;

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(Socket clientSocket, String serverText, ParkingService mainService) {
        this.client = clientSocket;
        this.serverText   = serverText;
        this.service = mainService;
    }

    @Override
    public void run() {
        try (PrintWriter pw = new PrintWriter(client.getOutputStream())) {
            String output = "No output provided";
            try {
                output = service.handleInput(client.getInputStream()).toString();
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                output = ex.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }

            pw.println(output);
            pw.flush();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read from week10.client.", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close week10.client socket.", e);
            }
        }
    }
}