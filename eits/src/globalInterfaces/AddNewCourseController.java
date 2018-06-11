/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class AddNewCourseController implements Initializable {

    @FXML
    private ComboBox<?> cb_tableSelection;
    @FXML
    private TextField tf_courseName;
    @FXML
    private TextField tf_courseIndustry;
    @FXML
    private TextField tf_courseLocation;
    @FXML
    private Button btn_addUser;
    @FXML
    private Button btn_cancel;
    @FXML
    private Pane header_panel;
    @FXML
    private Label lbl_windowHeader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_addCourse(MouseEvent event) {
    }

    @FXML
    private void cancel(MouseEvent event) {
    }
    
}
