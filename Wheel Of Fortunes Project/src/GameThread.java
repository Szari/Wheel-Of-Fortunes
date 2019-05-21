
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
    
    private void wczytajHaslo(){
        String[] hasla = new String[1000];
        int ile = 0;
        File file = new File("hasla.txt");
        try{
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                hasla[ile++] = in.nextLine();
            }
            haslo = hasla[new Random().nextInt(ile)];
            System.err.println(haslo);
        } catch (FileNotFoundException ex) {
            System.out.println("Plik z haslami nie istnieje");
        }
    }
    
    private void zakodujHaslo(){
        String temp = "";
        for(int i = 0; i < haslo.length(); i++){
            if(haslo.indexOf(i) == ' ')
                temp+="  ";
            else
                temp += "_ ";
        }
        haslo2 = haslo;
        haslo = temp;
    }
    
    @Override
    public void run(){
        byte[] buf = new byte[256];
        for(int i = 0; i < 3; i++){
            communication.sendPack(3, Integer.toString(port), usersIP[i], usersPort[i]);
        }
        wczytajHaslo();
        zakodujHaslo();
        boolean odgadniete = false;
        int zgadujacy = new Random().nextInt(3);
        DatagramPacket pakiet;
        while(!odgadniete){
            for(int i = 0; i < 3; i++){
                communication.sendPack(4, haslo, usersIP[i], usersPort[i]);
            }
            if(zgadujacy == 3){
                zgadujacy = 0;
            }
            for(int i = 0; i < 3; i++){
                communication.sendPack(5, usersNick[zgadujacy], usersIP[i], usersPort[i]);
            }
            pakiet = communication.getPack();
            communication.sendPack(5, usersNick[zgadujacy], pakiet.getAddress(), pakiet.getPort());
            String text = new String(pakiet.getData());System.out.println("1");
            String[] words = text.split(";");System.out.println("2");
            text = new String(pakiet.getData());System.out.println("4");
            words = text.split(";");System.out.println("5");
            System.out.println(words[1]);System.out.println("6");
            for(int i = 0; i < 3; i++){
                communication.sendPack(6, words[1], usersIP[i], usersPort[i]);
            }
            if(words[1].length() == 1){
                if(!sprawdzLitere(words[1])){
                    zgadujacy++;
                }
            }else if(words[1].length() > 1){
                if(!words[1].equals(haslo2)){
                    zgadujacy++;
                }
            }else{
                zgadujacy++;
            }
        }
    }
    
    private boolean sprawdzLitere(String z){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        char znak = z.toCharArray()[0];
        boolean czyJest = false;
        char[] slowo = haslo.toCharArray();
        for(int i = 0; i < haslo2.length(); i++){
            if(znak == haslo2.charAt(i) && haslo.charAt(i*2) == '_'){
                System.out.println(i);
                slowo[i*2] = znak;
                czyJest = true;
            }
        }
        haslo = String.valueOf(slowo);
        return czyJest;
    }
}
