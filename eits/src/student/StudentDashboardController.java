/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import beans.Courses;
import beans.Student;
import beans.User;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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

    
    private final ObservableList<Courses> data = FXCollections.observableArrayList();
    
    boolean visible = false;
    
    int ID = 0;
    
    User user = new User();
    
    ArrayList <String> currentUser;
    
    @FXML
    private TextField studentID;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private Pane studentInfoPane;
    @FXML
    private TextField courseID;
    @FXML
    private TextField employeeID;
    @FXML
    private TextField industryPreference;
    @FXML
    private Label errorOutput;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;
    @FXML
    private Label errorOutput1;
    @FXML
    private Pane passwordPane;
    @FXML
    private Button confirm;
    @FXML
    private Button confirmPassword;
    @FXML
    private TextField currentPassword;
    @FXML
    private TableView studentsTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*Courses bean = new Courses();
        CoursesModel model = new CoursesModel();
        
        bean.setTable("courses");
        
        try{
            ObservableList<Courses> coursesList = (ObservableList<Courses>) model.getCourses(bean);
            
            tableView.setItems(coursesList);
        }catch(Exception ex){
            System.err.println(ex);
        }
        
        ArrayList<Courses> list = new ArrayList<>();
        
        
        for (int i = 0; i < 10; i++) {
            
        }*/
        
        Student student = new Student();
        
        ID = student.getID();
        
        System.out.println(ID);
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(ID);

        currentUser = MainModel.getUserByID(user);

        System.out.println(currentUser);
        
        studentID.setText(Integer.toString(ID));
        firstName.setText(currentUser.get(0));
        lastName.setText(currentUser.get(1));
        email.setText(currentUser.get(2));
        
    }   

    @FXML
    private void actionUpdate(ActionEvent event) {
        
        firstName.setDisable(false);
        lastName.setDisable(false);
        email.setDisable(false);
        confirm.setVisible(true);
        
    }
    
    @FXML
    private void actionConfirmPassword(ActionEvent event) throws NoSuchAlgorithmException {
        
        Student access = new Student();
        
        access.setTable("student");
        
        access.setEmail(currentUser.get(2));
        access.setPassword(SecurityMethods.getHash(currentPassword.getText()));
        
        String cp = currentPassword.getText();
        String pw1 = password1.getText();
        String pw2 = password2.getText();
        
        if ("".equals(cp) || "".equals(pw1) || "".equals(pw2)) {
            errorOutput1.setText("There are empty fields.");
        } else {
            if (UserAccessModel.checkUserPass(access)) {
                if (pw1.equals(pw2) && !cp.equals(pw1) && !cp.equals(pw2)) {
                    user.setID(ID);
                    user.setPassword(SecurityMethods.getHash(pw1));
                    if (StudentModel.updateStudentPassword(user)) {
                        errorOutput1.setTextFill(Paint.valueOf("green"));
                        errorOutput1.setText("Success!");
                    } else {
                        errorOutput1.setText("There's been a mistake.");
                    }
                } else if(cp.equals(pw2) && pw2.equals(pw1)) {
                    errorOutput1.setText("The current password is the same as the new one.");
                } else {
                    errorOutput1.setText("The passwords entered do not match.");
                }
            } else {
                errorOutput1.setText("The current password appears to be wrong.");
            }
        }
        
    }

    @FXML
    private void actionUpdatePassword(ActionEvent event) {
        
        studentInfoPane.setVisible(false);
        passwordPane.setVisible(true);
        confirmPassword.setVisible(true);
        currentPassword.setDisable(false);
        password1.setDisable(false);
        password2.setDisable(false);
        
    }

    @FXML
    private void openStudentInfo(ActionEvent event) {
        
        passwordPane.setVisible(false);
        
        visible = MainModel.openClose(visible);

            if (visible) {
                studentInfoPane.setVisible(true);
            } else {
                studentInfoPane.setVisible(false);
            }
        
    }

    @FXML
    private void openCourses(ActionEvent event) throws Exception {
        
        studentsTable.getColumns().clear();
        
        TableColumn courseId = new TableColumn("ID");
        TableColumn name = new TableColumn("Name");
        TableColumn industry = new TableColumn("Industry");
        TableColumn location = new TableColumn("Location");
        TableColumn hours = new TableColumn("Hours");
        TableColumn degree = new TableColumn("Degree");
        
        studentsTable.getColumns().addAll(courseId, name, industry, location, hours, degree);
        
        try {
            
            CoursesModel model = new CoursesModel();
            
            ObservableList<Courses> list = model.getAllCourses();
           
           courseId.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
           name.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
           industry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry")); 
           location.setCellValueFactory(new PropertyValueFactory<Courses, String>("location")); 
           hours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours")); 
           degree.setCellValueFactory(new PropertyValueFactory<Courses, String>("finishingDegree")); 
           
           studentsTable.setItems(list);
            
        } catch(NullPointerException ex){
            System.out.println("Null Pointer Exception");
        } 
        
    }

    @FXML
    private void openAssessments(ActionEvent event) {
    }

    @FXML
    private void openGrades(ActionEvent event) {
    }

    @FXML
    private void openOther(ActionEvent event) {
    }

    @FXML
    private void actionUpdateConfirm(ActionEvent event) {
        
        if ("".equals(firstName.getText()) || "".equals(lastName.getText()) || "".equals(email.getText())) {
            errorOutput.setText("There are empty fields.");
        } else {
            
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setEmail(email.getText());
            user.setID(ID);

            if (StudentModel.updateStudent(user)) {

                errorOutput.setTextFill(Paint.valueOf("green"));
                errorOutput.setText("Success!");

                currentUser = MainModel.getUserByID(user);

                System.out.println(currentUser);

                studentID.setText(Integer.toString(ID));
                firstName.setText(currentUser.get(0));
                lastName.setText(currentUser.get(1));
                email.setText(currentUser.get(2));

            } else {
                errorOutput.setText("Error. Please try again.");
            }
            
        }
        
    }

}
