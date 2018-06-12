/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userAccess;

import beans.Student;
import beans.User;
import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserAccessModel;
import security.SecurityMethods;

/**
 * FXML Controller class
 *
 * @author 0111005906
 */
public class UserSignInController implements Initializable {
    
    private String studentDboardPath = "/student/studentDashboard.fxml";
    private String caseWorkerDboardPath = "/caseWorker/caseWorker.fxml";
    private String adminDboardPath = "/administrator/administratorDashboard.fxml";
    
    private Pane signin;
    @FXML
    private Pane signup;
    @FXML
    private TextField firstName;
    @FXML
    private Button btn_register;
    @FXML
    private TextField email;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label errorOutput2;
    @FXML
    private Pane banner;
    @FXML
    private TextField userNameLog;
    @FXML
    private PasswordField passwordLog;
    @FXML
    private Label errorOutputLog;
    @FXML
    private Button btn_signIn;
    @FXML
    private Button btn_submit;
    @FXML
    private GridPane gp_login;
    @FXML
    private Label formLabel;
    @FXML
    private Button btn_cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //sets the student properties so a new student can be added
    public void registerAction(ActionEvent event) throws NoSuchAlgorithmException, Exception {
                
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

                        Stage stage = null;
                        Parent root = null;

                        if (event.getSource() == btn_submit) {
                            stage = (Stage) btn_submit.getScene().getWindow();

                            root = FXMLLoader.load(getClass().getResource("userSignIn.fxml"));
                        }

                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();

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
    public void showRegister(ActionEvent event) throws IOException {      
        Stage stage = null;
        Parent root= null;
        
        if (event.getSource() == btn_register) {
            stage = (Stage) btn_register.getScene().getWindow();
                    
            root = FXMLLoader.load(getClass().getResource("userRegister.fxml"));
        }
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    
    }
    
    private boolean confirmUserType(String userType) throws NoSuchAlgorithmException{
        User bean = new User();
        
        //sets the email and password to be check
        bean.setTable(userType);
        bean.setEmail(userNameLog.getText());
        bean.setPassword(SecurityMethods.getHash(passwordLog.getText()));
        return UserAccessModel.checkUserPass(bean);
    }
    
    @FXML
    private void signInAction(ActionEvent event) throws NoSuchAlgorithmException, IOException {
 
        System.out.println(userNameLog.getText() + " : " + passwordLog.getText());
        
        if (userNameLog.getText().equals("") || passwordLog.getText().equals("")) {
            
            errorOutputLog.setText("There are empty fields.");
            
        } else {
            String tier = null;
            
            if (confirmUserType("student")) {              
                tier = "student";
                //if true open the student dashboard
                MainController window = new MainController();
                window.openNewWindow(studentDboardPath, btn_signIn);  
            } else {
                
                if (confirmUserType("caseworker")) {
                    tier = "caseworker";
                    //if true open the case worker dashboard
                    MainController window = new MainController();
                    window.openNewWindow(caseWorkerDboardPath, btn_signIn);
                } else {
                    if(confirmUserType("admin")) {
                        tier = "admin";
                        //if true open the admin dashboard
                        MainController window = new MainController();
                        window.openNewWindow(adminDboardPath, btn_signIn);
                    } else {
                        errorOutputLog.setText("User not found.\n Try to create a new user.");
                    }
                }
            }
            System.out.println(tier);     
        }
    }
    
    public void cancelRegistration(ActionEvent event) throws IOException{
        Stage stage = null;
        Parent root= null;
        
        if (event.getSource() == btn_cancel) {
            stage = (Stage) btn_cancel.getScene().getWindow();
                    
            root = FXMLLoader.load(getClass().getResource("userSignIn.fxml"));
        }
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}


