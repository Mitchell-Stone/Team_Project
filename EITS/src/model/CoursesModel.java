/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 0111005906
 */
public class CoursesModel {
    
    //method to get all courses on the database
    public static ArrayList<Courses> getCourses(Courses bean) throws Exception{
        ArrayList<Courses> coursesList = new ArrayList<Courses>();

        String sql = "SELECT * FROM $tablename";
        String query = sql.replace("$tablename", bean.getTable());

        ResultSet keys;

        try(Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ){
            keys = stmt.executeQuery();
            
            Courses course;
            
            while(keys.next()){
                course = new Courses(keys.getInt("courseID"),keys.getString("name"),keys.getString("industry"),
                                     keys.getString("location"), keys.getInt("numberOfHours"), keys.getInt("finishingDegree"));
                coursesList.add(course);
            }
        }catch(SQLException ex) {
                System.err.println(ex);
                return null;
        }

    return coursesList;
    }
    
    //overloaded method to get course list by course ID in relation to student ID
    public ArrayList<Courses> getCourses(int studentID){
        ArrayList<Courses> coursesList = new ArrayList<Courses>();


        //execute the SQL query here
        
        //This will look at the joining table for student and courses as you can have many courses to one student.
        



        return coursesList;
    }
        
        
}
