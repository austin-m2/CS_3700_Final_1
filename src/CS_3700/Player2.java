//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Player2 {

    public static void main(String[] args) throws Exception {

        DatagramSocket player1outsocket = new DatagramSocket(/*6972*/);
        DatagramSocket player3outsocket = new DatagramSocket(/*6971*/);
        DatagramSocket player1insocket  = new DatagramSocket(6969);
        DatagramSocket player3insocket  = new DatagramSocket(6973);

        for (int i = 0; i < 10; i++) {

            TimeUnit.MILLISECONDS.sleep(500);

            String outChoice = "P";
            byte arr[] = outChoice.getBytes();
            byte arrIn[] = outChoice.getBytes();

            InetAddress address = InetAddress.getByName("DESKTOP-J4CA988");


            DatagramPacket player1outpacket = new DatagramPacket(arr, arr.length, address, 6972);
            DatagramPacket player3outpacket = new DatagramPacket(arr, arr.length, address, 6971);
            DatagramPacket player1inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6969*/);
            DatagramPacket player3inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6973*/);



            player1insocket.receive(player1inpacket);
            System.out.println("packet received from player1");
            String player1choice = new String(player1inpacket.getData());

            player1outsocket.send(player1outpacket);
            System.out.println("packet sent to player1");
            player3outsocket.send(player3outpacket);
            System.out.println("packet sent to player3");

            player3insocket.receive(player3inpacket);
            System.out.println("packet received from player3");
            String player3choice = new String(player3inpacket.getData());


            System.out.println(player1choice);
            System.out.println(player3choice);

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
