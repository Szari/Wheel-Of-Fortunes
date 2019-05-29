package V2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;

public class Server {
    private DatagramSocket socket;
    private final int PORT = 7000;
    private ArrayList<Person> users;
    private Room[] rooms;
    private Random rand;
    
    public Server(){
        try{
           socket = new DatagramSocket(PORT);
           socket.setSoTimeout(5000);
           users = new ArrayList<>();
           rooms = new Room[50];
           for(int i = 1; i < 50; i++)
               rooms[i] = new Room(socket);
           rand = new Random();
        }catch(SocketException se){System.err.println("Socket Exception");}
    }
    
    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
    
    public void run(){
        DatagramPacket packet;
        String[] words;
        
        while(true){
            packet = getPack();
            words = packToArray(packet);
            switch(words[0]){
                case "100":
                    words[1] = checkUser(words[1]);
                    users.add(new Person(packet.getAddress(), packet.getPort(), words[1]));
                    sendPack(1, words[1], users.get(users.size()-1));
                    sendPack(2, getWaitingRooms(), users.get(users.size()-1));
                    break;
                case "101":
                    rooms[Integer.parseInt(words[1])].addUser(new Person(packet.getAddress(), packet.getPort(), words[2]));
                    if(rooms[Integer.parseInt(words[1])].getUserCount() == 3)
                        rooms[Integer.parseInt(words[1])].start();     
                    break;
                case "102":
                    rooms[Integer.parseInt(words[1])].zgaduje(words[2]);
                    break;
                case "103":
                    rooms[Integer.parseInt(words[1])].przekazPkt(words[2], words[3]);
                    break;
            }
        }
    }
    
    private DatagramPacket getPack(){
        byte[] daneDO = new byte[256];
        byte[] daneDW = new byte[256];
        
        DatagramPacket toReceive = new DatagramPacket(daneDO, daneDO.length);
        
        boolean reached = false;
        
        while(!reached){
            try{
                socket.receive(toReceive);
                reached = true;
                System.out.println("Received: " + new String(toReceive.getData()));
            }catch(SocketTimeoutException ste){
                System.err.println("WFP");
            }catch(IOException ioe){
                System.err.println("IOE");
            }
        }
        
        return toReceive;
    }
    
    private void sendPack(int nrPack, String text, Person person){
        byte[] daneDW = (Integer.toString(nrPack) + ";" + text + ";").getBytes();
        System.out.println("Sended: " + Integer.toString(nrPack) + ";" + text + ";");
        DatagramPacket toSend = new DatagramPacket(daneDW, daneDW.length, person.getMyIP(), person.getMyPort());
        try{
            socket.send(toSend);
        }catch(IOException ioe){
            System.err.println("IOE");
        }
    }
    
    private String[] packToArray(DatagramPacket packet){
        return new String(packet.getData()).split(";");
    }
    
    private String checkUser(String login){
        for (Person user : users) {
            if(user.getMyLogin().equals(login)){
                login = login.concat(Integer.toString(rand.nextInt(10)));
            }
        }
        return login;
    }
    
    private String getWaitingRooms(){
        String temp = "";
        for(int i = 1; i < 50; i++){
            if(!rooms[i].isWorking()){
                temp = temp.concat(Integer.toString(i)).concat(";");
            }
        }
        return temp;
    }
}
