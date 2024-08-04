import java.util.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
public class TCPClient {
    private final static String QUIT = "QUIT";
    private final static String KEYS = "KEYS";
    private final static String PUT = "PUT";
    private final static String DELETE = "DELETE";
    private final static String GET = "GET";
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;

    private TCPClient(Socket socket){
        this.socket = socket;
    }

    private void launchClient() throws IOException{
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        Scanner in = new Scanner(System.in);

        new Thread(new Runnable(){

            @Override
            public void run() {
                while(!socket.isClosed()){
                    try {
                        System.out.println(dis.readUTF());
                    } catch (IOException e) {
                        try {
                            socket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }

        }).start();

        while(!socket.isClosed()){
            try{
                dos.writeUTF(in.nextLine());
                dos.flush();
            } catch(IOException e){
                socket.close();
            }
        }
        in.close();
    }

    public static void main(String[] args) throws UnknownHostException, IOException{
        Socket socket = new Socket("localhost", 1234);
        TCPClient client = new TCPClient(socket);
        client.launchClient();
    }


    private static String getCurrentTimeStamp(){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentDateTime = dateFormat.format(currentDate);

        return currentDateTime;
    }

}
