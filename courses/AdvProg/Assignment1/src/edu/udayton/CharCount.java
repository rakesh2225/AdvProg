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
public class CharCount {
    
    public static void main(String[] args) {
        System.out.println("Enter input string");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        //new CharCount().countChar(line.toCharArray());
        new CharCount().countChar(line.toCharArray());
    }
     /*
    * Counts number of characters in a given string
    * with worst case complexity of O(n2)
    * Prints alphabet, ".", " "with count and 
    * number of special characters in the string
    */
    private void countCharOrder(char[] input) {
        System.out.println("The frequency of each character:");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int count = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] == ch || input[i] == Character.toUpperCase(ch)) {
                    count++;
                }
            }
            if (count > 0) 
                System.out.println(ch + " : " + count);
        }
        int spaceCount = 0;
        int dotCount = 0;
        int othersCount = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' ') {
                spaceCount++;
            } else if (input[i] == '.') {
                dotCount++;
            } else if ((input[i] < 'A' || input[i] > 'z')
                    || (input[i] > 'Z' && input[i] < 'a')) {
                othersCount++;
            }
        }
        
        if (spaceCount > 0) System.out.println("  : " + spaceCount);
        if (dotCount > 0) System.out.println(". : " + dotCount);
        if (othersCount > 0) System.out.println("Special character(s): " + othersCount);
    }
    
    /*
    * Counts number of characters in a given string
    * with worst case complexity of O(n2)
    * Prints every character with count
    */
    private void countChar(char[] input) {
        int dupLength = 0;
        char[] dupArr = new char[input.length];
        System.out.println("The frequency of each character:");
        for (int i = 0; i < input.length; i++) {            
            if(isDuplicate(input[i], dupArr)) {
                continue;
            } else {
                dupArr[dupLength++] = input[i];
            }
            int count = 1;
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    count++;
                }
            }
            System.out.println((char)input[i] + " - " + count);
        }
    }
    
    private boolean isDuplicate(char ch, char[] countedArr) {
        for (int i = 0 ; i < countedArr.length; i++) {
            if (countedArr[i] == ch) {
                return true;
            }
        }
        return false;
    }
    
}
