/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import beans.Student;
import beans.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.MainModel;
import model.StudentModel;

/**
 * FXML Controller class
 *
 * @author 0111005906
 */
public class AdministratorDashboardController implements Initializable {

    @FXML
    private TableView tbl_data;
    @FXML
    private VBox vb_selectionDetails;
    Label lbl_studentID = new Label("Student ID");
    Label lbl_EmployeeID = new Label("Employee ID");
    TextField tf_studentID = new TextField();
    Label lbl_fName = new Label("First Name");
    TextField tf_firstName = new TextField();
    Label lbl_lName = new Label("Last Name");
    TextField tf_lastName = new TextField();
    Label lbl_email = new Label("Email");
    TextField tf_email = new TextField();
    Label lbl_phNumber = new Label("Phone Number");
    Button update = new Button();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Populate my profle when the window opens or make it so a seperate window opens
    }    

    private void populateStudentTable(){
        //create the columns needed in the table
        TableColumn studentID = new TableColumn("Student ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("Email");
        tbl_data.getColumns().addAll(studentID, firstName, lastName, email);
        
        //connect to the database and retrieve all students
        try{
            //Insantiate the main model
            MainModel model = new MainModel();
            //get all the students and put them in an observable list
            ObservableList<Student> list = model.getAllStudents();
            
            //put the data in the appropriate columns
            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            
            //get the table and list the data
            tbl_data.setItems(list);
        }
        catch(SQLException ex){
            System.out.println("DATABASE ERROR SQL EXCEPTION");
        } 
    }
    
    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        //create the selection details pane for student
        createStudentDetails();
        
        //clear the table of any contents
        tbl_data.getColumns().clear();
        
        //Show all the students
        populateStudentTable();
    }
    
        @FXML
    private void showAllCaseWorkers(MouseEvent event) {
        
    }
    
    @FXML
    private void selectItem(MouseEvent event) {
        //When an item is selected in the table get all the data for that item
        Student student = (Student) tbl_data.getSelectionModel().getSelectedItem();
        
        //set the text fields to show the selected item to allow for changes
        tf_studentID.setText(Integer.toString(student.getStudentID()));      
        tf_firstName.setText(student.getFirstName());
        tf_lastName.setText(student.getLastName());
        tf_email.setText(student.getEmail());
        
    }
  
    private void createStudentDetails(){

        //creates the labels and text fields in the verticle box
        vb_selectionDetails.getChildren().add(lbl_studentID);
         
        tf_studentID.setId("tf_studentID");
        vb_selectionDetails.getChildren().add(tf_studentID);
        
        vb_selectionDetails.getChildren().add(lbl_fName);
        
        tf_firstName.setId("tf_firstName");
        vb_selectionDetails.getChildren().add(tf_firstName);
       
        vb_selectionDetails.getChildren().add(lbl_lName);
        
        tf_lastName.setId("tf_lastName");
        vb_selectionDetails.getChildren().add(tf_lastName);
        
        vb_selectionDetails.getChildren().add(lbl_email);
        
        tf_email.setId("tf_email");
        vb_selectionDetails.getChildren().add(tf_email);
        
        update.setText("Update");
        vb_selectionDetails.getChildren().add(update);
        //create an on action event so the button knows what to do when pressed
        update.setOnAction((event) -> {
            
            User user = new User();
            
            user.setFirstName(tf_firstName.getText());
            user.setLastName(tf_lastName.getText());
            user.setEmail(tf_email.getText());
            user.setID(Integer.parseInt(tf_studentID.getText()));
            
            StudentModel.updateStudent(user);
            tbl_data.getColumns().clear();
            populateStudentTable();
            System.out.println("Student updated");
        });
        
    }               


}
