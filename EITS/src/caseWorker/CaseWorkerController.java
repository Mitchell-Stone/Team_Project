/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseWorker;

import beans.Courses;
import beans.Student;
import controllers.MainController;
import java.io.IOException;
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
import model.CoursesModel;
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
    @FXML
    private Button logOutButton;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Loading....");
    }

    @FXML
    private void getAllStudents(ActionEvent event) throws SQLException {
        allStudentsTable.getColumns().clear();
        myStudentsTable.setVisible(false);
        secondaryTable.setVisible(false);
        allStudentsTable.setVisible(true);
        // show all students in table
        TableColumn studentID = new TableColumn("ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("email");

        allStudentsTable.getColumns().addAll(studentID, firstName, lastName, email);

        try {

            StudentModel model = new StudentModel();

            ObservableList<Student> list = model.getAllStudents();

            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

            allStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("Null Pointer Exception");
        }
    }

    @FXML
    private void getMyStudents(ActionEvent event) throws SQLException {
        myStudentsTable.getColumns().clear();
        myStudentsTable.setVisible(true);
        secondaryTable.setVisible(true);
        allStudentsTable.setVisible(false);
        //Show My students in table
        TableColumn studentID = new TableColumn("ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("email");

        myStudentsTable.getColumns().addAll(studentID, firstName, lastName, email);

        try {

            StudentModel model = new StudentModel();

            ObservableList<Student> list = model.getAllStudents();

            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

            myStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
        //Show Course in Secondary Table
        secondaryTable.getColumns().clear();
        TableColumn courseID = new TableColumn("ID");
        TableColumn courseName = new TableColumn("Course");
        TableColumn courseIndustry = new TableColumn("Industry");
        TableColumn courseLocation = new TableColumn("Location");
        secondaryTable.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation);
        try {

            CoursesModel model = new CoursesModel();

            ObservableList<Courses> list = model.getAllCourses();

            courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
            courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
            courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
            courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
            secondaryTable.setItems(list);
        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }

    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        
        String login = "/userAccess/userSignIn.fxml";
        
        MainController main = new MainController();
        main.openNewWindow(login, logOutButton);
        
    }

}
