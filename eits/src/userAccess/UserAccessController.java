/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userAccess;

import beans.Student;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserAccessModel;
import security.SecurityMethods;

/**
 *
 * @author mitch
 */
public class UserAccessController {

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

    //sets the student properties to be used to log in
    @FXML
    private void loginAction(ActionEvent event) throws NoSuchAlgorithmException {
        Student bean = new Student();
        bean.setUserName(userNameLog.getText());
        bean.setPassword(SecurityMethods.getHash(passwordLog.getText()));
        
        //System.out.println(bean.getUserName());
        //System.out.println(bean.getPassword());
        
        if (UserAccessModel.checkUserPass(bean)) {
            System.out.println("Y");
        } else {
            System.out.println("N");
        }
        
    }

    //sets the student properties so a new student can be added
    @FXML
    private void registerAction(ActionEvent event) throws NoSuchAlgorithmException, Exception {
        Student register = new Student();
        
        register.setTable("student");
        
        register.setFirstName(firstName.getText());
        register.setLastName(lastName.getText());
        register.setEmail(email.getText());
        
        String pw1 = password1.getText();
        String pw2 = password2.getText();
        
        if (pw1.equals(pw2)) {
            register.setPassword(SecurityMethods.getHash(pw1));
            if (!UserAccessModel.checkUserPass(register)) {
                if (UserAccessModel.add(register)) {
                    System.out.println("Y");
                } else {
                    System.out.println("N");
                }
            } else {
                errorOutput2.setText("Sorry, a user is already registered under these credentials.");
            }
        } else {
            errorOutput2.setText("Sorry, the passwords entered didn't match.");
        }
        
    } 
}
