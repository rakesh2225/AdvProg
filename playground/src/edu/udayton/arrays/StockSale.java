package edu.udayton.arrays;

public class StockSale {

    public static void main(String[] args) {
        int[] stocks = new int[]{1,2,3,4,5};
        //int[] stocks = new int[]{7,6,4,3,1};
        //int[] stocks = new int[]{7,1,5,3,6,4};
        System.out.println(new StockSale().maxProfit1(stocks));
    }

    public int maxProfit(int[] prices) {
        boolean bought = false;
        if (prices.length == 0) return 0;
        int j = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (!bought && (i + 1) < prices.length && prices[i] < prices[i + 1]) {
                bought = true;
                j = i;
                System.out.println("Bought for " + prices[i]);
            } else if (bought && (i == prices.length - 1 || ((i + 1) < prices.length && prices[i] > prices[i + 1]))) {
                bought = false;
                System.out.println("Sold for " + prices[i]);
                profit += (prices[i] - prices[j]);
            }
        }
        return profit;
    }

    public int maxProfit1(int []prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
