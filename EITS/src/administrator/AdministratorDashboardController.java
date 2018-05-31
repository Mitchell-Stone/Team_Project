/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import beans.Student;
import beans.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.MainModel;

/**
 * FXML Controller class
 *
 * @author 0111005906
 */
public class AdministratorDashboardController implements Initializable {

    @FXML
    private ListView<?> lv_data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showAllStudents(MouseEvent event) throws SQLException {
        System.out.println("You Clicked me dawg");
        
        try{
            MainModel model = new MainModel();
            //ArrayList<Student> list = model.getAllStudents();
            
            
            
            //System.out.println(list);
        }
        catch(NullPointerException ex){
            System.out.println("Null Pointer Exception");
        } 
    }
}
