/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import beans.User;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.MainModel;
import security.SecurityMethods;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class AddNewUserController implements Initializable {

    @FXML
    private ComboBox cb_tableSelection;
    @FXML
    private Button btn_addUser;
    private TextField tf_firstName;
    private TextField tf_lastName;
    private TextField tf_email;
    private TextField tf_password;
    
    private Button btn_returnToDashboard;
    @FXML
    private Button btn_cancel;
    @FXML
    private Pane header_panel;
    @FXML
    private Label lbl_windowHeader;
    @FXML
    private TextField tf_courseName;
    @FXML
    private TextField tf_courseIndustry;
    @FXML
    private TextField tf_courseLocation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
            "Student",
            "Case Worker",
            "Administrator"
        );
        cb_tableSelection.getItems().addAll(options);
         
    }    

    private void btn_addUser(MouseEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
        
        String userType = cb_tableSelection.getSelectionModel().getSelectedItem().toString();
        if (!userType.equals(cb_tableSelection.getPromptText())) {
            switch (userType) {
                case "Student":
                    userType = "student";
                    break;
                case "Case Worker":
                    userType = "caseworker";
                    break;
                case "Administrator":
                    userType = "admin";
                    break;
                default:
                    System.out.println("Select user type");
                    break;
            }
            User user = new User();
            user.setTable(userType);
            user.setFirstName(tf_firstName.getText());
            user.setLastName(tf_lastName.getText());
            user.setEmail(tf_email.getText());
            user.setPassword(SecurityMethods.getHash(tf_password.getText()));  
            MainModel.addNewUser(user);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } else {
            System.out.println("Please select user type.");
        }  
    }  

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void btn_addCourse(MouseEvent event) {
    }
}
