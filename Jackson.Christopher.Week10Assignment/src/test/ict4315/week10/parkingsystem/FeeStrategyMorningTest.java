package test.week10.parkingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.parkingsystem.CarType;
import week10.parkingsystem.Customer;
import week10.parkingsystem.FeeStrategyDefault;
import week10.parkingsystem.FeeStrategyMorning;
import week10.parkingsystem.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *  @author chrisjackson
 *  File: FeeStrategyMorningTest.java
 */

class FeeStrategyMorningTest {
    private Customer customerInstance;
    private Vehicle vehicle;
    private FeeStrategyMorning instance;
    private BigDecimal baseRate;

    public FeeStrategyMorningTest() {

    }

    @BeforeEach
    void init() {
        customerInstance = new Customer("chris", "123Main", "3212718559", "CID123");
        baseRate = new BigDecimal(10);
        instance = new FeeStrategyMorning(new FeeStrategyDefault());
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
        FeeStrategyMorning instance = new FeeStrategyMorning(new FeeStrategyDefault());
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
        assertThrows(NullPointerException.class, () -> instance.generateFee(baseRate, nullVehicle));
    }
}
