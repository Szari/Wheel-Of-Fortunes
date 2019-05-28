package V1;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;


public class Server {
    DatagramSocket socket;
    Communication communication;
    int countOfUsers = 0;
    InetAddress[] usersIP = new InetAddress[3];
    int[] usersPort = new int[3];
    String[] usersNick = new String[3];
    int port = 1024;
    public Server(){
        try {
            socket = new DatagramSocket(port);
            socket.setSoTimeout(10000);
            communication = new Communication(socket);
        } catch (SocketException ex) {
            System.err.println("SocketException in constructor in class Server");
        }
    }
    
    public void run(){
        System.err.println("Server is working...");
        while(true){
            while(countOfUsers != 3){
                DatagramPacket pakiet = communication.getPack();
                if(pakiet != null){
                    String text = new String(pakiet.getData());
                    String[] words = text.split(";");
                    if(words[0].equals("1")){
                        String temp = checkNick(words[1]);
                        usersNick[countOfUsers] = temp;
                        usersIP[countOfUsers] = pakiet.getAddress();
                        usersPort[countOfUsers] = pakiet.getPort();
                        communication.sendPack(1, temp, usersIP[countOfUsers], usersPort[countOfUsers]);
                        communication.sendPack(1, temp, usersIP[countOfUsers], usersPort[countOfUsers]);
                        countOfUsers++;
                        for(int i = 0; i < countOfUsers; i++)
                            for(int j = 0; j < countOfUsers; j++)
                                communication.sendPack(2, usersNick[i], usersIP[j], usersPort[j]);
                    }
                }
            }
            
            GameThread thread= new GameThread(++port, usersIP, usersPort, usersNick);
            thread.start();
            usersIP = new InetAddress[3];
            usersPort = new int[3];
            usersNick = new String[3];
            countOfUsers = 0;
        }
    }
    
    private String checkNick(String nick){
        for(int i = 0; i < countOfUsers; i++){
            if(nick.equals(usersNick[i])){
                nick = nick.concat(Integer.toString(new Random().nextInt(10)));
                i = 0;
            }
        }
        return nick;
    }
    
    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
}
