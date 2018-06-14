/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Attendance;
import beans.Submission;
import beans.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;



/**
 *
 * @author Martin
 */
public class ReportsModel {
    
    public static void getStudentReport(int userid) throws SQLException {
        
        User user = new User();
        
        user.setTable("student");
        user.setColumn("studentID");
        user.setID(userid);
    
        //////STUDENT INFO/////
    
        ArrayList<String> currentStudent = MainModel.getUserByID(user);
        
        System.out.println(currentStudent);
        
        ObservableList<Attendance> attendance = AttendanceModel.getAttendanceByStudentID(8);
        
        int visitCount = attendance.size();
        
        System.out.println("The student attended lesson " + visitCount + " times.");
        
        for (int i = 0; i < attendance.size(); i++) {
            
            System.out.println(attendance.get(i).getDate());
            
        }
        
        ObservableList<Submission> submissions = SubmissionsModel.getSubmissionsByStudentID(8);
        
        int numberOfSubmissions = submissions.size();
        
        System.out.println(numberOfSubmissions);

        /////DIPLOMA INFO/////

        ////ASSESSMENTS INFO////

        ///ADDITIONAL INFO///
    
    }
    
    
    
}
