package week10.parkingsystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;


public class TransactionManager {
    private List<ParkingTransaction> transactions;
    private PatternCreator patternCreator;

    public TransactionManager(List<ParkingTransaction> transactions) {
        this.transactions = transactions;
        this.patternCreator =  DaggerPatternCreatorComponent.create().getPatternCreator();
    }

    public ParkingTransaction park(ParkingEvent parkingEvent) {
        FeeStrategy feeStrategy;
        LocalDate date = LocalDate.ofInstant(parkingEvent.getParkTime(), ZoneId.of("America/New_York"));
        LocalTime time = LocalTime.ofInstant(parkingEvent.getParkTime(), ZoneId.of("America/New_York"));
        //Factory pattern
        feeStrategy = patternCreator.createPattern(parkingEvent.getLotId(), date, time);
        ParkingTransaction transaction;
        transaction = new ParkingTransaction(new Money(feeStrategy.generateFee(parkingEvent.getBaseRate(),
                parkingEvent.getParkingPermit().getVehicle()) ), date, parkingEvent.getParkingPermit(), parkingEvent.getLotId());
        transactions.add(transaction);
        return transaction;
    }

    public List<ParkingTransaction> getParkingTransactions() {
        return transactions;
    }
}
