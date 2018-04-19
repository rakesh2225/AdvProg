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
        isPositive = !s.contains("-");        
        if (!s.contains(".")) {
            s += ".0";
        }
        numLen = isPositive ? s.length() : s.length() - 1;
        decimalPointPosition = numLen - (isPositive ? s.indexOf(".") : s.indexOf(".") - 1);
        digits = new OperatorService().getDLL(s.replace('-', ' ').trim());
    }
    
    //Used only for multiplication
    public BigNumber(String s, boolean reverse) {
        if (reverse) {
            digits = new OperatorService().getDLL(new StringBuilder(s).reverse().toString());    
        } else {
            digits = new OperatorService().getDLL(s);    
        }
        numLen = s.length();
    }

    public BigNumber add(BigNumber num) {
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

    private String addBigNumbers(BigNumber num1, BigNumber num2) {
        int dec1 = num1.decimalPointPosition <= 0 ? 0 : num1.decimalPointPosition;
        int dec2 = num2.decimalPointPosition <= 0 ? 0 : num2.decimalPointPosition;
        int counter = dec1 - dec2;
        int carry = 0;
        String result = "";
        Node num1Node = num1.digits.getFirst();
        Node num2Node = num2.digits.getFirst();
        while (counter-- > 0) {
            result += String.valueOf(num1Node.ele);
            num1Node = num1Node.next;
        }
        while (num1Node != null && num2Node != null) {
            if ((String.valueOf(num1Node.ele)).equals(".")) {
                result += ".";
                num1Node = num1Node.next;
                num2Node = num2Node.next;
                continue;
            }
            int val = Integer.parseInt(String.valueOf(num1Node.ele)) + Integer.parseInt(String.valueOf(num2Node.ele)) + carry;
            result += val % 10;
            carry = val / 10;
            num1Node = num1Node.next;
            num2Node = num2Node.next;
        }
        if (num1Node == null && num2Node != null) {
            while (num2Node != null) {
                int val = Integer.parseInt(String.valueOf(num2Node.ele)) + carry;
                result += val % 10;
                carry = val / 10;
                num2Node = num2Node.next;
            }
        } else if (num2Node == null && num1Node != null) {
            while (num1Node != null) {
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

    private void formatNumForSub(BigNumber toMatch) {
        int dec1 = this.decimalPointPosition <= 0 ? 0 : this.decimalPointPosition;
        int dec2 = toMatch.decimalPointPosition <= 0 ? 0 : toMatch.decimalPointPosition;
        int diff = dec1 - dec2;
        if (diff < 0) {
            while (diff++ < 0) {
                Node node = new Node(0, this.digits.getFirst(), null);
                this.digits.setFirst(node);
                this.decimalPointPosition++;
            }
        }
        diff = (this.numLen - dec1) - (toMatch.numLen - dec2);
        if (diff < 0) {
            while (diff++ < 0) {                
                Node node = new Node(0, null, this.digits.getLast());
                this.digits.getLast().next = node;
                this.digits.setLast(node);
            }
        }
    }

    private void ninesCompliment() {
        for (Node node = this.digits.getFirst(); node != null; node = node.next) {
            if (!".".equals(String.valueOf(node.ele))) {
                node.ele = 9 - Integer.parseInt(String.valueOf(node.ele));
            }
        }
    }

    private BigNumber tensCompliment() {
        this.digits.setFirst(this.digits.getFirst().next);
        this.digits.getFirst().prev = null;
        int carry = 1;
        String result = "";
        for (Node node = this.digits.getLast(); node != null; node = node.prev) {
            if (!".".equals(String.valueOf(node.ele))) {
                int val = Integer.parseInt(String.valueOf(node.ele)) + carry;
                result += val % 10;
                carry = val / 10;
            } else {
                result += ".";
            }
        }
        if (carry > 0) {
            result += carry;
        }
        return new BigNumber(result);
    }

    public BigNumber sub(BigNumber num) {
        if (this.isEqualTo(num)) {
            return new BigNumber("0.0");
        }
        boolean pos = this.compare(num, false, true) > 0;
        num.formatNumForSub(this);
        num.ninesCompliment();
        BigNumber res = this.add(num);
        if (res.numLen != this.numLen && res.numLen != num.numLen) {
            res = res.tensCompliment();
        } else {
            res.ninesCompliment();
        }
        //System.out.println(res.toBigString(false));
        res.isPositive = pos;
        return res;
    }

    public BigNumber mul(BigNumber num) {
        int i = 0;
        int maxLen = 0;
        BigNumber[] addNums = new BigNumber[num.numLen];
        for (Node node = num.digits.getFirst(); node != null; node = node.next) {
            if (".".equals(String.valueOf(node.ele))) {
                continue;
            }
            int carry = 0;
            String result = "";
            int zeros = i;
            for (Node cur = this.digits.getFirst(); cur != null; cur = cur.next) {
                if (".".equals(String.valueOf(cur.ele))) {
                    continue;
                }
                while (zeros-- > 0) {
                    result += 0;
                }
                int val = (Integer.parseInt(String.valueOf(cur.ele)) * Integer.parseInt(String.valueOf(node.ele))) + carry;
                result += val % 10;
                carry = val / 10;
            }
            if (carry > 0) {
                result += carry;
            }
            if (maxLen < result.length()) {
                maxLen = result.length();
            }
            //System.out.println("add: " + result);
            addNums[i] = new BigNumber(result, true);
            i++;
        }
        int dec = (this.decimalPointPosition <= 0 ? 0 : this.decimalPointPosition - 1) + (num.decimalPointPosition <= 0 ? 0 : num.decimalPointPosition - 1);
        BigNumber result = addAllNums(addNums, maxLen);
        result.digits.insertAtIndex(new Node("."), maxLen - dec);
        if ((!this.isPositive && num.isPositive)
                || (this.isPositive && !num.isPositive)) {
            result.isPositive = false;
        }
        return result;
    }
    
    private BigNumber addAllNums(BigNumber[]addNums, int maxLen) {
        int carry = 0;
        String result = "";
        for (int j = 0; j < maxLen; j++) {
            int sum = 0;
            for (BigNumber addNum : addNums) {
                if (addNum == null || addNum.digits == null || addNum.digits.getFirst() == null) {
                    continue;
                }
                Node node = addNum.digits.getFirst();
                sum += (Integer.parseInt(String.valueOf(node.ele)) + carry);
                carry = 0;
                if (addNum.digits.getFirst() != null) {
                    addNum.digits.setFirst(addNum.digits.getFirst().next);
                }
            }
            result += sum % 10;
            carry = sum / 10;
        }
        if (carry > 0) {
            result += carry;
        } 
        return new BigNumber(result, false);
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
                && (num1LastNode.ele.equals(num2LastNode.ele))) {
            num1LastNode = num1LastNode.prev;
            num2LastNode = num2LastNode.prev;
        }
        return num1LastNode == null && num2LastNode == null;
    }

    public int compare(BigNumber num, boolean checkEqual, boolean ignoreSign) {
        if (checkEqual && this.isEqualTo(num)) {
            return 0;
        }
        if (ignoreSign) {
            return compareSameSign(num);
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
        }
        return -1;
    }

    private int getNegative() {
        if (!this.isPositive) {
            return 1;
        }
        return -1;
    }

    private int compareSameSign(BigNumber num) {
        Node num1Last = this.digits.getLast();
        Node num2Last = num.digits.getLast();
        int revNum1DecimalPointPos = this.numLen - (decimalPointPosition < 0 ? 0 : decimalPointPosition);
        int revNum2DecimalPointPos = num.numLen - (num.decimalPointPosition < 0 ? 0 : num.decimalPointPosition);
        if (revNum1DecimalPointPos > revNum2DecimalPointPos) {
            return getPositive();
        } else if (revNum1DecimalPointPos < revNum2DecimalPointPos) {
            return getNegative();
        } else {
            for (; num1Last != null && num2Last != null;) {
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

    public String toBigString(boolean preSign) {
        String dll = (!this.isPositive && preSign ? "-" : "");
        boolean trimZero = true;
        for (Node cur = digits.getFirst(); cur != null; cur = cur.next) {
            if ("0".equals(String.valueOf(cur.ele)) && cur.next != null && !".".equals(String.valueOf(cur.next.ele)) && trimZero) {
                continue;
            }
            dll += cur.ele;
            trimZero = false;
        }
        return dll + (!this.isPositive && !preSign ? "-" : "");
    }

}
