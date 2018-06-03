/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import beans.CaseWorker;
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
import model.CaseWorkerModel;
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
    Label lbl_employeeID = new Label("Employee ID");
    TextField tf_studentID = new TextField();
    TextField tf_employeeID = new TextField();
    Label lbl_fName = new Label("First Name");
    TextField tf_firstName = new TextField();
    Label lbl_lName = new Label("Last Name");
    TextField tf_lastName = new TextField();
    Label lbl_email = new Label("Email");
    TextField tf_email = new TextField();
    Label lbl_phNumber = new Label("Phone Number");
    Button update = new Button();
    
    //selected values: Student = 1, case worker = 2, admin = 3
    private int selectedTable = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Populate my profle when the window opens or make it so a seperate window opens
    }    

    private void populateTable(){
        if (selectedTable == 1) {
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
        } else if (selectedTable == 2) {
            //create the columns needed in the table
            TableColumn employeeID = new TableColumn("Employee ID");
            TableColumn firstName = new TableColumn("First Name");
            TableColumn lastName = new TableColumn("Last Name");
            TableColumn email = new TableColumn("Email");
            tbl_data.getColumns().addAll(employeeID, firstName, lastName, email);

            //connect to the database and retrieve all students
            try{
                //Insantiate the main model
                MainModel model = new MainModel();
                //get all the students and put them in an observable list
                ObservableList<CaseWorker> list = model.getAllCaseWorkers();

                //put the data in the appropriate columns
                employeeID.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("employeeID"));
                firstName.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("firstName"));
                lastName.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("lastName"));
                email.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("email"));

                //get the table and list the data
                tbl_data.setItems(list);
            }
            catch(SQLException ex){
                System.out.println("DATABASE ERROR SQL EXCEPTION");
            }
        }
    }
    
    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        //set the selected table value to one for student
        selectedTable = 1;
        
        //clear the vbox of its children ready for the new details and set the
        //text fields with the same name to be empty
        vb_selectionDetails.getChildren().clear();
        tf_studentID.setText("");
        tf_email.setText("");
        tf_firstName.setText("");
        tf_lastName.setText("");
        
        //create the selection details pane for student
        createStudentDetails();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //Show all the students
        populateTable();
    }
    
    @FXML
    private void showAllCaseWorkers(MouseEvent event) {
        //set the selected table value to one for case worker
        selectedTable = 2;
        
        //clear the vbox of its children ready for the new details and set the
        //text fields with the same name to be empty
        vb_selectionDetails.getChildren().clear();
        tf_employeeID.setText("");
        tf_email.setText("");
        tf_firstName.setText("");
        tf_lastName.setText("");
        
        //create the selection details pane for case worker
        createCaseWorkerDetails();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //show all case workers
        populateTable();
    }
    
    @FXML
    private void selectItem(MouseEvent event) {
        if (selectedTable == 1) {
            //When an item is selected in the table get all the data for that item
            Student student = (Student) tbl_data.getSelectionModel().getSelectedItem();

            //set the text fields to show the selected item to allow for changes
            tf_studentID.setText(Integer.toString(student.getStudentID()));      
            tf_firstName.setText(student.getFirstName());
            tf_lastName.setText(student.getLastName());
            tf_email.setText(student.getEmail());
        } else if (selectedTable == 2) {
            //When an item is selected in the table get all the data for that item
            CaseWorker caseWorker = (CaseWorker) tbl_data.getSelectionModel().getSelectedItem();

            //set the text fields to show the selected item to allow for changes
            tf_employeeID.setText(Integer.toString(caseWorker.getEmployeeID()));      
            tf_firstName.setText(caseWorker.getFirstName());
            tf_lastName.setText(caseWorker.getLastName());
            tf_email.setText(caseWorker.getEmail());
        }  
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
            populateTable();
            System.out.println("Student updated");
        });   
    }               

    private void createCaseWorkerDetails(){
        vb_selectionDetails.getChildren().add(lbl_employeeID);
        
        tf_employeeID.setId("tf_employeeID");
        vb_selectionDetails.getChildren().add(tf_employeeID);
        
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
            
            CaseWorker caseWorker = new CaseWorker();
            
            caseWorker.setFirstName(tf_firstName.getText());
            caseWorker.setLastName(tf_lastName.getText());
            caseWorker.setEmail(tf_email.getText());
            caseWorker.setEmployeeID(Integer.parseInt(tf_employeeID.getText()));
            
            CaseWorkerModel.updateCaseWorker(caseWorker);
            tbl_data.getColumns().clear();
            populateTable();
            System.out.println("Employee updated");
        });
        
    }

}
