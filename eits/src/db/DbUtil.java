/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 
Student Number: 7100438818

Name: Matteo Baldini

Date: 10/06/18

Purpose: 

Known Bugs:
*/

public class DbUtil {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String ADDRESS = "jdbc:mysql://localhost/eits";
    
    public static Connection getConn(DbType db_type) throws SQLException {
    
        switch(db_type) {
        
            case MYSQL:
                
                return DriverManager.getConnection(ADDRESS, USERNAME, PASSWORD);
                
            default:
                return null;
        
        }
    
    }
    
}
