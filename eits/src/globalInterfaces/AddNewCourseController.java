/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalInterfaces;

import beans.Courses;
import beans.Diploma;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.CoursesModel;
import model.DiplomaModel;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class AddNewCourseController implements Initializable {

    @FXML
    private TextField tf_courseName;
    @FXML
    private TextField tf_courseIndustry;
    @FXML
    private TextField tf_courseLocation;
    @FXML
    private Button btn_cancel;
    @FXML
    private Pane header_panel;
    @FXML
    private Label lbl_windowHeader;
    @FXML
    private Button btn_addCourse;
    @FXML
    private ComboBox cb_courseType;
    @FXML
    private TableView tbl_addSubjects;
    @FXML
    private TableView tbl_subjects;
    @FXML
    private GridPane gp_newCourse;
    @FXML
    private Label lbl_dropSubjectsHere;
    
    
    private int courseID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tbl_subjects.setOnDragDetected((MouseEvent event) -> {
            Courses st = (Courses) tbl_subjects.getSelectionModel().getSelectedItem();
            if (st != null) {
                ((Node) event.getSource()).setCursor(ImageCursor.CLOSED_HAND);
                
                Dragboard db = tbl_subjects.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(Integer.toString(st.getCourseID()));
                
                courseID = st.getCourseID();
                
                System.out.println("Draggin course with ID: " + st.getCourseID());
                
                event.consume();
            }
        });
        
        lbl_dropSubjectsHere.setOnDragDropped((DragEvent ev) -> {
            System.out.println("Drag has been dropped");
            
            boolean success = false;
            
            Dragboard db = ev.getDragboard();
            try {
                populateAddSubjectTable(Integer.parseInt(db.getString()));
                success = true;
            } catch (SQLException ex) {
                Logger.getLogger(AddNewCourseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            ev.setDropCompleted(success);
            
            ev.consume();
        });   
        
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
            "Diploma",
            "Certificate 4",
            "Certificate 3"
        );
        cb_courseType.getItems().addAll(options);
        
        try {
            populateSubjectsTable();
        } catch (SQLException ex) {
            Logger.getLogger(AddNewCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void populateSubjectsTable() throws SQLException{
        
        CoursesModel model = new CoursesModel();
        
        ObservableList<Courses> courseList = model.getAllCourses();
        model.getAllCourses();
        //create the columns needed in the table
        TableColumn name = new TableColumn("Course Name");
        TableColumn industry = new TableColumn("Course Industry");
        TableColumn location = new TableColumn("Course Location");
        tbl_subjects.getColumns().addAll(name, industry, location);
          
        //put the data in the appropriate columns

        name.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
        industry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
        location.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
        
        tbl_subjects.setItems(courseList); 
    }
    
    private void populateAddSubjectTable(int subjectID) throws SQLException{
        CoursesModel model = new CoursesModel();
        
        Courses course = model.getSubjectByID(subjectID);
        
        ObservableList<Courses> courseList = model.getAllCourses();
        courseList.add(course);
        
        //create the columns needed in the table
        TableColumn name = new TableColumn("Course Name");
        TableColumn industry = new TableColumn("Course Industry");
        TableColumn location = new TableColumn("Course Location");
        tbl_subjects.getColumns().addAll(name, industry, location);
          
        //put the data in the appropriate columns

        name.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
        industry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
        location.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
        
        tbl_subjects.setItems(courseList);
    }
    
    @FXML
    private void btn_addCourse(MouseEvent event) throws SQLException {
        Diploma dip = new Diploma();
        
        dip.setDiplomaName(tf_courseName.getText());
        dip.setDiplomaIndustry(tf_courseIndustry.getText());
        dip.setDiplomaLocation(tf_courseLocation.getText());
        dip.setCourseType(cb_courseType.getSelectionModel().getSelectedItem().toString());
        
        DiplomaModel.addNewDiploma(dip);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void cancel(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private void tbl_subjectMouseEntered(MouseEvent event) {
        ((Node) event.getSource()).setCursor(ImageCursor.OPEN_HAND);
    }   

}
