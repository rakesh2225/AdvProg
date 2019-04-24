/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author rakesh
 */
public class TemperatureConverter { 
    
    public static String getFahrenheit(String centigrade) {
        double centi = 0.0;
        String cen = centigrade.toLowerCase().split("c")[0];
        try {
            centi = Double.parseDouble(cen);
        } catch (NumberFormatException e) {
            return "Invalid Input";
        }        
        return new BigDecimal(String.valueOf((centi * 1.8) + 32)).setScale(1, RoundingMode.HALF_UP).toString() + " F";
    }
    
    public static String getCentigrade(String fahrenheit) {
        String far = fahrenheit.toLowerCase().split("f")[0];
        double faren = 0.0;
        try {
            faren = Double.parseDouble(far);
        } catch (NumberFormatException e) {
            return "Invalid Input";
        } 
        return new BigDecimal(String.valueOf((faren - 32) / 1.8)).setScale(1, RoundingMode.HALF_UP).toString() + " C";
    }
    
}
