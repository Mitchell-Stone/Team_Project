/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import beans.Submission;
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
                System.out.println("Error in fetching the current diploma.");
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    
    }
    
    public static boolean assignCourse(int studentID, int diplomaID) throws SQLException {
    
        String sql = "UPDATE student SET diplomaID = ? WHERE studentID = ?";
        
        ResultSet keys = null;
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
        
            stmt.setInt(1, diplomaID);
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
        
    public ObservableList<Courses> getCoursesByDiplomaID(int id) throws SQLException {
        
        ObservableList<Courses> list = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT diploma.diplomaID, diplomatocourses.courseID, courses.courseID, courses.name, courses.industry, courses.location, courses.numberOfHours, courses.finishingDegree "
                     + "FROM (( diploma INNER JOIN diplomatocourses ON diploma.diplomaID = diplomatocourses.diplomaID) INNER JOIN courses ON diplomatocourses.courseID = courses.courseID) WHERE diploma.diplomaID = ?";

        try(
                java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, id);
            
            Courses course;
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                course = new Courses(rs.getInt("diplomaID"), rs.getInt("courseID"), rs.getString("name"), rs.getString("industry"), rs.getString("location"), rs.getInt("numberOfHours"), rs.getInt("finishingDegree"));
                list.add(course);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }       
        return list;
    }
    
    public static boolean deleteCourse(int courseID){
        String sql = "DELETE FROM diploma WHERE diplomaID = ?";
               
        try{
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, courseID);
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

     public static ArrayList<String> getCourseByID(Submission submission) throws SQLException {

        ArrayList<String> subjectName = new ArrayList<>();
        ResultSet rs;

        String query = "SELECT * FROM courses WHERE courseID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, submission.getCourseID());
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                
                subjectName.add(Integer.toString(rs.getInt("courseID")));
                subjectName.add(rs.getString("name"));
                subjectName.add(rs.getString("industry"));
                subjectName.add(rs.getString("location"));
                subjectName.add(Integer.toString(rs.getInt("numberOfHours")));
                subjectName.add(Integer.toString(rs.getInt("finishingDegree")));
                
                return subjectName;
                
            } else {
                System.out.println("Error in fetching the current course.");
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    
    }
}
