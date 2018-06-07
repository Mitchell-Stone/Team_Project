
package model;

import beans.Student;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel extends MainModel {
    
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
    
    public Student getStudent(Student bean) throws SQLException {
    
        Student student = null;
        
        ResultSet rs = null;
        
        String sql = "SELECT * FROM $tablename WHERE $column = ?";
        
        String query = sql.replace("$tablename", bean.getTable().replace("$column", bean.getColumn()));
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, bean.getID());
            
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                
                bean.setID(rs.getInt(1));
                bean.setFirstName(rs.getString("firstName"));
                bean.setLastName(rs.getString("lastName"));
                
                return student;
                
            } else {
                
                return null;
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        
    }
    
    public static boolean updateStudent(User bean) {
    
        String sql = "UPDATE student SET firstName = ?, lastName = ?, email = ? WHERE studentID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setString(1, bean.getFirstName());
            stmt.setString(2, bean.getLastName());
            stmt.setString(3, bean.getEmail());
            stmt.setInt(4, bean.getID());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean updateStudentPassword(User bean) {
    
        String sql = "UPDATE student SET password = ? WHERE studentID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setString(1, bean.getPassword());
            stmt.setInt(2, bean.getID());
            
            int affected = stmt.executeUpdate();
            
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public ObservableList<Student> searchForStudentsByID(int studentID) throws SQLException{
        
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        
        Student student = null;
        
        ResultSet rs = null;

        //execute query to get all students
        String query = "SELECT * FROM student WHERE studentID LIKE '%?%'";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            stmt.setInt(1, studentID);
            
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
}
