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
        if ((this.isPositive && num.isPositive)
                || (!this.isPositive && !num.isPositive)) {
            String result = null;
            if (this.decimalPointPosition > num.decimalPointPosition) {
                result = addBigNumbers(this, num);
            } else {
                result = addBigNumbers(num, this);
            }
            if (!this.isPositive && !num.isPositive) {
                result += "-";
            }                
            return new BigNumber(result);
        }
        return null;
    }
    
    private String addBigNumbers(BigNumber num1, BigNumber num2) {
        int dec1 = num1.decimalPointPosition < 0 ? 0 : num1.decimalPointPosition;
        int dec2 = num2.decimalPointPosition < 0 ? 0 : num2.decimalPointPosition;
        int counter = dec1 - dec2;
        int carry = 0;
        String result = "";
        Node num1Node = num1.digits.getFirst();
        Node num2Node = num2.digits.getFirst();
        while (counter-- > 0) {
            result += String.valueOf(num1Node.ele);
            num1Node = num1Node.next;
        }
        while (num1Node != null && num2Node != null
                && (!String.valueOf(num1Node.ele).equals("-"))
                && (!String.valueOf(num2Node.ele).equals("-"))) {
            
            if ((String.valueOf(num1Node.ele)).equals(".")) {
                result += ".";
                num1Node = num1Node.next;
                num2Node = num2Node.next;
                continue;
            }
            int val =Integer.parseInt(String.valueOf(num1Node.ele)) + Integer.parseInt(String.valueOf(num2Node.ele)) + carry;
            result += val % 10;
            carry = val / 10;
            num1Node = num1Node.next;
            num2Node = num2Node.next;
        }
        if (num1Node == null && num2Node != null) {
            while (num2Node != null && !String.valueOf(num2Node.ele).equals("-")) {
                int val = Integer.parseInt(String.valueOf(num2Node.ele)) + carry;
                result += val % 10;
                carry = val / 10;
                num2Node = num2Node.next;
            }
        } else if (num2Node == null && num1Node != null) {
           while (num1Node != null && !String.valueOf(num1Node.ele).equals("-")) {
                int val = Integer.parseInt(String.valueOf(num1Node.ele)) + carry;
                result += val % 10;
                carry = val / 10;
                num1Node = num1Node.next;
            }
        }
        if (carry > 0) {
            result += carry;
        }
        return result;
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
    
    public String toBigString() {
        String dll = "";
        for (Node cur = digits.getFirst(); cur != null; cur = cur.next) 
            dll += cur.ele;
        return dll;
    }
    
    
    
}
