/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/* 
Student Number:011005906

Name: Mitch Stone

Date: 10/06/18

Purpose: Main Controller for non-specific controller functions

Known Bugs:
*/

public class MainController implements Initializable {

    @FXML
    private BorderPane mainpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void showLogin(MouseEvent event) throws IOException {
        
        loadUI("/userAccess/userSignIn");
    }
    
    @FXML
    private void showRegisterNow(MouseEvent event) throws IOException {
        
        loadUI("/userAccess/userRegistration");
    }
    
    @FXML
    private void showWhatsNew(MouseEvent event) throws IOException {
        
        //loadUI("/views/register");
    }
    
    @FXML
    private void showContactUs(MouseEvent event) throws IOException {
        
        //loadUI("/views/register");
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
    
    // Look at getting this working to be a global open new window function
    
    public void openNewWindow(String path, Button button) throws IOException{
        //closes current window and opens new one            
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(path));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    public void openNewWindow(String path) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        
        Scene scene = new Scene(loader.load());
        
        Stage inputStage = new Stage();
        inputStage.setScene(scene);
        inputStage.showAndWait();
    }
}
