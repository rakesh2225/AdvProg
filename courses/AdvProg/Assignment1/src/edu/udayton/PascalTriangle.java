/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author rakesh
 */
public class PascalTriangle {
    
    //length of the larges number in the triangle
    private static int maxLen = 0;

    //fixed length of each block
    private static int blockLen = 0;

    //empty block for prefix at each level
    private static String emptyBlock = "";
    
    //Max level until the number goes beyong long limit.
    private int maxLevel = 0;
    
    public static void  main (String[] args)
    {
        Scanner  scan = new Scanner(System.in);
        System.out.println("Enter number of levels.");
        int level = scan.nextInt();
        if (level <= 0) {
            System.out.println("Enter level greater than zero.");
            return;
        }
        if (level > 14500) {
            System.out.println("Excessive levels will lead to Out of Memory problems, choose levels less than 10,000");
        }
        PascalTriangle pt =  new PascalTriangle();
        long[][] triangle = pt.preparePascalTriangle(level);
        pt.printPascalTriangle(triangle);
    }

    /*
    * Prepares a 2-dimensional array of pascal triangle.
    */
    private long[][] preparePascalTriangle(int level) {
          long[][] triangle = new long[level][level];
          for (int i = 0; i < level; i++) {
                for (int j = 0; j < i+1; j++) {
                    if (j == 0 || j == i) {
                        triangle[i][j] = 1;
                        continue;
                    }
                    try {
                        triangle[i][j] = Math.addExact(triangle[i - 1][j] , triangle[i - 1][j - 1]);
                    } catch (ArithmeticException ex) {
                        maxLevel = i + 1;
                        System.out.println("Exeption handled for: " + ex.getMessage());
                        return triangle;
                    }
                    if (maxLen <  String.valueOf(triangle[i][j]).length())
                        maxLen =  String.valueOf(triangle[i][j]).length();
                }
          }
          maxLevel = level;
          return triangle;
    }
    
    /*
    * Controls printing of 2-dimensional array in required format.
    */
    private void printPascalTriangle(long[][] triangle) {
        if (maxLen % 2 == 1) maxLen += 1;
        blockLen = maxLen + 2;
        prepareEmptyBlock();
        StringBuilder builder = new StringBuilder();
        outer: for (int i = 0; i < maxLevel; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {                                     
                    builder.append(getPrefix(maxLevel - i - 1));
                }
                if (triangle[i][j] == 0) {
                    break outer;
                }
                builder.append(formatNum(String.valueOf(triangle[i][j])));                
            }
            builder.append("\n");   
          }
          writeFile(builder);
    }
    
    /*
    * Write output to the file
    */
    public static void writeFile(StringBuilder builder) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("pascalTriangle.txt"));
            out.write(builder.toString());
            System.out.println("Writing results to pascalTriangle.txt file complete");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
             if (out != null) {
                 try {
                    out.close();
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 }
             }
        }
    }
    
    /*
    * Uses empty block to prepare prefix spaces for each level.
    */
    private String getPrefix(int blocksNum) {
        String prefix = "";
        while (blocksNum-- > 0) {
            prefix += emptyBlock;
        }
        return prefix;
    }
    
    /*
    * Prepares empty block based on number block length.
    */
    private void prepareEmptyBlock() {
        int size = blockLen / 2; 
        while (size-- > 0) {
            emptyBlock += " ";
        }
    }
    
    /*
    * Format the number block
    */
    private String formatNum(String num) {
        int numLen = String.valueOf(num).length();
        String offset = getOffset(maxLen - numLen);
        String glitch = (maxLen - numLen) % 2 == 1 ? " " : "";
        return offset + num + offset + glitch;
    }
    
    /*
    * Get the spaces to place either side of the number in a block
    */
    private String getOffset(int spaces) {
        String offset = "";
        spaces /= 2;
        while (spaces-- > 0) {
            offset += " ";
        }
        return offset + " ";
    }
}