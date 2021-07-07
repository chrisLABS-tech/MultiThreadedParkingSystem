package week10.parkingsystem;

public class CustomerBuilder {
    private String name;
    private String address;
    private String phoneNum;
    private String customerID;

    public CustomerBuilder(String customerID) {
        this.customerID = customerID;
    }
    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public CustomerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }
    public CustomerBuilder setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }
    public Customer buildCustomer() {
        return new Customer(name, address, phoneNum, customerID);
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getCustomerID() {
        return customerID;
    }
}

