/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import beans.Administrator;
import beans.CaseWorker;
import beans.Courses;
import beans.Student;
import beans.User;
import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.AdministratorModel;
import model.CaseWorkerModel;
import model.CoursesModel;
import model.MainModel;
import model.StudentModel;
import security.SecurityMethods;

/**
 * FXML Controller class
 *
 * @author 0111005906 - Mitchell Stone
 *
 */
public class AdministratorDashboardController implements Initializable {

    private final String student = "student";
    private final String caseWorker = "caseWorker";
    private final String admin = "admin";
    
    @FXML
    private TableView tbl_data;
    @FXML
    private VBox vb_selectionDetails;
    @FXML
    private Button btn_addNewUser;
    @FXML
    private Button btn_administrator;
    @FXML
    private Button btn_returnToLogin;
    @FXML
    private AnchorPane ap_adminDashboard;    
    
    Label lbl_searchHeader = new Label();
    RadioButton rbtn_id = new RadioButton();
    RadioButton rbtn_fname = new RadioButton();
    RadioButton rbtn_lname = new RadioButton();
    RadioButton rbtn_email = new RadioButton();
    ToggleGroup rbtn_group = new ToggleGroup();
    
    
    Label lbl_header = new Label();
    Label lbl_search = new Label();
    TextField tf_search = new TextField();
    Button btn_submitSearch = new Button();
    Label lbl_studentID = new Label("Student ID");
    Label lbl_employeeID = new Label("Employee ID");
    Label lbl_adminID = new Label("Admin ID");
    TextField tf_studentID = new TextField();
    TextField tf_employeeID = new TextField();
    TextField tf_adminID = new TextField();
    Label lbl_fName = new Label("First Name");
    TextField tf_firstName = new TextField();
    Label lbl_lName = new Label("Last Name");
    TextField tf_lastName = new TextField();
    Label lbl_email = new Label("Email");
    TextField tf_email = new TextField();
    Label lbl_phNumber = new Label("Phone Number");
    Button btn_update = new Button();
    Button btn_delete = new Button();
    Label lbl_newPassword = new Label("Enter New Password");
    Button btn_changePassword = new Button();
    Button btn_confirmPassword = new Button();
    Button btn_cancel = new Button();
    TextField tf_changePassword = new TextField();
    VBox vb_myProfile = new VBox();
    Label title = new Label("Welcome");
    GridPane grid = new GridPane();
    
    
    //selected values: Student = 1, case worker = 2, admin = 3
    private String selectionType = null;
    
    private final String windowURL = "/globalInterfaces/addNewUser.fxml";
    private final String loginURL = "/userAccess/userSignIn.fxml";
    
