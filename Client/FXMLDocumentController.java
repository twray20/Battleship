package salvoclient;

import java.io.*;
import java.net.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label playState;

    //layout for setup
    @FXML
    private TextField xCoord;
    @FXML
    private TextField yCoord;
    @FXML
    private Label shipSetup;
    @FXML
    private Button startConfirm;
    @FXML
    private Button choice1;
    @FXML
    private Button choice2;
    @FXML
    private Button choice3;
    @FXML
    private Button choice4;
    @FXML
    private Label startPoint;
    @FXML
    private Label endPoint;

    //layout for playing
    @FXML
    private Button confirmShoot;
    @FXML
    private TextField yShoot;
    @FXML
    private TextField xShoot;
    @FXML
    private Rectangle messageBG;
    @FXML
    public Label message;
    @FXML
    private Label turn;

    //relevant variables
    private ClientHandle clienthandle;
    private int shipState = 4;
    private char xTemp;
    private int x, y, count;
    private boolean checkPoint;
    private int BstartX, BstartY, BendX, BendY;
    private int SstartX, SstartY, SendX, SendY;
    private int CstartX, CstartY, CendX, CendY;
    private int DstartX, DstartY, DendX, DendY;
    private ArrayList<ship> shipList;

    @FXML
    private void confirm(ActionEvent event) { // confirm start coordinate
        xTemp = xCoord.getText().charAt(0);
        x = convertChar(xTemp);
        y = Integer.parseInt(yCoord.getText());
        checkPoint = true;
        if (y + shipState < 11) {
            for (ship s : shipList) {
                for (int i = 0; i <= shipState; i++) {
                    if (s.x == x && s.y == (y + i)) {
                        checkPoint = false;
                    }
                }
            }
            if (checkPoint == true) {
                choice1.setVisible(true);
                choice1.setText(convertInt(x) + "" + (y + shipState));
            }
        } else {
            choice1.setVisible(false);
        }
        checkPoint = true;
        if (x + shipState < 11) {
            for (ship s : shipList) {
                for (int i = 0; i <= shipState; i++) {
                    if (s.x == (x + i) && s.y == y) {
                        checkPoint = false;
                    }
                }
            }
            if (checkPoint == true) {
                choice2.setVisible(true);
                choice2.setText(convertInt(x + shipState) + "" + y);
            }
        } else {
            choice2.setVisible(false);
        }
        checkPoint = true;
        if (y - shipState > 0) {
            for (ship s : shipList) {
                for (int i = 0; i <= shipState; i++) {
                    if (s.x == x && s.y == (y - i)) {
                        checkPoint = false;
                    }
                }
            }
            if (checkPoint == true) {
                choice3.setVisible(true);
                choice3.setText(convertInt(x) + "" + (y - shipState));
            }
        } else {
            choice3.setVisible(false);
        }
        checkPoint = true;
        if (x - shipState > 0) {
            for (ship s : shipList) {
                for (int i = 0; i <= shipState; i++) {
                    if (s.x == (x - i) && s.y == y) {
                        checkPoint = false;
                    }
                }
            }
            if (checkPoint == true) {
                choice4.setVisible(true);
                choice4.setText(convertInt(x - shipState) + "" + y);
            }
        } else {
            choice4.setVisible(false);
        }
    }

    @FXML
    private void nextShip1(ActionEvent event) { // choice 1 (y+)
        if (shipState == 4) {
            BstartX = x;
            BstartY = y;
            BendX = x;
            BendY = y + shipState;
            shipSetup.setText("4 Space Cruiser");
            int tempx = BstartX;
            int tempy = BstartY;
            while (tempy <= BendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 3) {
            CstartX = x;
            CstartY = y;
            CendX = x;
            CendY = y + shipState;
            shipSetup.setText("3 Space Submarine");
            int tempx = CstartX;
            int tempy = CstartY;
            while (tempy <= CendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 2) {
            SstartX = x;
            SstartY = y;
            SendX = x;
            SendY = y + shipState;
            shipSetup.setText("2 Space Destroyer");
            int tempx = SstartX;
            int tempy = SstartY;
            while (tempy <= SendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 1) {
            DstartX = x;
            DstartY = y;
            DendX = x;
            DendY = y + shipState;
            int tempx = DstartX;
            int tempy = DstartY;
            while (tempy <= DendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy++;
            }
            startGame();
            for (ship s : shipList) {
                System.out.println(s.getType() + " " + s.getCoord());
            }
        }
    }

    @FXML
    private void nextShip2(ActionEvent event) {// choice 2 (x+)
        if (shipState == 4) {
            BstartX = x;
            BstartY = y;
            BendX = x + shipState;
            BendY = y;
            shipSetup.setText("4 Space Cruiser");
            int tempx = BstartX;
            int tempy = BstartY;
            while (tempx <= BendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 3) {
            CstartX = x;
            CstartY = y;
            CendX = x + shipState;
            CendY = y;
            shipSetup.setText("3 Space Submarine");
            int tempx = CstartX;
            int tempy = CstartY;
            while (tempx <= CendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 2) {
            SstartX = x;
            SstartY = y;
            SendX = x + shipState;
            SendY = y;
            shipSetup.setText("2 Space Destroyer");
            int tempx = SstartX;
            int tempy = SstartY;
            while (tempx <= SendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx++;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 1) {
            DstartX = x;
            DstartY = y;
            DendX = x + shipState;
            DendY = y;
            int tempx = DstartX;
            int tempy = DstartY;
            while (tempx <= DendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx++;
            }
            startGame();
            for (ship s : shipList) {
                System.out.println(s.getType() + " " + s.getCoord());
            }
        }
    }

    @FXML
    private void nextShip3(ActionEvent event) { // choice 3 (y-)
        if (shipState == 4) {
            BstartX = x;
            BstartY = y;
            BendX = x;
            BendY = y - shipState;
            shipSetup.setText("4 Space Cruiser");
            int tempx = BstartX;
            int tempy = BstartY;
            while (tempy >= BendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 3) {
            CstartX = x;
            CstartY = y;
            CendX = x;
            CendY = y - shipState;
            shipSetup.setText("3 Space Submarine");
            int tempx = CstartX;
            int tempy = CstartY;
            while (tempy >= CendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 2) {
            SstartX = x;
            SstartY = y;
            SendX = x;
            SendY = y - shipState;
            shipSetup.setText("2 Space Destroyer");
            int tempx = SstartX;
            int tempy = SstartY;
            while (tempy >= SendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 1) {
            DstartX = x;
            DstartY = y;
            DendX = x;
            DendY = y - shipState;
            int tempx = DstartX;
            int tempy = DstartY;
            while (tempy >= DendY) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempy--;
            }
            startGame();
            for (ship s : shipList) {
                System.out.println(s.getType() + " " + s.getCoord());
            }
        }
    }

    @FXML
    private void nextShip4(ActionEvent event) { // choice 4 (x-)
        if (shipState == 4) {
            BstartX = x;
            BstartY = y;
            BendX = x - shipState;
            BendY = y;
            shipSetup.setText("4 Space Cruiser");
            int tempx = BstartX;
            int tempy = BstartY;
            while (tempx >= BendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 3) {
            CstartX = x;
            CstartY = y;
            CendX = x - shipState;
            CendY = y;
            shipSetup.setText("3 Space Submarine");
            int tempx = CstartX;
            int tempy = CstartY;
            while (tempx >= CendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 2) {
            SstartX = x;
            SstartY = y;
            SendX = x - shipState;
            SendY = y;
            shipSetup.setText("2 Space Destroyer");
            int tempx = SstartX;
            int tempy = SstartY;
            while (tempx >= SendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx--;
            }
            shipState--;
            hideChoices();
        } else if (shipState == 1) {
            DstartX = x;
            DstartY = y;
            DendX = x - shipState;
            DendY = y;
            int tempx = DstartX;
            int tempy = DstartY;
            while (tempx >= DendX) {
                shipList.add(new ship(tempx, tempy, shipState));
                tempx--;
            }
            startGame();
            for (ship s : shipList) {
                System.out.println(s.getType() + " " + s.getCoord());
            }
        }
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

    private char convertInt(int i) {    //int backto character
        char c;
        switch (i) {
            case 1:
                c = 'A';
                break;
            case 2:
                c = 'B';
                break;
            case 3:
                c = 'C';
                break;
            case 4:
                c = 'D';
                break;
            case 5:
                c = 'E';
                break;
            case 6:
                c = 'F';
                break;
            case 7:
                c = 'G';
                break;
            case 8:
                c = 'H';
                break;
            case 9:
                c = 'I';
                break;
            case 10:
                c = 'J';
                break;
            default:
                c = 'A';
                break;
        }
        return c;
    }

    private void startGame() {
        clienthandle.updateList(shipList);
        
        playState.setText("Game In Progress");
        startPoint.setVisible(false);
        endPoint.setVisible(false);
        xCoord.setVisible(false);
        yCoord.setVisible(false);
        startConfirm.setVisible(false);
        shipSetup.setVisible(false);
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);

        turn.setVisible(true);
        message.setVisible(true);
        messageBG.setVisible(true);
        yShoot.setVisible(true);
        xShoot.setVisible(true);
        confirmShoot.setVisible(true);
    }

    private void hideChoices() {
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public void gameOver() {
        turn.setVisible(false);
        yShoot.setVisible(false);
        xShoot.setVisible(false);
        confirmShoot.setVisible(false);
        playState.setText("Game Ended");
    }

    @FXML
    private void shoot(ActionEvent event) throws Exception{
        xTemp = xShoot.getText().charAt(0);
        x = convertChar(xTemp);
        y = Integer.parseInt(yShoot.getText());
        if (validPoint(xTemp, y) == true && clienthandle.getTurn() == true) {
            message.setText("Shooting " + xShoot.getText() + "" + yShoot.getText());
            clienthandle.sendMove(xShoot.getText(), yShoot.getText());
        } else if (validPoint(xTemp, y) == true && clienthandle.getTurn() == false){
            message.setText("Wait your turn");
        }
        else{
            message.setText("Invalid target");
        }
    }

    private boolean validPoint(char C, int I) {
        boolean validC = false;
        boolean validI = false;
        if (C == 'A' || C == 'B' || C == 'C' || C == 'D' || C == 'E' || C == 'F'
                || C == 'G' || C == 'H' || C == 'I' || C == 'J' || C == 'a' || C == 'b'
                || C == 'c' || C == 'd' || C == 'e' || C == 'f' || C == 'g' || C == 'h'
                || C == 'i' || C == 'j') {
            validC = true;
        }

        if (I == 1 || I == 2 || I == 3 || I == 4 || I == 5 || I == 6 || I == 7
                || I == 8 || I == 9 || I == 10) {
            validI = true;
        }

        if (validC == true && validI == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        final int httpd = 8081;
        final String host = "localhost";
        try {
            Socket socket = new Socket(host, httpd);
            clienthandle = new ClientHandle(socket);
            new Thread(clienthandle).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        shipList = new ArrayList<>();
        hideChoices();
        turn.setVisible(false);
        message.setVisible(false);
        messageBG.setVisible(false);
        yShoot.setVisible(false);
        xShoot.setVisible(false);
        confirmShoot.setVisible(false);
    }
}