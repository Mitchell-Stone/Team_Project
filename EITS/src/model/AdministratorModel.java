/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Administrator;
import db.DbType;
import db.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 0111005906
 */
public class AdministratorModel {
    
    public ObservableList<Administrator> getAllAdministrators() throws SQLException{
        ObservableList<Administrator> administratorList = FXCollections.observableArrayList();
        
        ResultSet rs = null;

        //execute query to get all case workers
        String query = "SELECT * FROM admin";

        try{
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);           
            
            Administrator admin;
            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                admin = new Administrator(rs.getInt("adminID"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                administratorList.add(admin);
            }            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }  
        return administratorList;
    }
    
    public static boolean updateAdmin(Administrator bean) {
    
        String sql = "UPDATE admin SET firstName = ?, lastName = ?, email = ? WHERE adminID = ?";
        
        try(
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setString(1, bean.getFirstName());
            stmt.setString(2, bean.getLastName());
            stmt.setString(3, bean.getEmail());
            stmt.setInt(4, bean.getAdminID());
            
            int affected = stmt.executeUpdate();
            
            return affected == 1;
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    
    }
}
