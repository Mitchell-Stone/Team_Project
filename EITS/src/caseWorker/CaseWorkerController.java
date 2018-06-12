/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseWorker;

import beans.CaseWorker;
import beans.Courses;
import beans.Diploma;
import beans.Student;
import beans.User;
import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.CoursesModel;
import model.DiplomaModel;
import model.StudentModel;
import security.SecurityMethods;

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
    @FXML
    private TextField idTextField;
    @FXML
    private TextField textFname;
    @FXML
    private TextField textLname;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textDiploma;
    @FXML
    private TextField textWorker;
    @FXML
    private VBox secondaryVbox;
    @FXML
    private Button addCourse;
    @FXML
    private TextField textCourseID;
    @FXML
    private TextField textCourseName;
    @FXML
    private TextField textIndustry;
    @FXML
    private TextField textLocation;
    @FXML
    private Button buttonAssessments;
    @FXML
    private Button buttonAttendance;
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField textWorkerEmail;
    @FXML
    private TextField textWorkerNumber;
    @FXML
    private TextField textPassword;
    @FXML
    private Button buttonConfirm;
    @FXML
    private Label labelFname;
    @FXML
    private Label labelLname;
    @FXML
    private VBox updateVbox;
    @FXML
    private Button buttonAssignStudent;
    @FXML
    private Button buttonUnassignStudent;
    @FXML
    private Label emptyFieldsLabel;
    @FXML
    private TextField textNewPassword;

    ArrayList<String> currentCaseWorker;

    CaseWorker caseworker = new CaseWorker();
    CaseWorker access = new CaseWorker();

    ArrayList<String> studentDiploma;
    ArrayList<String> studentCaseWorker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        uneditable();
        updateVbox.setVisible(false);

        try {
            currentCaseWorker = CaseWorkerModel.getCaseWorkerByID(caseworker);
        } catch (SQLException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showCaseWorkerName();
        try {
            getMyStudentsTable();
        } catch (SQLException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showCaseWorkerName() {
        labelFname.setText(currentCaseWorker.get(1));
        labelLname.setText(currentCaseWorker.get(2));
    }

    @FXML
    private void getAllStudents(ActionEvent event) throws SQLException {
        allStudentsTable.getColumns().clear();
        myStudentsTable.setVisible(false);
        secondaryTable.setVisible(false);
        allStudentsTable.setVisible(true);
        secondaryVbox.setVisible(false);
        buttonAssignStudent.setDisable(false);
        buttonUnassignStudent.setDisable(true);
        // show all students in table
        TableColumn studentID = new TableColumn("ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("email");
        TableColumn studentDiplomaID = new TableColumn("Diploma ID");
        TableColumn studentEmployeeID = new TableColumn("CaseWorker");

        allStudentsTable.getColumns().addAll(studentID, firstName, lastName, email, studentDiplomaID, studentEmployeeID);

        try {

            StudentModel model = new StudentModel();

            ObservableList<Student> list = model.getAllStudentsCW();

            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            studentDiplomaID.setCellValueFactory(new PropertyValueFactory<Student, String>("diplomaID"));
            studentEmployeeID.setCellValueFactory(new PropertyValueFactory<Student, String>("employeeID"));

            allStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("Null Pointer Exception");
        }
    }

    @FXML
    private void getMyStudents(ActionEvent event) throws SQLException {
        getMyStudentsTable();

    }

    public void getMyStudentsTable() throws SQLException, NumberFormatException {
        myStudentsTable.getColumns().clear();
        myStudentsTable.setVisible(true);
        secondaryTable.setVisible(true);
        allStudentsTable.setVisible(false);
        secondaryVbox.setVisible(true);
        buttonAssignStudent.setDisable(true);
        buttonUnassignStudent.setDisable(false);

        int employeeID = Integer.parseInt(currentCaseWorker.get(0));

        //Show My students in table
        TableColumn studentID = new TableColumn("ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("email");
        TableColumn studentDiplomaID = new TableColumn("Diploma ID");
        TableColumn studentEmployeeID = new TableColumn("CaseWorker");

        myStudentsTable.getColumns().addAll(studentID, firstName, lastName, email, studentDiplomaID, studentEmployeeID);

        try {

            StudentModel model = new StudentModel();

            ObservableList<Student> list = model.getStudentsByCaseWorker(employeeID);

            studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
            studentDiplomaID.setCellValueFactory(new PropertyValueFactory<Student, String>("diplomaID"));
            studentEmployeeID.setCellValueFactory(new PropertyValueFactory<Student, String>("employeeID"));

            myStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
        //Show Course in Secondary Table
        secondaryTable.getColumns().clear();
        TableColumn diplomaID = new TableColumn("ID");
        TableColumn diplomaName = new TableColumn("Diploma");
        TableColumn diplomaIndustry = new TableColumn("Industry");
        TableColumn diplomaLocation = new TableColumn("Location");
        secondaryTable.getColumns().addAll(diplomaID, diplomaName, diplomaIndustry, diplomaLocation);
        try {

            DiplomaModel model = new DiplomaModel();

            ObservableList<Diploma> list = model.getAllDiplomas();

            diplomaID.setCellValueFactory(new PropertyValueFactory("diplomaID"));
            diplomaName.setCellValueFactory(new PropertyValueFactory("diplomaName"));
            diplomaIndustry.setCellValueFactory(new PropertyValueFactory("diplomaIndustry"));
            diplomaLocation.setCellValueFactory(new PropertyValueFactory("diplomaLocation"));
            secondaryTable.setItems(list);
        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
    }

    @FXML
    private void selectStudent(MouseEvent event) throws SQLException {
        Student st = (Student) allStudentsTable.getSelectionModel().getSelectedItem();
        editable();
        idTextField.setText(Integer.toString(st.getStudentID()));
        textFname.setText(st.getFirstName());
        textLname.setText(st.getLastName());
        textEmail.setText(st.getEmail());

        studentDiploma = DiplomaModel.getDiplomaByStudent(st);

        if (studentDiploma == null) {
            textDiploma.setText("None Selected");
        } else {
            textDiploma.setText(studentDiploma.get(1));
        }

        studentCaseWorker = CaseWorkerModel.getCaseWorkerByStudent(st);

        if (studentCaseWorker == null) {
            textWorker.setText("Un-Assigned");
        } else {
            textWorker.setText(studentCaseWorker.get(1));
        }
        uneditable();
    }

    @FXML
    private void MyStudentSelect(MouseEvent event) throws SQLException {
        Student st = (Student) myStudentsTable.getSelectionModel().getSelectedItem();
        editable();
        idTextField.setText(Integer.toString(st.getStudentID()));
        textFname.setText(st.getFirstName());
        textLname.setText(st.getLastName());
        textEmail.setText(st.getEmail());

        studentDiploma = DiplomaModel.getDiplomaByStudent(st);

        if (studentDiploma == null) {
            textDiploma.setText("Un-Assigned");
        } else {
            textDiploma.setText(studentDiploma.get(1));
        }

        studentCaseWorker = CaseWorkerModel.getCaseWorkerByStudent(st);

        if (studentCaseWorker == null) {
            textWorker.setText("None Selected");
        } else {
            textWorker.setText(studentCaseWorker.get(1));
        }
        uneditable();
    }

    @FXML
    private void selectCourse(MouseEvent event) {
        Diploma di = (Diploma) secondaryTable.getSelectionModel().getSelectedItem();
        editable();
        textCourseID.setText(Integer.toString(di.getDiplomaID()));
        textCourseName.setText(di.getDiplomaName());
        textIndustry.setText(di.getDiplomaIndustry());
        textLocation.setText(di.getDiplomaLocation());
        uneditable();
    }

    private void editable() {
        idTextField.setEditable(true);
        textFname.setEditable(true);
        textLname.setEditable(true);
        textEmail.setEditable(true);
        textDiploma.setEditable(true);
        textWorker.setEditable(true);
        textCourseID.setEditable(true);
        textCourseName.setEditable(true);
        textLocation.setEditable(true);
        textIndustry.setEditable(true);
    }

    private void uneditable() {
        idTextField.setEditable(false);
        textFname.setEditable(false);
        textLname.setEditable(false);
        textEmail.setEditable(false);
        textDiploma.setEditable(false);
        textWorker.setEditable(false);
        textCourseID.setEditable(false);
        textCourseName.setEditable(false);
        textLocation.setEditable(false);
        textIndustry.setEditable(false);
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {

        String login = "/userAccess/userSignIn.fxml";
        MainController main = new MainController();
        main.openNewWindow(login, logOutButton);

    }

    @FXML
    private void addStudentToCourse(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int diplomaID = Integer.parseInt(textCourseID.getText());

        CoursesModel.assignCourse(studentID, diplomaID);
        textDiploma.setText(textCourseName.getText());

    }

    @FXML
    private void updatesVisible(ActionEvent event) {
        updateVbox.setVisible(true);
        textWorkerEmail.setText(currentCaseWorker.get(3));

    }

    @FXML
    private void confirmUpdates(ActionEvent event) throws SQLException, NoSuchAlgorithmException {

        if ("".equals(textPassword.getText()) || "".equals(textNewPassword.getText())
                || "".equals(textWorkerEmail.getText())) {
            emptyFieldsLabel.setVisible(true);
            emptyFieldsLabel.setText("Field(s) empty ");
        } else {

            caseworker.setFirstName(currentCaseWorker.get(1));
            caseworker.setLastName(currentCaseWorker.get(2));
            caseworker.setEmail(textWorkerEmail.getText());
            caseworker.setEmployeeID(Integer.parseInt(currentCaseWorker.get(0)));

            access.setPassword(SecurityMethods.getHash(textNewPassword.getText()));
            access.setEmployeeID(Integer.parseInt(currentCaseWorker.get(0)));

            String oldPass = currentCaseWorker.get(4);
            String Pass1 = SecurityMethods.getHash(textNewPassword.getText());
            if (oldPass != Pass1) {
                emptyFieldsLabel.setVisible(true);
                emptyFieldsLabel.setText("Wrong Password");
            } else {
                CaseWorkerModel.updateCaseWorkerPassword(access);
            }

            if (CaseWorkerModel.updateCaseWorker(caseworker)) {
                emptyFieldsLabel.setVisible(false);
            }
            // updateVbox.setVisible(false);
            currentCaseWorker = CaseWorkerModel.getCaseWorkerByID(caseworker);
            System.out.println(currentCaseWorker);

        }
    }

    @FXML
    private void assignToCaseWorker(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int employeeID = Integer.parseInt(currentCaseWorker.get(0));

        CaseWorkerModel.assignCaseWorker(studentID, employeeID);

        textWorker.setText(currentCaseWorker.get(1));
    }

    @FXML
    private void unassignToCaseWorker(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int employeeID = 1;

        CaseWorkerModel.assignCaseWorker(studentID, employeeID);
        getMyStudentsTable();
    }

}
