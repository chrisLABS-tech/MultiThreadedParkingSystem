package week10.parkingsystem;

import java.math.BigDecimal;
import java.time.Instant;

public class ParkingEvent {
    private ParkingPermit permit;
    private String lotId;
    private Instant incurredParkTime;
    private BigDecimal baseRate;

    public ParkingEvent(ParkingPermit permit, String lotId, Instant incurredParkTime, BigDecimal baseRate) {
        this.permit = permit;
        this.lotId = lotId;
        this.incurredParkTime = incurredParkTime;
        this.baseRate = baseRate;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public Instant getParkTime() {
        return incurredParkTime;
    }

    public String getLotId() {
        return lotId;
    }

    public ParkingPermit getParkingPermit() {
        return permit;
    }
}
