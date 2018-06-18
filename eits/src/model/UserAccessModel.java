/*

Student Number: 7100438818

Name: Matteo Baldini

Date: 18/06/2018

Purpose: Methods related to the login screen

Known Bugs: none

*/
package model;

import beans.Student;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserAccessModel extends MainModel  {
    
    //Method to check email and password that returns a boolean value
    
        public static boolean checkUserPass(User bean) {
    
        String sql = "SELECT * FROM $tablename WHERE email = ? AND password = ?";
        
        String query = sql.replace("$tablename", bean.getTable());
        
        ResultSet rs;
        
        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setString(1, bean.getEmail());
            stmt.setString(2, bean.getPassword());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                bean.setID(rs.getInt(1));
                
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
        
        //Adds a student to the database, returns boolean for succes or failure
        
        public static boolean add(Student bean) throws Exception {
    
        String sql = "INSERT INTO $tablename (email, password, firstName, lastName) VALUES (?, ?, ?, ?)";
        
        String query = sql.replace("$tablename", bean.getTable());
        
        ResultSet keys = null;
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ) {
        
            stmt.setString(1, bean.getEmail());
            stmt.setString(2, bean.getPassword());
            stmt.setString(3, bean.getFirstName());
            stmt.setString(4, bean.getLastName());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
        
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if(keys != null) {
                keys.close();
            }
        }
        
    }

}