    /**
     * Initializes the controller class.
     * @param rb
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //populates the my profile tab
        myProfile();
        
        //hides everything until it is needed
        hideChangePassword();
        tbl_data.setVisible(false);
        vb_selectionDetails.setVisible(false);
        
    } 
    
    private void myProfile(){    
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(col1);
        vb_myProfile.setLayoutY(400); 
        
        ap_adminDashboard.getChildren().add(grid);
        grid.getChildren().addAll(title, tf_firstName);
    }
    
    @FXML
    private void btn_myProfile(MouseEvent event) {
        tbl_data.setVisible(false);
        vb_selectionDetails.setVisible(false);
        
        myProfile();
    }

    private void populateTable(String user){
            if (null != user)switch (user) {
            case "student":{
                //create the columns needed in the table
                TableColumn studentID = new TableColumn("Student ID");
                TableColumn firstName = new TableColumn("First Name");
                TableColumn lastName = new TableColumn("Last Name");
                TableColumn email = new TableColumn("Email");
                tbl_data.getColumns().addAll(studentID, firstName, lastName, email);
                //connect to the database and retrieve all students
                try{
                    //Insantiate the main model
                    StudentModel model = new StudentModel();
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
                }   break;
                }
            case "caseWorker":{
                //create the columns needed in the table
                TableColumn employeeID = new TableColumn("Employee ID");
                TableColumn firstName = new TableColumn("First Name");
                TableColumn lastName = new TableColumn("Last Name");
                TableColumn email = new TableColumn("Email");
                tbl_data.getColumns().addAll(employeeID, firstName, lastName, email);
                //connect to the database and retrieve all students
                try{
                    //Insantiate the main model
                    CaseWorkerModel model = new CaseWorkerModel();
                    //get all the students and put them in an observable list
                    ObservableList<CaseWorker> list = model.getAllCaseWorkers();
                    
                    //put the data in the appropriate columns
                    employeeID.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("employeeID"));
                    firstName.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("firstName"));
                    lastName.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("lastName"));
                    email.setCellValueFactory(new PropertyValueFactory<CaseWorker, String>("email"));
                    
                    //get the table and list the data
                    tbl_data.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }   break;
                }
            case "admin":{
                //create the columns needed in the table
                TableColumn adminID = new TableColumn("Administrator ID");
                TableColumn firstName = new TableColumn("First Name");
                TableColumn lastName = new TableColumn("Last Name");
                TableColumn email = new TableColumn("Email");
                tbl_data.getColumns().addAll(adminID, firstName, lastName, email);
                //connect to the database and retrieve all students
                try{
                    //Insantiate the main model
                    AdministratorModel model = new AdministratorModel();
                    //get all the students and put them in an observable list
                    ObservableList<Administrator> list = model.getAllAdministrators();
                    
                    //put the data in the appropriate columns
                    adminID.setCellValueFactory(new PropertyValueFactory<Administrator, String>("adminID"));
                    firstName.setCellValueFactory(new PropertyValueFactory<Administrator, String>("firstName"));
                    lastName.setCellValueFactory(new PropertyValueFactory<Administrator, String>("lastName"));
                    email.setCellValueFactory(new PropertyValueFactory<Administrator, String>("email"));
                    
                    //get the table and list the data
                    tbl_data.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }   break;
                }
            case "courses":{
                //create the columns needed in the table
                TableColumn courseID = new TableColumn("Course ID");
                TableColumn courseName = new TableColumn("Course Name");
                TableColumn courseIndustry = new TableColumn("Course Industry");
                TableColumn courseLocation = new TableColumn("Location");
                TableColumn courseLengthInHours = new TableColumn("Course Length (hrs)");
                tbl_data.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseLengthInHours);
                //connect to the database and retrieve all courses
                try{
                    //Insantiate the main model
                    CoursesModel model = new CoursesModel();
                    //get all the students and put them in an observable list
                    ObservableList<Courses> list = model.getAllCourses();
                    
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Courses, String>("name"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Courses, String>("industry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Courses, String>("location"));
                    courseLengthInHours.setCellValueFactory(new PropertyValueFactory<Courses, String>("numberOfHours"));
                    
                    //get the table and list the data
                    tbl_data.setItems(list);
                }catch(SQLException ex){
                    System.out.println("DATABASE ERROR SQL EXCEPTION");
                }   break;
                }
            default:
                break;
            }
        }
    
    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        //show the table
        tbl_data.setVisible(true);
        vb_selectionDetails.setVisible(true);
        ap_adminDashboard.getChildren().remove(vb_myProfile);
        hideChangePassword();
        
        //set the selected table value
        selectionType = student;
        
        //clear the vbox of its children ready for the new details and set the
        //text fields with the same name to be empty
        vb_selectionDetails.getChildren().clear();
        tf_studentID.clear();
        tf_email.clear();
        tf_firstName.clear();
        tf_lastName.clear();
        
        //create the selection details pane for student
        createStudentDetails();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //Show all the students
        populateTable(student);
    }
    
    @FXML
    private void showAllCaseWorkers(MouseEvent event) {
        //show the table
        tbl_data.setVisible(true);
        vb_selectionDetails.setVisible(true);
        ap_adminDashboard.getChildren().remove(vb_myProfile);
        hideChangePassword();
        
        //set the selected table value
        selectionType = caseWorker;
        
        //clear the vbox of its children ready for the new details and set the
        //text fields with the same name to be empty
        vb_selectionDetails.getChildren().clear();
        tf_employeeID.clear();
        tf_email.clear();
        tf_firstName.clear();
        tf_lastName.clear();
        
        //create the selection details pane for case worker
        createCaseWorkerDetails();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //show all case workers
        populateTable(caseWorker);
    }
    
    @FXML
    private void showAllAdministrators(MouseEvent event) {
        //show the table
        tbl_data.setVisible(true);
        vb_selectionDetails.setVisible(true);
        ap_adminDashboard.getChildren().remove(vb_myProfile);
        hideChangePassword();
        
        //set the selected table value
        selectionType = admin;
        
        //clear the vbox of its children ready for the new details and set the
        //text fields with the same name to be empty
        vb_selectionDetails.getChildren().clear();
        tf_adminID.clear();
        tf_email.clear();
        tf_firstName.clear();
        tf_lastName.clear();
        
        //create the selection details pane for case worker
        createAdministratorDetails();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //show all case workers
        populateTable(admin);
    }
    
    @FXML
    private void showAllCourses(MouseEvent event) {
        //show the table
        tbl_data.setVisible(true);
        vb_selectionDetails.setVisible(false);
        
        //set the selected table value
        selectionType = "courses";
             
        //clear the table of its previous content
        tbl_data.getColumns().clear();
        
        //show all case workers
        populateTable("courses");
    }
    
    @FXML
    private void selectItem(MouseEvent event) {
        switch (selectionType) {
            case student:
                //When an item is selected in the table get all the data for that item
                Student st = (Student) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_studentID.setText(Integer.toString(st.getStudentID()));
                tf_firstName.setText(st.getFirstName());
                tf_lastName.setText(st.getLastName());
                tf_email.setText(st.getEmail());
                break;
            case caseWorker:
                //When an item is selected in the table get all the data for that item
                CaseWorker cw = (CaseWorker) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_employeeID.setText(Integer.toString(cw.getEmployeeID()));
                tf_firstName.setText(cw.getFirstName());
                tf_lastName.setText(cw.getLastName());
                tf_email.setText(cw.getEmail());
                break;
            case admin:
                //When an item is selected in the table get all the data for that item
                Administrator ad = (Administrator) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_adminID.setText(Integer.toString(ad.getAdminID()));
                tf_firstName.setText(ad.getFirstName());
                tf_lastName.setText(ad.getLastName()); 
                tf_email.setText(ad.getEmail());
                break;
            default:
                break;
        }
    }
    
    private void searchOptions(String userType){
        //Search
        lbl_searchHeader.setText("Searh for " + userType);
        lbl_searchHeader.setStyle("-fx-font: 24 arial;");
        rbtn_id.setText(userType + " ID");
        rbtn_id.setSelected(true);
        rbtn_id.requestFocus();
        rbtn_fname.setText("First Name");
        rbtn_lname.setText("Last Name");
        rbtn_email.setText("Email");  
        lbl_search.setText("Enter Search Value");;
        lbl_header.setText("Selection Details");
        lbl_header.setStyle("-fx-font: 24 arial;");
        btn_submitSearch.setText("Submit");
        
        //put the radio buttons into a toggle group
        rbtn_id.setToggleGroup(rbtn_group);
        rbtn_id.setSelected(true);
        rbtn_fname.setToggleGroup(rbtn_group);
        rbtn_lname.setToggleGroup(rbtn_group);
        rbtn_email.setToggleGroup(rbtn_group);
    }
  
    private void createStudentTable(ObservableList list){
        //create the columns needed in the table
        TableColumn studentID = new TableColumn("Student ID");
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("Email");
        tbl_data.getColumns().addAll(studentID, firstName, lastName, email);
          
        //put the data in the appropriate columns
        studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        firstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        
        tbl_data.setItems(list);
        
    }
    
    private void createStudentDetails(){
        //create search options
        searchOptions("Student");
                    
        //Selection Details
        btn_update.setText("Update Student");
        btn_delete.setText("Delete Student");
        btn_changePassword.setText("Change Password");
        btn_confirmPassword.setText("Confirm Password");
        btn_cancel.setText("Cancel");

        vb_selectionDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, lbl_header, lbl_studentID, tf_studentID,
                lbl_fName, tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, btn_delete, 
                btn_changePassword, lbl_newPassword, tf_changePassword, btn_confirmPassword, btn_cancel);
        
        //create an on action event so the button knows what to do when pressed
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    StudentModel model = new StudentModel();
                    if ("Student ID".equals(rbtn.getText())) {                   
                        //get all the students and put them in an observable list
                        ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "studentID");

                        createStudentTable(list);
                    }else if ("First Name".equals(rbtn.getText())) {
                        //get all the students and put them in an observable list
                        ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "firstName");

                        createStudentTable(list);
                    }else if ("Last Name".equals(rbtn.getText())) {
                        //get all the students and put them in an observable list
                        ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "lastName");

                        createStudentTable(list);
                    }else if ("Email".equals(rbtn.getText())) {
                        //get all the students and put them in an observable list
                        ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "email");

                        createStudentTable(list);
                    }
    
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
                }
        });
            
        btn_update.setOnAction((event) -> {
            updateUser(student, Integer.parseInt(tf_studentID.getText()), "studentID");
        });
        
        btn_delete.setOnAction((event) ->{
            deleteUser(student, "studentID", Integer.parseInt(tf_studentID.getText()));
        });
        
        btn_changePassword.setOnAction((event) ->{
            showChangePassword();
        });
        
        btn_confirmPassword.setOnAction((event) ->{
            confirmPassword(student, Integer.parseInt(tf_studentID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });  
    } 
   
    private void createCaseWorkerDetails(){
        searchOptions("Employee");
        
        tf_employeeID.setId("tf_employeeID");
        tf_firstName.setId("tf_firstName");
        tf_lastName.setId("tf_lastName");
        tf_email.setId("tf_email");      
        btn_update.setText("Update Case Worker");
        btn_delete.setText("Delete Case Worker");
        btn_changePassword.setText("Change Password");
        tf_changePassword.setId("tf_changePassword");
        btn_confirmPassword.setText("Confirm Password");
        btn_cancel.setText("Cancel");
        
        vb_selectionDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, lbl_header, lbl_employeeID, tf_employeeID, lbl_fName, 
                tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, 
                btn_delete, btn_changePassword, lbl_newPassword, tf_changePassword,
                btn_confirmPassword, btn_cancel);        
        
        //create an on action event so the button knows what to do when pressed
        btn_update.setOnAction((event) -> {
            updateUser(caseWorker, Integer.parseInt(tf_employeeID.getText()), "employeeID");
        });
        
        btn_delete.setOnAction((event) ->{       
            deleteUser(caseWorker, "employeeID", Integer.parseInt(tf_employeeID.getText()));            
        });
        
        btn_changePassword.setOnAction((event) ->{
            showChangePassword();
        });
        
        btn_confirmPassword.setOnAction((event) ->{
            confirmPassword(caseWorker, Integer.parseInt(tf_employeeID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });
    }
    
    private void createAdministratorDetails(){ 
        searchOptions("Administrator");
        
        tf_adminID.setId("tf_adminID");
        tf_firstName.setId("tf_firstName");
        tf_lastName.setId("tf_lastName");
        tf_email.setId("tf_email");
        btn_update.setText("Update Administrator");
        btn_delete.setText("Delete Administrator");
        btn_changePassword.setText("Change Password");
        tf_changePassword.setId("tf_changePassword");
        btn_confirmPassword.setText("Confirm Password");
        btn_cancel.setText("Cancel");
        
        vb_selectionDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, lbl_header, lbl_adminID, tf_adminID, lbl_fName, 
                tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, 
                btn_delete, btn_changePassword, lbl_newPassword, tf_changePassword, 
                btn_confirmPassword, btn_cancel);
        
        //create an on action event so the button knows what to do when pressed
        btn_update.setOnAction((event) -> {
            updateUser(admin, Integer.parseInt(tf_adminID.getText()), "adminID");
        });
        
        btn_delete.setOnAction((event) ->{
            deleteUser(admin, "adminID", Integer.parseInt(tf_adminID.getText()));
        });
        
        btn_changePassword.setOnAction((event) ->{
            showChangePassword();
        });
        
        btn_confirmPassword.setOnAction((event) ->{
            confirmPassword(admin, Integer.parseInt(tf_adminID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });
    }
    
    private void deleteUser(String userType, String idType, int userID){
        User user = new User();
        user.setTable(userType);
        user.setID(userID);     
 
        MainModel.deleteSelection(user, idType);
        
        tbl_data.getColumns().clear();
        populateTable(userType);
        
        tf_email.clear();
        tf_firstName.clear();
        tf_lastName.clear();
        System.out.println(userType + " deleted");
    }
    
    private void updateUser(String userType, int userID, String idType){
        User user = new User();

        user.setTable(userType);
        user.setIdType(idType);
        user.setFirstName(tf_firstName.getText());
        user.setLastName(tf_lastName.getText());
        user.setEmail(tf_email.getText());
        user.setID(userID);

        MainModel.updateUser(user);
        tbl_data.getColumns().clear();
        populateTable(userType);
        System.out.println(userType + " updated");       
    }
    
    public void confirmPassword(String userType, int userID, String password){
        User user = new User();
        user.setTable(userType);
        user.setID(userID);
        try {
            user.setPassword(SecurityMethods.getHash(password));
            System.out.println("Password changed");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error with encoding password.");
        }
        StudentModel.updateStudentPassword(user);

        tf_changePassword.clear();
        hideChangePassword();
    }
    
    private void showChangePassword(){
        btn_changePassword.setVisible(false);
        btn_cancel.setVisible(true);
        tf_changePassword.setVisible(true);
        btn_confirmPassword.setVisible(true);
        lbl_newPassword.setVisible(true);
    }
    
    private void hideChangePassword(){
        btn_changePassword.setVisible(true);
        btn_cancel.setVisible(false);
        tf_changePassword.setVisible(false);
        btn_confirmPassword.setVisible(false);
        lbl_newPassword.setVisible(false);
    }

    @FXML
    private void addNewUser(MouseEvent event) throws IOException {
        MainController main = new MainController();
        main.openNewWindow(windowURL);
    }

    @FXML
    private void returnToLogin(MouseEvent event) throws IOException {
        MainController main = new MainController();
        main.openNewWindow(loginURL, btn_returnToLogin);
    }
}
