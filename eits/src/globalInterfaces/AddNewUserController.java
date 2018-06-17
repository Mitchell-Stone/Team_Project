/*Student Number: 0111005906

Name: Mitchell Stone

Date: 12/06/18

Purpose: Works as the controller for the add new user window

Known Bugs: Error occurs when a user type is not selected and the add new user button is pressed
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.MainModel;
import security.SecurityMethods;

//Main controller for adding new user to the database
public class AddNewUserController implements Initializable {

    @FXML
    private ComboBox cb_tableSelection;
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Adds the user types to the dropdown selector
        ObservableList<String> options = FXCollections.observableArrayList(
            "Student",
            "Case Worker",
            "Administrator"
        );
        cb_tableSelection.getItems().addAll(options);  
    }    

    //Handles the Button event for adding the new user
    @FXML
    private void btn_addUser(MouseEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
        
        String userType = cb_tableSelection.getSelectionModel().getSelectedItem().toString();
        if (!userType.equals(cb_tableSelection.getPromptText())) {
            //checks for what type of user is selected to know where to put it in the database
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
            //collects the data entered and executes the database query to add new user
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

    //Hnadles the Button event for canceling
    @FXML
    private void cancel(MouseEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
