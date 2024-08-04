import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
public class ClientHandle {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private static ArrayList<ClientHandle> clients = new ArrayList<>();
    private HashMap<String, String> keyValStore;
    private final String QUIT = "QUIT";
    private final String KEYS = "KEYS";
    private final String PUT = "PUT";
    private final String DELETE = "DELETE";
    private final String GET = "GET";

    public ClientHandle(Socket socket, HashMap<String, String> keyValStore){
        this.socket = socket;
        this.keyValStore = keyValStore;
        try{
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            clients.add(this);
            dos.writeUTF(getCurrentTimeStamp() + " Connection successfull!\n");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void launchClientThread(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(!socket.isClosed()){
                    try {
                        dos.writeUTF("""
                                Please input commands in either of following forms:
                                GET <KEY>
                                PUT <KEY> <VALUE>
                                DELETE <KEY>
                                KEYS
                                QUIT
                                """);
                        dos.flush();
                        String[] order = dis.readUTF().trim().split(" ");
                        if(order.length != 0){
                            switch(order[0]){
                                case GET:
                                    if(order.length == 2){
                                        handleGetRequest(order[1]);
                                    } else{
                                        invalidOrder("MISS");
                                    }
                                    break;
                                case PUT:
                                    if(order.length == 3){
                                        handlePutRequest(order[1], order[2]);
                                        sendMessages(order[1], order[2]);
                                    } else{
                                        invalidOrder("MISS");
                                    }
                                    break;
                                case DELETE:
                                    if(order.length == 2){
                                        handleDelRequest(order[1]);
                                    } else{
                                        invalidOrder("MISS");
                                    }
                                    break;
                                case KEYS:
                                    handleKeysRequest();
                                    break;
                                case QUIT:
                                    shutDown();
                                    break;
                                default:
                                    invalidOrder(null);
                            }
                        } else{
                            invalidOrder(null);
                        }
                        
                    } catch (IOException e) {
                        shutDown();
                    }
                }
            }
            
        }).start();
    }

    void sendMessages(String key, String value){
        new Thread(new Runnable(){

            @Override
            public void run() {
                try{
                    if(value == null){
                        for(ClientHandle client: clients){
                            if(client.socket.equals(socket)){
                                continue;
                            }
                            client.dos.writeUTF(getCurrentTimeStamp() + " pair with key : " + key + " was deleted from storage by user with id : " + socket.getRemoteSocketAddress());
                            client.dos.flush();
                        }
                    } else{
                        for(ClientHandle client: clients){
                            if(client.socket.equals(socket)){
                                continue;
                            }
                            client.dos.writeUTF(getCurrentTimeStamp() + " pair with key : " + key + " and value : " + value + " was added to storage by user with id : " + socket.getRemoteSocketAddress());
                            client.dos.flush();
                        }
                    }
                } catch(IOException e){
                    shutDown();
                }
            }
        }).start();
    }

    private void invalidOrder(String reason) throws IOException{
        if(reason == null){
            dos.writeUTF(getCurrentTimeStamp() + " Wrong format of command\n");
            dos.flush();
            return;
        }
        switch(reason){
            case "MISS":
                dos.writeUTF(getCurrentTimeStamp() + " Parameter(s) missing\n");
                dos.flush();
                break;
            case "INVV":
                dos.writeUTF(getCurrentTimeStamp() + " Invalid values given\n");
                dos.flush();
                break;
        }
    }

    private void handleGetRequest(String key) throws UnknownHostException, IOException{
        if(keyValStore.containsKey(key)){
            dos.writeUTF(getCurrentTimeStamp() + " Success: value of key " + key + " is \"" + keyValStore.get(key) + "\"\n");
            dos.flush();
        } else{
            dos.writeUTF(getCurrentTimeStamp() + " No such key\n");
            dos.flush();
        }
    }

    private void handlePutRequest(String key, String value) throws UnknownHostException, IOException{
        keyValStore.put(key, value);
        dos.writeUTF(getCurrentTimeStamp() + " Success: pair with key :" + key + " and value : " + value + " was added\n");
        dos.flush();
    }

    private void handleDelRequest(String key) throws IOException{
        if(keyValStore.containsKey(key)){
            dos.writeUTF(getCurrentTimeStamp() + " Success: key " + key + " and its value \"" + keyValStore.remove(key) + "\" were removed\n");
            dos.flush();
            sendMessages(key, null);
        } else{
            dos.writeUTF(getCurrentTimeStamp() + " No such key\n");
            dos.flush();
        }
    }

    public void handleKeysRequest() throws IOException{
        dos.writeUTF(getCurrentTimeStamp() + " Success: keys:");
        for(Map.Entry<String, String> entry: keyValStore.entrySet()){
            dos.writeUTF(entry.getKey());
        }
        dos.flush();
    }

    private static String getCurrentTimeStamp(){
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");

        String currentDateTime = dateFormat.format(currentDate);

        return currentDateTime;
    }

    private void shutDown(){
        try{

            if(socket != null){
                socket.close();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
