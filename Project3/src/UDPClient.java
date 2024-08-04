import java.util.*;
import java.io.*;
import java.net.*;
public class UDPClient{

    private byte[] buffer;
    private int port;
    DatagramSocket socket;
    InetAddress inetAddress;

    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localHost");

        UDPClient client = new UDPClient(socket, address);
        client.sendAndReceive();
    }

    private UDPClient(DatagramSocket socket, InetAddress inetAddress){
        this.socket = socket;
        this.inetAddress = inetAddress;
    }

    private void sendAndReceive(){

        Scanner in = new Scanner(System.in);
        port = 1234;

        while(true){
            try{
                String order = in.nextLine();
                buffer = order.getBytes();
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                socket.send(dp);
                buffer = new byte[256];
                dp = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                socket.receive(dp);
                String output = new String(dp.getData(), 0, dp.getLength());
                System.out.println(output);
            } catch(IOException e){
                e.printStackTrace();
                break;
            }
        }

        in.close();
    }
}