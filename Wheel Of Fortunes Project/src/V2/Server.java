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
    /**
     * Funkcja nasłuchująca
     * Odbiera pakiety od użytkownika
     * Pakiety przekazuje do odpowiednich wątków, jeżeli taka potrzeba
     * Pakiety:
     *      - 100: Serwer dostaje nickname nowego gracza
     *      - 101: Gracz wybrał pokój, w którym chce grać i przekazuje jego numer
     *      - 102: Gracz przesyła jaki tekst zgaduje
     *      - 103: Gracz wysyła sowje punkty aby przekazać je innym graczom
     *      - 104: Gracz wysyła informacje, że chce wyjść z pokoju i wrócić do lobby
     *      - 105: Gracz wyszedł z gry
     */
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
                    if(rooms[Integer.parseInt(words[1])].getUserCount() < 3){
                        rooms[Integer.parseInt(words[1])].addUser(new Person(packet.getAddress(), packet.getPort(), words[2]));
                        if(rooms[Integer.parseInt(words[1])].getUserCount() == 3)
                            rooms[Integer.parseInt(words[1])].run();
                    }
                    sendToAll(2, getWaitingRooms());
                    break;
                case "102":
                    rooms[Integer.parseInt(words[1])].zgaduje(words[2]);
                    break;
                case "103":
                    rooms[Integer.parseInt(words[1])].przekazPkt(words[2], words[3]);
                    break;
                case "104":
                    rooms[Integer.parseInt(words[1])].usunGracza(words[2]);
                    break;
                case "105":
                    for(int i = 0; i < users.size(); i++){
                        if(users.get(i).getMyLogin().equals(words[2]))
                            users.remove(i);
                    }
                    rooms[Integer.parseInt(words[1])].wyszedlGracz();
                    break;
                case "106":
                    for(int i = 0; i < users.size(); i++){
                        if(users.get(i).getMyLogin().equals(words[1]))
                            users.remove(i);
                    }
                    break;
            }
        }
    }
    /**
     * Funkcja odbierająca pakiet od użytkownika
     * @return pakiet który został odebrany
     */
    private DatagramPacket getPack(){
        byte[] daneDO = new byte[256];
        byte[] daneDW = new byte[256];
        
        DatagramPacket toReceive = new DatagramPacket(daneDO, daneDO.length);
        
        boolean reached = false;
        
        while(!reached){
            try{
                socket.receive(toReceive);
                daneDW = (new String(toReceive.getData()).split(";")[0] + ";").getBytes();
                DatagramPacket toSend = new DatagramPacket(daneDW, daneDW.length, toReceive.getAddress(), toReceive.getPort());
                socket.send(toSend);
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
    
    /**
     * Funkcja wysyłająca pakiet do użytkownika
     * @param nrPack    numer pakietu
     * @param text      treść pakietu
     * @param person    dane użytkownika do którego ma trafić pakiet
     */
    private void sendPack(int nrPack, String text, Person person){
        byte[] daneDW = (Integer.toString(nrPack) + ";" + text + ";").getBytes();
        DatagramPacket toSend = new DatagramPacket(daneDW, daneDW.length, person.getMyIP(), person.getMyPort());
        
        System.out.println("Sended: " + Integer.toString(nrPack) + ";" + text + ";");
        
        try{
            socket.send(toSend);
        }catch(IOException ioe){
            System.err.println("IOE");
        }
    }
    
    /**
     * Funkcja zamienia pakiet na tablice ciagów liter
     */
    private String[] packToArray(DatagramPacket packet){
        return new String(packet.getData()).split(";");
    }
    /**
     * Funkcja sprawdza czy użytkownik o danym loginie już istnieje
     * Jeżeli tak dodaje losową liczbę do jego loginu aby stworzyć login unikatowy
     * @param login     login użytkownika
     * @return          zwraca poprawiony bądź nie login
     */
    private String checkUser(String login){
        for (Person user : users) {
            if(user.getMyLogin().equals(login)){
                login = login.concat(Integer.toString(rand.nextInt(10)));
            }
        }
        return login;
    }
    
    /**
     * Funckja sprawdzająca jakie pokoje są możliwe do wyboru przez użytkownika
     * @return      String z wolnymi pokojami
     */
    private String getWaitingRooms(){
        String temp = "";
        for(int i = 1; i < 50; i++){
            if(!rooms[i].isWorking()){
                temp = temp.concat(Integer.toString(i)).concat(";");
            }
        }
        return temp;
    }
    
    private void sendToAll(int nrPack, String text){
        for(int i = 0; i < users.size(); i++)
            sendPack(nrPack, text, users.get(i));
    }
}
