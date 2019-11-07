import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    private Socket sock;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(int port) {
        try {
            // Make server
            server = new ServerSocket(port);
            System.out.printf("Server started on port %d! \n", port);

            // Accept a connection
            sock = server.accept();
            System.out.println("Got a connection! ");

            // Set-up data streams
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());

            // Read data from client
            String l = in.readUTF();
            System.out.println(l);

            // String array
            String[] sls = l.split(" ");
            // Integer array
            int[] ils = new int[sls.length];

            // Convert String array to Integer array
            for(int i = 0; i < sls.length; i++) {
                ils[i] = Integer.parseInt(sls[i]);
            }

            // If ma'am says this won't work, write bubble sort function.
            Arrays.sort(ils);

            // Convert Integer array to String array
            for(int i = 0; i < sls.length; i++) {
                sls[i] = Integer.toString(ils[i]);
            }

            // Join String array into one String
            String s = String.join(" ", sls);
            // Send data to client
            out.writeUTF(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Server s = new Server(8000);
    }
}
