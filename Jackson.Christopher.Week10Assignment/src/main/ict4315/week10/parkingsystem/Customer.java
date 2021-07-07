package week10.parkingsystem;

import java.util.Objects;

/**
 * File: Customer.java
 * @author chrisjackson
 */

public class Customer {
    private String customerID;
    private String name;
    private String address;
    private String phoneNumber;

    public Customer(String name, String address, String phoneNumber, String customerID){
        try {
            if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || customerID.isEmpty()) {
                System.out.println("One or more inputs is not filled out");
                System.out.println("name: " + name + " address: " + address + " phoneNumber: " + phoneNumber);
            } else {
                this.name = name;
                this.address = address;
                this.phoneNumber = phoneNumber;
                this.customerID = customerID;
            }
        }
        catch (NullPointerException e) {
            System.out.println("One or more objects null, please try again");
            System.out.println("name: " + name + " address: " + address + " phoneNumber: " + phoneNumber);
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, name, address, phoneNumber);
    }

    @Override
    public String toString() {
        return "week9assignment.Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
