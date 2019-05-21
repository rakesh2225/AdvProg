package edu.udayton.arrays;

import java.util.Arrays;
import java.util.Collections;

public class ReverseArray {

    private void getPartialArray(char[] arr, int i, int j) {
        if (arr.length <= 0 || j < i) {
            return;
        }

        System.out.println("Hello");
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        new PrintArray().printCharArray(arr);
    }

    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'D', 'E'};
        new ReverseArray().getPartialArray(arr, 2,4);
    }
}
