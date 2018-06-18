
package model;
/* 
Student Number: 011005906, 7100438818

Name: Mitch Stone,  Matteo Baldini

Date: 10/06/18

Purpose: Non-Specific Databse Queries 

Known Bugs:
*/

import beans.Omni;
import beans.User;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import security.SecurityMethods;

public class MainModel {
    
    public static ObservableList<Omni> Quaeres(Omni bean) throws SQLException {
        
        ObservableList<Omni> result = FXCollections.observableArrayList();
    
        ResultSet rs = null;
        String query = "SELECT * FROM $table WHERE $column like ?";
        String sql = query.replace("$table", bean.getTable()).replace("$column", bean.getColumn());
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            int inputType = bean.getInputType();
            
            switch (inputType) {
                case 1:
                    stmt.setInt(1, bean.getNumber());
                    break;
                case 2:
                    stmt.setString(1, "%" + bean.getWord() +"%");
                    break;
                default:
                    System.out.println("What");
                    break;
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                //////////COLUMN NAMES/////////////
                
                ArrayList<String> columnNames = new ArrayList();
                
                ArrayList<String> columnTypes = new ArrayList();
                
                ResultSetMetaData rsmd = rs.getMetaData();
                
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    
                    columnNames.add(rsmd.getColumnName(i));
                    
                    columnTypes.add(rsmd.getColumnTypeName(i));
                    
                }
                
                
                
                /////////COLUMN VALUES///////////////
                
                Omni output = new Omni();
                
                int lenght = columnNames.size();
                
                switch(lenght) {
                
                    case 2:
                        
                        System.out.println("The table is diplomatocourses");
                        
                        output = new Omni(
                                rs.getInt(1),
                                rs.getInt(2));
                        
                    break;
                    
                    case 3:
                        
                        System.out.println("The table is attendance");
                        
                        output = new Omni(
                            rs.getString(1), 
                            rs.getInt(2), 
                            rs.getInt(3));
                        
                    break;
                    
                    case 4:
                        
                        System.out.println("The table is submissions");
                        
                        output = new Omni(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getInt(3), 
                        rs.getInt(4));
                        
                    break;
                    
                    case 5:
                        
                        if ("INT".equals(columnTypes.get(1))) {
                            System.out.println("The table is assessment");
                            
                            output = new Omni(
                            rs.getInt(1), 
                            rs.getInt(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getString(5));
                            
                        } else {
                            System.out.println("The table is diploma");
                            
                            output = new Omni(
                            rs.getInt(1), 
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getString(5));
                            
                        }
                        
                    break;
                    
                    case 6:
                        
                        if ("INT".equals(columnTypes.get(4))) {
                            System.out.println("The table is courses");
                            
                            output = new Omni(
                            rs.getInt(1), 
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getInt(5), 
                            rs.getInt(6));
                            
                        } else {
                            System.out.println("The table is admin");
                            
                            output = new Omni(
                            rs.getInt(1), 
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getString(5), 
                            rs.getString(6));
                            
                        }
                        
                    break;
                    
                    case 8:
                        
                        System.out.println("The table is caseworker");
                        
                        output = new Omni(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getString(8));
                        
                    break;
                    
                    case 11:
                        
                        System.out.println("The table is students");
                        
                        output = new Omni(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getInt(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7),
                        rs.getInt(8), 
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11));
                        
                    break;
                    
                    default:
                        
                        System.out.println("What");
                
                }
                
                bean.setColumnNames(columnNames);
                bean.setColumnTypes(columnTypes);
                
                result.add(output);
                
                System.out.println(output);
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        return result;
    
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
            
        } catch (SQLException e) {
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
    
    public static boolean updatePassword(User bean) {
    
        String sql = "UPDATE $tablename SET password = ? WHERE $idType = ?";
        String query = sql.replace("$tablename", bean.getTable()).replace("$idType", bean.getIDType());
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);
                ) {
            
            stmt.setString(1, SecurityMethods.getHash(bean.getPassword()));
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
}