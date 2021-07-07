package week10.parkingsystem;

import java.math.BigDecimal;

public class FeeStrategyRushHour extends ParkingFeeCalculator {
    private BigDecimal parkingFee;

    public FeeStrategyRushHour(FeeStrategy feeStrategy) {
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
        parkingFee.subtract(BigDecimal.valueOf(3));
        if (vehicle.getType() == CarType.COMPACT) {
            parkingFee.multiply(BigDecimal.valueOf(.8));
        }
        return parkingFee;
    }

    @Override
    public String generateFeeDescription() {
        return feeStrategy.generateFeeDescription() + ", Holiday ";
    }
}