/*

Question: Best Time to Buy and Sell Stock
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 */

/*
Link: https://www.youtube.com/watch?v=4YjEHmw1MX0&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=30
Question: Buy And Sell Stocks - One Transaction Allowed
1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed a single transaction.
 */

import java.util.Scanner;

public class _6_BestTimeToBuyAndSellStock {

    public static int getMaximumProfit1 (int[] prices){

        int leastSoFar = Integer.MAX_VALUE;
        int overAllProfit = 0;
        int profitIfSoldToday = 0;

        for (int i = 0; i < prices.length; i ++){
            if (prices[i] < leastSoFar){
                leastSoFar = prices[i];
            }

            profitIfSoldToday = prices[i] - leastSoFar;

            if (profitIfSoldToday > overAllProfit){
                overAllProfit = profitIfSoldToday;
            }
        }
        return overAllProfit;
    }

/*
Link: https://www.youtube.com/watch?v=HWJ9kIPpzXs&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=31
Question: Buy And Sell Stocks - Infinite Transactions Allowed
You have been given stock values/prices for N number of days. Every i-th day signifies the price of a stock on that day. Your task is to find the maximum profit which you can make by buying and selling the stocks.
Note: You may make as many transactions as you want but can not have more than one transaction at a time i.e, if you have the stock, you need to sell it first, and then only you can buy it again.
 */

    public static int getMaximumProfit2 (int[] prices){

        int buyingDate =  0;
        int sellingDate =  0;
        int profit =  0;

        for (int i = 1; i < prices.length; i++){

            // During increment
            if (prices[i] >= prices[i - 1]){
                sellingDate++;
            }
            // During decrement
            else {
                // First collect stockProfit
                profit += prices[sellingDate] - prices[buyingDate];
                // Make the both date as current
                buyingDate = sellingDate = i;
            }
        }
        // Collecting the last stockProfit
        profit += prices[sellingDate] - prices[buyingDate];

        return profit;
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int prices[] = new int[n];
        for(int i = 0; i < n; i++) {
            prices[i] = scn.nextInt();
        }
        System.out.println(getMaximumProfit1(prices));
    }
}