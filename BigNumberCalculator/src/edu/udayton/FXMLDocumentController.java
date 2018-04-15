/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author minaly
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblNum1;
    
    @FXML
    private Label lblNum2;
    
    @FXML
    private Label lblResult;
    
    @FXML
    private TextField txtNum1;
    
    @FXML
    private TextField txtNum2;
    
    @FXML
    private TextField txtResult;
    
    @FXML
    private Button btnComp;
    
    @FXML
    private Button btnQuit;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnSub;
    
    @FXML
    private Button btnMul;
    
    @FXML
    private Button btnGrt;
    
    @FXML
    private Button btnLess;
    
    @FXML
    private Button btnEqls;
    
    @FXML
    private Label lblOptr;
    
    @FXML
    private void handleComputeButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
       if (txtNum1.getText().equals("") || txtNum2.getText().equals("")) {
           return;
       }
       BigNumber bigNum1 = new BigNumber(txtNum1.getText());
       BigNumber bigNum2 = new BigNumber(txtNum2.getText());
        new OperatorService().printDLL(bigNum1.digits);
        new OperatorService().printDLL(bigNum2.digits);
    }
    
    @FXML
    private void handleSubButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleMulButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleGreaterButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleLessButtonAction(ActionEvent event) {
       
    }
    
    @FXML
    private void handleEqualButtonAction(ActionEvent event) {
       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
