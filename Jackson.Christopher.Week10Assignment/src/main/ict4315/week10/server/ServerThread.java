package week10.server;

import java.io.IOException;

public class ServerThread extends Thread{
    ParkingService service;

    public ServerThread(ParkingService service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            new Server(service).startServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
