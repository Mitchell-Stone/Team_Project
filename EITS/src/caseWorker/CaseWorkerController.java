/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseWorker;

import beans.Assessment;
import beans.Attendance;
import beans.CaseWorker;
import beans.Courses;
import beans.Diploma;
import beans.Student;
import beans.Submission;
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
import model.AssessmentModel;
import model.AttendanceModel;
import model.CaseWorkerModel;
import model.CoursesModel;
import model.DiplomaModel;
import model.StudentModel;
import model.SubmissionsModel;
import security.SecurityMethods;

/*

Student Number: 5103355915

Name: Jake Smith

Date: 10/06/18

Purpose: Handles Case Worker Dashboard

Known Bugs:

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
    @FXML
    private Button cancelUpdate;
    @FXML
    private Button gradeButton;
    @FXML
    private Label secondaryLabel;
    @FXML
    private Label textSecondary1;
    @FXML
    private Label textSecondary2;
    @FXML
    private Label textSecondary3;
    @FXML
    private Label textSecondary4;

    CaseWorker caseworker = new CaseWorker();
    CaseWorker access = new CaseWorker();

    ArrayList<String> studentDiploma;
    ArrayList<String> studentCaseWorker;
    ArrayList<String> currentCaseWorker;
    ArrayList<String> assessmentName;
    ArrayList<String> subjectName;
    
    int asid = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        uneditable();
        updateVbox.setVisible(false);

        try {
            //Gets Case Worker Logged in
            currentCaseWorker = CaseWorkerModel.getCaseWorkerByID(caseworker);
        } catch (SQLException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        showCaseWorkerName();
        try {
            //shows my students 
            getMyStudentsTable();
        } catch (SQLException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(CaseWorkerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Shows Case Worker first and last name
    private void showCaseWorkerName() {
        
        labelFname.setText(currentCaseWorker.get(1));
        labelLname.setText(currentCaseWorker.get(2));
    }
    
    //shows all students in table
    @FXML
    private void getAllStudents(ActionEvent event) throws SQLException {
        allStudentsTable.getColumns().clear();
        myStudentsTable.setVisible(false);
        secondaryTable.setVisible(false);
        allStudentsTable.setVisible(true);
        secondaryVbox.setVisible(false);
        buttonAssignStudent.setDisable(false);
        buttonUnassignStudent.setDisable(true);
        gradeButton.setVisible(false);
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

            studentID.setCellValueFactory(new PropertyValueFactory("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            studentDiplomaID.setCellValueFactory(new PropertyValueFactory("diplomaID"));
            studentEmployeeID.setCellValueFactory(new PropertyValueFactory("employeeID"));

            allStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("Null Pointer Exception");
        }
    }
    
    // shows diplomas in secondary table
    @FXML
    public void getMyStudentsTable() throws SQLException, NumberFormatException {
        getMyStudents();
        textCourseID.clear();
        textCourseName.clear();
        textLocation.clear();
        textIndustry.clear();
        secondaryLabel.setText("Diploma");
        textSecondary1.setText("ID");
        textSecondary2.setText("Name");
        textSecondary3.setText("Industry");
        textSecondary4.setText("Location");
        textSecondary3.setVisible(true);
        textSecondary4.setVisible(true);
        textIndustry.setVisible(true);
        textLocation.setVisible(true);
        addCourse.setVisible(true);

        //Show diplomas in Secondary Table
        secondaryLabel.setText("Diploma");
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
    
    //show my students in table
    public void getMyStudents() throws SQLException, NumberFormatException {
        myStudentsTable.getColumns().clear();
        secondaryTable.getColumns().clear();
        myStudentsTable.setVisible(true);
        secondaryTable.setVisible(true);
        allStudentsTable.setVisible(false);
        secondaryVbox.setVisible(true);
        buttonAssignStudent.setDisable(true);
        buttonUnassignStudent.setDisable(false);
        gradeButton.setVisible(false);

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

            studentID.setCellValueFactory(new PropertyValueFactory("studentID"));
            firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            studentDiplomaID.setCellValueFactory(new PropertyValueFactory("diplomaID"));
            studentEmployeeID.setCellValueFactory(new PropertyValueFactory("employeeID"));

            myStudentsTable.setItems(list);

        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
    }
    
    // Shows And prepares labels, textfields and buttons for submissions
    @FXML
    private void getSubmissons(ActionEvent event) throws SQLException {
        getMyStudents();
        textCourseID.clear();
        textCourseName.clear();
        textLocation.clear();
        textIndustry.clear();
        secondaryLabel.setText("Submission");
        textSecondary1.setText("Diploma:");
        textSecondary2.setText("Subject:");
        textSecondary3.setText("Assessment Name:");
        textSecondary4.setText("Grade:");
        textSecondary3.setVisible(true);
        textSecondary4.setVisible(true);
        textIndustry.setVisible(true);
        textLocation.setVisible(true);
        addCourse.setVisible(false);
        gradeButton.setVisible(true);
    }
    
     // Shows And prepares labels, textfields and buttons for submissions
    @FXML
    private void getAttendance(ActionEvent event) throws SQLException {
        getMyStudents();
        textCourseID.clear();
        textCourseName.clear();
        secondaryLabel.setText("Attendance");
        textSecondary1.setText("Name:");
        textSecondary2.setText("Date:");
        textSecondary3.setVisible(false);
        textSecondary4.setVisible(false);
        textIndustry.setVisible(false);
        textLocation.setVisible(false);
        addCourse.setVisible(false);

    }
    
    //populates Attendance in secondary table
    public void populateAttendance(int studID) throws SQLException {
        secondaryTable.getColumns().clear();
        TableColumn studentID = new TableColumn("StudentID");
        TableColumn date = new TableColumn("Date");
        secondaryTable.getColumns().addAll(studentID, date);
        try {

            AttendanceModel model = new AttendanceModel();

            ObservableList<Attendance> list = model.getAttendanceByStudentID(studID);

            studentID.setCellValueFactory(new PropertyValueFactory("studentID"));
            date.setCellValueFactory(new PropertyValueFactory("date"));
            secondaryTable.setItems(list);
        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
    }
    
    //populates submissions in secondary table
    public void populateSubmisson(int studID) throws SQLException {
        secondaryTable.getColumns().clear();
        TableColumn assessmentID = new TableColumn("AssessmentID");
        TableColumn courseID = new TableColumn("CourseID");
        TableColumn studentID = new TableColumn("StudentID");
        TableColumn grade = new TableColumn("Grade");
        secondaryTable.getColumns().addAll(assessmentID, courseID, studentID, grade);
        try {

            SubmissionsModel model = new SubmissionsModel();

            ObservableList<Submission> list = model.getSubmissionsByStudentID(studID);

            assessmentID.setCellValueFactory(new PropertyValueFactory("assessmentID"));
            courseID.setCellValueFactory(new PropertyValueFactory("courseID"));
            studentID.setCellValueFactory(new PropertyValueFactory("studentID"));
            grade.setCellValueFactory(new PropertyValueFactory("grade"));

            secondaryTable.setItems(list);
        } catch (NullPointerException ex) {
            System.out.println("No Pointer Exception");
        }
    }
    
    //select a student from allStudentTable and displays in textfields
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
    
    //select a student from myStudentTable and displays in textfields
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
        if (secondaryLabel.getText().equals("Attendance")) {
            int studID = Integer.parseInt(idTextField.getText());
            populateAttendance(studID);
        }
        if (secondaryLabel.getText().equals("Submission")) {
            int studID = Integer.parseInt(idTextField.getText());
            populateSubmisson(studID);
        }
        uneditable();
    }
    
    //Allows for specific information to be displayed in textfields
    @FXML
    private void selectSecondaryInformation(MouseEvent event) throws SQLException {
        //Selects correct information and runs case
        switch (secondaryLabel.getText()) {
            case "Diploma": {

                Diploma di = (Diploma) secondaryTable.getSelectionModel().getSelectedItem();
                editable();
                textCourseID.setText(Integer.toString(di.getDiplomaID()));
                textCourseName.setText(di.getDiplomaName());
                textIndustry.setText(di.getDiplomaIndustry());
                textLocation.setText(di.getDiplomaLocation());
                uneditable();
                break;
            }
            case "Attendance": {
                System.out.println(secondaryLabel.getText());
                Attendance at = (Attendance) secondaryTable.getSelectionModel().getSelectedItem();
                editable();
                textCourseName.setText(at.getDate());
                textCourseID.setText(textFname.getText());
                uneditable();
                break;
            }
            case "Submission": {
                Submission su = (Submission) secondaryTable.getSelectionModel().getSelectedItem();
                assessmentName = AssessmentModel.getAssessmentByID(su);
                subjectName = CoursesModel.getCourseByID(su);
                asid = su.getAssessmentID();
                editable();
                textCourseID.setText(textDiploma.getText());
                textCourseName.setText(subjectName.get(1));
                textIndustry.setText(assessmentName.get(2));
                uneditable();
                textLocation.setEditable(true);
                break;
            }
        }
    }
    //allows textfields to be editable
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
    
    //allows textfields to be uneditable
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
    
    //Adds selected student to diploma/course
    @FXML
    private void addStudentToCourse(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int diplomaID = Integer.parseInt(textCourseID.getText());

        CoursesModel.assignCourse(studentID, diplomaID);
        textDiploma.setText(textCourseName.getText());

    }
    
    //Makes update vbox visible
    @FXML
    private void updatesVisible(ActionEvent event) {
        updateVbox.setVisible(true);
        textWorkerEmail.setText(currentCaseWorker.get(3));

    }
    
    //Updates Case Worker information
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
            String pass1 = SecurityMethods.getHash(textPassword.getText());
            String pass2 = SecurityMethods.getHash(textNewPassword.getText());
            if (!oldPass.equals(pass1)) {
                textPassword.clear();
                textPassword.setPromptText("Incorrect Password");
            } else {
                CaseWorkerModel.updateCaseWorkerPassword(access);

                if (CaseWorkerModel.updateCaseWorker(caseworker)) {
                    currentCaseWorker = CaseWorkerModel.getCaseWorkerByID(caseworker);
                }
                updateVbox.setVisible(false);
                emptyFieldsLabel.setVisible(false);
                textWorkerEmail.setText(currentCaseWorker.get(3));
                textPassword.clear();
                textNewPassword.clear();
                System.out.println(currentCaseWorker);
            }
        }
    }

    //Closes and resets update Vbox
    @FXML
    private void cancelUpdate(ActionEvent event) {
        updateVbox.setVisible(false);
        emptyFieldsLabel.setVisible(false);
        textWorkerEmail.setText(currentCaseWorker.get(3));
        textPassword.clear();
        textNewPassword.clear();
    }
    
    // Assigns selected student to current Case Worker
    @FXML
    private void assignToCaseWorker(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int employeeID = Integer.parseInt(currentCaseWorker.get(0));

        CaseWorkerModel.assignCaseWorker(studentID, employeeID);

        textWorker.setText(currentCaseWorker.get(1));
    }
    
     // Unassigns selected student from current Case Worker
    @FXML
    private void unassignToCaseWorker(ActionEvent event) throws SQLException {

        int studentID = Integer.parseInt(idTextField.getText());
        int employeeID = 1;

        CaseWorkerModel.assignCaseWorker(studentID, employeeID);
        getMyStudentsTable();
    }
    
    //Updates Grade in submissions table
    @FXML
    private void gradeAssessment(ActionEvent event) throws SQLException {
        
        try {
            int grade = Integer.parseInt(textLocation.getText());
            System.out.println(grade);
            System.out.println("It's an Int.");
        } catch (NumberFormatException e) {
            textLocation.clear();
            textLocation.setPromptText("Invalid Grade");
        }
        
        int grade = Integer.parseInt(textLocation.getText());
        int studentID = Integer.parseInt(idTextField.getText());
        if (grade < 101 || grade < -1 && !textLocation.getPromptText().equals("Invalid Grade")){
            SubmissionsModel.updateGrade(grade, studentID, asid);
            populateSubmisson(studentID);
        } else {
            textLocation.clear();
            textLocation.setPromptText("Invalid Grade");
        }

    }
    
    //logs out Case Worker
    @FXML
    private void logOut(ActionEvent event) throws IOException {

        String login = "/userAccess/userSignIn.fxml";
        MainController main = new MainController();
        main.openNewWindow(login, logOutButton);

    }
}
