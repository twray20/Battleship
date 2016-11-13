// Thomas Wray
// TCW9331
// CMPS 360
// Programming Project : #4
// Due Date : 11/7/16
// Program Description: Create a server/client version of the game Salvo using a GUI
// Certificate of Authenticity: 
// I certify that the code in the method functions including method function main of this 
// project are entirely my own work. I did however use some assistance from the
// Liang textbook, chapter 31 Tic-Tac-Toe example and example 9d1d from moodle.
package salvoclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SalvoClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
