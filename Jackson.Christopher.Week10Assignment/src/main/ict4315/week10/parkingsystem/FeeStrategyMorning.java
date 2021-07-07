package week10.parkingsystem;

import java.math.BigDecimal;

/**
 *  @author chrisjackson
 *  File: FeeStrategyMorning.java
 */

public class FeeStrategyMorning extends ParkingFeeCalculator {
    private BigDecimal parkingFee;

    public FeeStrategyMorning(FeeStrategy feeStrategy) {
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
        this.parkingFee = baseRate;
        parkingFee.add(BigDecimal.valueOf(1));
        if (vehicle.getType() == CarType.COMPACT) {
            parkingFee.multiply(BigDecimal.valueOf(.8));
        }
        return parkingFee;
    }

    @Override
    public String generateFeeDescription() {
        return feeStrategy.generateFeeDescription() + ", Morning";
    }
}
