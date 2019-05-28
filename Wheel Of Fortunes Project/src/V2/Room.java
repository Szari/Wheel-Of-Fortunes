package V2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Room extends Thread implements Runnable{
    private ArrayList<Person> users;
    private DatagramSocket socket;
    private String haslo, zakodowaneHaslo;
    
    public Room(DatagramSocket socket){
        this.socket = socket;
        users = new ArrayList<>();
        haslo = zakodowaneHaslo = "";
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
    
    @Override
    public void run() {
        sendToAll(4, "");
        wczytajHaslo();
        System.out.println(haslo);
        zakodujHaslo();
        sendToAll(5, zakodowaneHaslo);
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
