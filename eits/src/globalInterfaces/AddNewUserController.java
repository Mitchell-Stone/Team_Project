/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import beans.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MainModel;

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
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;

    private String windowURL = "/administrator/administratorDashboard.fxml";
    @FXML
    private Button btn_returnToDashboard;
    
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

    @FXML
    private void btn_addUser(MouseEvent event) throws SQLException, IOException {
        
        String table = cb_tableSelection.getSelectionModel().getSelectedItem().toString();
        if (table != null) {
            User user = new User();
            user.setTable(table);
            user.setFirstName(tf_firstName.getText());
            user.setLastName(tf_lastName.getText());
            user.setEmail(tf_email.getText());
            user.setPassword(tf_password.getText());

            MainModel.addNewUser(user);

            openNewWindow(btn_addUser);
        }
    }  

    @FXML
    private void returnToDashboard(MouseEvent event) throws IOException {
        openNewWindow(btn_returnToDashboard);
    }
    
    private void openNewWindow(Button button) throws IOException{
       Stage stage = (Stage) button.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(windowURL));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
