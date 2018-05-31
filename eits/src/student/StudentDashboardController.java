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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        /*Courses bean = new Courses();
        CoursesModel model = new CoursesModel();
        
        bean.setTable("courses");
        
        try{
            ObservableList<Courses> coursesList = (ObservableList<Courses>) model.getCourses(bean);
            
            tableView.setItems(coursesList);
        }catch(Exception ex){
            System.err.println(ex);
        }
        
        ArrayList<Courses> list = new ArrayList<>();
        
        
        for (int i = 0; i < 10; i++) {
            
        }*/
        
        Student student = new Student();
        
        int ID = student.getID();
        
        System.out.println(ID);
        
        User user = new User();
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);
        
        ArrayList <String> currentUser = MainModel.getUserByID(user);
        
        System.out.println(currentUser);
        
        studentID.setText(Integer.toString(ID));
        firstName.setText(currentUser.get(0));
        lastName.setText(currentUser.get(1));
        email.setText(currentUser.get(2));
        
    }   
}
