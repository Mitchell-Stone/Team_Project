/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userAccess;

import beans.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UserAccessModel;

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
    private TextField password;
    @FXML
    private TextField userName;
    @FXML
    private Label errorOutput;

    @FXML
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

    @FXML
    private void createStudent(ActionEvent event) {
        
        
        
    }
    
}
