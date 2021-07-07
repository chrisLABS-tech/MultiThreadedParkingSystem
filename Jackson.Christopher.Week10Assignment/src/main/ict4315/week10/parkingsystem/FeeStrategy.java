package week10.parkingsystem;

import java.math.BigDecimal;

public interface FeeStrategy {

    BigDecimal generateFee(BigDecimal baseRate, Vehicle vehicle);

    String generateFeeDescription();
}