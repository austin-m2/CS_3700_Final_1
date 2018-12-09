//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Player1 {

    public static void main(String[] args) throws Exception {

        int numMatches = Integer.parseInt(args[0]);

        DatagramSocket player2outsocket = new DatagramSocket(/*6969*/);
        DatagramSocket player3outsocket = new DatagramSocket(/*6970*/);
        DatagramSocket player2insocket  = new DatagramSocket(6972);
        DatagramSocket player3insocket  = new DatagramSocket(6974);

        int points = 0;


        for (int i = 0; i < numMatches; i++) {

            TimeUnit.SECONDS.sleep(1);

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


            DatagramPacket player2outpacket = new DatagramPacket(arr, arr.length, address, 6969);
            DatagramPacket player3outpacket = new DatagramPacket(arr, arr.length, address, 6970);
            DatagramPacket player2inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6972*/);
            DatagramPacket player3inpacket  = new DatagramPacket(arrIn, arrIn.length/*, address, 6974*/);


            player2outsocket.send(player2outpacket);
            /*System.out.println("packet sent to player2");*/
            player3outsocket.send(player3outpacket);
            /*System.out.println("packet sent to player3");*/

            player2insocket.receive(player2inpacket);
            /*System.out.println("packet received from player2");*/
            String player2choice = new String(player2inpacket.getData());

            player3insocket.receive(player3inpacket);
            /*System.out.println("packet received from player3");*/
            String player3choice = new String(player3inpacket.getData());


            String myChoice = getChoice(outChoice);
            player2choice = getChoice(player2choice);
            player3choice = getChoice(player3choice);

            System.out.println("Round " + (i + 1));
            System.out.println("I choose " + myChoice + "!");
            System.out.println("Player 2 chose " + player2choice +". " + ((playRPS(myChoice, player2choice) == 1) ? "I win!" : ""));
            System.out.println("Player 3 chose " + player3choice +". " + ((playRPS(myChoice, player3choice) == 1) ? "I win!" : ""));

            points += playRPS(myChoice, player2choice) + playRPS(myChoice, player3choice);
            System.out.println("I have " + points + " points.\n");

        }




        //Date sendTime = new Date();

        //dsock.receive(dpack);
        //String message2 = new String(dpack.getData());
        //Date receiveTime = new Date();
        //System.out.println((receiveTime.getTime() - sendTime.getTime()) + " ms echo time for " + message2);
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
