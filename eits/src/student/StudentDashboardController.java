/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Courses;
import model.CoursesModel;
import beans.Student;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StudentModel;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TableView<Courses> tableView;
    @FXML
    private TableColumn<Courses, String> courseName;
    @FXML
    private TableColumn<Courses, String> industry;
    @FXML
    private TableColumn<Courses, String> location;
    
    private final ObservableList<Courses> data = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        StudentModel model = new StudentModel();
        //get the list of courses allocated to the student
        Student student = new Student();
        int studentID = 0; //need a way to get the student ID after the student logs in.
        
         model.getStudent(studentID);
         
         
        //display the course list in the table
        
        
    }   
}
