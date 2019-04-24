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
public class HeightConverter {
    
    public static String getMeters(String ftIn) {
        if (ftIn.contains("-"))
            return "Invalid Input";
        String[] val = ftIn.toLowerCase().split("ft");
        double in = 0.0;
        double inches = Double.parseDouble(val[0].trim()) * 12;
        if (val.length > 1) in += Double.parseDouble(val[1].trim().toLowerCase().split("in")[0]);
        return new BigDecimal(String.valueOf((in + inches) / 39.37)).setScale(2, RoundingMode.HALF_UP).toString() + " m";
    }
    //
    public static String getFtIn(String meters) {
        if (meters.contains("-"))
            return "Invalid Input";
        double inches = Double.parseDouble(meters.toLowerCase().split("m")[0].trim()) * 39.3701;
        int ft = Double.valueOf(inches / 12).intValue(); 
        return ft +" ft " + new BigDecimal(String.valueOf(inches % 12)).setScale(2, RoundingMode.HALF_UP).toString() + " in";
    }
    
}
