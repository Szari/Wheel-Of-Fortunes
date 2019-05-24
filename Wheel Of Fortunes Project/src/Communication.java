
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;


public class Communication {
    private byte[] daneDO, daneDW;
    private DatagramPacket toSend, toReceive;
    private final DatagramSocket socket;
    
    public Communication(DatagramSocket socket){
        this.socket = socket;
    }
    
    public void sendPack(int nr, String text, InetAddress ipAddress, int port){
        boolean reached = false;
        daneDO = new byte[256];
        daneDW = (Integer.toString(nr) + ";" + text + ";").getBytes();
        System.out.println("Sended: " + Integer.toString(nr) + ";" + text + ";");
        toReceive = new DatagramPacket(daneDO, daneDO.length);
        toSend = new DatagramPacket(daneDW, daneDW.length, ipAddress, port);
        try{
        socket.send(toSend);}catch(IOException ex){}
        while(!reached){
            try{
                socket.receive(toReceive);
                reached = true;
            }catch(SocketTimeoutException ex){
                try{
                socket.send(toSend);
                }catch(IOException e){}
                System.out.println("Waiting for confirmaton package number "+nr);
            }
            catch(IOException ex){
                System.err.println("IOException trying send package");
            }
        }
    }
    
    public DatagramPacket getPack(){
        boolean reached = false;
        int brakKomunikacji = 0;
        daneDO = new byte[256];
        daneDW = new byte[256];
        
        toReceive = new DatagramPacket(daneDO, daneDO.length); 
     
        while(!reached){
            if(brakKomunikacji == 6){
                toReceive = null;
                break;
            }
            try{
                socket.receive(toReceive);
                System.out.println("Received: " + new String(toReceive.getData()));
                daneDW = (new String(toReceive.getData()).split(";")[0] + ";").getBytes();
                toSend = new DatagramPacket(daneDW, daneDW.length, toReceive.getAddress(), toReceive.getPort());
                socket.send(toSend);
                
                reached = true;
            }catch(SocketTimeoutException ex){
                System.out.println("Waiting for package");
                brakKomunikacji++;
            }
            catch(IOException ex){
                System.err.println("IOException trying get package");
            }
        }
        return toReceive;
    }
}
