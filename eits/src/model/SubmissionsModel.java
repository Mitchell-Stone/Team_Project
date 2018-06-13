/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Assessment;
import beans.Attendance;
import beans.Submission;
import db.DbType;
import db.DbUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mitch
 */
public class SubmissionsModel {
     public ObservableList<Submission> getSubmissionsByStudentID( int studentID ) throws SQLException {

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
    
}
