package edu.udayton.arrays;

public class TrapRainWater {

    public static void main(String[] args) {
        int[] rain = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] rain = new int[]{0,1,0,2};
        //int[] rain = new int[]{2, 0, 2};
        System.out.println(new TrapRainWater().trap(rain));
    }

    public int trap(int[] height) {
        if (height.length < 1) {
            return 0;
        }
        int totalDrops = 0;
        int dropInDitch = 0;
        boolean ditchStart = false;
        int prevMaxHeight = height[0];
        for (int i = 1; i < height.length; i++) {
            if (prevMaxHeight > height[i]) {
                ditchStart = true;
                dropInDitch += prevMaxHeight - height[i];
                continue;
            }
            if (ditchStart && (prevMaxHeight < height[i] || (prevMaxHeight == height[i] && dropInDitch > 0))) {
                totalDrops += dropInDitch;
                System.out.println("Total : " + totalDrops + ", drops in ditch: " + dropInDitch);
                dropInDitch = 0;
                ditchStart = false;
            }
            if (prevMaxHeight < height[i] || dropInDitch == 0) {
                prevMaxHeight = height[i];
            }
        }
        return totalDrops;
    }
}
