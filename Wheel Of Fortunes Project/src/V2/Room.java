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
    /**
     * Dodaje użytkownika do listy graczy w danym pokoju
     * @param person    Nowy gracz
     */
    public void addUser(Person person){
        users.add(person);
        for(int i = 0; i < users.size(); i++){
            for(int j =0; j < users.size(); j++){
                sendPack(3, users.get(j).getMyLogin(), users.get(i));
            }
        }
    }
    /**
     * @return      Zwraca ilość graczy w pokoju 
     */
    public int getUserCount(){
        return users.size();
    }
    /**
     * @return      Zwraca true albo false w zależnosci czy jest 3 graczy co jest jednoznaczne że są w trakcie rozgrywki
     */
    public boolean isWorking(){
        return users.size() == 3;
    }
    /**
     * Wysyła graczom komunikat kogo obecnie jest kolej zgadywania
     */
    public void ktoZgaduje(){
        if(kto == 3) kto = 0;
        sendToAll(6, users.get(kto).getMyLogin());
    }
    /**
     * Wysyła graczom komunikat o jaką stawkę toczy się obecnie rozgrywka
     */
    public void stawka(){
        sendToAll(8, Integer.toString(rand.nextInt(1400)+100));
    }
    /**
     * Funkcja sprawdzająca to co zgaduje użytkownik
     * Wysyła komunikat co jest obecnie zgadywane
     * @param zgadywane Jeżeli długość stringa jest równa = 1 sprawdza czy została trafiona literka, w wypadku kiedy jest dłuższa sprawdza czy zgadywane hasło jest poprawne
     * Jeżeli hasło zostało odgadnięte kończy rozgrywkę wysyłając pakiet nr 11 i czyści wszystkie zmienne
     */
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
            for(int i = 0; i < zakodowaneHaslo.length(); i++){
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
            }else{
                kto++;
            }
        }
        if(odgadniete){
            sendToAll(11, users.get(kto).getMyLogin().concat(";").concat(haslo));
            sendToAll(11, users.get(kto).getMyLogin().concat(";").concat(haslo));
            users = new ArrayList<>();
            haslo = zakodowaneHaslo = "";
            kto = 0;
            odgadniete = false;   
        }else{
            sendToAll(5, zakodowaneHaslo);
            sendToAll(5, zakodowaneHaslo);
            ktoZgaduje();
            stawka();
        }
    }
    /**
     * Wysyła graczom obecny stan punktów danego gracza
     * @param kto   kogo punkty
     * @param ile   ile ma punktów
     */
    public void przekazPkt(String kto, String ile){
        sendToAll(10, kto.concat(";").concat(ile));
    }
    /**
     * Sprawdza ile razy dana litera występuje hasle i jezeli wystepuje podmienia znak '_' na daną litere
     * @param z     litera do sprawdzenia
     * @return      ilosc wystąpień
     */
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
    /**
     * Wysyła pakiet do klienta
     * @param nrPack    Numer pakietu
     * @param text      Zawartość pakietu
     * @param person    Do kogo ma być wysłany 
     */
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
    /**
     * Wysyła pakiet to wszystkich użytkowników w pokoju
     * @param nrPack    Numer pakietu
     * @param text      Zawartość pakietu
     */
    private void sendToAll(int nrPack, String text){
        for(int i = 0; i < users.size(); i++)
            sendPack(nrPack, text, users.get(i));
    }
    /**
     * Wczytuje wszystkie hasla z pliku i losuje jedno do rozgrywki a nastepnie wywołująć kolejną funkcje zakodowuje hasło
     */
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
    /**
     * Zakodowuje hasło wybrane do zgadywania w trakcie danej rozgrywki
     */
    private void zakodujHaslo(){
        for(int i = 0; i < haslo.length(); i++){
            if(haslo.charAt(i) == ' '){
                zakodowaneHaslo+="  ";
            }
            else{
                zakodowaneHaslo += "_ ";
            }
        }
    }
    /**
     * Usuwa gracza z listy grazy w pokoju, funkcja możliwa tylko przed wystartowaniem rozgrywki
     * @param nickname  Nickname do usunięcia
     */
    public void usunGracza(String nickname){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getMyLogin().equals(nickname))
                users.remove(i);
        }
        sendToAll(12, nickname);
    }
    /**
     * Wysyła graczom informację że jeden z graczy wyszedł z aplikacji żeby wrócili do lobby oraz czyści swoje dane aby być gotowym na kolejną rozgrywkę
     */
    public void wyszedlGracz(){
        sendToAll(13, "");
        users = new ArrayList<>();
        haslo = zakodowaneHaslo = "";
        kto = 0;
        odgadniete = false;  
    }
    
}
