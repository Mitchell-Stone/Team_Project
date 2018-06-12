/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author mitch
 */
public class CaseWorkerModel {

    public ObservableList<CaseWorker> getAllCaseWorkers() throws SQLException {
        ObservableList<CaseWorker> caseWorkerList = FXCollections.observableArrayList();

        ResultSet rs = null;

        //execute query to get all case workers
        String query = "SELECT * FROM caseworker";

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            CaseWorker caseWorker;

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                caseWorker = new CaseWorker(rs.getInt("employeeID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
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

    public static boolean updateCaseWorker(CaseWorker bean) {

        String sql = "UPDATE caseworker SET firstName = ?, lastName = ?, email = ? WHERE employeeID = ?";

        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, bean.getFirstName());
            stmt.setString(2, bean.getLastName());
            stmt.setString(3, bean.getEmail());
            stmt.setInt(4, bean.getEmployeeID());

            int affected = stmt.executeUpdate();

            return affected == 1;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public static ArrayList<String> getCaseWorkerByID(CaseWorker caseworker) throws SQLException {
        ArrayList<String> currentCaseWorker = new ArrayList<>();

        String query = "SELECT * FROM caseworker WHERE employeeID = ?";

        ResultSet rs;

        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, caseworker.getID());
            rs = stmt.executeQuery();

            if (rs.next()) {
                currentCaseWorker.add(Integer.toString(rs.getInt("employeeID")));
                currentCaseWorker.add(rs.getString("firstName"));
                currentCaseWorker.add(rs.getString("lastName"));
                currentCaseWorker.add(rs.getString("email"));
                currentCaseWorker.add(rs.getString("password"));
                currentCaseWorker.add(rs.getString("specialty"));
                currentCaseWorker.add(Integer.toString(rs.getInt("phoneNumber")));
                currentCaseWorker.add(rs.getString("bio"));

                return currentCaseWorker;
            } else {
                System.out.println("No");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public ObservableList<CaseWorker> searchForCaseWorker(String searchValue, String searchType) throws SQLException {

        ObservableList<CaseWorker> employeeList = FXCollections.observableArrayList();

        CaseWorker employee = null;

        ResultSet rs = null;

        //execute query to get all students
        String sql = "SELECT * FROM caseworker WHERE $searchType LIKE ?";

        String query = sql.replace("$searchType", searchType);

        try {
            java.sql.Connection conn = DbUtil.getConn(DbType.MYSQL);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, "%" + searchValue + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                employee = new CaseWorker(rs.getInt("employeeID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return employeeList;
    }

    public static boolean assignCaseWorker(int studentID, int employeeID) throws SQLException {

        String sql = "UPDATE student SET employeeID = ? WHERE studentID = ?";

        ResultSet keys = null;

        try (
                Connection conn = DbUtil.getConn(DbType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, employeeID);
            stmt.setInt(2, studentID);

            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }

    }
    
    public static boolean updateCaseWorkerPassword(User bean) {
    
        String sql = "UPDATE caseworker SET password = ? WHERE studentID = ?";
        
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
}
