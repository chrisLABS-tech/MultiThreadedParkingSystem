package week10.parkingsystem;

import java.time.LocalDate;

public class ParkingTransaction {
    private LocalDate date;
    private ParkingPermit permit;
    private String lotId;
    private Money chargedAmount;

    public ParkingTransaction(Money chargedAmount, LocalDate date, ParkingPermit permit, String lotId) {
        this.chargedAmount = chargedAmount;
        this.date = date;
        this.permit = permit;
        this.lotId = lotId;
    }

    public String getLotId() {
        return lotId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Money getAmount() {
        return chargedAmount;
    }

    public ParkingPermit getPermit() {
        return permit;
    }
}
