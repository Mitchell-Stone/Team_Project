/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eits;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 0111005906
 */
public class MainWindowController implements Initializable {

    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadLoginWindow(javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader fxml = FXMLLoader.load(getClass().getResource("./LoginWindow.fxml"));
        
        AnchorPane pane = fxml.load();
        
        rootPane.getChildren().setAll(pane);
    }
    
}
