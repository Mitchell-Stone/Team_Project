/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import db.DbType;
import db.DbUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 0111005906
 */
public class CoursesModel extends MainModel {
    
    //overloaded method to get course list by course ID in relation to student ID
    public ArrayList<Courses> getCourses(int studentID){
        ArrayList<Courses> coursesList = new ArrayList<>();


        //execute the SQL query here
        
        //This will look at the joining table for student and courses as you can have many courses to one student.
        



        return coursesList;
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
