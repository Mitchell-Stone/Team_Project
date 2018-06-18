/*
Student Number: 5103355915, 0111005906, 7100438818, 6105091917

Name: Jake Smith, Mitch Stone, Matteo Baldini, Dion Bird

Date: 10/06/18

Purpose: Diploma Model

Known Bugs:
 */
package model;

import beans.Diploma;
import beans.Student;
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

public class DiplomaModel {

    //DB query that gets all the diplomas and adds them to an observable collection
    public ObservableList<Diploma> getAllDiplomas() throws SQLException {

        ObservableList<Diploma> diplomaList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all diplomas
        String query = "SELECT * FROM diploma";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            Diploma diploma;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                diploma = new Diploma(
                        rs.getInt("diplomaID"),
                        rs.getString("name"),
                        rs.getString("industry"),
                        rs.getString("location"),
                        rs.getString("degree"));
                diplomaList.add(diploma);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return diplomaList;
    }
    
    //Static db query that returns all diplomas for use in reports
    public static ObservableList<Diploma> getAllDiplomasS() throws SQLException {

        ObservableList<Diploma> diplomaList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all diplomas
        String query = "SELECT * FROM diploma";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            Diploma diploma;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                diploma = new Diploma(
                        rs.getInt("diplomaID"),
                        rs.getString("name"),
                        rs.getString("industry"),
                        rs.getString("location"),
                        rs.getString("degree"));
                diplomaList.add(diploma);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return diplomaList;
    }

    //Get all diplomas related to the foreign key in the student table
    public static ArrayList<String> getDiplomaByStudent(Student student) throws SQLException {

        ArrayList<String> studentDiploma = new ArrayList<>();
        ResultSet rs;

        String query = "SELECT * FROM diploma WHERE diplomaID = ?";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, student.getDiplomaID());
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                
                studentDiploma.add(Integer.toString(rs.getInt("diplomaID")));
                studentDiploma.add(rs.getString("name"));
                studentDiploma.add(rs.getString("industry"));
                studentDiploma.add(rs.getString("location"));
                studentDiploma.add(rs.getString("degree"));
                
                return studentDiploma;
                
            } else {
                System.out.println("Error in fetching the current diploma.");
                return null;
            }
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    
    }

    //database query that allows an admin to add a new diploma/course
    public static boolean addNewDiploma(Diploma bean) throws SQLException {

        String query = "INSERT INTO diploma (name, industry, location, degree) VALUES (?, ?, ?, ?)";

        ResultSet keys = null;

        try {
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, bean.getDiplomaName());
            stmt.setString(2, bean.getDiplomaIndustry());
            stmt.setString(3, bean.getDiplomaLocation());
            stmt.setString(4, bean.getCourseType());

            int affected = stmt.executeUpdate();

            return affected == 1;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
    }
    
    //gets all the industries related to a diploma/course
    public static ArrayList<String> getIndustries() {
        
        ArrayList<String> industries = new ArrayList();
        
        ResultSet rs;
    
        String query = "SELECT industry FROM diploma";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
            
                industries.add(rs.getString("industry"));
                
            }
            
            return industries;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    //gets all the ids related to a diploma/course
    public static ArrayList<String> getIDs() {
        
        ArrayList<String> industries = new ArrayList();
        
        ResultSet rs;
    
        String query = "SELECT diplomaID FROM diploma";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
            
                industries.add(Integer.toString(rs.getInt("diplomaID")));
                
            }
            
            return industries;
            
        } catch (Exception e) {
            return null;
        }
        
    }
    
    //database query to apply the diploma id to a student by id
    public static void assignDiplomaToStudent(int studentid, int diplomaid) {
    
        String query = "UPDATE student SET diplomaID = ? WHERE studentID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setInt(1, diplomaid);
            stmt.setInt(2, studentid);
            
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }

}
