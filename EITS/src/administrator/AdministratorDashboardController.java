/*Student Number: 0111005906

Name: Mitchell Stone

Date: 12/06/18

Purpose: Works as the controller for the administrator dashboard window

Known Bugs: Tables do not upadate automatically when a new user or course is added and that table is showing.
            The courses context menu can be brought back up on alternate tables where it should not be able to.
            Errors occur when the empty space on a table is selected 
*/
package administrator;

import beans.Administrator;
import beans.CaseWorker;
import beans.Courses;
import beans.Diploma;
import beans.Student;
import beans.User;
import controllers.MainController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.AdministratorModel;
import model.CaseWorkerModel;
import model.CoursesModel;
import model.DiplomaModel;
import model.MainModel;
import model.StudentModel;

public class AdministratorDashboardController implements Initializable {

    private final String student = "student";
    private final String caseWorker = "caseWorker";
    private final String admin = "admin";
    private final String courses = "courses";
    
    @FXML
    private TableView tbl_data;
    @FXML
    private VBox vb_selectionDetails;
    @FXML
    private Button btn_returnToLogin;  
    @FXML
    private GridPane gp_adminDashboard;
    @FXML
    private VBox vb_searchDetails;
    
    @FXML
    Label lbl_searchHeader = new Label();
    RadioButton rbtn_id = new RadioButton();
    RadioButton rbtn_fname = new RadioButton();
    RadioButton rbtn_lname = new RadioButton();
    RadioButton rbtn_email = new RadioButton();
    ToggleGroup rbtn_group = new ToggleGroup();
    
    
    Label lbl_header = new Label("Selection Details");
    Label lbl_search = new Label();
    TextField tf_search = new TextField();
    Button btn_submitSearch = new Button();
    Button btn_cancelSearch = new Button();
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
    HBox hb_buttons = new HBox();
    Label title = new Label("Welcome");
    GridPane grid = new GridPane();
    ContextMenu contextMenu = new ContextMenu();
    
    TableView tbl_subjectTable = new TableView();

    private String selectionType = null;
    
