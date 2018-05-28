/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Student;
import java.util.ArrayList;

/**
 *
 * @author 0111005906
 */
public class StudentModel {
    
    
    
    public ArrayList<Student> getStudents(){
        ArrayList<Student> studentList = new ArrayList<Student>();
        
        //execute query to get all students
        
        return studentList;
    }
    
    public Student getStudent(int studentID)
    {
        Student student = null;
        //execute query to find student by id
        return student;
    }
}
