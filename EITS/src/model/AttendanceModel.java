/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

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
    
}