    private final String addNewUserURL = "/globalInterfaces/addNewUser.fxml";
    private final String addNewCourseURL = "/globalInterfaces/addNewCourse.fxml";
    private final String loginURL = "/userAccess/userSignIn.fxml";
    private final String reportsURL = "/globalInterfaces/reports.fxml";
    
    
    /**
     * Initializes the controller class.
     * @param rb
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //hides everything until it is needed
        hideChangePassword();
        tbl_data.setVisible(false);
        vb_selectionDetails.setVisible(false);
    } 
    
    //Finds what type of user is selected to know how to populate the table
    private void populateTable(String user){
        tbl_data.getColumns().clear();
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
                //connect to the database and retrieve all case workers
                try{
                    //Insantiate the main model
                    CaseWorkerModel model = new CaseWorkerModel();
                    //get all the case workers and put them in an observable list
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
                //connect to the database and retrieve all admins
                try{
                    //Insantiate the main model
                    AdministratorModel model = new AdministratorModel();
                    //get all the admins and put them in an observable list
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
                TableColumn courseType = new TableColumn("Type");
                tbl_data.getColumns().addAll(courseID, courseName, courseIndustry, courseLocation, courseType);
                //connect to the database and retrieve all courses/subjects
                try{
                    //Insantiate the main model
                    DiplomaModel model = new DiplomaModel();
                    //get all the courses/subjects and put them in an observable list
                    ObservableList<Diploma> list = model.getAllDiplomas();
                    
                    System.out.println(list);
                    //put the data in the appropriate columns
                    courseID.setCellValueFactory(new PropertyValueFactory<Diploma, String>("diplomaID"));
                    courseName.setCellValueFactory(new PropertyValueFactory<Diploma, String>("diplomaName"));
                    courseIndustry.setCellValueFactory(new PropertyValueFactory<Diploma, String>("diplomaIndustry"));
                    courseLocation.setCellValueFactory(new PropertyValueFactory<Diploma, String>("diplomaLocation"));
                    courseType.setCellValueFactory(new PropertyValueFactory<Diploma, String>("courseType"));
                    
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
    
    private void populateSubjectTable(int diplomaID){
        //create the columns needed in the table
        TableColumn subjectID = new TableColumn("Subject ID");
        TableColumn subjectName = new TableColumn("Subject Name");
        TableColumn subjectLocation = new TableColumn("Subject Location");
        tbl_subjectTable.getColumns().addAll(subjectID, subjectName, subjectLocation);
        //connect to the database and retrieve all courses/subjects
        try{
            //Insantiate the main model
            CoursesModel model = new CoursesModel();
            //get all the courses/subjects and put them in an observable list
            ObservableList<Courses> list = model.getCoursesByDiplomaID(diplomaID);
            
            System.out.println(list);
            //put the data in the appropriate columns
            subjectID.setCellValueFactory(new PropertyValueFactory<Courses, String>("courseID"));
            subjectName.setCellValueFactory(new PropertyValueFactory<Diploma, String>("name"));
            subjectLocation.setCellValueFactory(new PropertyValueFactory<Diploma, String>("location"));

            //get the table and list the data
            tbl_subjectTable.setItems(list);
        }catch(SQLException ex){
            System.out.println("DATABASE ERROR SQL EXCEPTION");
        }
    }
    
    //sets the dashboard to a default state
    private void setDashboard(){
        //show the table
        tbl_data.setVisible(true);
        tbl_subjectTable.setVisible(false);
        tf_search.clear();
        vb_selectionDetails.setVisible(false);
        vb_searchDetails.setVisible(true);
        gp_adminDashboard.getChildren().remove(vb_myProfile);
        hideChangePassword();
    }
    
    //Used to clear data for transitioning between windows
    private void clearDashboardDetails(){
        //Clear specific areas of their data
        vb_selectionDetails.getChildren().clear();
        tf_studentID.clear();
        tf_email.clear();
        tf_firstName.clear();
        tf_lastName.clear();
        
        //clear the table of its previous content
        tbl_data.getColumns().clear();
    }
    
    //Runs the functions to populate the table with students
    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        //sets the dashboard to a default state
        setDashboard();
        
        //create search options
        searchStudent();
        
        //sets the selection type so the controllers know how to populate the table.
        selectionType = student;
        
        //text fields with the same name to be empty
        clearDashboardDetails();
        
        createStudentDetails();
        
        //Show all the students
        populateTable(student);
    }
    
    //Runs the functions to populate the table with case workers
    @FXML
    private void showAllCaseWorkers(MouseEvent event) {
        //sets the dashboard to a default state
        setDashboard();
        
        //create search options
        searchCaseWorker();
        
        //sets the selection type so the controllers know how to populate the table.
        selectionType = caseWorker;
        
        //text fields with the same name to be empty
        clearDashboardDetails();
        
        createCaseWorkerDetails();
        
        //show all case workers
        populateTable(caseWorker);
    }
    
    //Runs the functions to populate the table with admins
    @FXML
    private void showAllAdministrators(MouseEvent event) {
        //sets the dashboard to a default state
        setDashboard();
        
        //create search options
        searchAdmin();
        
        //sets the selection type so the controllers know how to populate the table.
        selectionType = admin;
        
        //text fields with the same name to be empty
        clearDashboardDetails();
        
        createAdministratorDetails();
              
        //show all case workers
        populateTable(admin);
    }
    
    //Runs the functions to populate the table with courses
    @FXML
    private void showAllCourses(MouseEvent event) {
        //sets the dashboard to a default state
        setDashboard();
        
        vb_searchDetails.setVisible(false);
        vb_selectionDetails.setVisible(false);
        tbl_subjectTable.setVisible(false);

        selectionType = courses;
        
        tbl_data.getColumns().clear();
        
        //show all case workers
        populateTable(courses);
    }
    
    //Runs the functions to populate a new table to show all subjects related to a selected course
    private void showCourseSubjects(int diplomaID) throws SQLException {
        //Sets the margins for the new table
        GridPane.setMargin(tbl_subjectTable, new Insets(20,20,20,0));
        
        //Adds the new table into the grid pane
        //(Component, colIndex, rowIndex, colSpan, rowSpan)
        gp_adminDashboard.add(tbl_subjectTable, 2, 1, 1, 2);
        
        //Execute the query to get all the subjects related to a diploma ID
        populateSubjectTable(diplomaID);
    }

    //Handles the mouse events for seleting items in the tables for each user and course type
    @FXML
    private void selectItem(MouseEvent event) throws SQLException {
        switch (selectionType) {
            case student:
                vb_selectionDetails.setVisible(true);
                //When an item is selected in the table get all the data for that item
                Student st = (Student) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_studentID.setText(Integer.toString(st.getStudentID()));
                tf_firstName.setText(st.getFirstName());
                tf_lastName.setText(st.getLastName());
                tf_email.setText(st.getEmail());
                break;
            case caseWorker:
                vb_selectionDetails.setVisible(true);
                //When an item is selected in the table get all the data for that item
                CaseWorker cw = (CaseWorker) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_employeeID.setText(Integer.toString(cw.getEmployeeID()));
                tf_firstName.setText(cw.getFirstName());
                tf_lastName.setText(cw.getLastName());
                tf_email.setText(cw.getEmail());
                break;
            case admin:
                vb_selectionDetails.setVisible(true);
                //When an item is selected in the table get all the data for that item
                Administrator ad = (Administrator) tbl_data.getSelectionModel().getSelectedItem();
                //set the text fields to show the selected item to allow for changes
                tf_adminID.setText(Integer.toString(ad.getAdminID()));
                tf_firstName.setText(ad.getFirstName());
                tf_lastName.setText(ad.getLastName()); 
                tf_email.setText(ad.getEmail());
                break;
            case courses:
                tbl_subjectTable.getColumns().clear();
                gp_adminDashboard.getChildren().remove(tbl_subjectTable);
                tbl_subjectTable.setVisible(true);

                Diploma di = (Diploma) tbl_data.getSelectionModel().getSelectedItem();
                
                showCourseSubjects(di.getDiplomaID());      
                
                tableContextMenu();

                break;
            default:
                break;
        }
    }
    
    //Creates the context menu for the courses table
    private void tableContextMenu(){
        //Content for the context menu
        MenuItem mi1 = new MenuItem("Edit Course");
        MenuItem mi2 = new MenuItem("Add Course");
        MenuItem mi3 = new MenuItem("Delete Course");
        
        //Handles the action event for each selection
        //Edit course
        mi1.setOnAction((ActionEvent event) -> {
            Object item = tbl_data.getSelectionModel().getSelectedItem();
            System.out.println("Edit Course");
        });
        
        //Add course - opens a new window
        mi2.setOnAction((ActionEvent event) -> {
            MainController cont = new MainController();
            try {
                cont.openNewWindow(addNewCourseURL);
                event.consume();
            } catch (IOException ex) {
                Logger.getLogger(AdministratorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        
        //Delete course
        mi3.setOnAction((ActionEvent event) -> {
            Diploma item = (Diploma) tbl_data.getSelectionModel().getSelectedItem();
            
            CoursesModel.deleteCourse(item.getDiplomaID());
            System.out.println("Delete Course");
            
            tbl_data.getColumns().clear();
            populateTable(courses);
        });
        
        //adds the selections to the context menu and sets it to the table
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(mi1, mi2, mi3);
        tbl_data.setContextMenu(menu);
    }
    
    //Handles the process of searching for a student
    private void searchStudent(){
        //sets the search VBox for setudent details
        tf_search.clear();
        vb_searchDetails.getChildren().clear();
        
        lbl_searchHeader.setText("Searh for Students");
        lbl_searchHeader.setStyle("-fx-font: 24 arial;");
        rbtn_id.setText("Student ID");
        rbtn_id.setSelected(true);
        rbtn_id.requestFocus();
        rbtn_fname.setText("First Name");
        rbtn_lname.setText("Last Name");
        rbtn_email.setText("Email");  
        lbl_search.setText("Enter Search Value");
        btn_submitSearch.setText("Submit");
        btn_cancelSearch.setText("Cancel");
        
        //put the radio buttons into a toggle group
        rbtn_id.setToggleGroup(rbtn_group);
        rbtn_id.setSelected(true);
        rbtn_fname.setToggleGroup(rbtn_group);
        rbtn_lname.setToggleGroup(rbtn_group);
        rbtn_email.setToggleGroup(rbtn_group);
        
        //adds all components to the VBox
        vb_searchDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, btn_cancelSearch);
        
        //handles the button on action event to conduct the search
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    StudentModel model = new StudentModel();
                    TableColumn studentId = null;
                    
                    //logic for what search type is selected with the radio buttons
                    if (null != rbtn.getText()) switch (rbtn.getText()) {
                        case "Student ID":{
                            ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "studentID");
                            createTable(list, studentId, "studentID");
                                break;
                            }
                        case "First Name":{
                            ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "firstName");
                            createTable(list, studentId, "studentID");
                                break;
                            }
                        case "Last Name":{
                            ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "lastName");
                            createTable(list, studentId, "studentID");
                                break;
                            }
                        case "Email":{
                            ObservableList<Student> list = model.searchForStudents(tf_search.getText(), "email");
                            createTable(list, studentId, "studentID");
                                break;
                            }
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
            }
        });
        
        //handles the button event to clear the fields
        btn_cancelSearch.setOnAction((event) ->{
            populateTable(student);
            tf_search.clear();
        });
    }
    
    //Handles the process of searching for a case worker
    private void searchCaseWorker(){
        //sets the search VBox for case worker details
        vb_searchDetails.getChildren().clear();
        
        lbl_searchHeader.setText("Searh for Case Workers");
        lbl_searchHeader.setStyle("-fx-font: 24 arial;");
        rbtn_id.setText("Case Worker ID");
        rbtn_id.setSelected(true);
        rbtn_id.requestFocus();
        rbtn_fname.setText("First Name");
        rbtn_lname.setText("Last Name");
        rbtn_email.setText("Email");  
        lbl_search.setText("Enter Search Value");
        lbl_header.setText("Selection Details");
        lbl_header.setStyle("-fx-font: 24 arial;");
        btn_submitSearch.setText("Submit");
        btn_cancelSearch.setText("Cancel");
        
        //put the radio buttons into a toggle group
        rbtn_id.setToggleGroup(rbtn_group);
        rbtn_id.setSelected(true);
        rbtn_fname.setToggleGroup(rbtn_group);
        rbtn_lname.setToggleGroup(rbtn_group);
        rbtn_email.setToggleGroup(rbtn_group);
        
        //adds all components to the VBox
        vb_searchDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, btn_cancelSearch);
        
        //handles the button on action event to conduct the search
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    CaseWorkerModel model = new CaseWorkerModel();
                    TableColumn employeeId = null;
                    
                    //logic for what search type is selected with the radio buttons
                    if (null != rbtn.getText()) switch (rbtn.getText()) {
                        case "Employee ID":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "employeeID");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "First Name":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "firstName");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "Last Name":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "lastName");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "Email":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "email");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
            }
        });
        
        //handles the button event to clear the fields
        btn_cancelSearch.setOnAction((event) ->{
            populateTable(caseWorker);
            tf_search.clear();
        });
    }
    
    //Handles the process of searching for a admins
    private void searchAdmin(){
        //sets the search VBox for admin details
        vb_searchDetails.getChildren().clear();
        
        lbl_searchHeader.setText("Searh for Administrator");
        lbl_searchHeader.setStyle("-fx-font: 24 arial;");
        rbtn_id.setText("Administrator ID");
        rbtn_id.setSelected(true);
        rbtn_id.requestFocus();
        rbtn_fname.setText("First Name");
        rbtn_lname.setText("Last Name");
        rbtn_email.setText("Email");  
        lbl_search.setText("Enter Search Value");
        lbl_header.setText("Selection Details");
        lbl_header.setStyle("-fx-font: 24 arial;");
        btn_submitSearch.setText("Submit");
        btn_cancelSearch.setText("Cancel");
        
        //put the radio buttons into a toggle group
        rbtn_id.setToggleGroup(rbtn_group);
        rbtn_id.setSelected(true);
        rbtn_fname.setToggleGroup(rbtn_group);
        rbtn_lname.setToggleGroup(rbtn_group);
        rbtn_email.setToggleGroup(rbtn_group);
        
        //adds all components to the VBox
        vb_searchDetails.getChildren().addAll(lbl_searchHeader, rbtn_id, rbtn_fname, rbtn_lname, 
                rbtn_email, lbl_search, tf_search, btn_submitSearch, btn_cancel, btn_cancelSearch);
        
        //handles the button on action event to conduct the search
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    AdministratorModel model = new AdministratorModel();
                    TableColumn adminId = null;
                    
                    //logic for what search type is selected with the radio buttons
                    if (null != rbtn.getText()) switch (rbtn.getText()) {
                        case "Administrator ID":{
                            //get all the students and put them in an observable list
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "adminID");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "First Name":{
                            //get all the students and put them in an observable list
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "firstName");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "Last Name":{
                            //get all the students and put them in an observable list
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "lastName");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "Email":{
                            //get all the students and put them in an observable list
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "email");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
            }
        });
        
        //handles the button event to clear the fields
        btn_cancelSearch.setOnAction((event) ->{
            populateTable(admin);
            tf_search.clear();
        });
    }
  
    //Flexible function to create a table with the headings needed
    private void createTable(ObservableList list, TableColumn col, String idType){
        //create the columns needed in the table
        col = new TableColumn(idType);
        TableColumn firstName = new TableColumn("First Name");
        TableColumn lastName = new TableColumn("Last Name");
        TableColumn email = new TableColumn("Email");
        tbl_data.getColumns().addAll(col, firstName, lastName, email);
          
        //put the data in the appropriate columns
        col.setCellValueFactory(new PropertyValueFactory<User, String>(idType));
        firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        
        tbl_data.setItems(list); 
    }
    
    private void createStudentDetails(){                 
        //Selection Details
        tf_studentID.setId("tf_studentID");
        tf_firstName.setId("tf_firstName");
        tf_lastName.setId("tf_lastName");
        tf_email.setId("tf_email"); 
        btn_update.setText("Update Student");
        btn_delete.setText("Delete Student");
        btn_changePassword.setText("Change Password");
        btn_confirmPassword.setText("Confirm Password");
        btn_cancel.setText("Cancel");
        
        //adds all components to the VBox
        vb_selectionDetails.getChildren().addAll(lbl_header, lbl_studentID, tf_studentID,
                lbl_fName, tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, btn_delete,
                btn_changePassword, lbl_newPassword, tf_changePassword, btn_confirmPassword, btn_cancel);
        
        //create an on action event for each button so it knows what to do when pressed    
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
            confirmPassword(student, "studentID", Integer.parseInt(tf_studentID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });  
    } 
   
    private void createCaseWorkerDetails(){
        //Selection details
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
        
        //add each component to the VBox
        vb_selectionDetails.getChildren().addAll(lbl_header, lbl_employeeID, tf_employeeID, lbl_fName, 
                tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, 
                btn_delete, btn_changePassword, lbl_newPassword, tf_changePassword,
                btn_confirmPassword, btn_cancel);        
        
        //create an on action event for each button so it knows what to do when pressed
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    CaseWorkerModel model = new CaseWorkerModel();
                    TableColumn employeeId = null;
                    
                    //logic for know what to search by what radio button is selected
                    if (null != rbtn.getText()) switch (rbtn.getText()) {
                        case "Employee ID":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "employeeID");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "First Name":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "firstName");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "Last Name":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "lastName");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        case "Email":{
                            ObservableList<CaseWorker> list = model.searchForCaseWorker(tf_search.getText(), "email");
                            createTable(list, employeeId, "employeeID");
                                break;
                            }
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
            }
        });
        
        //create an on action event for each button so it knows what to do when pressed
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
            confirmPassword(caseWorker, "employeeID", Integer.parseInt(tf_employeeID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });
    }
    
    private void createAdministratorDetails(){ 
        //Selection Details
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
        
        //Add the components to the VBox
        vb_selectionDetails.getChildren().addAll(lbl_header, lbl_adminID, tf_adminID, lbl_fName, 
                tf_firstName, lbl_lName, tf_lastName, lbl_email, tf_email, btn_update, 
                btn_delete, btn_changePassword, lbl_newPassword, tf_changePassword, 
                btn_confirmPassword, btn_cancel);
        
        //create an on action event so the button knows what to do when pressed
        btn_submitSearch.setOnAction((event) -> { 
            tbl_data.getColumns().clear();
            if (rbtn_group.getSelectedToggle() != null) {
                RadioButton rbtn = (RadioButton) rbtn_group.getSelectedToggle();
                System.out.println(rbtn.getText());
                try { 
                    AdministratorModel model = new AdministratorModel();
                    TableColumn adminId = null;
                    
                    //logic for know what type of search is selected in the radio buttons
                    if (null != rbtn.getText()) switch (rbtn.getText()) {
                        case "Administrator ID":{
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "adminID");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "First Name":{
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "firstName");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "Last Name":{
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "lastName");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        case "Email":{
                            ObservableList<Administrator> list = model.searchForAdmin(tf_search.getText(), "email");
                            createTable(list, adminId, "adminID");
                                break;
                            }
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    System.out.println("SQL Database Error");
                    System.out.println(ex);
                }
            }
        });
        
        //create an on action event for each button so it knows what to do when pressed
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
            confirmPassword(admin, "adminID" ,Integer.parseInt(tf_adminID.getText()), tf_changePassword.getText());         
        });
        
        btn_cancel.setOnAction((event) ->{
            hideChangePassword();
        });
    }
    
    //Flexible function that can delete any user type
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
    
    //Flexible function that can update any user type
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
    
    //Function to update any user types password
    public void confirmPassword(String userType, String idType, int userID, String password){
        User user = new User();
        
        user.setIDType(idType);
        user.setTable(userType);
        user.setID(userID);
        user.setPassword(password);
        
        MainModel.updatePassword(user);
        
        tf_changePassword.clear();
        hideChangePassword();
    }
    
    //Used to show the password fucntions on the GUI
    private void showChangePassword(){
        btn_changePassword.setVisible(false);
        btn_cancel.setVisible(true);
        tf_changePassword.setVisible(true);
        btn_confirmPassword.setVisible(true);
        lbl_newPassword.setVisible(true);
    }
    
    //Used to hide the password functions from the GUI
    private void hideChangePassword(){
        btn_changePassword.setVisible(true);
        btn_cancel.setVisible(false);
        tf_changePassword.setVisible(false);
        btn_confirmPassword.setVisible(false);
        lbl_newPassword.setVisible(false);
    }
    
    //Function to return the user to the login window
    @FXML
    private void returnToLogin(MouseEvent event) throws IOException {
        MainController main = new MainController();
        main.openNewWindow(loginURL, btn_returnToLogin);
    }

    //Shows the reports window
    @FXML
    private void btn_showReports(ActionEvent event) throws SQLException {
        vb_selectionDetails.setVisible(false);
        vb_searchDetails.setVisible(false);
        tbl_data.setVisible(false);
        tbl_subjectTable.setVisible(false);
        
        MainController main = new MainController();
        try{
            main.openNewWindow(reportsURL);
        }catch(IOException ex){
            System.out.println(ex);
        }
        
    }
    
    //Function to open the add user window when the button is selected
    @FXML
    private void addUser(MouseEvent event) {
        MainController main = new MainController();
        try{
            main.openNewWindow(addNewUserURL);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    //Clears the window for settings
    //Yet to be implemented.
    @FXML
    private void btn_showSettings(MouseEvent event) {
        vb_selectionDetails.setVisible(false);
        vb_searchDetails.setVisible(false);
        tbl_data.setVisible(false);
        tbl_subjectTable.setVisible(false);
    }
}