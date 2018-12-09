//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Player1 {

    public static void main(String[] args) throws Exception {

        DatagramSocket player2outsocket = new DatagramSocket(/*6969*/);
        DatagramSocket player3outsocket = new DatagramSocket(/*6970*/);
        DatagramSocket player2insocket  = new DatagramSocket(6972);
        DatagramSocket player3insocket  = new DatagramSocket(6974);


        for (int i = 0; i < 10; i++) {

            TimeUnit.SECONDS.sleep(1);

            String outChoice = "R";
            byte arr[] = outChoice.getBytes();
            byte arrIn[] = outChoice.getBytes();

            InetAddress address = InetAddress.getByName("DESKTOP-J4CA988");


            DatagramPacket player2outpacket = new DatagramPacket(arr, arr.length, address, 6969);
            DatagramPacket player3outpacket = new DatagramPacket(arr, arr.length, address, 6970);
            DatagramPacket player2inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6972*/);
            DatagramPacket player3inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6974*/);


            player2outsocket.send(player2outpacket);
            System.out.println("packet sent to player2");
            player3outsocket.send(player3outpacket);
            System.out.println("packet sent to player3");

            player2insocket.receive(player2inpacket);
            System.out.println("packet received from player2");
            String player2choice = new String(player2inpacket.getData());

            player3insocket.receive(player3inpacket);
            System.out.println("packet received from player3");
            String player3choice = new String(player3inpacket.getData());


            System.out.println(player2choice);
            System.out.println(player3choice);

        }




        //Date sendTime = new Date();

        //dsock.receive(dpack);
        //String message2 = new String(dpack.getData());
        //Date receiveTime = new Date();
        //System.out.println((receiveTime.getTime() - sendTime.getTime()) + " ms echo time for " + message2);
    }
}
