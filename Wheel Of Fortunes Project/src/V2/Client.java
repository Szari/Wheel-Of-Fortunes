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
            }
        }
    }
    
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
    
    private String[] packToArray(DatagramPacket packet){
        return new String(packet.getData()).split(";");
    }
    
    
    
    
    
    //  ======= BUTTON HANDLER ==========
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
        }
        
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
