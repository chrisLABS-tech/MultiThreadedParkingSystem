package week10.parkingsystem;


import java.math.BigDecimal;

/**
 *  @author chrisjackson
 *  File: FeeStrategyAfternoon.java
 */

public class FeeStrategyAfternoon extends ParkingFeeCalculator {
    private BigDecimal parkingFee;

    public FeeStrategyAfternoon(FeeStrategy feeStrategy) {
        super(feeStrategy);
    }


    @Override
    public BigDecimal generateFee(BigDecimal baseRate, Vehicle vehicle) {
        try {
            vehicle.getType();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("Vehicle argument cannot be null");
        }
        parkingFee = baseRate;
        parkingFee.add(BigDecimal.valueOf(3));
        if (vehicle.getType() == CarType.COMPACT) {
            parkingFee.multiply(BigDecimal.valueOf(.8));
        }
        return parkingFee;
    }

    public String generateFeeDescription() {
        return feeStrategy.generateFeeDescription() + ", Afternoon";
    }
}
