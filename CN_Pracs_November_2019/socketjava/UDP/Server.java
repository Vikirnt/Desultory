import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    private DatagramSocket socket;
    private DatagramPacket p;
    private byte[] buf = new byte[1024];

    public Server(int myport) {
        try {
            // Make socket
            socket = new DatagramSocket(myport);
            
            // Receive packet
            p = new DatagramPacket(buf, buf.length);
            socket.receive(p);

            // Get client data from packet
            InetAddress address = p.getAddress();
            int port = p.getPort();

            // Extract packet data to String
            String l = new String(p.getData(), 0, p.getLength());
            System.out.println("Received " + l + " from " + address.toString() + " : " + port);

            // String list of numbers
            String[] sls = l.split(" ");
            // Integer list of numbers
            int[] ils = new int[sls.length];

            // Convert string array to integer array
            for(int i = 0; i < sls.length; i++) {
                ils[i] = Integer.parseInt(sls[i]);
            }

            // If ma'am says this won't work, write bubble sort function.
            Arrays.sort(ils);

            // Convert integer array to string array
            for(int i = 0; i < sls.length; i++) {
                sls[i] = Integer.toString(ils[i]);
            }

            // Join string array to one string
            String out = String.join(" ", sls);

            // Make byte packet
            buf = out.getBytes();
            p = new DatagramPacket(buf, buf.length, address, port);
            // Send packet
            socket.send(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Server s = new Server(4445);
    }
}
