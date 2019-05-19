
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
    
    public Client(){
        this.setVisible(true);
        try{
            socket = new DatagramSocket();
            socket.setSoTimeout(5000);
            ButtonHandler bHandler = new ButtonHandler(socket);
            startButton.addActionListener(bHandler);
            sendText.addActionListener(bHandler);
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
                InetAddress address = InetAddress.getByName("localhost");
                Communication communication = new Communication(socket);
                
                if(source == startButton){
                    String nick = nickname.getText();
                    if(nick.length() > 3){
                        loginPanel.setVisible(false);
                        gamePanel.setVisible(true);
                        communication.sendPack(1, nick, address, portSerwera);
                    }
                }else if(source == sendText){
                    communication.sendPack(22, tekstZgadujacego.getText(), address, portSerwera);
                    sendText.setEnabled(false);
                }
                
            }catch(IOException ex){
                System.err.println("IOException in class ButtonHandler");
            }
        }
    }
    
    public void run(){
        
        Communication communication = new Communication(socket);
        DatagramPacket packet;
        byte[] buf;
        while(true){
            buf = new byte[256];
            
            packet = communication.getPack();
            String text = new String(packet.getData());
            String[] words = text.split(";");
            
            switch(Integer.parseInt(words[0])){
                case 1:
                    player1.setText(words[1]);
                    p1.setVisible(true);
                    pp1.setVisible(true);
                    infoLabel.setText("Czekanie na dołączenie reszty graczy");
                    break;
                case 2:
                    if(!words[1].equals(player1.getText()))
                    if(player2.getText().equals("")){
                        player2.setText(words[1]);
                        p2.setVisible(true);
                        pp2.setVisible(true);
                        infoLabel.setText("<html>Czekanie na dołączenie reszty graczy<br/>Nowy gracz "+words[1]+"</html>");
                    }else{
                        player3.setText(words[1]);
                        p3.setVisible(true);
                        pp3.setVisible(true);
                        infoLabel.setText("<html>Czekanie na dołączenie reszty graczy<br/>Nowy gracz "+words[1]+"</html>");     
                    }
                    break;
                case 3:
                    infoLabel.setText("Zaczynamy rozgrywkę");
                    portSerwera = Integer.parseInt(words[1]);
                    break;
                case 4:
                    haslo.setVisible(true);
                    haslo.setText(words[1]);
                    break;
                case 5:
                    infoLabel.setText("Zgaduje "+words[1]);
                    if(words[1].equals(player1.getText()))
                        sendText.setEnabled(true);
            }
            
        }
        
    }
    
    public static void main (String[] args){
        Client client = new Client();
        client.run();
    }
}
