/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uberdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mitch
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
