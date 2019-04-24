package edu.udayton.arrays;

import java.util.ArrayList;
import java.util.List;

public class States {

    public static void main(String[] args) {
        States states = new States();
        //int[] st = new int[]{1, 0, 0, 0, 0, 1, 0, 0};
        int[] st1 = new int[]{1, 1, 1, 0, 1, 1, 1, 1};
        states.cellCompete(st1, 2);
    }
        private List<Integer> getNextDayStates(int[] states, int[] nextDayStates, int days) {
            if (days == 0) {
                List<Integer> res = new ArrayList<>();
                for (int i = 0; i < states.length; i++) {
                    res.add(states[i]);
                }
                return res;
            }
            for (int j = 0; j < states.length; j++) {
                if (j == 0) {
                    if (states[1] == 0) {
                        nextDayStates[0] = 0;
                    } else {
                        nextDayStates[0] = 1;
                    }
                } else if (j == states.length - 1) {
                    if (states[states.length - 2] == 0)  {
                        nextDayStates[j] = 0;
                    } else {
                        nextDayStates[j] = 1;
                    }
                } else {
                    nextDayStates[j] = states[j - 1] ^ states[j + 1];
                }
            }
            System.out.println("Next day states: ");
            for (int i = 0; i < nextDayStates.length; i++) {
                System.out.print(nextDayStates[i] + " ");
            }
            System.out.println("");
            return getNextDayStates(nextDayStates, states, --days);
        }

        // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
        public List<Integer> cellCompete(int[] states, int days)
        {
            if (days < 0) {
                throw new IllegalArgumentException("Invalid number of days");
            }
            List<Integer> res = new ArrayList<>();
            if (days == 0) {
                for (int i = 0; i < states.length; i++) {
                    System.out.print(states[i] + " ");
                    res.add(states[i]);
                }
                return res;
            }
            if (states.length < 2) {
                res.add(0);
                return res;
            }
            int[] nextDayStates = new int[states.length];
            res = getNextDayStates(states, nextDayStates, days);
            return res;
        }
        // METHOD SIGNATURE ENDS

}
