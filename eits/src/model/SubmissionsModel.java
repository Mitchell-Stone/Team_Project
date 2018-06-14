/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Assessment;
import beans.Attendance;
import beans.Submission;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mitch
 */
public class SubmissionsModel {
     public static ObservableList<Submission> getSubmissionsByStudentID( int studentID ) throws SQLException {////////////////FOJAKE///////////////

        ObservableList<Submission> submissionsList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM submissions WHERE studentID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentID);
            rs = stmt.executeQuery();

            Submission submission;

            while (rs.next()) {
                submission = new Submission (rs.getInt("assessmentID"), rs.getInt("courseID"), rs.getInt("studentID"), rs.getInt("grade"));

                submissionsList.add(submission);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return submissionsList;
    }
     
      public static boolean updateGrade (int grade, int studentID) throws SQLException {

        String sql = "UPDATE submissions SET grade = ? WHERE studentID = ?";

        ResultSet keys = null;

        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, grade);
            stmt.setInt(2, studentID);

            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }

    }
    
}
