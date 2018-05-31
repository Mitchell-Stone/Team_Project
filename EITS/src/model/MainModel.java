
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel {
    
    public ObservableList<Student> getAllStudents() throws SQLException{
        
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM student";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            Student student;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                student = new Student(rs.getInt("studentID"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                studentList.add(student);
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
    public static ArrayList<String> getUserByID(User user) {
        
        ArrayList<String> currentUser = new ArrayList<>();
    
        String sql = "SELECT * FROM $tablename WHERE $column = ?";
        
        String query = sql.replace("$tablename", "student").replace("$column", "studentID");
        
        ResultSet rs;
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, user.getID());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                currentUser.add(rs.getString("firstName"));
                currentUser.add(rs.getString("lastName"));
                currentUser.add(rs.getString("email"));
                currentUser.add(rs.getString("password"));
                //currentUser.add(rs.getString("industryPreference"));
                //currentUser.add(rs.getString("visitCount"));
                //currentUser.add(rs.getString("averageGrade"));
                //currentUser.add(rs.getString("assessmentsPassed"));
                
                return currentUser;
                
            } else {
                System.out.println("N");
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    
    }
}