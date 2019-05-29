package V2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Room extends Thread{
    private ArrayList<Person> users;
    private DatagramSocket socket;
    private String haslo, zakodowaneHaslo;
    private int kto;
    private Random rand;
    private boolean odgadniete;
    
    public Room(DatagramSocket socket){
        this.socket = socket;
        users = new ArrayList<>();
        haslo = zakodowaneHaslo = "";
        rand = new Random();
        kto = rand.nextInt(2);
        odgadniete = false;
    }
    
    public void addUser(Person person){
        users.add(person);
        for(int i = 0; i < users.size(); i++){
            for(int j =0; j < users.size(); j++){
                sendPack(3, users.get(j).getMyLogin(), users.get(i));
            }
        }
    }
    
    public int getUserCount(){
        return users.size();
    }
    
    public boolean isWorking(){
        return users.size() == 3;
    }
    
    public void ktoZgaduje(){
        sendToAll(6, users.get(kto).getMyLogin());
        if(kto == 3) kto = 0;
    }
    
    public void stawka(){
        sendToAll(8, Integer.toString(rand.nextInt(1400)+100));
    }
    
    public void zgaduje(String zgadywane){
        sendToAll(7, zgadywane);
        int ile = 0;
        if(zgadywane.length() == 1){
            ile = sprawdzLitere(zgadywane);
            if(ile == 0){
                kto++;
            }else
                sendPack(9, Integer.toString(ile), users.get(kto));
            boolean jest = false;
            for(int i = 0; i < haslo.length(); i++){
                if(zakodowaneHaslo.charAt(i) == '_'){
                    jest = true;
                    break;
                }
            }
            if(!jest){
                odgadniete = true;
            }
        }else if(zgadywane.length() > 1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            if(zgadywane.equals(haslo)){
                odgadniete = true;
            }
        }
        if(odgadniete){
            sendToAll(11, users.get(kto).getMyLogin().concat(";").concat(haslo));
        }else{
            sendToAll(5, zakodowaneHaslo);
            ktoZgaduje();
            stawka();
        }
    }
    
    public void przekazPkt(String kto, String ile){
        sendToAll(10, kto.concat(";").concat(ile));
    }
    
    private int sprawdzLitere(String z){
        int ile = 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}
        char znak = z.toCharArray()[0];
        boolean czyJest = false;
        char[] slowo = zakodowaneHaslo.toCharArray();
        for(int i = 0; i < haslo.length(); i++){
            if(znak == haslo.charAt(i) && zakodowaneHaslo.charAt(i*2) == '_'){
                ile++;
                slowo[i*2] = znak;
                czyJest = true;
            }
        }
        zakodowaneHaslo = String.valueOf(slowo);
        return ile;
    }
    
    
    @Override
    public void run() {
        sendToAll(4, "");
        wczytajHaslo();
        sendToAll(5, zakodowaneHaslo);
        ktoZgaduje();
        stawka();
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
    
    private void sendToAll(int nrPack, String text){
        for(int i = 0; i < 3; i++)
            sendPack(nrPack, text, users.get(i));
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
        zakodujHaslo();
    }
    
    private void zakodujHaslo(){
        for(int i = 0; i < haslo.length(); i++){
            if(haslo.indexOf(i) == ' '){
                zakodowaneHaslo+="  ";
            }
            else{
                zakodowaneHaslo += "_ ";
            }
        }
    }
    
}
