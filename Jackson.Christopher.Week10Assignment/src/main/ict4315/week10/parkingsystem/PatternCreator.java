package week10.parkingsystem;


import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  @author chrisjackson
 *  File: PatternCreator.java
 */

public class PatternCreator {

    @Inject
    public PatternCreator() {

    }

    //This is a factory method which uses if/else statements to return the correct instance
    public FeeStrategy createPattern(String lotID,
                                     LocalDate date, LocalTime time) {
        if (lotID.equals("Lot1") || lotID.equals("Lot2") || lotID.equals("Lot3")) {
            //These lots follow the Morning - Afternoon pricing strategy
            if (time.isAfter(LocalTime.NOON)
                    && time.isAfter(LocalTime.of(4,30))
                    && time.isBefore(LocalTime.of(7,00))) {
                return new FeeStrategyRushHour(new FeeStrategyAfternoon(new FeeStrategyDefault()));
            } else if (time.isAfter(LocalTime.NOON)) {
                return new FeeStrategyAfternoon(new FeeStrategyDefault());
            } else {
                return new FeeStrategyMorning(new FeeStrategyDefault());
            }
        } else {
            //These lots follow the Weekday - Weekend pricing strategy
            if (date.getDayOfWeek().name() == "SATURDAY"
                    || date.getDayOfWeek().name() == "SUNDAY") {
                return new FeeStrategyWeekend(new FeeStrategyDefault());
            } else {
                return new FeeStrategyWeekday(new FeeStrategyDefault());
            }
        }
    }
}
