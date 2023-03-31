/*
Question: Fractional Knapsack
Given weights and values of N items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
Note: Unlike 0/1 knapsack, you are allowed to break the item.

Example 1:

Input:
N = 3, W = 50
values[] = {60,100,120}
weight[] = {10,20,30}
Output:
240.00
Explanation:Total maximum value of item we can have is 240.00 from the given capacity of sack.

Example 2:

Input:
N = 2, W = 50
values[] = {60,100}
weight[] = {10,20}
Output:
160.00
Explanation:
Total maximum value of item we can have is 160.00 from the given capacity of sack.
 */

import java.util.Arrays;  
import java.util.Comparator;

public class _4_FractionalKnapsack {

    // Time Complexity: O(NlogN + N) --> O(n log n) to sort the items and O(n) to iterate through all the items for calculating the answer.
    // Space Complexity: O(1)  --> No extra space used

    /* given in question */
    static class Item {
        int value, weight;
        public Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }

    public static double fractionalKnapsack(int W, Item arr[], int n) {

        // sort in descending order of weight
        Arrays.sort(arr, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double)(o1.value) / (double)(o1.weight);
                double r2 = (double)(o2.value) / (double)(o2.weight);

                return (int) (r2 - r1);
//                      Or
//                if(r1 < r2) return 1;
//                else if(r1 > r2) return -1;
//                else return 0;
            }
        });

        int curWeight = 0;
        double finalvalue = 0.0;

        for (int i = 0; i < n; i++) {

            if (curWeight + arr[i].weight <= W) {
                curWeight += arr[i].weight;
                finalvalue += arr[i].value;
            }

            else {
                int remaining = W - curWeight;
                finalvalue += ((double)arr[i].value / (double)arr[i].weight) * (double)remaining;
                break;
            }
        }

        return finalvalue;
    }
}
