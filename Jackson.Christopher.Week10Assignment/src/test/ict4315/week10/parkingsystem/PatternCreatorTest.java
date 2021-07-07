package test.week10.parkingsystem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week10.parkingsystem.CarType;
import week10.parkingsystem.Customer;
import week10.parkingsystem.FeeStrategy;
import week10.parkingsystem.FeeStrategyAfternoon;
import week10.parkingsystem.FeeStrategyDefault;
import week10.parkingsystem.FeeStrategyMorning;
import week10.parkingsystem.FeeStrategyRushHour;
import week10.parkingsystem.FeeStrategyWeekday;
import week10.parkingsystem.FeeStrategyWeekend;
import week10.parkingsystem.PatternCreator;
import week10.parkingsystem.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;



/**
 *  @author chrisjackson
 *  File: PatternCreatorTest.java
 */

public class PatternCreatorTest {
    private PatternCreator instance;
    private BigDecimal baseRate;
    private Customer customerInstance;

    private Vehicle vehicle;
    private LocalDate date;
    private LocalTime time;
    private FeeStrategy feeStrategy;


    public PatternCreatorTest() {

    }

    @BeforeEach
    void init() {
        instance = new PatternCreator();
        baseRate = new BigDecimal(5);
        customerInstance = new Customer("chris", "123Main", "3212718559", "CID123");
        customerInstance = new Customer("Chris","123Main","3212718559", "CID123");
        vehicle = new Vehicle("Permit1234", LocalDate.now().plusYears(1),
                "123license", CarType.COMPACT, customerInstance);
    }

    @Test
    void createPatternAfternoonTest() {
        time = LocalTime.parse("12:01");
        feeStrategy = instance.createPattern("Lot1", date, time);
        BigDecimal result = feeStrategy.generateFee(baseRate,vehicle);
        FeeStrategyAfternoon instance = new FeeStrategyAfternoon(new FeeStrategyDefault());
        assertEquals(instance.generateFee(baseRate,vehicle), result);
    }

    @Test
    void createPatternMorningTest() {
        time = LocalTime.parse("11:59");
        feeStrategy = instance.createPattern("Lot1", date, time);
        BigDecimal result = feeStrategy.generateFee(baseRate,vehicle);
        FeeStrategyMorning instance = new FeeStrategyMorning(new FeeStrategyDefault());
        assertEquals(instance.generateFee(baseRate,vehicle), result);
    }

    @Test
    void createPatternWeekdayTest() {
        date  = LocalDate.parse("2021-04-16");
        feeStrategy = instance.createPattern("Lot4", date, time);
        BigDecimal result = feeStrategy.generateFee(baseRate,vehicle);
        FeeStrategyWeekday instance = new FeeStrategyWeekday(new FeeStrategyDefault());
        assertEquals(instance.generateFee(baseRate,vehicle), result);
    }

    @Test
    void createPatternWeekendTest() {
        date = LocalDate.parse("2021-04-17");
        feeStrategy = instance.createPattern("Lot4", date, time);
        BigDecimal result = feeStrategy.generateFee(baseRate,vehicle);
        FeeStrategyWeekend instance = new FeeStrategyWeekend(new FeeStrategyDefault());
        assertEquals(instance.generateFee(baseRate,vehicle), result);
    }

    @Test
    void createPatternRushHourTest() {
        time = LocalTime.parse("17:00");
        feeStrategy = instance.createPattern("Lot1", date, time);
        BigDecimal result = feeStrategy.generateFee(baseRate,vehicle);
        FeeStrategyRushHour instance = new FeeStrategyRushHour(new FeeStrategyDefault());
        assertEquals(instance.generateFee(baseRate,vehicle), result);
    }
}