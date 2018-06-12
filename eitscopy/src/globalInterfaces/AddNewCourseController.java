/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import beans.Courses;
import beans.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.CoursesModel;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class AddNewCourseController implements Initializable {

    @FXML
    private ComboBox cb_tableSelection;
    @FXML
    private TextField tf_courseName;
    @FXML
    private TextField tf_courseIndustry;
    @FXML
    private TextField tf_courseLocation;
    @FXML
    private Button btn_addUser;
    @FXML
    private Button btn_cancel;
    @FXML
    private Pane header_panel;
    @FXML
    private Label lbl_windowHeader;
    @FXML
    private TableView tbl_courses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
            "Diploma"
        );
        cb_tableSelection.getItems().addAll(options);
        
        try {
            populateCoursesTable();
        } catch (SQLException ex) {
            Logger.getLogger(AddNewCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void populateCoursesTable() throws SQLException{
        
        CoursesModel model = new CoursesModel();
        
        ObservableList<Courses> courseList = model.getAllCourses();
        model.getAllCourses();
        //create the columns needed in the table
        TableColumn name = new TableColumn("Course Name");
        TableColumn industry = new TableColumn("Course Industry");
        TableColumn location = new TableColumn("Course Location");
        tbl_courses.getColumns().addAll(name, industry, location);
          
        //put the data in the appropriate columns

        name.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
        industry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
        location.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
        
        tbl_courses.setItems(courseList); 
    }
    
    @FXML
    private void btn_addCourse(MouseEvent event) {
    }

    @FXML
    private void cancel(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
