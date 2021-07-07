package week10.parkingsystem;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * File: ParkingLot.java
 * @author chrisjackson
 */

public class ParkingLot implements ParkingObservable {

    private String lotId;
    private String address;
    private int totalCapacity;
    private int currentCapacityCount;
    private BigDecimal baseRate;
    private ParkingEvent parkingEvent;
    private List<ParkingObserver> observers;

    public ParkingLot(String lotId, String address, int totalCapacity, int currentCapacityCount, BigDecimal baseRate) {
        this.address = address;
        this.lotId = lotId;
        this.totalCapacity = totalCapacity;
        this.currentCapacityCount = currentCapacityCount;
        this.baseRate = baseRate;
        this.observers = new ArrayList<>();
    }

    public void add(ParkingObserver parkingObserver) {
        observers.add(parkingObserver);
    }

    public void remove(ParkingObserver parkingObserver) {
        if(observers.contains(parkingObserver)) {
            observers.remove(parkingObserver);
        }
    }

    public void update(List<ParkingObserver> observers) {
        for (ParkingObserver parkingObserver : observers) {
            parkingObserver.update();
        }
    }

    public ParkingEvent getParkingEvent() {
        return parkingEvent;
    }

    private void setParkingEvent(ParkingEvent parkingEvent) {
        this.parkingEvent = parkingEvent;
    }

    public List<ParkingObserver> getObservers() {
        return observers;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public int getCurrentCapacityCount() {
        return currentCapacityCount;
    }

    public int getTotalCapacity() { return totalCapacity;}

    public String getAddress() {
        return address;
    }

    public String getLotId() {
        return lotId;
    }

    public void entry(ParkingPermit permit) {
        //check current expiration date
        if (permit.getPermitExpiration().isAfter(LocalDate.now())
                && currentCapacityCount >=0
                && currentCapacityCount<totalCapacity) {
            setParkingEvent(new ParkingEvent(permit, lotId, Instant.now(), baseRate));
            update(observers);
            System.out.println("Here is your park time: " + parkingEvent.getParkTime());
            currentCapacityCount++;
        }
        else if (currentCapacityCount >= totalCapacity){
            System.out.print("We are sorry this lot is at capacity");
        }
        else {
            System.out.println("We are sorry your permit is expired. See the Parking Office");
        }
    }

    public void exit(Vehicle vehicles) {
        //every vehicle can exit
        currentCapacityCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return totalCapacity == that.totalCapacity
                && currentCapacityCount == that.currentCapacityCount
                && lotId.equals(that.lotId)
                && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotId, address, totalCapacity, currentCapacityCount);
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "lotId='" + lotId + '\'' +
                ", address='" + address + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", currentCapacityCount=" + currentCapacityCount +
                '}';
    }
}
