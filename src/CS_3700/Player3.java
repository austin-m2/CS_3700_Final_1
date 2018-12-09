//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class Player3 {

    public static void main(String[] args) throws Exception {

        DatagramSocket player1insocket = new DatagramSocket(6970);
        DatagramSocket player2insocket = new DatagramSocket(6971);
        DatagramSocket player1outsocket = new DatagramSocket(/*6974*/);
        DatagramSocket player2outsocket = new DatagramSocket(/*6973*/);

        for (int i = 0; i < 10; i++) {
            String outChoice = "S";
            byte arr[] = outChoice.getBytes();
            byte arrIn[] = outChoice.getBytes();

            InetAddress address = InetAddress.getByName("DESKTOP-J4CA988");


            DatagramPacket player1outpacket = new DatagramPacket(arr, arr.length, address, 6974);
            DatagramPacket player2outpacket = new DatagramPacket(arr, arr.length, address, 6973);
            DatagramPacket player1inpacket = new DatagramPacket(arrIn, arrIn.length/*, address, 6970*/);
            DatagramPacket player2inpacket = new DatagramPacket(arrIn, arrIn.length/*, address, 6971*/);


            player1insocket.receive(player1inpacket);
            String player1choice = new String(player1inpacket.getData());

            player2insocket.receive(player2inpacket);
            String player2choice = new String(player2inpacket.getData());

            player1outsocket.send(player1outpacket);
            player2outsocket.send(player2outpacket);





            System.out.println(player1choice);
            System.out.println(player2choice);
        }




/*        byte arr1[] = new byte[150];
        DatagramPacket dpack = new DatagramPacket(arr1, arr1.length);


        while (true) {
            dsock.receive(dpack);
            byte arr2[] = dpack.getData();
            int packSize = dpack.getLength();
            String s2 = new String(arr2, 0, packSize);

            System.out.println(new Date() + " " + dpack.getAddress() + ": " + dpack.getPort() + " " + s2);
            dsock.send(dpack);
        }*/



/*
        InetAddress address = InetAddress.getByName("DESKTOP-J4CA988");

        DatagramSocket dsock = new DatagramSocket();
        String message1 = "hello";
        byte arr[] = message1.getBytes();
        DatagramPacket dpack = new DatagramPacket(arr, arr.length, address, 7);
        dsock.send(dpack);
        Date sendTime = new Date();

        dsock.receive(dpack);
        String message2 = new String(dpack.getData());
        Date receiveTime = new Date();
        System.out.println((receiveTime.getTime() - sendTime.getTime()) + " ms echo time for " + message2);
*/



    }
}
