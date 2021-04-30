package a5;

import java.io.IOException;
import java.net.*;

public class ClientVelocimetre {
    private int portDesti;
    private String ipServer;
    private InetAddress adrecaDesti;
    private MulticastSocket multicastSocket;
    private InetAddress multicastIP;
    InetSocketAddress groupMulticast;
    NetworkInterface netIf;
    boolean continueRunning = true;
    public ClientVelocimetre(String ip, int port) {
        this.portDesti = port;
        ipServer=ip;
        try {
            multicastSocket = new MulticastSocket(5557);
            multicastIP = InetAddress.getByName("224.0.2.12");
            groupMulticast = new InetSocketAddress(multicastIP,5557);
            netIf = NetworkInterface.getByName("enp2s0");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runClient()throws IOException{
      byte []  receivedData = new byte[1024];
      multicastSocket.joinGroup(groupMulticast,netIf);
      while(continueRunning){
          DatagramPacket datagramPacket = new DatagramPacket(receivedData,1024);
          multicastSocket.receive(datagramPacket);
          continueRunning = printData(datagramPacket.getData());
      }

    }

    private boolean printData(byte[] data) {
    }
}
