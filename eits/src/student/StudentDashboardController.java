/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Courses;
import beans.Student;
import beans.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.MainModel;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class StudentDashboardController implements Initializable {

    
    private final ObservableList<Courses> data = FXCollections.observableArrayList();
    @FXML
    private TextField studentID;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Student student = new Student();
        
        int ID = student.getID();
        
        System.out.println(ID);
        
        User user = new User();
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);
        
        ArrayList <String> currentUser = MainModel.getUserByID(user);
        
        for (int i = 0; i < currentUser.size(); i++) {
        
            System.out.println(currentUser.get(i));
        
        }
        
        studentID.setText(Integer.toString(ID));
        firstName.setText(currentUser.get(0));
        lastName.setText(currentUser.get(1));
        email.setText(currentUser.get(2));
        
    }   
}
