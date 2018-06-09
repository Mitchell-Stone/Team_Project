/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uberfunction1;

import Beans.Assessment;
import Beans.Omni;
import Ubermodel.AssessmentModel;
import Ubermodel.MainModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Martin
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt2;
    @FXML
    private TableView tbl1;
    
    ObservableList<Omni> tableItems;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        
        Omni omni = new Omni();
        
        omni.setTable(txt1.getText());
        omni.setColumn(txt2.getText());
        
        if ("".equals(txt3.getText())) {
            System.out.println("Text 3 is empty.");
            } else {
                try {
                int number = Integer.parseInt(txt3.getText());
                omni.setInputType(1);
                omni.setNumber(number);
            } catch (Exception e) {
                String word = txt3.getText();
                omni.setInputType(2);
                omni.setWord(word);
            }
        }
        
        tableItems = MainModel.Quaeres(omni);
        
        ArrayList<String> columnNames = omni.getColumnNames();
                
        ArrayList<String> columnTypes = omni.getColumnTypes();
        
        System.out.println(columnNames);
                
        System.out.println(columnTypes);
        
        tbl1.getColumns().clear();
        
        for (int i = 0; i < columnNames.size(); i++) {
            
            TableColumn column = new TableColumn(columnNames.get(i));
        
            tbl1.getColumns().addAll(column);
            
            column.setCellValueFactory(new PropertyValueFactory(columnNames.get(i)));

        }
        
        tbl1.setItems(tableItems);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("Start");
        
    }    
    
}
