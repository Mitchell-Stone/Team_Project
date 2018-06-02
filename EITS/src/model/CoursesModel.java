/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Courses;
import java.util.ArrayList;

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
        
        
}
