package test.week10.server;


import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import week10.parkingsystem.Address;
import week10.parkingsystem.Customer;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingOffice;
import week10.parkingsystem.Vehicle;
import week10.server.ParkingService;

/**
 *
 * @author Christopher Jackson
 */
public class ParkingServiceTest {
    String streetAddress1 = "123Main, ";
    String streetAddress2 = "";
    String city = "Melbourne, ";
    String state = "FL";
    String zipcode = "34292";
    Address testAddress = new Address(streetAddress1, streetAddress2, city, state,
            zipcode);
    ParkingOffice parkingOffice = new ParkingOffice("Office", "123UniversityAve",
            new HashMap<String, Customer>(), new HashMap<String, Vehicle>(), new HashMap<String, ParkingLot>());
    public ParkingServiceTest() {
    }


    /**
     * Test of handleInput method, of class ParkingService.
     */
    @Test
    public void testHandleCustomerInput() throws Exception {
        System.out.println("handleInput");
        InputStream anyInputStream = new ByteArrayInputStream("{\"Command\":\"CUSTOMER\",\"name\":\"Lam\"}".getBytes());
        ParkingService instance = new ParkingService(parkingOffice);
        String expResult = ": ";
        String result = instance.handleInput(anyInputStream).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of handleInput method, of class ParkingService.
     */
    @Test
    public void testHandleCarInput() throws Exception {
        System.out.println("handleInput");
        InputStream anyInputStream = new ByteArrayInputStream("{\"license\":\"123\",\"Command\":\"CAR\",\"customer\":\"jon\"}".getBytes());
        ParkingService instance = new ParkingService(parkingOffice);
        String expResult = ": ";
        String result = instance.handleInput(anyInputStream).toString();
        assertEquals(expResult, result);
    }

}