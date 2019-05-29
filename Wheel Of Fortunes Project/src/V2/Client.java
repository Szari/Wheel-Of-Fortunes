package V2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client extends GUI{
    DatagramSocket socket;
    int portSerwera = 7000;
    InetAddress address;
    String myNickname;
    ButtonHandler bHandler;
        int pokoj;
    
    public Client(){
        this.setVisible(true);
        try{
            socket = new DatagramSocket();
            socket.setSoTimeout(5000);
            address = InetAddress.getByName("localhost");
            bHandler = new ButtonHandler(socket);
            startButton.addActionListener(bHandler);
            roomButton.addActionListener(bHandler);
            sendText.addActionListener(bHandler);
            leaveButton.addActionListener(bHandler);
            exitButton.addActionListener(bHandler);
            newGame.addActionListener(bHandler);
        }catch(SocketException se){
            System.err.println("SE");
        }catch(UnknownHostException uhe){
            System.err.println("UHE");
        }
    }
    
    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }
    /**
     * Funckja nasłuchująca i reagująca odpowiednio do otrzymanego pakietu
     */
    private void run(){
        DatagramPacket packet;
        String[] words;
        int kwota = 0, kwotaa = 0;
        
        while(true){
            packet = getPack();
            words = packToArray(packet);
            
            switch(Integer.parseInt(words[0])){
                case 1:
                    myNickname = words[1];
                    loginInLobby.setText(words[1]);
                    player1.setText(words[1]);
                    player1.setVisible(true);
                    p1.setVisible(true);
                    pp1.setVisible(true);
                    break;
                case 2:
                        pokoje.setVisible(true);
                        nrPokoju.setVisible(true);
                    roomButton.setVisible(true);
                        String temp = "Wolne pokoje: ";
                    for(int i = 1; i < words.length; i++){
                        temp = temp.concat(words[i]).concat(", ");
                    }
                        pokoje.setLineWrap(true);
                        pokoje.setText(temp);
                    break;
                case 3:
                    if(!words[1].equals(player1.getText())){
                        if(player2.getText().equals("")){
                            player2.setText(words[1]);
                            player2.setVisible(true);
                            p2.setVisible(true);
                            pp2.setVisible(true);
                        }else{
                            player3.setText(words[1]);
                            player3.setVisible(true);
                            p3.setVisible(true);
                            pp3.setVisible(true);
                            infoLabel.setText("Czekanie na dołączenie reszty graczy");
                        }
                    }
                    break;
                case 4:
                    infoLabel.setText("Zaczynamy rozgrywkę");
                    leaveButton.setEnabled(false);
                    exitButton.setEnabled(true);
                    break;
                case 5:
                    haslo.setVisible(true);
                    haslo.setText(words[1]);
                    odgadywane.setVisible(true);
                    litery.setVisible(true);
                    break;
                case 6:
                    infoLabel.setText("Zgaduje "+words[1]);
                    if(words[1].equals(player1.getText()))
                            sendText.setEnabled(true);
                    break;
                case 7:
                    if(litery.getText().equals(""))
                        litery.setText(litery.getText().concat(words[1]));
                    else
                        litery.setText(litery.getText().concat(", ").concat(words[1]));
                    infoLabel.setText("Zgadywane: "+ words[1]);
                    break;
                case 8:
                    if(!stawka.isVisible()){
                        stawka.setVisible(true);
                        stawkaL.setVisible(true);
                    }
                    kwotaa = Integer.parseInt(words[1]);
                    stawkaL.setText(words[1]);
                    break;
                case 9:
                    int ile = Integer.parseInt(words[1]);
                    if(kwotaa != 0){
                        kwota += (kwotaa*ile);
                        pp1.setText(Integer.toString(kwota));
                            sendPack(103, (Integer.toString(pokoj)).concat(";").concat(player1.getText()).concat(";").concat(Integer.toString(kwota)));
                    }
                    break;
                case 10:
                    System.out.println("[1]"+words[1]);
                    System.out.println("[2]"+words[2]);
                    System.out.println("[3]"+words[3]);
                    if(player2.getText().equals(words[1]))
                        pp2.setText(words[2]);
                    else if(player3.getText().equals(words[1]))
                        pp3.setText(words[2]);
                    break;
                case 11: 
                    infoLabel.setText("Odgadnieto haslo! Wygrywa " + words[1]);
                    haslo.setText(words[2]);
                    newGame.setVisible(true);
                    break;
                case 12:
                    if(player2.getText().equals(words[1])){
                        player2.setText("");
                        p2.setVisible(false);
                        pp2.setVisible(false);
                    }else if(player3.getText().equals(words[1])){
                        player3.setText("");
                        p3.setVisible(false);
                        pp3.setVisible(false);
                    }
                    break;
                case 13:
                    waitingPanel.setVisible(true);
                    gamePanel.setVisible(false);
                    p2.setVisible(false);
                    pp2.setVisible(false);
                    player2.setText("");
                    p3.setVisible(false);
                    pp3.setVisible(false);
                    player3.setText("");
                    haslo.setVisible(false);
                    sendText.setEnabled(false);
                    stawka.setVisible(false);
                    odgadywane.setVisible(false);
                    newGame.setVisible(false);
                    litery.setText("");
                    stawkaL.setText("");
                    errorLobby1.setVisible(true);
                    leaveButton.setEnabled(true);
                    exitButton.setEnabled(true);
                    break;
            }
        }
    }
    /**
     * Funkcja wysyłająca pakiet do serwera
     * @param nrPack    numer pakietu
     * @param text      zawartość pakietu
     */
    private void sendPack(int nrPack, String text){
        boolean reached = false;
        byte[] daneDO = new byte[256];
        byte[] daneDW = (Integer.toString(nrPack) + ";" + text + ";").getBytes();
        System.out.println("Sended: " + Integer.toString(nrPack) + ";" + text + ";");
        DatagramPacket toSend = new DatagramPacket(daneDW, daneDW.length, address, portSerwera);
        DatagramPacket toReceive = new DatagramPacket(daneDO, daneDO.length);
        try{
            socket.send(toSend);
            while(!reached){
            try{
                socket.receive(toReceive);
                reached = true;
            }catch(SocketTimeoutException ex){
                try{
                socket.send(toSend);
                }catch(IOException e){}
                System.out.println("Waiting for confirmaton package number "+nrPack);
            }
            }
        }catch(IOException ioe){
            System.err.println("IOE");
        }
    }
    /**
     * Odbiera pakiet
     * @return      Zwraca otrzymany pakiet
     */
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
    /**
     * Zamienia pakiet na tablice ciągów znaków
     * @param packet    Pakiet do przekonwertowania
     * @return          Tablica ciagów znaków
     */
    private String[] packToArray(DatagramPacket packet){
        return new String(packet.getData()).split(";");
    }
    
    
    /**
     * Klasa nasłuchująca reakcji z przyciskami
     */
    public class ButtonHandler implements ActionListener{
        DatagramSocket socket;

        public ButtonHandler(DatagramSocket socket) {
            this.socket = socket;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
                if(source == startButton){
                    myNickname = nickname.getText();
                    if(myNickname.length() > 3){
                        loginPanel.setVisible(false);
                        waitingPanel.setVisible(true);
                        loginLabel.setVisible(true);
                        loginInLobby.setVisible(true);
                        sendPack(100, myNickname);
                    }else{
                        errorLogin.setVisible(true);
                    }
                }
                else if(source == roomButton){
                            pokoj = Integer.parseInt(nrPokoju.getText());
                    errorLobby1.setVisible(false);
                            if(pokoj > 0 && pokoj < 50){
                        waitingPanel.setVisible(false);
                        gamePanel.setVisible(true);
                        tekstZgadujacego.setVisible(true);
                        sendText.setVisible(true);
                        infoLabel.setVisible(true);
                        infoLabel.setText("Oczekiwanie na resztę graczy...");
                            sendPack(101, Integer.toString(pokoj).concat(";").concat(myNickname));
                    }else{
                        errorLobby.setVisible(true);
                    }
                }
                else if(source == sendText){
                    if(tekstZgadujacego.getText().length()>0){
                        sendText.setEnabled(false);
                            sendPack(102, Integer.toString(pokoj).concat(";").concat(tekstZgadujacego.getText()));
                        tekstZgadujacego.setText("");
                    }
                }
                else if(source == newGame){
                    waitingPanel.setVisible(true);
                    gamePanel.setVisible(false);
                    p1.setVisible(false);
                    pp1.setVisible(false);
                    p2.setVisible(false);
                    pp2.setVisible(false);
                    player2.setText("");
                    p3.setVisible(false);
                    pp3.setVisible(false);
                    player3.setText("");
                    haslo.setVisible(false);
                    sendText.setEnabled(false);
                    stawka.setVisible(false);
                    odgadywane.setVisible(false);
                    newGame.setVisible(false);
                    litery.setText("");
                    stawkaL.setText("");
                    leaveButton.setEnabled(true);
                    exitButton.setEnabled(false);
                }
                else if(source == leaveButton){
                    waitingPanel.setVisible(true);
                    gamePanel.setVisible(false);
                    p2.setVisible(false);
                    pp2.setVisible(false);
                    player2.setText("");
                    p3.setVisible(false);
                    pp3.setVisible(false);
                    player3.setText("");
                    haslo.setVisible(false);
                    sendText.setEnabled(false);
                    stawka.setVisible(false);
                    odgadywane.setVisible(false);
                    newGame.setVisible(false);
                    litery.setText("");
                    stawkaL.setText("");
                        sendPack(104, Integer.toString(pokoj).concat(";").concat(myNickname));
                }
                else if(source == exitButton){
                        sendPack(105, Integer.toString(pokoj).concat(";").concat(myNickname));
                    System.exit(0);
                }
        }
        /**
         * Wysyła pakiet do serwera
         * @param nrPack    numer pakietu
         * @param text      zawartość pakietu
         */
        private void sendPack(int nrPack, String text){
            byte[] daneDW = (Integer.toString(nrPack) + ";" + text + ";").getBytes();
            System.out.println("Sended: " + Integer.toString(nrPack) + ";" + text + ";");
            DatagramPacket toSend = new DatagramPacket(daneDW, daneDW.length, address, portSerwera);
            try{
                socket.send(toSend);
            }catch(IOException ioe){
                System.err.println("IOE");
            }
        }    
    }
}
