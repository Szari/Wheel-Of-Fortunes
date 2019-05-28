package V2;

import java.net.InetAddress;

public class Person {
    private final InetAddress myIP;
    private final int myPort;
    private final String myLogin;

    public Person(InetAddress myIP, int myPort, String myLogin) {
        this.myIP = myIP;
        this.myPort = myPort;
        this.myLogin = myLogin;
    }

    public InetAddress getMyIP() {
        return myIP;
    }

    public int getMyPort() {
        return myPort;
    }

    public String getMyLogin() {
        return myLogin;
    }
    
    @Override
    public String toString(){
        return "Hi, " + myLogin;
    }
    
    
}
