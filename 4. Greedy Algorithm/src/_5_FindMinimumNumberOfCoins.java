/*
Question: Find Minimum Number Of Coins
---------------------------------------
Given a amount. The task is to make the change for that given amount, given that you have an
infinite supply of each of coins{1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins. Find the minimum number of coins to make the change.

 Input:
 13
 Output:
 3

 Input:
 96
 Output:
 5
 */

import java.util.ArrayList;

public class _5_FindMinimumNumberOfCoins {
 
    // Time Complexity: O(amount)
    // Space Complexity: O(1) 

    public static int findMinimumCoins(int amount){
        ArrayList<Integer> ans = new ArrayList<>();

        int denomination[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int n = denomination.length;

        for (int i = n - 1; i >= 0; i--){
            while (amount >= denomination[i]){
                amount -= denomination[i];
                ans.add(denomination[i]);
            }
        }

        int count = 0;
        for (int i = 0; i < ans.size(); i++){
//            System.out.print(ans.get(i) + " ");
            count++;
        }
        return count;
    }
}
