package test.week10.parkingsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.parkingsystem.CarType;
import week10.parkingsystem.Customer;
import week10.parkingsystem.Money;
import week10.parkingsystem.ParkingEvent;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingPermit;
import week10.parkingsystem.ParkingTransaction;
import week10.parkingsystem.TransactionManager;
import week10.parkingsystem.Vehicle;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionManagerTest {
    private List<ParkingTransaction> transactions = new ArrayList<>();
    private ParkingLot lot;
    private ParkingPermit permit;
    private Vehicle vehicle;
    private Customer customer;
    private BigDecimal baseRate;
    private LocalDate date = LocalDate.now();
    private Instant instant = Instant.now();

    @BeforeEach
    void init() {
        customer = new Customer("chris","123MainStreet","3212718559","cid123");
        vehicle = new Vehicle("permitID123",LocalDate.now().plusYears(1),"123license", CarType.SUV, customer);
        baseRate = new BigDecimal(1000);
        lot = new ParkingLot("Lot6", "123 University Ave", 20, 0, baseRate);
        permit = new ParkingPermit(vehicle, date);
        date = LocalDate.ofInstant(instant, ZoneId.of("America/New_York"));
    }

    @Test
    void park() {
        TransactionManager transactionManager = new TransactionManager(transactions);
        ParkingEvent parkingEvent = new ParkingEvent(permit, lot.getLotId(), instant, baseRate);
        ParkingTransaction instance;
        instance = transactionManager.park(parkingEvent);

        ParkingTransaction parkingTransaction = new ParkingTransaction(instance.getAmount(), date, permit, lot.getLotId());
        ParkingPermit expectedPermit = parkingTransaction.getPermit();
        Money expectedMoney = parkingTransaction.getAmount();

        //Check ParkingTransaction amount Money is correct
        assertEquals(expectedMoney.toString(), instance.getAmount().toString());

        //Check permit passed in as an argument is the same permit retrieved from instance
        assertEquals(permit.toString(), instance.getPermit().toString());

        //Test permit after it is passed to ParkingTransaction against ParkingTransaction instance returned by park()
        assertEquals(expectedPermit.toString(), instance.getPermit().toString());

        //Ensure transaction is added to List<ParkingTransaction> transactions
        assertEquals(transactions.get(0).getPermit().getVehicle().getPermit(), permit.getVehicle().getPermit());
    }
}