import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class TCPServer{
    private ServerSocket serverSocket;
    private static HashMap<String, String> keyValStore = new HashMap<>();
    private final String QUIT = "QUIT";
    private final String KEYS = "KEYS";
    private final String PUT = "PUT";
    private final String DELETE = "DELETE";
    private final String GET = "GET";
    DataInputStream serverInputStream;
    DataOutputStream serverOutputStream;


    private TCPServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    private static String getCurrentTimeStamp(){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");

        String currentDateTime = dateFormat.format(currentDate);

        return currentDateTime;
    }

    private void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();

                ClientHandle clientHandle = new ClientHandle(clientSocket, keyValStore);
                clientHandle.launchClientThread();
            }
        } catch(IOException e){
            shutDown();
        }
    }

    private void shutDown(){
        System.out.println("Shutting down");
        try{

            if(serverSocket != null){
                serverSocket.close();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234);
        TCPServer server = new TCPServer(serverSocket);
        server.startServer();
    }
}
