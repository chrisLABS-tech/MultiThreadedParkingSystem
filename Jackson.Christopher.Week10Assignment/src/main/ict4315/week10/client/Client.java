package week10.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;


public class Client {

    public static final String[][] COMMANDS = new String[][] {
            {"Register Customer", "CUSTOMER", "id", "firstName", "lastName", "phoneNumber"},
            {"Register Vehicle",  "CAR",      "type","license", "customer"},
            {"Start Parking",     "PARK",     "Permit Id", "Time"},
            {"Finish Parking",    "PARK",     "Permit Id", "Time"},
            {"Get Charges",       "CHARGES",  "Customer", "Car"},
    };

    private static final int PORT = 7777;
    private static final String SERVER = "localhost";
    private static ObjectMapper mapper = new ObjectMapper();

    private Client() {}

    public static String runCommand(String command, Map<String, String> data)
            throws IOException {

        InetAddress host = InetAddress.getByName(SERVER);
        try (Socket link = new Socket(host, PORT);
             DataInputStream input = new DataInputStream(link.getInputStream());) {
            // connect to week10.server
            System.out.println("You are now connected to: " + host.getHostAddress());
            OutputStream output = link.getOutputStream();
            data.put("Command", command);
            var response = mapper.writeValueAsString(data); // json to respond
            byte[] toWrite = response.getBytes(); // json bytes to pass through to outputstream
            output.write(toWrite);
            return response;
        }

    }

    public static Map<String, Command> commands() {
        Map<String, Command> commands = new HashMap<>();
        for (String[] description : COMMANDS) {
            commands.put(description[1], new Command(description[0], description[1],
                    Arrays.asList(description).subList(2, description.length)));
        }
        return commands;
    }

    /**
     * Run this as: $ java ict4300.week8.week10.client.Client COMMAND label1=value1 label2=value2 ...
     * Use LIST to get the list of commands and their labels.
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0 || args[0].equals("LIST")) {
            System.out.println("Here are the commands we know about.");
            System.out.println(
                    "Usage: $ java ict4300.week8.client.Client COMMAND label1=value1 label2=value2 ...");
            System.out.println();
            for (String[] description : COMMANDS) {
                System.out.format("%s: %s ", description[0], description[1]);
                for (int i = 2; i < description.length; ++i) {
                    System.out.format("%s=value ", description[i].replaceAll(" ", "").toLowerCase());
                }
                System.out.println();
            }
            return;
        }

        Command command = commands().get(args[0]);
        if (command == null) {
            System.out.println("Unrecognised command: " + args[0]);
            System.out.print("Known commands: ");
            String comma = "";
            for (String[] description : COMMANDS) {
                System.out.print(comma + description[1]);
                comma = ", ";
            }
            System.out.println();
            return;
        }
        Map<String, String> values = new LinkedHashMap<>();
        for (String label : command.fieldNames()) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i].startsWith(label.replaceAll(" ", "").toLowerCase())) {
                    values.put(label, args[i].replaceAll(".*=", ""));
                    break;
                }
            }
        }
        System.out.println(Client.runCommand(args[0], values));
    }
}