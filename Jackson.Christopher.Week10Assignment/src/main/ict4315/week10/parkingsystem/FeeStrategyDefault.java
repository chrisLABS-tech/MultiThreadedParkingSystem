package week10.parkingsystem;

import java.math.BigDecimal;

public class FeeStrategyDefault implements FeeStrategy {
    private BigDecimal parkingFee;

    public FeeStrategyDefault() {

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
        if (vehicle.getType() == CarType.COMPACT) {
            parkingFee.multiply(BigDecimal.valueOf(.8));
        }
        return parkingFee;
    }

    @Override
    public String generateFeeDescription() {
        return "Standard Parking Charge";
    }
}
