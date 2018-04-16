/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
    private Label errMsg;
    
    private void validateInput() throws Exception {
        double dobNum1 = Double.parseDouble(txtNum1.getText());
        double dobNum2 = Double.parseDouble(txtNum2.getText());
        String num1 = String.valueOf(dobNum1);
        String num2 = String.valueOf(dobNum2);
        txtNum1.setText(num1);
        txtNum2.setText(num2);
    }
    
    private void setErrorMessage() {
        txtResult.setText("");
        errMsg.setText("Invalid Input");
    }
    
    private void clearErrorMessage() {
        errMsg.setText("");
    }
    
    private void updateLabelOperator(String opr) {
        lblOptr.setText(opr);
    }
    
    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            clearErrorMessage();
            updateLabelOperator("+");
            validateInput();
            BigNumber bigNum1 = new BigNumber(txtNum1.getText());
            //bigNum1.printDLL();
            //System.out.println("");
            BigNumber bigNum2 = new BigNumber(txtNum2.getText());
            //bigNum2.printDLL();
            //BigNumber result = bigNum1.add(bigNum2);
        } catch (Exception e) {
            setErrorMessage();
        }
    }
    
    @FXML
    private void handleSubButtonAction(ActionEvent event) {
        clearErrorMessage();
        updateLabelOperator("-");
        try {
            
        } catch (Exception e) {
            setErrorMessage();
        }        
    }
    
    @FXML
    private void handleMulButtonAction(ActionEvent event) {
        clearErrorMessage();
        updateLabelOperator("*");
        try {
            
        } catch (Exception e) {
            setErrorMessage();
        } 
    }
    
    @FXML
    private void handleGreaterButtonAction(ActionEvent event) throws Exception {
            clearErrorMessage();
            updateLabelOperator(">");
            try {
                validateInput();
                BigNumber bigNum1 = new BigNumber(txtNum1.getText());
                BigNumber bigNum2 = new BigNumber(txtNum2.getText());
                txtResult.setText(String.valueOf(bigNum1.compare(bigNum2) > 0).toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
                setErrorMessage();
            }
    }
    
    @FXML
    private void handleLessButtonAction(ActionEvent event) {
            clearErrorMessage();
            updateLabelOperator("<");
            try {
                validateInput();
                BigNumber bigNum1 = new BigNumber(txtNum1.getText());
                BigNumber bigNum2 = new BigNumber(txtNum2.getText());
                txtResult.setText(String.valueOf(bigNum1.compare(bigNum2) < 0).toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
                setErrorMessage();
            }
    }
    
    @FXML
    private void handleEqualButtonAction(ActionEvent event) throws Exception {
            clearErrorMessage();
            updateLabelOperator("==");
            try {
                validateInput();
                BigNumber bigNum1 = new BigNumber(txtNum1.getText());
                BigNumber bigNum2 = new BigNumber(txtNum2.getText());
                txtResult.setText(String.valueOf(bigNum1.compare(bigNum2) == 0).toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
                setErrorMessage();
            }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
