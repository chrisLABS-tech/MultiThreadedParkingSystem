package week10.parkingsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** Author: Christopher Jackson
 * File: week1.ParkingOffice.java
 */

public class ParkingOffice {
    private String name;
    private String address;

    private HashMap<String, Customer> customers;
    private HashMap<String, Vehicle> vehicles;
    private HashMap<String, Car> cars;
    private HashMap<String, ParkingLot> lots;
    private List<ParkingPermit> permits;
    private PermitManager permitManager;


    public ParkingOffice(String name, String address, HashMap<String, Customer> customers, HashMap<String,
            Vehicle> vehicles, HashMap<String, ParkingLot> lots) {
        this.name = name;
        this.address = address;
        this.customers = customers;
        this.vehicles = vehicles;
        this.lots = lots;
        permitManager = new PermitManager(permits);

    }

    public String register(Customer c) {
        customers.put(c.getCustomerID(), c);
        return c.toString();
    }

    public String register(Car car) {
        cars.put(car.getLicensePlate(), car);
        return car.toString();
    }

    public String register(String name, String address, String phoneNumber) {
        //String cID uses private method generateCustomerID to generate customer id
        String cID = generateCustomerID(name, address, phoneNumber);
        CustomerBuilder customerBuilder = new CustomerBuilder(cID)
                .setName(name)
                .setAddress(address)
                .setPhoneNum(phoneNumber);
        customers.put(cID, customerBuilder.buildCustomer());
        return cID;
    }

    public String register(String customerID, String license, CarType carType) {
        CarType t;
        if (carType.equals("COMPACT")) {
            t = CarType.COMPACT;
        } else {
            t = CarType.SUV;
        }
        //String pID uses private method generatePermitID to generate permit id
        String pID = generatePermitID();
        vehicles.put(pID, new Vehicle(pID, LocalDate.now(), license, t, customers.get(customerID)));
        return pID;
    }

    public Customer getCustomer(String name) {
        try {
            Iterator<Map.Entry<String, Customer>> customerIterator = customers.entrySet().iterator();
            while(customerIterator.hasNext()) {
                Map.Entry<String, Customer> entry = customerIterator.next();
                //Searches same customer for matching String name
                if(entry.getValue().getName().contains(name)) {
                    //returns same customer object if there is a match
                    return entry.getValue();
                }
            }
        }
        //Will catch exceptions when attempting to iterate Customers
        catch (Exception e) {
            e.printStackTrace();
        }
        //Return null object if name is not found
        return null;
    }

    //ID can only be generated when name, address, and phoneNumber are initialized
    private String generateCustomerID(String name, String address, String phoneNumber) {
        return "CID" + UUID.randomUUID();
    }

    //To create a permitID, customer object, license, and week1.CarType t must be initialized
    private String generatePermitID() {
        return "PID" + UUID.randomUUID();
    }

    public ParkingLot getParkingLot(String id) throws Exception {

        for (int i = 0; i < lots.size(); i++) {
            if (lots.get(i).getLotId().equals(id)) {
                return lots.get(i);
            }
        }
        return null;
    }

    public Collection<String> getCustomerIDs() {
        return customers.keySet();
    }

    public Collection<String> getPermitIDs() { return vehicles.keySet(); }

    public Collection<String> getPermitIDs(Customer customer) {
        List<String> tempPermitList = new ArrayList<>();
        Iterator<Map.Entry<String, Vehicle>> itr = vehicles.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry<String, Vehicle> entry = itr.next();
            //check each instance of vehicle to see if the vehicle owner id matches customer customerID
            if (customer.getCustomerID().equals(entry.getValue().getOwner().getCustomerID())) {
                tempPermitList.add(entry.getKey());
            }
        }
        return tempPermitList;
    }



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public HashMap<String, ParkingLot> getLots() {
        return lots;
    }

    public PermitManager getPermitManager() {
        return permitManager;
    }

}

