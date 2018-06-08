/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Administrator;
import beans.Assessment;
import beans.Student;
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
    
}
