package test.week10.parkingsystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.parkingsystem.CarType;
import week10.parkingsystem.Customer;
import week10.parkingsystem.FeeStrategyDefault;
import week10.parkingsystem.FeeStrategyWeekday;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *  @author chrisjackson
 *  File: WeekDayFeeStrategyTest.java
 */

class FeeStrategyWeekdayTest {
    Customer customerInstance;
    ParkingLot lot;
    Vehicle vehicle;
    FeeStrategyWeekday instance;
    LocalDate date;
    LocalTime time;
    BigDecimal baseRate;

    public FeeStrategyWeekdayTest() {

    }
    @BeforeEach
    void init() {
        baseRate = new BigDecimal(10);
        customerInstance = new Customer("chris", "123Main", "3212718559", "CID123");
        lot = new ParkingLot("Lot1", "123 University Ave", 20, 0, baseRate);
        instance = new FeeStrategyWeekday(new FeeStrategyDefault());
        date = LocalDate.now();
        time = LocalTime.now();
    }
    @Test
    void generateFeeCompact() {
        vehicle = new Vehicle("permit123", LocalDate.now().plusYears(1),
                "License123", CarType.COMPACT, customerInstance);
        BigDecimal expResult = baseRate;
        expResult.add(BigDecimal.valueOf(1));
        if (vehicle.getType() == CarType.COMPACT) {
            expResult.multiply(BigDecimal.valueOf(.8));
        }
        assertEquals(expResult, instance.generateFee(baseRate, vehicle));
    }
    @Test
    void generateFeeSUV() {
        vehicle = new Vehicle("permit123", LocalDate.now().plusYears(1),
                "License123", CarType.SUV, customerInstance);
        BigDecimal expResult = baseRate;
        expResult.add(BigDecimal.valueOf(1));

        assertEquals(expResult, instance.generateFee(baseRate, vehicle));
    }
    @Test
    void vehicleFieldNull() {
        Vehicle nullVehicle = null;
        assertThrows(NullPointerException.class, () -> instance.generateFee(baseRate,nullVehicle));
    }
}


