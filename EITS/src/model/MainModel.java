
package model;

import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainModel {

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
    
    public static boolean updateUser (User bean) {
    
        String sql = "UPDATE $tablename SET firstName = ?, lastName = ?, email = ? WHERE $idType = ?";
        
        String query = sql.replace("$tablename", bean.getTable()).replace("$idType", bean.getIdType());
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
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
    
    public static boolean deleteSelection(User bean, String idType){
        String sql = "DELETE FROM $tablename WHERE $idType = ?";
        
        String query = sql.replace("$tablename", bean.getTable()).replace("$idType", idType);
        
        try{
            Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, bean.getID());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public static ArrayList<String> getUserByID(User user) {
        
        ArrayList<String> currentUser = new ArrayList<>();
    
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
                
                currentUser.add(Integer.toString(rs.getInt("studentID")));
                currentUser.add(Integer.toString(rs.getInt("diplomaID")));
                currentUser.add(Integer.toString(rs.getInt("employeeID")));
                currentUser.add(rs.getString("firstName"));
                currentUser.add(rs.getString("lastName"));
                currentUser.add(rs.getString("email"));
                currentUser.add(rs.getString("password"));
                currentUser.add(rs.getString("industryPreference"));
                currentUser.add(rs.getString("visitCount"));
                currentUser.add(rs.getString("averageGrade"));
                currentUser.add(rs.getString("assessmentsPassed"));
                
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