package userAccess;

import beans.Student;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserAccessModel;
import security.SecurityMethods;

/**
 *
 * @author mitch
 */
public class UserAccessController {
    
    @FXML
    private Pane signin;
    @FXML
    private Pane signup;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label errorOutput2;
    @FXML
    private TextField userNameLog;
    @FXML
    private PasswordField passwordLog;
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_signin;
    @FXML
    private Label errorOutputLog;

    //sets the student properties to be used to log in
    @FXML
    private void loginAction(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        Student bean = new Student();
        
        bean.setTable("student");
        
        if (userNameLog.getText().equals("") || passwordLog.getText().equals("")) {
            
            errorOutputLog.setText("There are empty fields.");
            
        } else {
            
            bean.setEmail(userNameLog.getText());
            bean.setPassword(SecurityMethods.getHash(passwordLog.getText()));

            String tier = null;

            //System.out.println(bean.getUserName());
            //System.out.println(bean.getPassword());

            if (UserAccessModel.checkUserPass(bean)) {
                
                tier = "student";
                
                //closes current window and opens new one

                        Stage stage = (Stage) btn_register.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/student/studentDashboard.fxml"));

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                
            } else {
                bean.setTable("caseworker");
                if (UserAccessModel.checkUserPass(bean)) {
                    tier = "caseworker";
                } else {
                    bean.setTable("admin");
                    if(UserAccessModel.checkUserPass(bean)) {
                        tier = "admin";
                    } else {
                        errorOutputLog.setText("User not found.\n Try to create a new user.");
                    }
                }
            }
            
            System.out.println(tier);
            
        }
    }

    //sets the student properties so a new student can be added
    @FXML
    private void registerAction(ActionEvent event) throws NoSuchAlgorithmException, Exception {
        Student register = new Student();
        
        register.setTable("student");
        
        if (firstName.getText().equals("") || lastName.getText().equals("") || email.getText().equals("") ||
                 password1.getText().equals("") || password2.getText().equals("")) {
            
            errorOutput2.setText("There are empty fields.");
            
        } else {
            
            register.setFirstName(firstName.getText());
            register.setLastName(lastName.getText());
            register.setEmail(email.getText());

            String pw1 = password1.getText();
            String pw2 = password2.getText();

            if (pw1.equals(pw2)) {
                register.setPassword(SecurityMethods.getHash(pw1));
                if (!UserAccessModel.checkUserPass(register)) {
                    if (UserAccessModel.add(register)) {

                        signup.setVisible(false);
                        signin.setVisible(true);

                    } else {
                        errorOutput2.setText("There's been a mistake.");
                    }
                } else {
                    errorOutput2.setText("Sorry, a user is already\n registered under these credentials.");
                }
            } else {
                errorOutput2.setText("Sorry, the passwords\n entered didn't match.");
            }
            
        }
        
    } 

    @FXML
    private void showLogin(ActionEvent event) {
        
        signup.setVisible(false);
        signin.setVisible(true);
        
    }

    @FXML
    private void showRegister(ActionEvent event) {
        
        signup.setVisible(true);
        signin.setVisible(false);
        
    }
}


