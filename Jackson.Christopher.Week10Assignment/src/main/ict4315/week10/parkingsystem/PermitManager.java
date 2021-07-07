package week10.parkingsystem;

import java.time.LocalDate;
import java.util.List;

public class PermitManager {
    private List<ParkingPermit> permits;
    private ParkingPermit permit;

    public PermitManager(List<ParkingPermit> permits) {
        this.permits = permits;
    }

    public ParkingPermit register(Vehicle vehicle) {
        permit = new ParkingPermit(vehicle, LocalDate.now());
        permits.add(permit);
        return permit;
    }

    public ParkingPermit getParkingPermit(String id) throws Exception {

        for (int i = 0; i < permits.size(); i++) {
            if (permits.get(i).getPermitID().equals(id)) {
                return permits.get(i);
            }
        }
        System.out.println("No ID");
        return null;
    }
}
