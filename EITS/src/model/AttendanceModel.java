/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Attendance;
import beans.Student;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Martin
 */
public class AttendanceModel {
    
    public static void logAttendance(int id) throws SQLException {
    
            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

            String query = "INSERT INTO attendance (date, studentID, courseID) VALUES (?, ?, 1)";
            
            try(
                    Connection conn = DbUtil.getConn(DbType.MYSQL);
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ) {
                
                stmt.setDate(1, ourJavaDateObject);
                stmt.setInt(2, id);
                
                stmt.executeUpdate();
            
        } catch (Exception e) {
                System.out.println(e);
        }
    }
    
    public ObservableList<Attendance> getAttendanceByStudentID( int studentID ) throws SQLException {

        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM attendance WHERE studentID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentID);
            rs = stmt.executeQuery();

            Attendance attendance;

            while (rs.next()) {
                attendance = new Attendance(rs.getString("date"), rs.getInt("studentID"), rs.getInt("courseID"));

                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return attendanceList;
    }
    
}
