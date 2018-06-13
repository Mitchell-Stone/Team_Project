/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Assessment;
import beans.Attendance;
import beans.CaseWorker;
import beans.Courses;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import model.AssessmentModel;
import model.AttendanceModel;
import model.CaseWorkerModel;
import model.CoursesModel;
import model.DiplomaModel;
import model.MainModel;
import model.StudentModel;
import model.UserAccessModel;
import security.SecurityMethods;

/**
 * FXML Controller class
 *
 * @author mitch
 */
public class StudentDashboardController implements Initializable {

    
    @FXML
    private TableView table1;
    @FXML
    private VBox leftVbox;
    @FXML
    private Label leftLabelMain;
    @FXML
    private Label label1;
    @FXML
    private TextField text1;
    @FXML
    private Label label2;
    @FXML
    private TextField text2;
    @FXML
    private Label label3;
    @FXML
    private TextField text3;
    @FXML
    private Label label4;
    @FXML
    private TextField text4;
    @FXML
    private Label label5;
    @FXML
    private TextField text5;
    @FXML
    private Label label6;
    @FXML
    private TextField text6;
    @FXML
    private Label label7;
    @FXML
    private TextField text7;
    @FXML
    private Label errorLabel;
    @FXML
    private Button btn1;
    @FXML
    private Label uselesslabel;
    @FXML
    private Button btn2;
    @FXML
    private Button logoutBtn;
    @FXML
    private Pane bottomPaneLeft;
    @FXML
    private Pane bottomPaneCenter;
    @FXML
    private TextArea test1;
    @FXML
    private Button logoutBtn1;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
    
    //VARIABLES
    
    private final ObservableList<Courses> data = FXCollections.observableArrayList();
    
    Student currentStudent = new Student();
    
    User user = new User();
    
    boolean visible = false;
    String action1 = null;
    String action2 = null;
    
    int ID = 0;
    
    ArrayList <String> currentUser;
    
    ArrayList<String> currentDiploma;
    
    ObservableList<Assessment> tableItems;
    
    int submitAssessmentID = 0;
    int submitCourseID = 0;
    
    boolean dropped = false;
    
    VBox diplomas = new VBox();
    
    @FXML
    private Button industrybtn;
    
    //END OF VARIABLES
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Student student = new Student();
        
