import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
    private DatagramSocket socket;
    private DatagramPacket p;
    private byte[] buf = new byte[1024];

    private static Scanner inpScanner = new Scanner(System.in);

    public Client(String saddress, int sport) {
        try {
            // Make socket
            socket = new DatagramSocket();
            
            // Take input
            System.out.println("Enter a list of numbers -> ");
            String l = inpScanner.nextLine();

            // Make byte packet
            buf = l.getBytes();
            InetAddress addr = InetAddress.getByName(saddress);
            p = new DatagramPacket(buf, buf.length, addr, sport);
            // Send packet
            socket.send(p);

            // Make buffer for packet from server
            buf = new byte[1024];
            p = new DatagramPacket(buf, buf.length);
            // Receive packet
            socket.receive(p);

            // Display packet from server
            String out = new String(p.getData(), 0, p.getLength());
            System.out.println(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Client c = new Client("localhost", 4445); 
    }
}       

