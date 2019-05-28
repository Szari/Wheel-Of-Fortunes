package V1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client extends GUI{
    DatagramSocket socket;
    int portSerwera = 1024;
    InetAddress address;
    
    public Client(){
        this.setVisible(true);
        try{
            socket = new DatagramSocket();
            socket.setSoTimeout(10000);
            ButtonHandler bHandler = new ButtonHandler(socket);
            startButton.addActionListener(bHandler);
            sendText.addActionListener(bHandler);
            newGame.addActionListener(bHandler);
        }catch(SocketException ex){
            System.err.println("SocketException in constructor in class Client");
        }
    }
    
    public class ButtonHandler implements ActionListener{
        DatagramSocket socket;
        
        public ButtonHandler(DatagramSocket socket){
            this.socket = socket;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            try{
                DatagramPacket toSend;
                DatagramPacket toReceive;
                byte[] buf = new byte[256];
                address = InetAddress.getByName("localhost");
                Communication communication = new Communication(socket);
                
                if(source == startButton){
                    String nick = nickname.getText();
                    if(nick.length() > 3){
                        loginPanel.setVisible(false);
                        gamePanel.setVisible(true);
                        communication.sendPack(1, nick, address, portSerwera);
                    }else{
                        errorLogin.setVisible(true);
                    }
                }else if(source == sendText){
                    communication.sendPack(22, tekstZgadujacego.getText(), address, portSerwera);
                    sendText.setEnabled(false);
                    tekstZgadujacego.setText("");
                }else if(source == newGame){
                    loginPanel.setVisible(true);
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
                    nickname.setText(player1.getText());
                    
                }
                
            }catch(IOException ex){
                System.err.println("IOException in class ButtonHandler");
            }
        }
    }
    
    public void run(){
        Communication communication = new Communication(socket);
        DatagramPacket packet;
        int kwota = 0, kwotaa = 0;
        
        while(true){
            packet = communication.getPack();
            if(sprawdzKomunikacje(packet)){
                String text = new String(packet.getData());
                String[] words = text.split(";");
                
                switch(Integer.parseInt(words[0])){
                    // potwierdzenie nicku 
                    case 1:
                        player1.setText(words[1]);
                        p1.setVisible(true);
                        pp1.setVisible(true);
                        infoLabel.setText("Czekanie na dołączenie reszty graczy");
                        break;
                    // otrzymanie wszystkich nickow w pokoju i ustawienie ich
                    case 2:
                        if(!words[1].equals(player1.getText()))
                            if(player2.getText().equals("")){
                                player2.setText(words[1]);
                                p2.setVisible(true);
                                pp2.setVisible(true);
                                infoLabel.setText("Czekanie na dołączenie reszty graczy");
                            }else{
                                player3.setText(words[1]);
                                p3.setVisible(true);
                                pp3.setVisible(true);
                                infoLabel.setText("Czekanie na dołączenie reszty graczy");
                            }
                        break;
                    // przywitanie się serwera
                    case 3:
                        infoLabel.setText("Zaczynamy rozgrywkę");
                        portSerwera = Integer.parseInt(words[1]);
                        break;
                    // otrzymanie hasla
                    case 4:
                        haslo.setVisible(true);
                        haslo.setText(words[1]);
                        odgadywane.setVisible(true);
                        break;
                    // otrzymanie informacji kto zgaduje 
                    case 5:
                        infoLabel.setText("Zgaduje "+words[1]);
                        if(words[1].equals(player1.getText()))
                            sendText.setEnabled(true);
                        break;
                    // otrzymanie zgadywanego hasla/literki
                    case 6:
                        if(litery.getText().equals(""))
                            litery.setText(litery.getText().concat(words[1]));
                        else
                            litery.setText(litery.getText().concat(", ").concat(words[1]));
                        infoLabel.setText("Zgadywane: "+ words[1]);
                        break;
                    // otrzymanie o jaką stawkę gra
                    case 7:
                        if(!stawka.isVisible())
                            stawka.setVisible(true);
                        kwotaa = Integer.parseInt(words[1]);
                        stawkaL.setText(words[1]);
                        break;
                    // otrzymuje ile trafił znakow
                    case 8:
                        int ile = Integer.parseInt(words[1]);
                        if(kwotaa != 0){
                            kwota += (kwotaa*ile);
                            pp1.setText(Integer.toString(kwota));
                            communication.sendPack(23, ((player1.getText()).concat(";")).concat(Integer.toString(kwota)), address, portSerwera);
                        }else{
                            System.err.println("false");
                        }
                        break;
                    // orzymanie punktow innych uzytkownikow
                    case 9:
                        if(player2.getText().equals(words[1]))
                            pp2.setText(words[2]);
                        else if(player3.getText().equals(words[1]))
                            pp3.setText(words[2]);
                        break;
                    // otrzymane przy wygraniu 
                    case 10:
                        infoLabel.setText("Haslo odgadniete przez: " + words[1] + ". Gratulacje!!");
                        newGame.setVisible(true);
                        portSerwera = 1024;
                        break;
                    // otrzymanie zgadnietego hasla 
                    case 11:
                        haslo.setText(words[1]);
                        break;
                }
            }
        }
        
    }
    
    boolean sprawdzKomunikacje(DatagramPacket packet){
        if(packet == null){
            infoLabel.setText("Brak odpowiedzi od serwera. Gra przerwana");
            newGame.setVisible(true);
            portSerwera = 1024;
            sendText.setEnabled(false);
            return false;
        }
        return true;
    }
    
    public static void main (String[] args){
        Client client = new Client();
        client.run();
    }
}
