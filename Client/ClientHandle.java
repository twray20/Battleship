package salvoclient;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandle implements Runnable {  //client is player 2, server is player 1

    private Socket socket;
    BufferedReader in = null;
    DataOutputStream out = null;
    String shot;
    private int hitCount;
    private boolean myTurn = false;
    private boolean continuePlay = true;
    private boolean waiting = true;
    private ArrayList<ship> shiplist = new ArrayList<>();
    private char state = 'C';
    private boolean firstTurn = true;

    public ClientHandle(Socket socket) throws Exception {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            while (continuePlay) {
                receiveInfo();
                waitForPlayer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitForPlayer() throws InterruptedException {
        System.out.println("waiting for you to move");
        while (waiting) {
            Thread.sleep(1000);
        }
        waiting = true;
    }

    public void sendMove(String x, String y) throws IOException {
        out.writeBytes(x + "\n");
        out.writeBytes(y + "\n");
        waiting = false;
        myTurn = false;
        String check = in.readLine();
        if (check.charAt(0) == 'h') {
            System.out.println("Hit at " + x + "" + y);
            hitCount++;
        } else if (check.charAt(0) == 'D') {
            System.out.println("Sunk their Destroyer");
        } else if (check.charAt(0) == 'S') {
            System.out.println("Sunk their Submarine");
        } else if (check.charAt(0) == 'C') {
            System.out.println("Sunk their Cruiser");
        } else if (check.charAt(0) == 'B') {
            System.out.println("Sunk their Battleship");
        } else {
            System.out.println("Miss at " + x + "" + y);
        }
    }

    private void receiveInfo() throws IOException {

        if (shiplist.size() > 0 && firstTurn != true) {
            String stateRead = in.readLine();
            state = stateRead.charAt(0);
        }

        if (state == '2') {
            System.out.println("You Won");
            continuePlay = false;
        } else if (state == '1') {
            System.out.println("You Lost");
            continuePlay = false;
        } else {
            System.out.println("waiting for other player to move");
            receiveMove();
            myTurn = true;
        }
        firstTurn = false;
        isLost();

    }

    private void receiveMove() throws IOException {
        String x = in.readLine();
        int yC = Integer.parseInt(in.readLine());
        int xC = convertChar(x.charAt(0));
        boolean gotHit = false;
        ship temp = new ship();
        int count = 0;
        for (ship a : shiplist) {
            if (a.x == xC && a.y == yC) {
                System.out.println("I got hit at " + x + yC);
                a.shipHit();
                temp = a;
                gotHit = true;
            }
        }
        for (ship a : shiplist) {
            if (a.getLength() == temp.getLength() && a.getHit()) {
                count++;
            }
        }
        if (count == temp.getLength() && gotHit == true) {
            out.writeBytes(temp.getType() + "\n");
            System.out.println("Your " + temp.getType() + " has been sunk");
        } else if (gotHit == true) {
            out.writeBytes("hit\n");
        } else if (gotHit == false) {
            System.out.println("They missed at " + x + yC);
            out.writeBytes("miss\n");
        }
    }

    public void updateList(ArrayList<ship> shiplist) {
        this.shiplist = shiplist;
    }

    private void isLost() throws IOException {
        for (ship a : shiplist) {
            if (a.getHit() == false) {
                out.writeBytes("continue\n");
                return;
            }
        }
        out.writeBytes("1 Wins\n");
        System.out.println("You lost");
        continuePlay = false;
    }

    public boolean getTurn() {
        return myTurn;
    }

    private int convertChar(char c) {   //character to int (A-J to 1-10)
        int num = 0;
        if (c == 'a' || c == 'A') {
            num = 1;
        }
        if (c == 'b' || c == 'B') {
            num = 2;
        }
        if (c == 'c' || c == 'C') {
            num = 3;
        }
        if (c == 'd' || c == 'D') {
            num = 4;
        }
        if (c == 'e' || c == 'E') {
            num = 5;
        }
        if (c == 'f' || c == 'F') {
            num = 6;
        }
        if (c == 'g' || c == 'G') {
            num = 7;
        }
        if (c == 'h' || c == 'H') {
            num = 8;
        }
        if (c == 'i' || c == 'I') {
            num = 9;
        }
        if (c == 'j' || c == 'J') {
            num = 10;
        }
        return num;
    }
}
