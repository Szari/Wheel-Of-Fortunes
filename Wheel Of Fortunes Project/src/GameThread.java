
import java.io.File;
import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;


public class GameThread extends Thread {
    DatagramSocket socket;
    Communication communication;
    InetAddress[] usersIP = new InetAddress[3];
    int[] usersPort = new int[3];
    String[] usersNick = new String[3];
    int port;
    DatagramPacket toSend, toReceive;
    String haslo, haslo2;
    boolean odgadniete;
    
    public GameThread(int port, InetAddress[] usersIP, int[] usersPort, String[] usersNick){
        System.err.println("Wątek wystartował z nr: "+port);
        this.port = port;
        this.usersIP = usersIP;
        this.usersPort = usersPort;
        this.usersNick = usersNick;
        try{
            this.socket = new DatagramSocket(port);
            socket.setSoTimeout(10000);
        }catch(SocketException ex){
            System.err.println("SocketException in construtor in thread GameThread");
        }
        communication = new Communication(socket);
    }
    
    @Override
    public void run(){
        odgadniete = false;
        int zgadujacy = new Random().nextInt(3), rand;
        DatagramPacket pakiet;
        
        // przywitanie się wątku
        sendToAll(3, Integer.toString(port));
        wczytajHaslo();
        zakodujHaslo();
        
        while(!odgadniete){
            if(zgadujacy == 3) zgadujacy = 0;
            rand = new Random().nextInt(1400)+100;
            
            // zakodowane haslo
            sendToAll(4, haslo);
            
            // zgadujacy
            sendToAll(5, usersNick[zgadujacy]);
            
            // stawka
            sendToAll(7, Integer.toString(rand));
            
            // odbieram tekst wyslany przez uzytkownika
            pakiet = communication.getPack();
            if(sprawdzKomunikacje(pakiet)){
                communication.sendPack(5, usersNick[zgadujacy], pakiet.getAddress(), pakiet.getPort());
                
                String text = new String(pakiet.getData());
                String[] words = text.split(";");

                // wyslanie obecnie zgadywaniego tekstu
                sendToAll(6, words[1]);
                
                if(!sprawdz(words[1], pakiet))
                    zgadujacy++;
                else{
                    // odbieram kwote obecnie grajacego uzytkownika
                    pakiet = communication.getPack();
                    if(sprawdzKomunikacje(pakiet)){
                        text = new String(pakiet.getData());
                        words = text.split(";");
                        sendToAll(9, words[1].concat(";").concat(words[2]));
                    }
                }
            }else{
                break;
            }
        }
        System.err.println("Thread "+ port + " kończy działanie");
    }
    
    private void wczytajHaslo(){
        String[] hasla = new String[1000];
        int ile = 0;
        File file = new File("hasla.txt");
        try{
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                hasla[ile++] = in.nextLine();
            }
            System.err.println("Ilosc hasel: " +ile);
            haslo = hasla[new Random().nextInt(ile)];
            System.err.println(haslo);
        } catch (FileNotFoundException ex) {
            System.out.println("Plik z haslami nie istnieje");
        }
    }
    
    private void zakodujHaslo(){
        String temp = "";
        for(int i = 0; i < haslo.length(); i++){
            if(haslo.indexOf(i) == ' '){
                temp+="  ";
            }
            else{
                temp += "_ ";
            }
        }
        haslo2 = haslo;
        haslo = temp;
    }
    
    private int sprawdzLitere(String z){
        int ile = 0;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        char znak = z.toCharArray()[0];
        boolean czyJest = false;
        char[] slowo = haslo.toCharArray();
        for(int i = 0; i < haslo2.length(); i++){
            if(znak == haslo2.charAt(i) && haslo.charAt(i*2) == '_'){
                ile++;
                slowo[i*2] = znak;
                czyJest = true;
            }
        }
        haslo = String.valueOf(slowo);
        return ile;
    }
    
    private boolean sprawdz(String text, DatagramPacket pakiet){
        int ile = 0;
        if(text.length() == 1){
            ile = sprawdzLitere(text);
            if(ile==0){
                return false;
            }
            System.out.println(haslo);
            boolean jest = false;
            for(int i = 0; i < haslo.length(); i++){
                if(haslo.charAt(i) == '_'){
                    jest = true;
                    break;
                }
            }
            if(!jest){
                odgadniete = true;
            }
        }else if(text.length() > 1){
            if(!text.equals(haslo2)){
                return false;
            }
            else{
                odgadniete = true;
            }
        }
        String ilee = String.valueOf(ile);
        communication.sendPack(8, ilee , pakiet.getAddress(), pakiet.getPort());
        if(odgadniete){
            if(pakiet.getPort() == usersPort[0]){
                sendToAll(10, usersNick[0]);
                communication.sendPack(10, usersNick[0] , pakiet.getAddress(), pakiet.getPort());
            }
            else if(pakiet.getPort() == usersPort[1]){
                sendToAll(10, usersNick[1]);
                communication.sendPack(10, usersNick[1] , pakiet.getAddress(), pakiet.getPort());
            }
            else{
                sendToAll(10, usersNick[2]);
                communication.sendPack(10, usersNick[2] , pakiet.getAddress(), pakiet.getPort());
            }
            sendToAll(11, haslo2);
        }
        return true;
    }
    
    private void sendToAll(int nr, String text){
        for(int i = 0; i < 3; i++){
            communication.sendPack(nr, text, usersIP[i], usersPort[i]);
        }
    }
    
    private boolean sprawdzKomunikacje(DatagramPacket packet){
        return packet != null;
    }
}
