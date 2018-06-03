
package model;

import beans.CaseWorker;
import beans.Student;
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

public class MainModel {
    
    public static boolean openClose(boolean visible) {
        boolean visibility;
        if (visible) {
            visibility = false;
        } else {
            visibility = true;
        }
        return visibility;
    }
    
    public static boolean addNewUser(User bean) throws SQLException{
    
        String sql = "INSERT INTO $tablename (email, password, firstName, lastName) VALUES (?, ?, ?, ?)";
        
        String query = sql.replace("$tablename", bean.getTable());
        
        ResultSet keys = null;
        
        try{
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
            stmt.setString(1, bean.getEmail());
            stmt.setString(2, bean.getPassword());
            stmt.setString(3, bean.getFirstName());
            stmt.setString(4, bean.getLastName());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
        
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if(keys != null) {
                keys.close();
            }
        }
    }
    
    
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
    
    public ObservableList<CaseWorker> getAllCaseWorkers() throws SQLException{
        ObservableList<CaseWorker> caseWorkerList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all case workers
        String query = "SELECT * FROM caseworker";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            CaseWorker caseWorker;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                caseWorker = new CaseWorker(rs.getInt("employeeID"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                caseWorkerList.add(caseWorker);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }  
        return caseWorkerList;
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