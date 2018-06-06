/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 0111005906
 */
public class CoursesModel extends MainModel {
    
    public static ArrayList<String> getDiplomaByID(User user) {
        
        ArrayList<String> currentDiploma = new ArrayList<>();
    
        String sql = "SELECT * FROM $tablename WHERE $column = ?";
        
        String query = sql.replace("$tablename", user.getTable()).replace("$column", user.getColumn());
        
        ResultSet rs;
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, user.getID());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                currentDiploma.add(Integer.toString(rs.getInt("diplomaID")));
                currentDiploma.add(rs.getString("name"));
                currentDiploma.add(rs.getString("industry"));
                currentDiploma.add(rs.getString("location"));
                currentDiploma.add(rs.getString("degree"));
                
                return currentDiploma;
                
            } else {
                System.out.println("N");
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    
    }
    
    public static boolean assignCourse(int studentID, int courseID) throws SQLException {
    
        String sql = "UPDATE student SET courseID = ? WHERE studentID = ?";
        
        ResultSet keys = null;
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
        
            stmt.setInt(1, courseID);
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
            if(keys != null) {
                keys.close();
            }
        }
    
    }  
    
    public ObservableList<Courses> getAllCourses() throws SQLException{
        
        ObservableList<Courses> studentList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM courses";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            Courses course;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                course = new Courses(
                        rs.getInt("courseID"), 
                        rs.getString("name"), 
                        rs.getString("location"), 
                        rs.getString("industry"), 
                        rs.getInt("numberOfHours"), 
                        rs.getInt("finishingDegree"));
                studentList.add(course);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }  
        return studentList;
    }
        
        
}
