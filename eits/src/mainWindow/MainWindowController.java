/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class MainWindowController implements Initializable {

    public String getNextWindow() {
        return nextWindow;
    }

    public void setNextWindow(String nextWindow) {
        this.nextWindow = nextWindow;
    }

    private String nextWindow;
    
    @FXML
    private BorderPane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showLogin(MouseEvent event) {    
        nextWindow = "/userAccess/userSignIn";
    }

    @FXML
    private void showRegisterNow(MouseEvent event) {
        nextWindow = "/userAccess/userRegistration";
    }

    @FXML
    private void showWhatsNew(MouseEvent event) {
    }

    @FXML
    private void showContactUs(MouseEvent event) {
    }
    
    public void loadUI(){
        Parent root = null;

        try{
            root = FXMLLoader.load(getClass().getResource(nextWindow));

        } catch(IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainpane.setCenter(root);
    }
    
}
