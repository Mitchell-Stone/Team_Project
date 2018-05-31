/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import beans.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.MainModel;

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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        //create the selection details pane for student
        createStudentDetails();
        
        tbl_data.getColumns().clear();

        //create the columns needed in the table
        TableColumn studentID = new TableColumn("Student ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("Email");
        tbl_data.getColumns().addAll(studentID, firstName, lastName, email);
        
        //connect to the database and retrieve all students
        try{
            MainModel model = new MainModel();
            ObservableList<Student> list = model.getAllStudents();
            
            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            
            tbl_data.setItems(list);
        }
        catch(NullPointerException ex){
            System.out.println("Null Pointer Exception");
        } 
    }
    
    @FXML
    private void selectItem(MouseEvent event) {
        
    }

    
    private void createStudentDetails(){
        
        Label lbl_id = new Label("Student ID");
        vb_selectionDetails.getChildren().add(lbl_id);
        
        TextField tf_studentID = new TextField();
        tf_studentID.setId("tf_studentID");
        vb_selectionDetails.getChildren().add(tf_studentID);
        
        Label lbl_fName = new Label("First Name");
        vb_selectionDetails.getChildren().add(lbl_fName);
        
        TextField tf_firstName = new TextField();
        tf_studentID.setId("tf_firstName");
        vb_selectionDetails.getChildren().add(tf_firstName);
        
        Label lbl_lName = new Label("Last Name");
        vb_selectionDetails.getChildren().add(lbl_lName);
        
        TextField tf_lastName = new TextField();
        tf_studentID.setId("tf_lastName");
        vb_selectionDetails.getChildren().add(tf_lastName);
        
        Label lbl_email = new Label("Email");
        vb_selectionDetails.getChildren().add(lbl_email);
        
        TextField tf_email = new TextField();
        tf_studentID.setId("tf_email");
        vb_selectionDetails.getChildren().add(tf_email);
        
    }
}
