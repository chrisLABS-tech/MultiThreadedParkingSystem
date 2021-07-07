package week10.parkingsystem;

import java.math.BigDecimal;

/**
 * File: Money.java
 * @author chrisjackson
 */

public class Money {
    private BigDecimal cents;

    public Money(BigDecimal cents) {
        this.cents = cents;
    }

    public BigDecimal getCents() {
        return cents;
    }

    public BigDecimal getDollars() { return cents.divide(BigDecimal.valueOf(100)); }


    @Override
    public String toString() {
        return "USD " +
                "$" + getDollars();
    }
}