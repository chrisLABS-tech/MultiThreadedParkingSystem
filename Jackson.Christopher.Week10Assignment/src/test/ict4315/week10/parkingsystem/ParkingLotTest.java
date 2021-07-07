package test.week10.parkingsystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.parkingsystem.CarType;
import week10.parkingsystem.Customer;
import week10.parkingsystem.ParkingEvent;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingObserver;
import week10.parkingsystem.ParkingPermit;
import week10.parkingsystem.ParkingTransaction;
import week10.parkingsystem.TransactionManager;
import week10.parkingsystem.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * File: ParkingLotTest.java
 * @author chrisjackson
 */
public class ParkingLotTest {
    private ParkingPermit parkingPermit;
    private ParkingLot instance;
    private BigDecimal baseRate;
    private Customer custInst;
    private Vehicle vehicleInst;
    private ParkingObserver parkingObserver;
    private List<ParkingTransaction> transactions;
    private TransactionManager transactionManager;

    public ParkingLotTest() {
    }

    @BeforeEach
    public void init() {
        baseRate = new BigDecimal(10);

        instance = new ParkingLot("1","98 University Pl",20, 0, baseRate);

        custInst = new Customer("John", "123 Main", "QQQ", "CID123");

        vehicleInst = new Vehicle("PID123", LocalDate.now().plusYears(1),"AAA", CarType.SUV, custInst);

        parkingPermit = new ParkingPermit(vehicleInst, vehicleInst.getPermitExpiration());

        transactions = new ArrayList<>();

        TransactionManager transactionManager = new TransactionManager(transactions);

        parkingObserver = new ParkingObserver(instance, transactionManager);
    }

    /**
     * Test of entry method, of class ParkingLot.
     */
    @Test
    public void testEntry() {
        System.out.println("entry");

//        Vehicle parks in lot 10 times
        for(int i=0; i< instance.getTotalCapacity(); i++) {
            instance.entry(parkingPermit);
        }
        instance.getParkingEvent();
        assertEquals(instance.getTotalCapacity(), instance.getCurrentCapacityCount());

        ParkingEvent parkingEvent = new ParkingEvent(parkingPermit, instance.getLotId(), instance.getParkingEvent().getParkTime(), instance.getBaseRate());

        //Test ParkingEvent is created successfully
        assertEquals(parkingEvent.getParkingPermit(), instance.getParkingEvent().getParkingPermit());
    }

    /**
     * Test of exit method, of class ParkingLot.
     */
    @Test
    public void testExit() {
        System.out.println("exit");
        BigDecimal baseRate = new BigDecimal(10);

        ParkingLot instance;
        instance = new ParkingLot("1","98 University Pl",20, 0, baseRate);

        Customer custInst = new Customer("John", "123 Main", "QQQ", "CID123");

        Vehicle vehicleInst;
        vehicleInst = new Vehicle("PID123", LocalDate.now().plusYears(1),"AAA", CarType.SUV, custInst);

        //Vehicle parks in lot 10 times
        for(int i=0; i< instance.getTotalCapacity(); i++) {
            instance.entry(parkingPermit);
        }

        for(int i=20; i>0; i--) {
            instance.exit(vehicleInst);
        }

        assertEquals(0, instance.getCurrentCapacityCount());
    }

    @Test
    public void testAdd() {
        instance.add(parkingObserver);

        assertTrue(instance.getObservers().contains(parkingObserver));
    }

    @Test
    public void testRemove() {
        instance.add(parkingObserver);

        assertTrue(instance.getObservers().contains(parkingObserver));

        instance.remove(parkingObserver);

        assertFalse(instance.getObservers().contains(parkingObserver));
    }
}