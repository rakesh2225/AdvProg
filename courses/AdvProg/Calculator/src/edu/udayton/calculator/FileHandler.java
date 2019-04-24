/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton.calculator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Arrays;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author rakesh
 */
public class FileHandler {
    
     /*
    * Write output to the file
    */
    public static void writeExpressionToFile(String expression) {
        try {
            Path path = Paths.get("calculatorHistory.txt");
            Files.write(path, Arrays.asList(expression), StandardCharsets.UTF_8,
                Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            System.out.println("Writing results to calculatorHistory.txt file complete");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
