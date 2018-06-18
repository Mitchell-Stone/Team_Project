/* 
Student Number:011005906

Name: Mitch Stone

Date: 10/06/18

Purpose: Main Controller for non-specific controller functions

Known Bugs:
*/

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainController implements Initializable {

    @FXML
    private BorderPane mainpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //Function specific to opening the login window
    @FXML
    private void showLogin(MouseEvent event) throws IOException {
        
        loadUI("/userAccess/userSignIn");
    }
    
    //Function specific to opening the registration window
    @FXML
    private void showRegisterNow(MouseEvent event) throws IOException {
        
        loadUI("/userAccess/userRegistration");
    }
    
    
    private void loadUI(String ui){
        Parent root = null;
        
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch(IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mainpane.setCenter(root);
    }
    
    //Opens a new window while close the previous window    
    public void openNewWindow(String path, Button button) throws IOException{
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(path));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    //Opens a new window withouth closing the previous window
    public void openNewWindow(String path) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene scene = new Scene(loader.load());

        Stage inputStage = new Stage();
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }
}
