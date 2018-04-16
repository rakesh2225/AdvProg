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
    
    public int numLen = 0;
    
    public boolean isPositive = true;
    
    private int decimalPointPosition = -1;
    
    public BigNumber(String s) {    
        numLen = s.length();
        isPositive = !s.startsWith("-");
        decimalPointPosition = s.length() - s.indexOf(".");
        digits = new OperatorService().getDLL(s);
    }
    
    public BigNumber add(BigNumber num) {
        return null;
    }
    
    public BigNumber sub(BigNumber n) {
        return null;
    }
    
    public BigNumber mul(BigNumber n) {
        return null;
    }
    
    public boolean isEqualTo(BigNumber num) {        
        if (this.numLen != num.numLen) {
            return false;
        } else if ((this.isPositive && !num.isPositive)
                || (!this.isPositive && num.isPositive)) {
            return false;
        }
        Node num1LastNode = this.digits.getLast();
        Node num2LastNode = num.digits.getLast();
        while ((num1LastNode != null && num2LastNode != null)
                && (num1LastNode.ele.equals(num2LastNode.ele)) ) {
            num1LastNode = num1LastNode.prev;
            num2LastNode = num2LastNode.prev;
        }
        return num1LastNode == null && num2LastNode == null;
    }
    
    public int compare(BigNumber num) {
        if (this.isEqualTo(num)) {
            return 0;
        }
        if (this.isPositive && !num.isPositive) {
            return 1;
        } else if (!this.isPositive && num.isPositive) {
            return -1;
        } else {
            return compareSameSign(num);
        }
    }
    
    private int getPositive() {
          if (this.isPositive) {
                return 1;
          } return -1;
    }

    private int getNegative() {
          if (!this.isPositive) {
                return 1;
          } return -1;
    }
    
    private int compareSameSign(BigNumber num) {
        Node num1Last = this.digits.getLast();
            Node num2Last = num.digits.getLast();
            int revNum1DecimalPointPos = this.numLen - decimalPointPosition < 0 ? 0 : decimalPointPosition;
            int revNum2DecimalPointPos = num.numLen - num.decimalPointPosition < 0 ? 0 : num.decimalPointPosition;
            if (revNum1DecimalPointPos > revNum2DecimalPointPos) {
                 return getPositive();
            } else if (revNum1DecimalPointPos < revNum2DecimalPointPos) {
                 return getNegative();
            } else {
                if (!this.isPositive) {
                    num1Last = num1Last.prev;
                    num2Last = num2Last.prev;
                }
                for (; num1Last != null && num2Last != null ;) {           
                    System.out.println("1: " + String.valueOf(num1Last.ele) + ", 2: " + String.valueOf(num2Last.ele));
                    if (String.valueOf(num1Last.ele).equals(String.valueOf(num2Last.ele))) {
                        num1Last = num1Last.prev;
                        num2Last = num2Last.prev;
                        continue;
                    }
                    if (Integer.parseInt(String.valueOf(num1Last.ele)) > Integer.parseInt(String.valueOf(num2Last.ele))) {
                        return getPositive();
                    } else if (Integer.parseInt(String.valueOf(num1Last.ele)) < Integer.parseInt(String.valueOf(num2Last.ele))) {
                        return getNegative();
                    }                    
                }
                if (num1Last == null && num2Last != null) {   //21.23 , 21.234
                    return getNegative();
                } else {
                    return getPositive();
                }
            }
    }
    
    public void printDLL() {
        for (Node cur = digits.getFirst(); cur != null; cur = cur.next) 
            System.out.print(" -> " + cur.ele);
        System.out.println("");
    }
    
}
