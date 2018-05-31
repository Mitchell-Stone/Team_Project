/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Student;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mitch
 */
public class UserAccessModel extends MainModel  {
        public static boolean checkUserPass(Student bean) {
    
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
                bean.setFirstName(rs.getString("firstName"));
                bean.setLastName(rs.getString("lastName"));
                
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
        
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
            
            if(affected == 1) {
                return true;
            } else {
                return false;
            }
        
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
