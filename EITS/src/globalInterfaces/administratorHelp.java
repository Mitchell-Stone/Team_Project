/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 0111005906
 */
public class administratorHelp implements Initializable {
    private final String openCourseHelp = "/globalInterfaces/administratorCourseHelp.fxml";
    @FXML
    private Button btn_showCourseHelp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    private void btn_vewCourseHelp(MouseEvent event) {
        MainController main = new MainController();  
        try{
            main.openNewWindow(openCourseHelp, btn_showCourseHelp);
        }catch(IOException ex){
            System.out.println(ex);    
        }
    }
}
