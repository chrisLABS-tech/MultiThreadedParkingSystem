package week10.parkingsystem;

public class ParkingObserver {
    private TransactionManager transactionManager;
    private ParkingLot lot;

    public ParkingObserver(ParkingLot lot, TransactionManager transactionManager) {
        this.lot = lot;
        this.transactionManager = transactionManager;
    }

    public void update() {
        transactionManager.park(lot.getParkingEvent());
    }
}
