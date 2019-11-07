import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;

    private static Scanner inpScanner = new Scanner(System.in);

    public Client(String addr, int port) {
        try {
            // Connect to Server socket
            sock = new Socket(addr, port);

            // Set-up data streams
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());

            // Take input
            System.out.println("Enter a list of numbers -> ");
            String l = inpScanner.nextLine();

            // Write above input to server
            out.writeUTF(l);
            
            // Get output from server
            String r = in.readUTF();
            System.out.println("Received " + r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Client c = new Client("127.0.0.1", 8000); 
    }
}       

