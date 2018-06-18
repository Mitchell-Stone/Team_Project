/*

Student Number: 7100438818

Name: Matteo Baldini

Date: 18/06/2018

Purpose: Controller ffor the reports window

Known Bugs: none

*/
package globalInterfaces;

import beans.Assessment;
import beans.Attendance;
import beans.CaseWorker;
import beans.Diploma;
import beans.Omni;
import beans.Student;
import beans.Submission;
import beans.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.AssessmentModel;
import model.AttendanceModel;
import model.CaseWorkerModel;
import model.DiplomaModel;
import model.MainModel;
import model.StudentModel;
import model.SubmissionsModel;

public class ReportsController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private VBox box1;
    @FXML
    private VBox box2;
    @FXML
    private VBox box3;
    @FXML
    private VBox box4;
    @FXML
    private Label selectionType;
    
    String type = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void fetchReport(ActionEvent event) throws SQLException {
        
        switch(type) {
        
            case "student":
                
                selectionType.setText("Student");
                
                clear();
                
                int ID = Integer.parseInt(id.getText());
                
                User user = new User();
        
                user.setTable("student");
                user.setColumn("studentID");
                user.setID(ID);

                ArrayList<String> currentStudent = MainModel.getUserByID(user);

                Student student = new Student();

                student.setDiplomaID(Integer.parseInt(currentStudent.get(1)));

                ArrayList<String> currentaDiploma = DiplomaModel.getDiplomaByStudent(student);

                ObservableList<Attendance> attendance = AttendanceModel.getAttendanceByStudentID(ID);

                ObservableList<Submission> submissions = SubmissionsModel.getSubmissionsByStudentID(ID);

                ObservableList<Assessment> assessmentList = AssessmentModel.getAssessmentsByDiplomaID(Integer.parseInt(currentStudent.get(1)));

                CaseWorker caseworker = new CaseWorker();

                caseworker.setID(Integer.parseInt(currentStudent.get(2)));

                ArrayList<String> currentCaseWorker = CaseWorkerModel.getCaseWorkerByID(caseworker);

                //////STUDENT INFO/////

                System.out.println(currentStudent);

                Text studentInfo = new Text("Student Information: \n");

                Text studentID = new Text("Student ID: " + currentStudent.get(0));
                Text firstName = new Text("First Name: " + currentStudent.get(3));
                Text lastName = new Text("Last Name: " + currentStudent.get(4));
                Text email = new Text("Email: " + currentStudent.get(5));
                Text industry = new Text("Industry Preference: " + currentaDiploma.get(2));

                Text endOfStudentInfo = new Text("\nEnd Of Student Information. \n\n");

                box1.getChildren().addAll(studentInfo, studentID, firstName, lastName, email, industry, endOfStudentInfo);

                /////DIPLOMA INFO/////

                Text diplomaInfo = new Text("Diploma Information: \n");

                Text diplomaID = new Text("Diploma ID: " + currentaDiploma.get(0));
                Text name = new Text("Name: " + currentaDiploma.get(1));
                Text industria = new Text("Industry: " + currentaDiploma.get(2));
                Text location = new Text("Location: " + currentaDiploma.get(3));
                Text degree = new Text("Degree: " + currentaDiploma.get(4));

                Text endOfDiplomaInfo = new Text("\nEnd Of Diploma Information. \n\n");

                box1.getChildren().addAll(diplomaInfo, diplomaID, name, industria, location, degree, endOfDiplomaInfo);

                ////ASSESSMENTS INFO////

                Text assessmentInfo = new Text("Assessments Information: \n");

                box2.getChildren().add(assessmentInfo);

                for (int i = 0; i < assessmentList.size(); i++) {

                    Text line = new Text(assessmentList.get(i).getTitle());

                    box2.getChildren().add(line);

                }

                Text endOfAssessmentInfo = new Text("\nEnd Of Assessment Information. \n\n");

                box2.getChildren().add(endOfAssessmentInfo);

                ///CASE WORKER INFO///

                Text caseWorkerInfo = new Text("CaseWorker Information: \n");

                Text caseworkerID = new Text("CaseWorkerID: " + currentCaseWorker.get(0));
                Text firstname = new Text("First Name: " + currentCaseWorker.get(1));
                Text lastname = new Text("Last Name: " + currentCaseWorker.get(2));
                Text mail = new Text("Email: " + currentCaseWorker.get(3));
                Text phonenumber = new Text("Phone: " + currentCaseWorker.get(6));

                Text endOfCaseWorkerInfo = new Text("\nEnd Of CaseWorker Information. \n\n");

                box3.getChildren().addAll(caseWorkerInfo, caseworkerID, firstname, lastname, mail, phonenumber, endOfCaseWorkerInfo);

                ///ADDITIONAL INFO///

                Text additionalInfo = new Text("Additional Information: \n");

                Text visitCount = new Text("Visits since start: " + attendance.size());

                Text dates = new Text("Dates: ");

                box3.getChildren().addAll(additionalInfo, visitCount, dates);

                for (int i = 0; i < attendance.size(); i++) {

                    Text date = new Text(attendance.get(i).getDate());

                    box3.getChildren().add(date);

                }
            
            break;
            
            case "diploma":
                
                clear();
                
                selectionType.setText("Diploma");
                
                int did = Integer.parseInt(id.getText());
                
                Omni omni = new Omni();
                
                omni.setTable("diploma");
                omni.setColumn("diplomaID");
                
                if ("".equals(id.getText())) {
                    System.out.println("Text 3 is empty.");
                    } else {
                        try {
                        int number = Integer.parseInt(id.getText());
                        omni.setInputType(1);
                        omni.setNumber(number);
                    } catch (Exception e) {
                        String word = id.getText();
                        omni.setInputType(2);
                        omni.setWord(word);
                    }
                }
                
                ObservableList<Omni> diploma = MainModel.Quaeres(omni);
                
                Text diplomainfo = new Text("Diploma Information: \n");

                Text diplomaid = new Text("Diploma ID: " + diploma.get(0).getDiplomaID());
                Text diplomaname = new Text("Name: " + diploma.get(0).getName());
                Text diplomaindustria = new Text("Industry: " + diploma.get(0).getIndustry());
                Text diplomalocation = new Text("Location: " + diploma.get(0).getLocation());
                Text diplomadegree = new Text("Degree: " + diploma.get(0).getDegree());

                box1.getChildren().addAll(diplomainfo, diplomaid, diplomaname, diplomaindustria, diplomalocation, diplomadegree);
                
                omni.setTable("student");
                omni.setColumn("diplomaID");
                
                if ("".equals(id.getText())) {
                    System.out.println("Text 3 is empty.");
                    } else {
                        try {
                        int number = Integer.parseInt(id.getText());
                        omni.setInputType(1);
                        omni.setNumber(number);
                    } catch (Exception e) {
                        String word = id.getText();
                        omni.setInputType(2);
                        omni.setWord(word);
                    }
                }
                
                ObservableList<Omni> students = MainModel.Quaeres(omni);
                
                Text header = new Text("Students currently assigned:");
                box2.getChildren().add(header);
                
                for (int i = 0; i < students.size(); i++) {
                    
                    Text studente = new Text(" | " + Integer.toString(students.get(i).getStudentID()) + " | " + students.get(i).getFirstName() + " | " + students.get(i).getLastName());
                    
                    box2.getChildren().add(studente);
                    
                }
            
            break;
            
            case "general":
                
                selectionType.setText("General");
                
                clear();
                
                Text generaltitle = new Text("\nGeneral information.\n\n");
                
                ObservableList<Student> allstudents = StudentModel.getAllStudents();
                
                Text studenti = new Text("There are currently " + allstudents.size() + " students.\n");
                
                ObservableList<CaseWorker> allcw = CaseWorkerModel.getAllCaseWorkersS();
                
                Text cases = new Text("There are currently " + allcw.size() + " employees.\n");
                
                ObservableList<Diploma> diplomi = DiplomaModel.getAllDiplomasS();
                
                Text dipnames = new Text("Currently we offer "+ diplomi.size() + " diplomas.\n");
                
                box1.getChildren().addAll(generaltitle, studenti, cases, dipnames);
                
                for (int i = 0; i < diplomi.size(); i++) {
                    
                    Text text = new Text(diplomi.get(i).getDiplomaName());
                    
                    box1.getChildren().add(text);
                    
                }
            
            break;
            
            default:
            
                System.out.println("ERROR");
        
        }
        
    }

    @FXML
    private void student(MouseEvent event) {
        type = "student";
        selectionType.setText("Student");
    }

    @FXML
    private void general(MouseEvent event) {
        type = "general";
        selectionType.setText("General");
    }

    @FXML
    private void diploma(MouseEvent event) {
        type = "diploma";
        selectionType.setText("Diploma");
    }
    
    //clears the vboxs
    
    private void clear() {
    
        box1.getChildren().clear();
        box2.getChildren().clear();
        box3.getChildren().clear();
        box4.getChildren().clear();
    
    }
    
}
