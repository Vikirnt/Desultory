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
            this.sock = new Socket(addr, port);
            this.in = new DataInputStream(this.sock.getInputStream());
            this.out = new DataOutputStream(this.sock.getOutputStream());

            System.out.println("Enter a list of numbers -> ");
            String l = this.inpScanner.nextLine();

            out.writeUTF(l);
            String r = "";

            while(!r.equalsIgnoreCase("DONE")) {
                System.out.println(r);
                r = in.readUTF();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Client c = new Client("127.0.0.1", 8000); 
    }
}       

