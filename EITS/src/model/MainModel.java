
package model;

import beans.Student;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class MainModel {
    
    public ArrayList<Student> getAllStudents() throws SQLException{
        
        ArrayList<Student> studentList = new ArrayList<Student>();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM student";

        try{
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            Student student;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                student = new Student(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                studentList.add(student);
            }            
        } catch (Exception e) {
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
