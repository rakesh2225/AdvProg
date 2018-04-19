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
public class Fraction {
    
    private long num;
    private long den;

    public Fraction() {
        num = 1;
        den = 2;
    }
 
    public Fraction(long num, long den) {
        this.num = num;
        this.den = den;
    }
    
    /*
    * Adds two proper fractions
    */
    public Fraction add(Fraction fr) {
        return (new Fraction(((this.num * fr.den) + (this.den * fr.num)) , (this.den * fr.den))).reduce();
    }
    
    /*
    * Subtracts two proper fractions
    */
     public Fraction sub(Fraction fr) {
        return (new Fraction(((this.num * fr.den) - (this.den * fr.num)) , (this.den * fr.den))).reduce();
     }
    
     /*
     * Multiplies two proper fractions
     */
    public Fraction mul(Fraction fr) {
        return (new Fraction((this.num * fr.num), (this.den * fr.den))).reduce();
    }
    
    /*
    * Divides two proper fractions
    */
    public Fraction divide(Fraction fr) {
        return (new Fraction((this.num * fr.den), (this.den * fr.num))).reduce();
    }
    
    /*
    * prints the fraction to console
    */
    public void print() {
        System.out.println(this.num + "/" + this.den);
    }
    
    /*
    * Converts a proper fraction to mixed fraction.
    */
    public void convertToMixedFraction() {
        if (this.num == 0) {
            System.out.println("0");
            return;
        }
        long mod = this.num % this.den;
        long q = (this.num - mod) / this.den;
        System.out.println(q + "(" + mod + "/" + this.den + ")");
    }
    
    /*
    * Calculates greatest common devisor
    */
    public static long gcd(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }
    
    /*
    * Reduces fraction using gcd
    */
    public Fraction reduce() {
        long gcd = gcd(num, den);
        if (gcd == 0) {
            return this;
        }
        num = num / gcd;
        den = den / gcd;
        return this;
    }
}
        