/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton.converter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author rakesh
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label1;
    
    @FXML
    private Label label2;
    
    @FXML
    private Label msg;
    
    @FXML
    private TextField text1;
    
    @FXML
    private TextField text2;
    
    @FXML
    private ToggleGroup groupRadio;
            
    private static final String TEMPERATURE = "Temperature";
    
    private static final String HEIGHT = "Height";
    
    private static final String WEIGHT = "Weight";
    
    private static final String INVALID_INPUT = "Invalid Input";
    
    private void clearText() {
        text1.setText("");
        text2.setText("");
    }
    
    @FXML
    private void handleTempRadioAction(ActionEvent event) {
        clearText();
        label1.setText("Centigrade");
        label2.setText("Farenheit");
    }
    
    @FXML
    private void handleWeightRadioAction(ActionEvent event) {
        clearText();
        label1.setText("Kilograms");
        label2.setText("Pounds Ounces");
    }
    
    @FXML
    private void handleHeightRadioAction(ActionEvent event) {
        clearText();
        label1.setText("Meters");
        label2.setText("Feet Inches");
    }
    
    public FXMLDocumentController() {
    
    }
    
    @FXML
    private void handleConvertBtnAction(ActionEvent event) {
        msg.setText("");
        String choice = ((RadioButton)groupRadio.getSelectedToggle()).getText();
        if (null != choice) 
            switch (choice) {
            case TEMPERATURE:
                handleTemperature();
                break;
            case WEIGHT:
                handleWeight();
                break;
            case HEIGHT:
                handleHeight();
                break;
            default:
                break;
        }   
    }
    
    //Manages temperature convert action
    private void handleTemperature() {
         if (!text1.getText().trim().equals("")) {
                String result = TemperatureConverter.getFahrenheit(text1.getText().trim());
                if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text2.setText("");
                } else {
                    text2.setText(result);
                }
            } else if(!text2.getText().trim().equals("")) {
                String result = TemperatureConverter.getCentigrade(text2.getText().trim());
                if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text1.setText("");
                } else {
                    text1.setText(result);
                }
            }
    }
    
    //Manages weight convert action
    private void handleWeight() {
        if (!text1.getText().trim().equals("")) {
                String result = WeightConverter.getLbsOz(text1.getText().trim());
                if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text2.setText("");
                } else {
                    text2.setText(result);
                }
            } else if(!text2.getText().trim().equals("")) {
                String result = WeightConverter.getKilogram(text2.getText().trim());
                if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text1.setText("");
                } else {
                    text1.setText(result);
                }
            }
    }
    
    //Manages height convert action
    private void handleHeight() {
        if (!text1.getText().trim().equals("")) {
                String result = HeightConverter.getFtIn(text1.getText().trim());
                if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text2.setText("");
                } else {
                    text2.setText(result);
                }
            } else if(!text2.getText().trim().equals("")) {
                 String result = HeightConverter.getMeters(text2.getText().trim());
                 if (INVALID_INPUT.equals(result)) {
                    msg.setText(result);
                    text1.setText("");
                } else {
                    text1.setText(result);
                }
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text1.setOnKeyPressed((KeyEvent ke) -> {
            text2.setText("");
        });
         text2.setOnKeyPressed((KeyEvent ke) -> {
             text1.setText("");
        });
    }    
    
}
