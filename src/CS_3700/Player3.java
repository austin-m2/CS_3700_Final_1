//package CS_3700;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;


public class Player3 {

    public static void main(String[] args) throws Exception {
        int numMatches = Integer.parseInt(args[0]);
        int points = 0;

        DatagramSocket player1insocket = new DatagramSocket(6970);
        DatagramSocket player2insocket = new DatagramSocket(6971);
        DatagramSocket player1outsocket = new DatagramSocket();
        DatagramSocket player2outsocket = new DatagramSocket();

        for (int i = 0; i < numMatches; i++) {

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


            DatagramPacket player1outpacket = new DatagramPacket(arr, arr.length, address, 6974);
            DatagramPacket player2outpacket = new DatagramPacket(arr, arr.length, address, 6973);
            DatagramPacket player1inpacket = new DatagramPacket(arrIn, arrIn.length);
            DatagramPacket player2inpacket = new DatagramPacket(arrIn, arrIn.length);


            player1insocket.receive(player1inpacket);
            String player1choice = new String(player1inpacket.getData());

            player2insocket.receive(player2inpacket);
            String player2choice = new String(player2inpacket.getData());

            player1outsocket.send(player1outpacket);
            player2outsocket.send(player2outpacket);

            String myChoice = getChoice(outChoice);
            player1choice = getChoice(player1choice);
            player2choice = getChoice(player2choice);

            System.out.println("Round " + (i + 1));
            System.out.println("I choose " + myChoice + "!");
            System.out.println("Player 1 chose " + player1choice +". " + ((playRPS(myChoice, player1choice) == 1) ? "I win!" : ""));
            System.out.println("Player 2 chose " + player2choice +". " + ((playRPS(myChoice, player2choice) == 1) ? "I win!" : ""));

            points += playRPS(myChoice, player1choice) + playRPS(myChoice, player2choice);
            System.out.println("I have " + points + " points.\n");
        }
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
