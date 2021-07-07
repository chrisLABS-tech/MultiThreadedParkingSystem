package week10.parkingsystem;

import java.time.LocalDate;
import java.util.Objects;

/**
 * File: Vehicle.java
 * @author chrisjackson
 */

public class Vehicle {

    private String permit;
    private LocalDate permitExpiration;
    private String license;
    private CarType type;
    private Customer owner;

    public Vehicle(String permit, LocalDate permitExpiration, String license, CarType type, Customer owner) {
        this.permit = permit;
        this.permitExpiration = permitExpiration;
        this.license = license;
        this.type = type;
        this.owner = owner;
    }

    public CarType getType() {
        return type;
    }

    public LocalDate getPermitExpiration() {
        return permitExpiration;
    }

    public String getLicense() {
        return license;
    }

    public String getPermit() {
        return permit;
    }

    public Customer getOwner() {
        return owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(permit, permitExpiration, license, type, owner);
    }

    @Override
    public String toString() {
        return "week9assignment.Vehicle{" +
                "permit='" + permit + '\'' +
                ", permitExpiration=" + permitExpiration +
                ", license='" + license + '\'' +
                ", type=" + type +
                ", owner=" + owner +
                '}';
    }
}
