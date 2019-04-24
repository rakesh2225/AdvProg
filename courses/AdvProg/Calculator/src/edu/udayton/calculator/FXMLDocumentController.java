/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author rakesh
 */
public class FXMLDocumentController implements Initializable {
    
    private double result = 0.0;
        
    private static final String SPACE = " ";
    
    private boolean digitEntered = false;
    
    private boolean operatorEntered = false;
    
    @FXML
    private TextField text;
    
    @FXML
    private Label label;
    
    //Enters digit onto text field based on conditions
    private void enterDigit(String digit) {
        digitEntered = true;
        if ("0".equals(text.getText())) {
            text.setText("");
        }
        if (operatorEntered) {
             text.setText(digit);
        } else {
             text.setText(text.getText() + digit);
        }
        operatorEntered = false;
    }
    
    @FXML
    private void handleZeroBtnAction(ActionEvent event) {
        enterDigit("0");
    }
    
    @FXML
    private void handleOneBtnAction(ActionEvent event) {
        enterDigit("1");
    }
    
    @FXML
    private void handleTwoBtnAction(ActionEvent event) {
        enterDigit("2");
    }
    
    @FXML
    private void handleThreeBtnAction(ActionEvent event) {
        enterDigit("3");
    }
    
    @FXML
    private void handleFourBtnAction(ActionEvent event) {
         enterDigit("4");
    }
    
    @FXML
    private void handleFiveBtnAction(ActionEvent event) {
        enterDigit("5");
    }
    
    @FXML
    private void handleSixBtnAction(ActionEvent event) {
         enterDigit("6");
    }
    
    @FXML
    private void handleSevenBtnAction(ActionEvent event) {
         enterDigit("7");
    }
    
    @FXML
    private void handleEightBtnAction(ActionEvent event) {
         enterDigit("8");
    }    
    
    @FXML
    private void handleNineBtnAction(ActionEvent event) {
         enterDigit("9");
    }    
    
    //Format and print value on the result box
    private String printValue(double result) {
        if (String.valueOf(result).split("\\.").length > 1 && Double.parseDouble(String.valueOf(result).split("\\.")[1]) == 0) {
            return String.valueOf(result).split("\\.")[0];
        } else {
            return String.valueOf(result);
        }
    }
    
    //Performs action based on operator
    private double performAction() {
        char action = label.getText().charAt(label.getText().length() - 1);
        if ("".equals(action)) {
            return result;
        }
        switch (action) {
                case '+' :  {
                    String expression = printValue(result) + " + " + printValue(Double.parseDouble(text.getText())) + " = ";
                    result += Double.parseDouble(text.getText());
                    FileHandler.writeExpressionToFile(expression += printValue(result));
                    break;
                }
                case '-' :  {
                    String expression = printValue(result) + " - " + printValue(Double.parseDouble(text.getText())) + " = ";
                    result -= Double.parseDouble(text.getText());
                    FileHandler.writeExpressionToFile(expression += printValue(result));
                    break;
                }
                case '/' : {
                    String expression = "";
                        if (result == 0 || "0".equals(text.getText())) {
                            result = 0;
                            break;
                        } else {
                            expression = printValue(result) + " / " + printValue(Double.parseDouble(text.getText())) + " = ";
                            result /= Double.parseDouble(text.getText());
                        }
                        FileHandler.writeExpressionToFile(expression += printValue(result));
                        break;
                }
                case '*' : {
                    if (result == 0) {
                        result = Double.parseDouble(text.getText());
                        break;
                    }
                    String expression = printValue(result) + " * " + printValue(Double.parseDouble(text.getText())) + " = ";
                    result *= Double.parseDouble(text.getText());
                    FileHandler.writeExpressionToFile(expression += printValue(result));
                    break;
                }
        }
        return result;
    }
    
    private void setLabelText(String val) {
        if (val.length() > 25) {
            val = val.substring(val.length() - 25, val.length());
        }
        label.setText(val);
    }
    
    private String getTextLabel() {
        if (text.getText().startsWith("-")) {
            return text.getText().substring(1, text.getText().length());
        }
        return text.getText();
    }
    
    //Manages any operator button click
    private void handleOperatorClick(String operator) {
        operatorEntered = true;
        if (label.getText().isEmpty() && text.getText().isEmpty()) {
            setLabelText(" 0" + SPACE + operator);
            digitEntered = false;
            return;
        }
        if (digitEntered) {
            if (!label.getText().isEmpty()) {
                String textVal = getTextLabel();
                text.setText(printValue(performAction()));
                setLabelText(" " + label.getText() + " " + textVal + " " + operator);  
            } else {
                result += Double.parseDouble(text.getText());
                setLabelText(text.getText() + SPACE + operator);
            }
        } else {
            setLabelText(" " + (label.getText().substring(0, label.getText().length() - 1)).trim() + " " + operator);
        }
        digitEntered = false;
    }
    
    @FXML
    private void handleAddBtnAction(ActionEvent event) {
        handleOperatorClick("+");
    }
    
    @FXML
    private void handleSubBtnAction(ActionEvent event) {
        handleOperatorClick("-");
    }
    
    @FXML
    private void handleDivideBtnAction(ActionEvent event) {
        handleOperatorClick("/");
    }
    
    @FXML
    private void handleMulBtnAction(ActionEvent event) {
        handleOperatorClick("*");
    }    
            
    @FXML
    private void handleDotBtnAction(ActionEvent event) {
        if (text.getText().isEmpty()) {
            text.setText("0" + ".");
        }
        if (text.getText().endsWith(".")) {
            text.setText(text.getText().substring(0, text.getText().length() - 1) + ".");
        } else {
            text.setText(text.getText() + ".");
        }
    }
    
    @FXML
    private void handleEqualBtnAction(ActionEvent event) {
        if (label.getText().isEmpty()) {
            return;
        }
        if (digitEntered) {
            text.setText(printValue(performAction()));
            label.setText("");
        }
        result = 0;
        digitEntered = true;
        operatorEntered = true;
    }    
    
    @FXML
    private void handleClearBtnAction(ActionEvent event) {
        text.setText("");
        label.setText("");
        result = 0.0;
        operatorEntered = false;
        digitEntered = false;
    }
    
    @FXML
    private void handleBackspaceBtnAction(ActionEvent event) {
        if (text.getText().isEmpty()) {
            text.setText("");
            return;
        }
        text.setText(text.getText().substring(0, text.getText().length() - 1));
    }
    
    @FXML
    private void handleSignAltBtnAction(ActionEvent event) {
        if (text.getText().startsWith("-")) {
            text.setText(text.getText().substring(1, text.getText().length()));
        } else {
            text.setText("-" + text.getText());
        }
    }
    
    @FXML
    private void handlePercentBtnAction(ActionEvent event) {
        text.setText(printValue(result * (Double.parseDouble(text.getText()) / 100)));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
