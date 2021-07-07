package week10.parkingsystem;

/**
 *  @author chrisjackson
 *  File: ParkingFeeCalculator.java
 */

public abstract class ParkingFeeCalculator implements FeeStrategy {
    protected FeeStrategy feeStrategy;

    public ParkingFeeCalculator(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public String generateFeeDescription() {
        return feeStrategy.generateFeeDescription();
    }
}