        ID = student.getID();
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);

        currentUser = MainModel.getUserByID(user);

        System.out.println(currentUser);
        
        try {
            AttendanceModel.logAttendance(Integer.parseInt(currentUser.get(0)));
        } catch (SQLException ex) {
            Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setStart();
        
    }   

    @FXML
    private void myprofile(ActionEvent event) {
        
        showStudentProfile();
        
    }

    @FXML
    private void diploma(ActionEvent event) {
        
        displayDiploma();
        
    }

    @FXML
    private void assignments(ActionEvent event) throws SQLException {
        
        displayAssignments();
        
        Attendance log = new Attendance();
        
        log.setStudentID(ID);
        
    }

    @FXML
    private void industry(ActionEvent event) {
        
        displayIndustries();
        
    }
    
    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        
        String login = "/userAccess/userSignIn.fxml";
        
        MainController main = new MainController();
        main.openNewWindow(login, logoutBtn);
        
    }

    @FXML
    private void btn1(ActionEvent event) throws NoSuchAlgorithmException {
        
        switch(action1) {
        
            case "update_user_details":
                
                updateUserDetails();
                
                break;
                
            case "update_user_details_confirm":
                
                updateUserdetailsConfirm();
                
                break;
                
            case "update_password_confirm":
                
                updatePasswordConfirm();
                
                break;
                
            case "reset_user_details":
                
                resetTextAndLabels();
                
                resetToUserDetails();
                
                showStudentProfile();
                
                break;
                
            case "confirm_diploma":
                
                confirmDiplomaSelection();
                
                diplomas.setVisible(false);
                
                resetTextAndLabels();
                
                btn1.setVisible(false);
                btn2.setVisible(false);
                
                break;
                
            default:
                System.out.println("Error in event handler 1.");
        
        }
        
    }

    @FXML
    private void btn2(ActionEvent event) throws SQLException {
        
        switch(action2) {
        
            case "update_password":
                
                updatePassword();
                
                break;
                
            case "submit":
                
                System.out.println("SUBMISSION");
                
                submitAssessment();
                
                break;
                
            default:
                System.out.println("Error in event handler 2.");
        
        }
        
    }
    
    private void showStudentProfile() {
    
        resetTextAndLabels();
        
        table1.setVisible(false);
        
        leftLabelMain.setText("Student details");
        
        label1.setText("Student ID:");
        text1.setEditable(false);
        label2.setText("Diploma ID:");
        text2.setEditable(false);
        label3.setText("Employee ID:");
        text3.setEditable(false);
        label4.setText("First Name:");
        text4.setEditable(false);
        label5.setText("Last Name:");
        text5.setEditable(false);
        label6.setText("Email:");
        text6.setEditable(false);
        
        label7.setVisible(false);
        text7.setVisible(false);
        
        text1.setText(currentUser.get(0));
        text2.setText(currentUser.get(1));
        text3.setText(currentUser.get(2));
        text4.setText(currentUser.get(3));
        text5.setText(currentUser.get(4));
        text6.setText(currentUser.get(5));
        
        btn1.setVisible(true);
        btn2.setVisible(true);
        
        btn1.setText("Update Details");
        btn2.setText("Update Password");
        
        action1 = "update_user_details";
        action2 = "update_password";
        
    }
    
    private void updateUserDetails() {
    
        text1.setDisable(true);
        text2.setDisable(true);
        text3.setDisable(true);
        text4.setEditable(true);
        text5.setEditable(true);
        text6.setEditable(true);
        
        btn1.setText("Confirm Updates");
        btn2.setVisible(false);
        
        action1 = "update_user_details_confirm";
        
    }
    
    private void updateUserdetailsConfirm() {
    
        if ("".equals(text4.getText()) || "".equals(text5.getText()) || "".equals(text6.getText())) {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("There are empty fields.");
        } else {
            
            user.setID(ID);
            user.setFirstName(text4.getText());
            user.setLastName(text5.getText());
            user.setEmail(text6.getText());

            if (StudentModel.updateStudent(user)) {

                errorLabel.setTextFill(Paint.valueOf("green"));
                errorLabel.setText("Success!");
                
                user.setID(ID);

                currentUser = MainModel.getUserByID(user);

                System.out.println(currentUser);

                text1.setText(currentUser.get(0));
                text4.setText(currentUser.get(3));
                text5.setText(currentUser.get(4));
                text6.setText(currentUser.get(5));
                
                btn1.setText("Back");
                action1 = "reset_user_details";

            } else {
                errorLabel.setTextFill(Paint.valueOf("red"));
                errorLabel.setText("Error. Please try again.");
            }         
        }
    
    }
    
    private void updatePassword() {
    
        leftLabelMain.setText("Password Update");
        
        label1.setText("Current password:");
        text1.setEditable(true);
        text1.setText("");
        label2.setText("New password:");
        text2.setText("");
        text2.setEditable(true);
        label3.setText("Repeat the new password:");
        text3.setText("");
        text3.setEditable(true);
        
        label4.setVisible(false);
        text4.setVisible(false);
        label5.setVisible(false);
        text5.setVisible(false);
        label6.setVisible(false);
        text6.setVisible(false);
        label7.setVisible(false);
        text7.setVisible(false);
        
        btn2.setVisible(false);
        
        btn1.setText("Confirm Updates");
        
        action1 = "update_password_confirm";
    
    }
    
    private void updatePasswordConfirm() throws NoSuchAlgorithmException {
    
        Student access = new Student();
        
        access.setTable("student");
        
        access.setEmail(currentUser.get(5));
        access.setPassword(SecurityMethods.getHash(text1.getText()));
        
        String cp = text1.getText();
        String pw1 = text2.getText();
        String pw2 = text3.getText();
        
        if ("".equals(cp) || "".equals(pw1) || "".equals(pw2)) {
            errorLabel.setTextFill(Paint.valueOf("red"));
            errorLabel.setText("There are empty fields.");
        } else {
            if (UserAccessModel.checkUserPass(access)) {
                if (pw1.equals(pw2) && !cp.equals(pw1) && !cp.equals(pw2)) {
                    user.setID(ID);
                    user.setPassword(SecurityMethods.getHash(pw1));
                    if (StudentModel.updateStudentPassword(user)) {
                        errorLabel.setTextFill(Paint.valueOf("green"));
                        errorLabel.setText("Success!");
                        
                        btn1.setText("Back");
                        
                        action1 = "reset_user_details";
                        
                    } else {
                        errorLabel.setTextFill(Paint.valueOf("red"));
                        errorLabel.setText("There's been a mistake.");
                    }
                } else if(cp.equals(pw2) && pw2.equals(pw1)) {
                    errorLabel.setTextFill(Paint.valueOf("red"));
                    errorLabel.setText("The current password is the same as the new one.");
                } else {
                    errorLabel.setTextFill(Paint.valueOf("red"));
                    errorLabel.setText("The passwords entered do not match.");
                }
            } else {
                errorLabel.setTextFill(Paint.valueOf("red"));
                errorLabel.setText("The current password appears to be wrong.");
            }
        }
    
    }
    
    private void displayDiploma() {
        
        emptyFields();
        emptyLabels();
        
        resetTextAndLabels();
        
        table1.setVisible(false);
    
        leftLabelMain.setText("Diploma");
        
        label1.setText("Diploma ID");
        
        label2.setText("Name");
        
        label3.setText("Industry");
        
        label4.setText("Location");
        
        label5.setText("Degree");
        
        label6.setVisible(false);
        text6.setVisible(false);
        label7.setVisible(false);
        text7.setVisible(false);
        
        btn1.setVisible(false);
        btn2.setVisible(false);
        
        user.setID(Integer.parseInt(currentUser.get(1)));
        System.out.println(currentUser.get(1));
        user.setTable("diploma");
        user.setColumn("diplomaID");
        
        
        
        if (CoursesModel.getDiplomaByID(user) == null) {
            errorLabel.setTextFill(Paint.valueOf("white"));
            errorLabel.setText("Diploma not selected,\n you can do so under the Industry tab.");
            text1.setText("Not available");
            text2.setText("Not available");
            text3.setText("Not available");
            text4.setText("Not available");
            text5.setText("Not available");
        } else {
            currentDiploma = CoursesModel.getDiplomaByID(user);
        }
        
        System.out.println("Current diploma information " + currentDiploma);
        
        text1.setText(currentDiploma.get(0));
        text1.setEditable(false);
        text2.setText(currentDiploma.get(1));
        text2.setEditable(false);
        text3.setText(currentDiploma.get(2));
        text3.setEditable(false);
        text4.setText(currentDiploma.get(3));
        text4.setEditable(false);
        text5.setText(currentDiploma.get(4));
        text5.setEditable(false);
    
    }
    
    private void displayAssignments() throws SQLException {
        
        emptyFields();
        emptyLabels();
        
        resetTextAndLabels();
        
        leftLabelMain.setText("Assessments");
        
        label1.setText("Assessment ID:");
        label2.setText("Title:");
        label3.setText("Description:");
        label4.setText("Due date:");
        label5.setText("Submitted:");
        label6.setText("Grade:");
        
        text1.setEditable(false);
        text2.setEditable(false);
        text3.setEditable(false);
        text4.setEditable(false);
        text5.setEditable(false);
        text6.setEditable(false);
        
        btn1.setVisible(false);
        btn2.setVisible(false);
        
        table1.setVisible(true);
        
        user.setID(Integer.parseInt(currentUser.get(1)));
        user.setTable("diploma");
        user.setColumn("diplomaID");
        
        currentDiploma = CoursesModel.getDiplomaByID(user);
    
        System.out.println("Current diplomaID " + currentDiploma.get(0));
        
                //create the columns needed in the table
                //TableColumn diplomaid = new TableColumn("Diploma ID");
                //TableColumn courseid = new TableColumn("Course ID");
                TableColumn assessmentid = new TableColumn("Assessment ID");
                TableColumn title = new TableColumn("Title");
                TableColumn description = new TableColumn("Description");
                TableColumn date = new TableColumn("Date");
                table1.getColumns().addAll(assessmentid, title, description, date);
                //connect to the database and retrieve all students
                try{
                    //Insantiate the main model
                    StudentModel model = new StudentModel();
                    //get all the students and put them in an observable list
                    tableItems = AssessmentModel.getAssessmentsByDiplomaID(Integer.parseInt(currentDiploma.get(0)));
                    //put the data in the appropriate columns
                    //diplomaid.setCellValueFactory(new PropertyValueFactory<Student, String>("diplomaID"));
                    //courseid.setCellValueFactory(new PropertyValueFactory<Student, String>("courseID"));
                    assessmentid.setCellValueFactory(new PropertyValueFactory<Student, String>("assessmentID"));
                    title.setCellValueFactory(new PropertyValueFactory<Student, String>("title"));
                    description.setCellValueFactory(new PropertyValueFactory<Student, String>("description"));
                    date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));
                    
                    //get the table and list the data
                    table1.setItems(tableItems);
                    
                }
                catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }
    
    }

    @FXML
    private void displayAssignmentInfo(MouseEvent event) {
        
        displayAssessmentInfo();
        
    }
    
    private void displayAssessmentInfo() {
        
        emptyFields();
        emptyLabels();
        
        leftLabelMain.setText("Details");
    
        Assessment assessment = (Assessment) table1.getSelectionModel().getSelectedItem();
        text1.setText(Integer.toString(assessment.getAssessmentID()));
        text2.setText(assessment.getTitle());
        text3.setText(assessment.getDescription());
        text4.setText(assessment.getDate());
        
        System.out.println("CourseID  " + Integer.toString(assessment.getCourseID()));
        System.out.println("DiplomaID  " + Integer.toString(assessment.getDiplomaID()));
        
        Assessment submission = new Assessment();
        
        submission.setAssessmentID(assessment.getAssessmentID());
        submission.setCourseID(assessment.getCourseID());
        submission.setStudentID(Integer.parseInt(currentUser.get(0)));
        
        int type = AssessmentModel.checkSubmission(submission);
        
        switch (type) {
            case 4000:
                System.out.println("No Submission");
                text5.setText("No");
                text6.setText("Not available");
                
                btn2.setVisible(true);
                
                btn2.setText("Submit");
                action2 = "submit";
                
                submitAssessmentID = assessment.getAssessmentID();
                submitCourseID = assessment.getCourseID();
                
                test1.setVisible(true);
                test1.setText("File not detected");
                
                break;
            case 0:
                System.out.println("Submitted but not graded");
                text5.setText("Yes");
                text6.setText("Pending");
                btn2.setVisible(false);
                test1.setVisible(false);
                break;
            case 999:
                System.out.println("what");
                text5.setText("Error");
                text6.setText("Error");
                break;
            default:
                System.out.println("Grade is " + type);
                text5.setText("Yes");
                text6.setText(Integer.toString(type));
                btn2.setVisible(false);
                test1.setVisible(false);
                break;
        }
    
    }
    
    private void submitAssessment() throws SQLException {
        
        if (dropped) {
            Assessment submission = new Assessment();
        
            submission.setAssessmentID(submitAssessmentID);
            submission.setCourseID(submitCourseID);
            submission.setStudentID(Integer.parseInt(currentUser.get(0)));

            AssessmentModel.submitAssessment(submission);

            tableItems = AssessmentModel.getAssessmentsByDiplomaID(Integer.parseInt(currentDiploma.get(0)));

            text5.setText("Yes");
            text6.setText("Pending");

            btn2.setVisible(false);
            dropped = false;
            
            test1.setVisible(false);
            test1.setText("");
            
        } else {
            test1.setText("Error, file necessary before submission.");
        }
        
        
    
    }
    
    public void displayIndustries() {
        
        emptyFields();
        emptyLabels();
    
        table1.setVisible(false);
        
        resetTextAndLabels();
        
        diplomas.setVisible(true);
        
        leftLabelMain.setText("Diploma");
        
        label1.setText("Diploma ID");
        
        label2.setText("Name");
        
        label3.setText("Industry");
        
        label4.setText("Location");
        
        label5.setText("Degree");
        
        label6.setVisible(false);
        text6.setVisible(false);
        label7.setVisible(false);
        text7.setVisible(false);
        
        btn1.setVisible(false);
        btn2.setVisible(false);
        
        grid.add(diplomas, 3, 2);
        
        ArrayList<String> industries = DiplomaModel.getIndustries();
        
        ArrayList<String> ids = DiplomaModel.getIDs();
        
        System.out.println(industries);
        
        System.out.println(ids);
        
        diplomas.setSpacing(8);
        
        Student dpl = new Student();
        
        for (int i = 0; i < industries.size(); i++) {
            
            Pane pane = new Pane();
            
            Label title = new Label(industries.get(i));
            
            Label id = new Label(ids.get(i));
            
            title.setStyle("-fx-text-fill: white;" + "-fx-font-size: 25;");
            title.setLayoutX(20);
            title.setLayoutY(15);
            
            title.setOnMouseClicked((MouseEvent diplomaSelected) -> {
                
                
                
                try {
                    dpl.setDiplomaID(Integer.parseInt(id.getText()));
                    
                    ArrayList<String> selectedDiploma = DiplomaModel.getDiplomaByStudent(dpl);
                    
                    text1.setText(selectedDiploma.get(0));
                    text1.setEditable(false);
                    text2.setText(selectedDiploma.get(1));
                    text2.setEditable(false);
                    text3.setText(selectedDiploma.get(2));
                    text3.setEditable(false);
                    text4.setText(selectedDiploma.get(3));
                    text4.setEditable(false);
                    text5.setText(selectedDiploma.get(4));
                    text5.setEditable(false);
                    
                    if (Integer.parseInt(currentUser.get(1)) == 0) {

                        btn1.setVisible(true);
                    
                        btn1.setText("Confirm");

                        action1 = "confirm_diploma";

                    } else {
                        btn1.setVisible(false);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            id.setStyle("-fx-text-fill: white;" + "-fx-font-size: 15;");
            
            id.setLayoutX(20);
            id.setLayoutY(70);
            
            pane.getChildren().addAll(title, id);
            
            pane.setMinHeight(100);
            pane.setStyle("-fx-border-color: #0066ff;" + "-fx-background-color: #404040;");
            
            diplomas.getChildren().add(pane);
            
        }
    
    }
    
    private void confirmDiplomaSelection() {
    
        DiplomaModel.assignDiplomaToStudent(Integer.parseInt(currentUser.get(0)), Integer.parseInt(text1.getText()));
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);

        currentUser = MainModel.getUserByID(user);
        
        user.setTable("diploma");
        user.setColumn("diplomaID");
        user.setID(Integer.parseInt(currentUser.get(1)));
        
        currentDiploma = CoursesModel.getDiplomaByID(user);
    
    }

    private void resetToUserDetails() {
        
        diplomas.setVisible(false);
    
        leftLabelMain.setVisible(true);
        leftLabelMain.setText("Student details");
        
        label1.setText("Student ID:");
        text1.setEditable(false);
        label2.setText("Diploma ID:");
        text2.setEditable(false);
        label3.setText("Employee ID:");
        text3.setEditable(false);
        label4.setText("First Name:");
        text4.setEditable(false);
        label5.setText("Last Name:");
        text5.setEditable(false);
        label6.setText("Email:");
        text6.setEditable(false);
        
        label7.setVisible(false);
        text7.setVisible(false);
        
        errorLabel.setText("");
    
    }
    
    private void resetTextAndLabels() {
        
        diplomas.setVisible(false);
        
        leftLabelMain.setVisible(true);
    
        label1.setVisible(true);
        text1.setDisable(false);
        text1.setVisible(true);
        label2.setVisible(true);
        text2.setDisable(false);
        text2.setVisible(true);
        label3.setVisible(true);
        text3.setDisable(false);
        text3.setVisible(true);
        label4.setVisible(true);
        text4.setDisable(false);
        text4.setVisible(true);
        label5.setVisible(true);
        text5.setDisable(false);
        text5.setVisible(true);
        label6.setVisible(true);
        text6.setDisable(false);
        text6.setVisible(true);
        
        errorLabel.setText("");
        
        test1.setVisible(false);
        test1.setText("");
    
    }
    
    private void setStart() {
        
        diplomas.setVisible(false);
    
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);
        text5.setVisible(false);
        text6.setVisible(false);
        text7.setVisible(false);
        
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        
        btn1.setVisible(false);
        btn2.setVisible(false);
        
        table1.setVisible(false);
        
        leftLabelMain.setVisible(false);
        
        errorLabel.setText("");
        
        test1.setVisible(false);
    
    }
    
    private void emptyLabels() {
        
        leftLabelMain.setText("");
    
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        
        errorLabel.setText("");
    
    }
    
    private void emptyFields() {
    
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        text7.setText("");
    
    }

    ////////////DRAG/DROP/////////////
    
    @FXML
    private void dragOver(DragEvent event) {
        Dragboard board = event.getDragboard();
      if (board.hasFiles()) {
        event.acceptTransferModes(TransferMode.ANY);
      }
    }

    @FXML
    private void dragDropped(DragEvent event) {
        
        test1.setText("File Dropped");
        dropped = true;

    }

    @FXML
    private void caseWorker(ActionEvent event) throws SQLException {
        
        resetTextAndLabels();
        
        label1.setText("CaseWorker ID");
        label2.setText("First Name");
        label3.setText("Last Name");
        label4.setText("Email");
        label5.setText("Phone Number");
        
        label6.setVisible(false);
        label7.setVisible(false);
        
        text6.setVisible(false);
        text7.setVisible(false);
        
        table1.setVisible(false);
        test1.setVisible(false);
        
        leftLabelMain.setText("CaseWorker");
        
        btn1.setVisible(false);
        btn2.setVisible(false);
        
        CaseWorker caseworker = new CaseWorker();
        
        caseworker.setID(Integer.parseInt(currentUser.get(2)));
        
        ArrayList<String> cwInfo = CaseWorkerModel.getCaseWorkerByID(caseworker);
        
        if (cwInfo == null) {
            text1.setText("Unassigned");
            text2.setText("Unassigned");
            text3.setText("Unassigned");
            text4.setText("Unassigned");
            text5.setText("Unassigned");
        } else {
            System.out.println(cwInfo);
            text1.setText(cwInfo.get(0));
            text2.setText(cwInfo.get(1));
            text3.setText(cwInfo.get(2));
            text4.setText(cwInfo.get(3));
            text5.setText(cwInfo.get(5));
        }
        
    }
    
}
