package week10.parkingsystem;

import java.util.ArrayList;
import java.util.List;

public interface ParkingObservable {
    List<ParkingObserver> observers = new ArrayList<ParkingObserver>();

    void add(ParkingObserver parkingObserver);
    void remove(ParkingObserver parkingObserver);
    void update(List<ParkingObserver> observers);
}
