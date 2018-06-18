/*

Student Number: 7100438818, 5103355915

Name: Matteo Baldini, Jake Smith

Date: 18/06/2018

Purpose: Assessment Model

Known Bugs: none

*/
package model;

import beans.Administrator;
import beans.Assessment;
import beans.Student;
import beans.Submission;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author Martin
 */
public class AssessmentModel {
    
    //gets assessments assigned to a single diploma
    
    public static ObservableList<Assessment> getAssessmentsByDiplomaID(int id) throws SQLException {
        
        ObservableList<Assessment> assessmentList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT diploma.diplomaID, diplomatocourses.courseID, assessment.assessmentID, assessment.title, assessment.description, assessment.date FROM (( diploma INNER JOIN diplomatocourses ON diploma.diplomaID = diplomatocourses.diplomaID) INNER JOIN assessment ON diplomatocourses.courseID = assessment.courseID) WHERE diploma.diplomaID = ?";

        try(
                java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, id);
            
            Assessment assessment;
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                assessment = new Assessment(rs.getInt("diplomaID"), rs.getInt("courseID"), rs.getInt("assessmentID"), rs.getString("title"), rs.getString("description"), rs.getString("date"));
                assessmentList.add(assessment);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        
        return assessmentList;
    
    }
    
    //checks the status of a single submission, return a different error code for each state
    
    public static int checkSubmission(Assessment bean) {
        
        ResultSet rs = null;
    
        String sql = "SELECT * FROM submissions WHERE assessmentID = ? AND courseID = ? AND studentID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setInt(1, bean.getAssessmentID());
            stmt.setInt(2, bean.getCourseID());
            stmt.setInt(3, bean.getStudentID());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("grade");
            } else {
                return 4000;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return 999;
        }
    
    }
    
    //creates new submission with the necessary IDs
    
    public static void submitAssessment(Assessment bean) {
    
        String sql = "INSERT INTO submissions (assessmentID, courseID, studentID) VALUES (?, ?, ?)";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setInt(1, bean.getAssessmentID());
            stmt.setInt(2, bean.getCourseID());
            stmt.setInt(3, bean.getStudentID());
            
            stmt.executeUpdate();
            
        } catch (Exception e) {
        }
    
    }
    
    //get single assessment by assessmentID
    
    public static ArrayList<String> getAssessmentByID(Submission submission) throws SQLException {

        ArrayList<String> assessmentName = new ArrayList<>();
        ResultSet rs;

        String query = "SELECT * FROM assessment WHERE assessmentID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, submission.getAssessmentID());
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                
                assessmentName.add(Integer.toString(rs.getInt("assessmentID")));
                assessmentName.add(Integer.toString(rs.getInt("courseID")));
                assessmentName.add(rs.getString("title"));
                assessmentName.add(rs.getString("description"));
                assessmentName.add(rs.getString("date"));
                
                return assessmentName;
                
            } else {
                System.out.println("Error in fetching the current assessment.");
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    
    }
   
    
}
