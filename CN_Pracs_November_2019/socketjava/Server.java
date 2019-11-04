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
            this.server = new ServerSocket(port);
            System.out.printf("Server started on port %d! \n", port);

            this.sock = server.accept();
            System.out.println("Got a connection! ");

            this.in = new DataInputStream(this.sock.getInputStream());
            this.out = new DataOutputStream(this.sock.getOutputStream());

            String l = in.readUTF();
            System.out.println(l);

            String[] sls = l.split(" ");
            int[] ils = new int[sls.length];
            for(int i = 0; i < sls.length; i++) {
                ils[i] = Integer.parseInt(sls[i]);
            }

            Arrays.sort(ils);

            for(int i : ils) {
                this.out.writeUTF("" + i);
            }
            this.out.writeUTF("DONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Server s = new Server(8000);
    }
}
