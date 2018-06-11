
package Ubermodel;

import Beans.Omni;
import Uberdb.DbType;
import Uberdb.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
                
                int length = columnNames.size();
               
                switch(length) {
                
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
    
}