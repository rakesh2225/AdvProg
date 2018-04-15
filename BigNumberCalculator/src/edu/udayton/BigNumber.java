/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

/**
 *
 * @author rakesh
 */
public class BigNumber {
    
    public DLL digits;
    
    private int decimalPointPosition = -1;
    
    public BigNumber(String s) {        
        digits = new OperatorService().getDLL(s);
    }
    
    public BigNumber add(BigNumber num) {
        //return new OperatorService().getDLL(num);
        return null;
    }
    
    public BigNumber sub(BigNumber n) {
        return null;
    }
    
    public BigNumber mul(BigNumber n) {
        return null;
    }
    
    
}
