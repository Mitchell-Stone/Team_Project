/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Administrator;
import beans.Assessment;
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
    
    public static ObservableList<String> getCoursesByDiplomaID(int id) {
        
        ObservableList<String> ggg = null;
        
        ResultSet rs;
    
        String sql = "SELECT * FROM diplomatocourses WHERE diplomaID = 1";
        
        try(
                    Connection conn = DbUtil.getConn(DbType.MYSQL);
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ) {
            
                    rs = stmt.executeQuery();
            
                while(rs.next()) {
                
                    ggg.add(Integer.toString(rs.getInt(1)));
                
                }
                
                return ggg;
            
        } catch (Exception e) {
                System.out.println(e);
                return null;
        }
    
    }
    
    public static ObservableList<Assessment> getCoursesByDiplomaIDTest(int id) throws SQLException{
        
        ObservableList<Assessment> coursesList = FXCollections.observableArrayList();
        
        ResultSet rs = null;
        
        String query = "SELECT courseID FROM diplomatocourses WHERE diplomaID = 1";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            Assessment assessment;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                assessment = new Assessment(rs.getInt("courseID"));
                coursesList.add(assessment);
                
                
                
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }  
        return coursesList;
    }
    
}
