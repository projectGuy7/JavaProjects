import java.util.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class UDPServer{
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    DatagramPacket datagramPacket;
    private static HashMap<String, String> keyValStore = new HashMap<>();
    private byte[] buffer = new byte[256];
    int port;
    private final static String quit = "QUIT";
    private final static String keys = "KEYS";
    private final static String put = "PUT";
    private final static String delete = "DELETE";
    private final static String get = "GET";


    public static void main(String[] args) throws IOException{
        DatagramSocket serverSocket = new DatagramSocket(1234);

        UDPServer server = new UDPServer(serverSocket);
        server.receiveThenSend();
    }

    private UDPServer(DatagramSocket datagramSocket){
        this.datagramSocket = datagramSocket;
    }

    private static String getCurrentTimeStamp(){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");

        String currentDateTime = dateFormat.format(currentDate);

        return currentDateTime;
    }

    private void receiveThenSend(){
        Outer:
        while(true){
            try{
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                inetAddress = datagramPacket.getAddress();
                port = datagramPacket.getPort();
                String[] order = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).trim().split(" ");
                if(order.length != 0){
                    switch(order[0]){
                        case put:
                            if(order.length == 3){
                                handlePutRequest(order[1], order[2]);
                            } else{
                                send(getCurrentTimeStamp() + " Invalid order");
                            }
                            break;
                        case delete:
                            if(order.length == 2){
                                handleDelRequest(order[1]);
                            } else{
                                send(getCurrentTimeStamp() + " Invalid order");
                            }
                            break;
                        case get:
                            if(order.length == 2){
                                handleGetRequest(order[1]);
                            } else{
                                send(getCurrentTimeStamp() + " Invalid order");
                            }
                            break;
                        case keys:
                            handleKeysRequest();
                            break;
                        case quit:
                            break Outer;
                        default:
                            send(getCurrentTimeStamp() + " Invalid order");
                    }
                } else{
                    send(getCurrentTimeStamp() + " Invalid order");
                }
            } catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    private void handlePutRequest(String key, String value) throws IOException{
        keyValStore.put(key, value);
        send(getCurrentTimeStamp() + " Success: pair with key " + key + " and value " + value + " was added to storage");
    }

    private void handleDelRequest(String key) throws IOException{
        if(keyValStore.remove(key) != null){
            send(getCurrentTimeStamp() + " Success: value with key " + key + " was deleted");
        } else{
            send(getCurrentTimeStamp() + " No such key");
        }
    }

    private void handleGetRequest(String key) throws IOException{
        if(keyValStore.containsKey(key)){
            send(getCurrentTimeStamp() + " Success: value of key " + key + " is " + keyValStore.get(key));
        } else{
            send(getCurrentTimeStamp() + " No such key");
        }
    }

    private void handleKeysRequest() throws IOException{
        StringBuilder message = new StringBuilder(getCurrentTimeStamp() + " Success: keys:\n");
        for(String key: keyValStore.keySet()){
            message.append(key + "\n");
        }
        send(message.toString());
    }

    private void send(String message) throws IOException{
        datagramPacket = new DatagramPacket(message.getBytes(), message.length(), inetAddress, port);
        datagramSocket.send(datagramPacket);
    }
}
