/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseWorker;

import beans.Courses;
import beans.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StudentModel;

/**
 * FXML Controller class
 *
 * @author Jake
 */
public class CaseWorkerController implements Initializable {
    

    @FXML
    private TableView allStudentsTable;
    @FXML
    private TableView secondaryTable;
    @FXML
    private TableView myStudentsTable;
    @FXML
    private Button myStudentsButton;
    @FXML
    private Button allStudentsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void allStudentsButton(ActionEvent event) throws SQLException {
        allStudentsTable.getColumns().clear();

        TableColumn studentID = new TableColumn("ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("email");

        allStudentsTable.getColumns().addAll(studentID, firstName, lastName, email);

        try {

            StudentModel model = new StudentModel();

            ObservableList<Student> list = model.getAllStudents();

            studentID.setCellValueFactory(new PropertyValueFactory<Courses, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Courses, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Courses, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Courses, String>("email"));

            allStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("Null Pointer Exception");
        }
    }

}
 