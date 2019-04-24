/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

import java.util.Scanner;

/**
 *
 * @author Team
 */
public class MixedFraction {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter mixed fraction 1 with space between each number");
        String[] mf1 = scan.nextLine().split(" ");
        System.out.println("Enter mixed fraction 2 with space between each number");
        String[] mf2 = scan.nextLine().split(" ");

        if (mf1.length != 3 || mf2.length != 3 || mf1[2].equals("0") || mf2[2].equals("0")) {
            System.out.println("Invalid Input, please provide valid mixed fraction");
            return;
        }

        try {
            Fraction fr1 = new Fraction(Integer.parseInt(mf1[0]), 1);
            Fraction fr2 = new Fraction(Integer.parseInt(mf1[1]), Integer.parseInt(mf1[2]));
            Fraction fr3 = new Fraction(Integer.parseInt(mf2[0]), 1);
            Fraction fr4 = new Fraction(Integer.parseInt(mf2[1]), Integer.parseInt(mf2[2]));

            new MixedFraction().performOperations(fr1.add(fr2), fr3.add(fr4));                          
        } catch (NumberFormatException ex) {
            System.out.println("Number parsing exception: " + ex.getMessage());
        }
    }

    /*
    * Performs Summation, Subtraction, Multiplication and Division operations
    * on two mixed fractions, reduces the resultant fraction and outputs in mixed fraction
    */
    private void performOperations(Fraction pf1, Fraction pf2) {
        Fraction addResult = pf1.add(pf2);
        System.out.print("Summation: ");
        addResult.convertToMixedFraction();

        Fraction subResult = pf1.sub(pf2);
        System.out.print("Subtraction: ");
        subResult.convertToMixedFraction();

        Fraction mulResult = pf1.mul(pf2);
        System.out.print("Multiplication : ");
        mulResult.convertToMixedFraction();

        Fraction divResult = pf1.divide(pf2);
        System.out.print("Division: ");
        divResult.convertToMixedFraction();
    }
}
