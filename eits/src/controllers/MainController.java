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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class MainController implements Initializable {

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
    private void showLogin(MouseEvent event) throws IOException {
        
        loadUI("/views/login");
    }
    
    @FXML
    private void showRegisterNow(MouseEvent event) throws IOException {
        
        loadUI("/views/register");
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
    
    
    
}
