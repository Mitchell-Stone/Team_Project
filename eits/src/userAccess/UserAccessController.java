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
import mainWindow.MainWindowController;
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
    private TextField password;
    private TextField userName;
    @FXML
    private Button btn_register;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label errorOutput2;

    //sets the student properties to be used to log in
    private void loginAction(ActionEvent event) {
        Student bean = new Student();
        bean.setUserName(userName.getText());
        bean.setPassword(password.getText());
        
        System.out.println(bean.getUserName());
        System.out.println(bean.getPassword());
        
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
            
            if (UserAccessModel.add(register)) {
                
                
                
            } else {
                
                
            }
            
        } else {
            
            errorOutput2.setText("Sorry, the passwords entered didn't match.");
            
        }
        
    } 
}
