package week10.parkingsystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private final String licensePlate;
    private final Customer owner;
    private CarType type;

    @JsonCreator
    public Car(
            @JsonProperty("type") CarType type,
            @JsonProperty("license") String license,
            @JsonProperty("customer") Customer customer) {
        this.type = type;
        this.licensePlate = license;
        this.owner = customer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


    public Customer getCustomer() {
        return owner;
    }

    /**
     * @return
     */
    public CarType getType() {
        return type;
    }

    /**
     * @param Type
     */
    public void setType(CarType Type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car [license=" + licensePlate + ", customer=" + owner + "]";
    }
}
