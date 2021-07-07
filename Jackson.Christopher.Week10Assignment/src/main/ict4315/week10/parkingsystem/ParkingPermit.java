package week10.parkingsystem;

import java.time.LocalDate;

public class ParkingPermit {
    private String permitID;
    private Vehicle vehicle;
    private LocalDate permitExpiration;

    public ParkingPermit(Vehicle vehicle, LocalDate date) {
        this.vehicle = vehicle;
        this.setPermitExpiration(date.plusYears(1));
    }

    public LocalDate getPermitExpiration() {
        return permitExpiration;
    }

    private void setPermitExpiration(LocalDate permitExpiration) {
        this.permitExpiration = permitExpiration;
    }

    public String getPermitID() {
        return permitID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
