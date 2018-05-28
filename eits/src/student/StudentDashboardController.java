/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Courses;
import beans.Student;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
        industry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
        location.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
        
        tableView.getItems().setAll(parseCoursesList());
    }
    
    public List<Courses> parseCoursesList(){
        
        //execute SQL statement to get all course for the studen that logged in
        
        //create a for loop to iterate through the courses to populate the list
        
        //return courses;
        
    }
    
}
