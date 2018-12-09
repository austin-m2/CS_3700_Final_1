//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Player2 {

    public static void main(String[] args) throws Exception {
        int numMatches = Integer.parseInt(args[0]);

        int points = 0;

        DatagramSocket player1outsocket = new DatagramSocket(/*6972*/);
        DatagramSocket player3outsocket = new DatagramSocket(/*6971*/);
        DatagramSocket player1insocket  = new DatagramSocket(6969);
        DatagramSocket player3insocket  = new DatagramSocket(6973);

        for (int i = 0; i < numMatches; i++) {


            TimeUnit.MILLISECONDS.sleep(500);

            String outChoice = null;
            int num = (int) (Math.random() * 3.0) + 1;
            switch (num) {
                case 1:
                    outChoice = "R";
                    break;
                case 2:
                    outChoice = "P";
                    break;
                case 3:
                    outChoice = "S";
                    break;
                default:
                    System.out.println("Error: you messed it up somehow!");
                    System.exit(1);
            }

            byte arr[] = outChoice.getBytes();
            byte arrIn[] = outChoice.getBytes();

            InetAddress address = InetAddress.getByName("DESKTOP-J4CA988");


            DatagramPacket player1outpacket = new DatagramPacket(arr, arr.length, address, 6972);
            DatagramPacket player3outpacket = new DatagramPacket(arr, arr.length, address, 6971);
            DatagramPacket player1inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6969*/);
            DatagramPacket player3inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6973*/);



            player1insocket.receive(player1inpacket);
            String player1choice = new String(player1inpacket.getData());

            player1outsocket.send(player1outpacket);
            player3outsocket.send(player3outpacket);


            player3insocket.receive(player3inpacket);

            String player3choice = new String(player3inpacket.getData());


            String myChoice = getChoice(outChoice);
            player1choice = getChoice(player1choice);
            player3choice = getChoice(player3choice);

            System.out.println("Round " + (i + 1));
            System.out.println("I choose " + myChoice + "!");
            System.out.println("Player 1 chose " + player1choice +". " + ((playRPS(myChoice, player1choice) == 1) ? "I win!" : ""));
            System.out.println("Player 3 chose " + player3choice +". " + ((playRPS(myChoice, player3choice) == 1) ? "I win!" : ""));

            points += playRPS(myChoice, player1choice) + playRPS(myChoice, player3choice);
            System.out.println("I have " + points + " points.\n");






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

    public static String getChoice(String c) {
        if (c.equals("R")) {
            return "Rock";
        } else if (c.equals("P")) {
            return "Paper";
        } else if (c.equals("S")){
            return "Scissors";
        } else {
            return "ERROR";
        }
    }

    /**
     *
     * @param me R, P, or S
     * @param them R, P, or S
     * @return 0 if lose, 1 if win
     */
    public static int playRPS(String me, String them) {
        if (me.equals("Rock")) {
            if (them.equals("Paper")) {
                return 0;
            } else if (them.equals("Scissors")) {
                return 1;
            } else {
                return 0;
            }
        } else if (me.equals("Paper")) {
            if (them.equals("Rock")) {
                return 1;
            } else if (them.equals("Scissors")) {
                return 0;
            } else {
                return 0;
            }
        } else { //me == S
            if (them.equals("Rock")) {
                return 0;
            } else if (them.equals("Paper")) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
