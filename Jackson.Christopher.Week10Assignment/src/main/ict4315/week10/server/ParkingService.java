package week10.server;



import week10.client.RequestData;
import week10.client.ResponseData;
import week10.parkingsystem.Address;
import week10.parkingsystem.ParkingLot;
import week10.parkingsystem.ParkingOffice;
import week10.parkingsystem.ParkingPermit;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Jackson
 */
public class ParkingService {

    protected final ParkingOffice parkingOffice;

    public ParkingService(ParkingOffice parkingOffice) {
        this.parkingOffice = parkingOffice;
    }

    public ResponseData handleInput(InputStream in) throws Exception {
        @SuppressWarnings("resource")
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        RequestData requestData = (RequestData) objectInputStream.readObject();
        return performCommand(requestData);
    }

    private ResponseData performCommand(RequestData requestData) {
        Map<String, String> args = requestData.getData();
        ResponseData responseData = new ResponseData();
        switch (requestData.getCommandName()) {
            case "CUSTOMER":
                Address customerAddress = new Address(args.get("Address 1"), args.get("Address 2"), args.get("City"), args.get("State"), args.get("Zipcode"));
                try {
                    responseData.setResponse(parkingOffice.register(args.get("First Name"), args.get("Last Name"), args.get("Phone number")));
                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "CAR":
                try {
                    responseData.setResponse(parkingOffice.register(args.get("Customer Id"), args.get("License"), args.get("CarType")));
                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "PARK": {
                try {
                    ParkingLot parkingLot = parkingOffice.getParkingLot(args.get("Parking lot Id"));

                    ParkingPermit permit = parkingOffice.getPermitManager().getParkingPermit((args.get("Permit Id")));

                    parkingLot.entry(permit);

                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

//            case "CHARGES":
//            {
//                try {
//                    responseData.setResponse(parkingOffice.get(parkingOffice.getCustomer(args.get("Customer Id"))));
//                } catch (Exception ex) {
//                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            break;

            default:
                responseData.setSuccess(false);
                responseData.setError("Command is not found");
        }
        System.out.println("Response: " + responseData.getResponse());
        return responseData;
    }

}