/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Attendance;
import beans.Courses;
import beans.Student;
import beans.User;
import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import static model.AssessmentModel.getCoursesByDiplomaID;
import model.AttendanceModel;
import model.CoursesModel;
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
    @FXML
    private Button logoutBtn;
    @FXML
    private SplitMenuButton industrySelect;
    @FXML
    private MenuItem musicInd;
    @FXML
    private MenuItem itInd;
    @FXML
    private MenuItem memeInd;
    @FXML
    private MenuItem psychInd;
    
    //END OF VARIABLES
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Student student = new Student();
        industrySelect.setVisible (false);
        
        ID = student.getID();
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);

        currentUser = MainModel.getUserByID(user);

        System.out.println(currentUser);
        
        try {
            AttendanceModel.logAttendance(ID);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        table1.setVisible (false);
        industrySelect.setVisible (true);
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
                
            default:
                System.out.println("Error in event handler 1.");
        
        }
        
    }

    @FXML
    private void btn2(ActionEvent event) {
        
        switch(action2) {
        
            case "update_password":
                
                updatePassword();
                
                break;
                
            default:
                System.out.println("Error in event handler 2.");
        
        }
        
    }
    
    private void showStudentProfile() {
    
        resetTextAndLabels();
        
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
            errorLabel.setText("There are empty fields.");
        } else {
            
            user.setID(ID);
            user.setFirstName(text4.getText());
            user.setLastName(text5.getText());
            user.setEmail(text6.getText());

            if (StudentModel.updateStudent(user)) {

                errorLabel.setTextFill(Paint.valueOf("green"));
                errorLabel.setText("Success!");

                currentUser = MainModel.getUserByID(user);

                System.out.println(currentUser);

                text1.setText(currentUser.get(0));
                text4.setText(currentUser.get(3));
                text5.setText(currentUser.get(4));
                text6.setText(currentUser.get(5));
                
                btn1.setText("Back");
                action1 = "reset_user_details";

            } else {
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
                        errorLabel.setText("There's been a mistake.");
                    }
                } else if(cp.equals(pw2) && pw2.equals(pw1)) {
                    errorLabel.setText("The current password is the same as the new one.");
                } else {
                    errorLabel.setText("The passwords entered do not match.");
                }
            } else {
                errorLabel.setText("The current password appears to be wrong.");
            }
        }
    
    }
    
    private void displayDiploma() {
    
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
        user.setTable("diploma");
        user.setColumn("diplomaID");
        
        currentDiploma = CoursesModel.getDiplomaByID(user);
        
        System.out.println(currentDiploma);
        
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
    
    private void displayAssignments() {
        
        user.setID(Integer.parseInt(currentUser.get(1)));
        user.setTable("diploma");
        user.setColumn("diplomaID");
        
        currentDiploma = CoursesModel.getDiplomaByID(user);
    
        System.out.println(currentDiploma.get(0));
        
        ArrayList ggg = getCoursesByDiplomaID(Integer.parseInt(currentDiploma.get(0)));
        
        System.out.println(ggg);
        
        
        
        
    
    }

    private void resetToUserDetails() {
    
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
    
    }

    @FXML
    private void music(ActionEvent event) {
        table1.setVisible (true);
        industrySelect.setVisible (false);
        table1.getColumns().clear();
        TableColumn courseID = new TableColumn("Course ID");
                TableColumn courseName = new TableColumn("Course Name");
                TableColumn courseIndustry = new TableColumn("Course Industry");
                TableColumn courseLocation = new TableColumn("Location");
                TableColumn courseLengthInHours = new TableColumn("Course Length (hrs)");
                table1.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseLengthInHours);
                //connect to the database and retrieve all courses
                try{
                    //Insantiate the main model
                    CoursesModel model = new CoursesModel();
                    //get all the courses and put them in an observable list
                    ObservableList<Courses> list = model.getIndustryMusic();
                    
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
                    courseLengthInHours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours"));
                    
                    //get the table and list the data
                    table1.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }
    }

    @FXML
    private void it(ActionEvent event) {
        table1.setVisible (true);
        industrySelect.setVisible (false);
        table1.getColumns().clear();
        TableColumn courseID = new TableColumn("Course ID");
                TableColumn courseName = new TableColumn("Course Name");
                TableColumn courseIndustry = new TableColumn("Course Industry");
                TableColumn courseLocation = new TableColumn("Location");
                TableColumn courseLengthInHours = new TableColumn("Course Length (hrs)");
                table1.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseLengthInHours);
                //connect to the database and retrieve all courses
                try{
                    //Insantiate the main model
                    CoursesModel model = new CoursesModel();
                    //get all the courses and put them in an observable list
                    ObservableList<Courses> list = model.getIndustryIt();
                    
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
                    courseLengthInHours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours"));
                    
                    //get the table and list the data
                    table1.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }
    }

    @FXML
    private void memes(ActionEvent event) {
        table1.setVisible (true);
        industrySelect.setVisible (false);
        table1.getColumns().clear();
        TableColumn courseID = new TableColumn("Course ID");
                TableColumn courseName = new TableColumn("Course Name");
                TableColumn courseIndustry = new TableColumn("Course Industry");
                TableColumn courseLocation = new TableColumn("Location");
                TableColumn courseLengthInHours = new TableColumn("Course Length (hrs)");
                table1.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseLengthInHours);
                //connect to the database and retrieve all courses
                try{
                    //Insantiate the main model
                    CoursesModel model = new CoursesModel();
                    //get all the courses and put them in an observable list
                    ObservableList<Courses> list = model.getIndustryMemes();
                    
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
                    courseLengthInHours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours"));
                    
                    //get the table and list the data
                    table1.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }
    }

    @FXML
    private void psychSci(ActionEvent event) {
        table1.setVisible (true);
        industrySelect.setVisible (false);
        table1.getColumns().clear();
        TableColumn courseID = new TableColumn("Course ID");
                TableColumn courseName = new TableColumn("Course Name");
                TableColumn courseIndustry = new TableColumn("Course Industry");
                TableColumn courseLocation = new TableColumn("Location");
                TableColumn courseLengthInHours = new TableColumn("Course Length (hrs)");
                table1.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseLengthInHours);
                //connect to the database and retrieve all courses
                try{
                    //Insantiate the main model
                    CoursesModel model = new CoursesModel();
                    //get all the courses and put them in an observable list
                    ObservableList<Courses> list = model.getIndustryPsi();
                    
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
                    courseLengthInHours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours"));
                    
                    //get the table and list the data
                    table1.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }
    }
    
}
