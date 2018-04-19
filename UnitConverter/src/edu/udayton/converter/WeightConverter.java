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
public class WeightConverter {
     
    //Convert pounds and ounces to kilograms
    public static String getKilogram(String lbsOz) {
        if (lbsOz.contains("-")) {
            return "Invalid Input";
        }
        String[] val = lbsOz.toLowerCase().split("lbs");
        double oz = 0.0;
        double loz = Double.parseDouble(val[0].trim()) * 16;
        if (val.length > 1)  oz += Double.parseDouble(val[1].trim().toLowerCase().split("oz")[0]);
        return new BigDecimal(String.valueOf((loz + oz) / 35.274)).setScale(2, RoundingMode.HALF_UP).toString() + " kg";
    }
    
    //Converts kilograms to pounds and ounces
    public static String getLbsOz(String kilograms) {
         if (kilograms.contains("-")) {
            return "Invalid Input";
        }
        double oz = Double.parseDouble(kilograms.toLowerCase().split("kg")[0]) * 35.275;
        int lbs = Double.valueOf(oz / 16).intValue(); 
        String pounds = lbs == 0 ? "" : lbs + " lbs";
        return pounds + new BigDecimal(String.valueOf(oz % 16)).setScale(2, RoundingMode.HALF_UP).toString() + " oz";
    }
}
